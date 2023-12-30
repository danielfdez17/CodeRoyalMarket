package business.worker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class UpdateWorker extends WorkerTests {
	
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
