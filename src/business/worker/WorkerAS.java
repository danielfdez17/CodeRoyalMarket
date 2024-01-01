package business.worker;

import java.util.List;

public interface WorkerAS {
	
	public int createFullTimeWorker(FullTimeWorkerTransfer fullTime);
	public int createPartTimeWorker(PartTimeWorkerTransfer partTime);
	public WorkerTransfer readWorker(int workerId);
	public List<WorkerTransfer> readAllWorkers();
	public List<WorkerTransfer> readAllWorkersByWarehouse(int warehouseId);
	public int updateFullTimeWorker(FullTimeWorkerTransfer fullTime);
	public int updatePartTimeWorker(PartTimeWorkerTransfer partTime);
	public int deleteWorker(int workerId);
	
}