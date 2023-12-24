package business.client;

import java.util.ArrayList;
import java.util.List;

import business.entityManagerFactory.EMFFactory;
import business.sintaxChecker.SintaxChecker;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

public class ClientASImp implements ClientAS {
	
	public int createClient(ClientTransfer client) {
		int res = SintaxError;
		if (this.isValid(client)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				TypedQuery<ClientBO> query = em.createNamedQuery("business.client.ClientBO.findBynif", ClientBO.class);
				query.setParameter("nif", client.getNif());
				ClientBO clientBO = null;
				if (query.getResultList().isEmpty()) {
					clientBO = new ClientBO(client);
					em.persist(clientBO);
					et.commit();
					res = clientBO.getId();
					client.setId(res);
				}
				else {
					clientBO = query.getResultList().get(0);
					if (clientBO.isActive()) {
						et.rollback();
						res = ActiveClient;
					}
					else {
						client.setActive(true);
						clientBO = new ClientBO(client);
						et.commit();
						res = InactiveClient;
					}
				}
			} catch (Exception e) {
				res = UnespectedError;
			} finally {
				em.close();
			}
		}
		return res;
	}

	public ClientTransfer readClient(int clientId) {
		ClientTransfer res = null;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			ClientBO clientBO = em.find(ClientBO.class, clientId, LockModeType.OPTIMISTIC);
			if (clientBO == null) {
				et.rollback();
			}
			else {
				et.commit();
				res = clientBO.toTransfer();
			}
		} catch (Exception e) {
			res = null;
		} finally {
			em.close();
		}
		return res;
	}

	public List<ClientTransfer> readAllCllients() {
		List<ClientTransfer> res = new ArrayList<ClientTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		TypedQuery<ClientBO> query = em.createNamedQuery("business.client.ClientBO.findAll", ClientBO.class);
		for (ClientBO c : query.getResultList()) {
			res.add(c.toTransfer());
		}
		em.close();
		return res;
	}

	public int updateClient(ClientTransfer client) {
		int res = SintaxError;
		if (this.isValid(client)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				ClientBO clientBO = em.find(ClientBO.class, client.getId());
				if (clientBO != null) {
					client.setActive(true);
					clientBO = new ClientBO(client);
					et.commit();
					res = client.getId();
				}
				else {
					et.rollback();
					res = NonexistentClient;
				}
			} catch(Exception e) {
				res = UnespectedError;
			} finally {
				em.close();
			}
		}
		return res;
	}

	public int deleteClient(int clientId) {
		int res = SintaxError;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			ClientBO clientBO = em.find(ClientBO.class, clientId);
			if (clientBO != null) {
				if (clientBO.isActive()) {
					clientBO.setActive(false);
					et.commit();
					res = clientId;
				}
				else {
					et.rollback();
					res = InactiveClient;
				}
			}
			else {
				et.rollback();
				res = NonexistentClient;
			}
		} catch(Exception e) {
			res = UnespectedError;
		} finally {
			em.close();
		}
		return res;
	}
	
	private boolean isValid(ClientTransfer client) {
		return client != null && 
				SintaxChecker.isNif(client.getNif()) &&
				SintaxChecker.isName(client.getName()) &&
				client.getBalance() >= 0.0;
	}
}