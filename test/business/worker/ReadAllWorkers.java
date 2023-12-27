package business.worker;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ReadAllWorkers {

	private static final String nif = "12345678", city = "city";
	private static final int hours = 38;
	private static final double hourPrice = 42, salary = 2889;
	
	private static BusinessFactory bf;
	private static WorkerAS workerAS;
	private static WarehouseAS warehouseAS;
	
	private FullTimeWorkerTransfer fullTime;
	private PartTimeWorkerTransfer partTime;
	private WarehouseTransfer warehouse;
	
	private int warehouseId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		workerAS = bf.createWorkerAS();
		warehouseAS = bf.createWarehouseAS();
	}
	
	private void setASs(String name, String nif1, String nif2) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		fullTime = new FullTimeWorkerTransfer(nif1, name, warehouseId, salary);
		workerAS.createFullTimeWorker(fullTime);
		
		partTime = new PartTimeWorkerTransfer(nif2, name, warehouseId, hourPrice, hours);
		workerAS.createPartTimeWorker(partTime);
	}
	
	@Test public void readAllWorkersOK() {
		String name = "readAllWorkersOK", nif1 = nif + "Q", nif2 = nif + "R";
		this.setASs(name, nif1, nif2);
		List<WorkerTransfer> res = workerAS.readWorkers();
		assertFalse(res.isEmpty());
	}
	
//	@Test public void readAllWorkersKO() {
//		List<WorkerTransfer> res = workerAS.readWorkers();
//		assertTrue(res.isEmpty());
//	}
}
