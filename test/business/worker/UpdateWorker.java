package business.worker;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class UpdateWorker {
	
	private static final String nif = "12347578", city = "city";
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
	
	@Test public void updateWorkerOK() {
		String name = "updateWorkerOK", nif1 = nif + "W", nif2 = nif + "X";
		this.setASs(name, nif1, nif2);
		assertEquals(workerAS.updateFullTimeWorker(fullTime), fullTimeId);
		assertEquals(workerAS.updatePartTimeWorker(partTime), partTimeId);
	}
	
	@Test public void updateWorkerKONonexistentWarehouse() {
		fullTime = new FullTimeWorkerTransfer(nif + "A", city, INF, salary);
		partTime = new PartTimeWorkerTransfer(nif + "B", city, INF, hourPrice, hours);
		assertEquals(workerAS.updateFullTimeWorker(fullTime), Errors.NonexistentWarehouse);
		assertEquals(workerAS.updatePartTimeWorker(partTime), Errors.NonexistentWarehouse);
	}
	
	@Test public void updateWorkerKOInactiveWarehouse() {
		String name = "updateWorkerKOInactiveWarehouse", nif1 = nif + "Y", nif2 = nif + "Z";
		this.setASs(name, nif1, nif2);
		warehouse = new WarehouseTransfer(name + "versionTwo", city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		warehouseAS.deleteWarehouse(warehouseId);
		fullTime.setWarehouseId(warehouseId);
		partTime.setWarehouseId(warehouseId);
		assertEquals(workerAS.updateFullTimeWorker(fullTime), Errors.InactiveWarehouse);
		assertEquals(workerAS.updatePartTimeWorker(partTime), Errors.InactiveWarehouse);
	}
	
	@Test public void updateWorkerKONonexistentWorker() {
		String name = "updateWorkerKONonexistentWorker", nif1 = nif + "E", nif2 = nif + "F";
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		fullTime = new FullTimeWorkerTransfer(INF, nif1, name, warehouseId, false, salary);
		partTime = new PartTimeWorkerTransfer(INF, nif2, name, warehouseId, false, hourPrice, hours);
		assertEquals(workerAS.updateFullTimeWorker(fullTime), Errors.NonexistentWorker);
		assertEquals(workerAS.updatePartTimeWorker(partTime), Errors.NonexistentWorker);
	}
	
	// Syntax error equals in CreateWorker tests
}
