package business.worker;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ReadWorker {

	private static final String nif = "12345678", city = "city";
	private static final int hours = 38, INF = 999999999;
	private static final double hourPrice = 42, salary = 2889;
	
	private static BusinessFactory bf;
	private static WorkerAS workerAS;
	private static WarehouseAS warehouseAS;
	
	private FullTimeWorkerTransfer fullTime;
	private PartTimeWorkerTransfer partTime;
	private WarehouseTransfer warehouse;
	
	private int warehouseId, fullTimeId, partTimeId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		workerAS = bf.createWorkerAS();
		warehouseAS = bf.createWarehouseAS();
	}
	
	private void setASs(String name, String nif1, String nif2) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		fullTime = new FullTimeWorkerTransfer(nif1, name, warehouseId, salary);
		fullTimeId = workerAS.createFullTimeWorker(fullTime);
		
		partTime = new PartTimeWorkerTransfer(nif2, name, warehouseId, hourPrice, hours);
		partTimeId = workerAS.createPartTimeWorker(partTime);
	}
	
	@Test public void readWorkerOK() {
		String name = "readWorkerOK", nif1 = nif + "U", nif2 = nif + "V";
		this.setASs(name, nif1, nif2);
		assertTrue(fullTimeId > 0);
		assertTrue(partTimeId > 0);
		assertNotNull(workerAS.readWorker(fullTimeId));
		assertNotNull(workerAS.readWorker(partTimeId));
	}
	
	@Test public void readWorkerKO() {
		assertNull(workerAS.readWorker(INF));
	}
}
