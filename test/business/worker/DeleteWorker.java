package business.worker;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class DeleteWorker {
	
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
	
	@Test public void deleteWorkerOK() {
		String name = "deleteWorkerOK", nif1 = nif + "M", nif2 = nif + "N";
		this.setASs(name, nif1, nif2);
		assertEquals(workerAS.deleteWorker(fullTimeId), fullTimeId);
		assertEquals(workerAS.deleteWorker(partTimeId), partTimeId);
	}
	
	@Test public void deleteWorkerKONonexistentWorker() {
		assertEquals(workerAS.deleteWorker(INF), Errors.NonexistentWorker);
	}
	
	@Test public void deleteWorkerKOInactiveWorker() {
		String name = "deleteWorkerKOInactiveWorker", nif1 = nif + "O", nif2 = nif + "P";
		this.setASs(name, nif1, nif2);
		workerAS.deleteWorker(fullTimeId);
		workerAS.deleteWorker(partTimeId);
		assertEquals(workerAS.deleteWorker(fullTimeId), Errors.InactiveWorker);
		assertEquals(workerAS.deleteWorker(partTimeId), Errors.InactiveWorker);
	}
}
