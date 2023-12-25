package business.warehouse;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import business.entityManagerFactory.EMFFactory;
import business.product.ProductBO;
import business.sintaxChecker.SintaxChecker;
import business.worker.WorkerBO;
import utilities.BusinessException;
import utilities.Errors;

public class WarehouseASImp implements WarehouseAS {

	@Override
	public int createWarehouse(WarehouseTransfer warehouse) {
		int res = Errors.SintaxError;
		if (this.isValid(warehouse)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				
				BusinessException be = new BusinessException();
				TypedQuery<WarehouseBO> query = em.createNamedQuery("business.warehouse.WarehouseBO.findByname", WarehouseBO.class);
				query.setParameter("name", warehouse.getName());
				WarehouseBO warehouseBO = null;
				
				if (query.getResultList().isEmpty()) {
					warehouseBO = new WarehouseBO(warehouse);
					em.persist(warehouseBO);
					et.commit();
					res = warehouseBO.getId();
					warehouse.setId(res);
				}
				else {
					warehouseBO = query.getResultList().get(0);
					if (warehouseBO.isActive()) {
						res = Errors.ActiveWarehouse;
						throw be;
					}
					
					warehouseBO = new WarehouseBO(warehouse.getName(), warehouse.getCity());
					warehouseBO.setActive(true);
					et.commit();
					res = Errors.InactiveWarehouse;
				}
				
				
			} catch (Exception e) {
				if (e instanceof BusinessException) {
					et.rollback();
				}
				else {
					res = Errors.UnespectedError;
				}
			} finally {
				em.close();
			}
		}
		return res;
	}

	@Override
	public WarehouseTransfer readWarehouse(int warehouseId) {
		WarehouseTransfer res = null;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			WarehouseBO warehouseBO = em.find(WarehouseBO.class, warehouseId, LockModeType.OPTIMISTIC);
			if (warehouseBO == null) {
				throw be;
			}
			
			et.commit();
			res = warehouseBO.toTransfer();
			
		} catch(Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			res = null;
		} finally {
			em.close();
		}
		
		return res;
	}

	@Override
	public List<WarehouseTransfer> readWarehouses() {
		List<WarehouseTransfer> res = new ArrayList<WarehouseTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		TypedQuery<WarehouseBO> query = em.createNamedQuery("business.warehouse.WarehouseBO.findAll", WarehouseBO.class);
		
		for (WarehouseBO w : query.getResultList()) {
			res.add(w.toTransfer());
		}
		
		em.close();
		return res;
	}

	@Override
	public int updateWarehouse(WarehouseTransfer warehouse) {
		int res = Errors.SintaxError;
		if (this.isValid(warehouse)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				
				BusinessException be = new BusinessException();
				WarehouseBO warehouseBO = em.find(WarehouseBO.class, warehouse.getId());
				
				if (warehouseBO == null) {
					res = Errors.NonexistentWarehouse;
					throw be;
				}
				
				warehouseBO.setName(warehouse.getName());
				warehouseBO.setCity(warehouse.getCity());
				warehouseBO.setActive(true);
				et.commit();
				res = warehouseBO.getId();
				
			} catch (Exception e) {
				if (e instanceof BusinessException) {
					et.rollback();
				}
				else {
					res = Errors.UnespectedError;
				}
			} finally {
				em.close();
			}
		}
		return res;
	}

	@Override
	public int deleteWarehouse(int warehouseId) {
		int res = Errors.SintaxError;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			
			BusinessException be = new BusinessException();
			WarehouseBO warehouseBO = em.find(WarehouseBO.class, warehouseId);
			
			if (warehouseBO == null) {
				res = Errors.NonexistentWarehouse;
				throw be;
			}
			
			if (!warehouseBO.isActive()) {
				res = Errors.InactiveWarehouse;
				throw be;
			}
			
			boolean warehouseWithActiveProducts = false;
			for (ProductBO p : warehouseBO.getProducts()) {
				if (p.isActive()) {
					warehouseWithActiveProducts = true;
					break;
				}
			}
			
			if (warehouseWithActiveProducts) {
				res = Errors.WarehouseWithActiveProducts;
				throw be;
			}
			
			boolean warehouseWithActiveWorkers = false;
			for (WorkerBO w : warehouseBO.getWorkers()) {
				if (w.isActive()) {
					warehouseWithActiveWorkers = true;
					break;
				}
			}
			
			if (warehouseWithActiveWorkers) {
				res = Errors.WarehouseWithActiveWorkers;
				throw be;
			}
			
			warehouseBO.setActive(false);
			et.commit();
			res = warehouseBO.getId();
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				res = Errors.UnespectedError;
			}
		} finally {
			em.close();
		}
		return res;
	}
	
	private boolean isValid(WarehouseTransfer warehouse) {
		return warehouse != null &&
				SintaxChecker.isCity(warehouse.getCity()) &&
				SintaxChecker.isName(warehouse.getName());
	}
	
}