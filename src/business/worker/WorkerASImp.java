package business.worker;

import java.util.ArrayList;
import java.util.List;

import business.entityManagerFactory.EMFFactory;
import business.syntaxChecker.SyntaxChecker;
import business.warehouse.WarehouseBO;
import javax.persistence.LockModeType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import utilities.BusinessException;
import utilities.Errors;

public class WorkerASImp implements WorkerAS {

	@Override
	public int createFullTimeWorker(FullTimeWorkerTransfer fullTime) {
		int res = Errors.SyntaxError;
		if (this.isFullTimeValid(fullTime)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			try {
				BusinessException be = new BusinessException();
				TypedQuery<WorkerBO> query = em.createNamedQuery("business.worker.WorkerBO.findBynif", WorkerBO.class);
				query.setParameter("nif", fullTime.getNif());
				WorkerBO workerBO = null;
				WarehouseBO warehouseBO = em.find(WarehouseBO.class, fullTime.getWarehouseId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				if (warehouseBO == null) {
					res = Errors.NonexistentWarehouse;
					throw be;
				}
				
				if (!warehouseBO.isActive()) {
					res = Errors.InactiveWarehouse;
					throw be;
				}
				
				if (query.getResultList().isEmpty()) {
					workerBO = new FullTimeWorkerBO(fullTime, warehouseBO);
					em.persist(workerBO);
					warehouseBO.getWorkers().add(workerBO);
					et.commit();
					res = workerBO.getId();
					fullTime.setId(res);
				}
				else {
					workerBO = query.getResultList().get(0);
					if (workerBO.isActive()) {
						res = Errors.ActiveWorker;
						throw be;
					}
					
					if (!workerBO.getWarehouseBO().getName().equals(warehouseBO.getName())) {
						warehouseBO.getWorkers().remove(workerBO);
						workerBO.setWarehouseBO(warehouseBO);
						warehouseBO.getWorkers().add(workerBO);
					}
					
					workerBO.setName(fullTime.getName());
					((FullTimeWorkerBO) workerBO).setSalary(fullTime.getSalary());
					et.commit();
					res = Errors.InactiveWorker;
					
				}
				
				
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

	@Override
	public int createPartTimeWorker(PartTimeWorkerTransfer partTime) {
		int res = Errors.SyntaxError;
		if (this.isPartTimeValid(partTime)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			try {
				BusinessException be = new BusinessException();
				TypedQuery<WorkerBO> query = em.createNamedQuery("business.worker.WorkerBO.findBynif", WorkerBO.class);
				query.setParameter("nif", partTime.getNif());
				WorkerBO workerBO = null;
				WarehouseBO warehouseBO = em.find(WarehouseBO.class, partTime.getWarehouseId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				if (warehouseBO == null) {
					res = Errors.NonexistentWarehouse;
					throw be;
				}
				
				if (!warehouseBO.isActive()) {
					res = Errors.InactiveWarehouse;
					throw be;
				}
				
				if (query.getResultList().isEmpty()) {
					workerBO = new PartTimeWorkerBO(partTime, warehouseBO);
					em.persist(workerBO);
					warehouseBO.getWorkers().add(workerBO);
					et.commit();
					res = workerBO.getId();
					partTime.setId(res);
				}
				else {
					workerBO = query.getResultList().get(0);
					if (workerBO.isActive()) {
						res = Errors.ActiveWorker;
						throw be;
					}
					
					if (!workerBO.getWarehouseBO().getName().equals(warehouseBO.getName())) {
						warehouseBO.getWorkers().remove(workerBO);
						workerBO.setWarehouseBO(warehouseBO);
						warehouseBO.getWorkers().add(workerBO);
					}
					
					workerBO.setName(partTime.getName());
					((PartTimeWorkerBO) workerBO).setHourPrice(partTime.getHourPrice());
					((PartTimeWorkerBO) workerBO).setHours(partTime.getHours());
					et.commit();
					res = Errors.InactiveWorker;
					
				}
				
				
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

	@Override
	public WorkerTransfer readWorker(int workerId) {
		WorkerTransfer res = null;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			WorkerBO workerBO = em.find(WorkerBO.class, workerId, LockModeType.OPTIMISTIC);
			
			if (workerBO == null) {
				throw be;
			}
			
			et.commit();
			res = workerBO.toTransfer();
			
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
	public List<WorkerTransfer> readWorkers() {
		List<WorkerTransfer> res = new ArrayList<WorkerTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		TypedQuery<WorkerBO> query = em.createNamedQuery("business.worker.WorkerBO.findAll", WorkerBO.class);
		for (WorkerBO w : query.getResultList()) {
			res.add(w.toTransfer());
		}
		em.close();
		return res;
	}

	@Override
	public List<WorkerTransfer> readWorkersByWarehouse(int warehouseId) {
		List<WorkerTransfer> res = new ArrayList<WorkerTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			WarehouseBO warehouseBO = em.find(WarehouseBO.class, warehouseId, LockModeType.OPTIMISTIC);
			
			if (warehouseBO == null || !warehouseBO.isActive()) {
				throw be;
			}
			
			for (WorkerBO w : warehouseBO.getWorkers()) {
				em.lock(w, LockModeType.OPTIMISTIC);
				res.add(w.toTransfer());
			}
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			res = new ArrayList<WorkerTransfer>();
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public int updateFullTimeWorker(FullTimeWorkerTransfer fullTime) {
		int res = Errors.SyntaxError;
		if (this.isFullTimeValid(fullTime)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				BusinessException be = new BusinessException();
				
				FullTimeWorkerBO fullTimeBO = em.find(FullTimeWorkerBO.class, fullTime.getId());
				WarehouseBO warehouseBO = em.find(WarehouseBO.class, fullTime.getWarehouseId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				if (warehouseBO == null) {
					res = Errors.NonexistentWarehouse;
					throw be;
				}
				
				if (!warehouseBO.isActive()) {
					res = Errors.InactiveWarehouse;
					throw be;
				}
				
				if (fullTimeBO == null) {
					res = Errors.NonexistentWorker;
					throw be;
				}
				
				if (!fullTimeBO.getWarehouseBO().equals(warehouseBO)) {
					warehouseBO.getWorkers().remove(fullTimeBO);
					fullTimeBO.setWarehouseBO(warehouseBO);
					warehouseBO.getWorkers().add(fullTimeBO);
				}
				
				fullTimeBO.setName(fullTime.getName());
				((FullTimeWorkerBO) fullTimeBO).setSalary(fullTime.getSalary());
				et.commit();
				res = fullTimeBO.getId();
				
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

	@Override
	public int updatePartTimeWorker(PartTimeWorkerTransfer partTime) {
		int res = Errors.SyntaxError;
		if (this.isPartTimeValid(partTime)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				BusinessException be = new BusinessException();
				
				PartTimeWorkerBO partTimeBO = em.find(PartTimeWorkerBO.class, partTime.getId());
				WarehouseBO warehouseBO = em.find(WarehouseBO.class, partTime.getWarehouseId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				if (warehouseBO == null) {
					res = Errors.NonexistentWarehouse;
					throw be;
				}
				
				if (!warehouseBO.isActive()) {
					res = Errors.InactiveWarehouse;
					throw be;
				}
				
				if (partTimeBO == null) {
					res = Errors.NonexistentWorker;
					throw be;
				}
				
				if (!partTimeBO.getWarehouseBO().equals(warehouseBO)) {
					warehouseBO.getWorkers().remove(partTimeBO);
					partTimeBO.setWarehouseBO(warehouseBO);
					warehouseBO.getWorkers().add(partTimeBO);
				}
				
				partTimeBO.setName(partTime.getName());
				((PartTimeWorkerBO) partTimeBO).setHourPrice(partTime.getHourPrice());
				((PartTimeWorkerBO) partTimeBO).setHours(partTime.getHours());
				et.commit();
				res = partTimeBO.getId();
				
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

	@Override
	public int deleteWorker(int workerId) {
		int res = Errors.NonexistentWorker;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			WorkerBO workerBO = em.find(WorkerBO.class, workerId);
			
			if (workerBO == null) {
				res = Errors.NonexistentWorker;
				throw be;
			}
			
			if (!workerBO.isActive()) {
				res = Errors.InactiveWorker;
				throw be;
			}
			
			workerBO.setActive(false);
			et.commit();
			res = workerId;
			
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
	
	private boolean isFullTimeValid(FullTimeWorkerTransfer fullTime) {
		return this.isValid(fullTime) &&
				fullTime.getSalary() > 0.0;
	}
	
	private boolean isPartTimeValid(PartTimeWorkerTransfer partTime) {
		return this.isValid(partTime) &&
				partTime.getHourPrice() > 0.0 &&
				partTime.getHours() > 0;
	}
	
	private boolean isValid(WorkerTransfer worker) {
		return worker != null &&
				SyntaxChecker.isName(worker.getName()) && 
				SyntaxChecker.isNif(worker.getNif()) &&
				worker.getWarehouseId() > 0;
	}
	
}