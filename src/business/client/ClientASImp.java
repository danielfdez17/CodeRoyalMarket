package business.client;

import java.util.ArrayList;
import java.util.List;

import business.entityManagerFactory.EMFFactory;
import business.syntaxChecker.SyntaxChecker;
import utilities.BusinessException;
import utilities.Errors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

public class ClientASImp implements ClientAS {
	
	public int createClient(ClientTransfer client) {
		int res = Errors.SyntaxError;
		if (this.isValid(client)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				BusinessException be = new BusinessException();
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
						res = Errors.ActiveClient;
						throw be;
					}
					
					clientBO.setActive(true);
					clientBO = new ClientBO(client.getNif(), client.getName(), client.getBalance());
					et.commit();
					res = Errors.InactiveClient;
				}
			} catch (Exception e) {
				if (e instanceof BusinessException) {
					et.rollback();
				}
				else {
					res = Errors.UnexpectedError;
				}
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
			BusinessException be = new BusinessException();
			ClientBO clientBO = em.find(ClientBO.class, clientId, LockModeType.OPTIMISTIC);
			if (clientBO == null) {
				throw be;
			}
			
			et.commit();
			res = clientBO.toTransfer();
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			res = null;
		} finally {
			em.close();
		}
		return res;
	}

	public List<ClientTransfer> readAllClients() {
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
		int res = Errors.SyntaxError;
		if (this.isValid(client)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				BusinessException be = new BusinessException();
				ClientBO clientBO = em.find(ClientBO.class, client.getId());
				
				if (clientBO == null) {
					res = Errors.NonexistentClient;
					throw be;
				}

				clientBO.setActive(true);
				clientBO.setName(client.getName());
				clientBO.setNif(client.getNif());
				clientBO.setBalance(client.getBalance());
				et.commit();
				res = client.getId();
				
			} catch(Exception e) {
				if (e instanceof BusinessException) {
					et.rollback();
				}
				else {
					res = Errors.UnexpectedError;
				}
			} finally {
				em.close();
			}
		}
		return res;
	}

	public int deleteClient(int clientId) {
		int res = Errors.SyntaxError;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ClientBO clientBO = em.find(ClientBO.class, clientId);
			
			if (clientBO == null) {
				res = Errors.NonexistentClient;
				throw be;
			}
			
			if (!clientBO.isActive()) {
				res = Errors.InactiveClient;
				throw be;
			}
			
			clientBO.setActive(false);
			et.commit();
			res = clientId;
			
		} catch(Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				res = Errors.UnexpectedError;
			}
		} finally {
			em.close();
		}
		return res;
	}
	
	private boolean isValid(ClientTransfer client) {
		return client != null && 
				SyntaxChecker.isNif(client.getNif()) &&
				SyntaxChecker.isName(client.getName()) &&
				client.getBalance() >= 0.0;
	}
}