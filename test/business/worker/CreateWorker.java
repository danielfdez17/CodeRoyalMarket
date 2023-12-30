package business.worker;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class CreateWorker extends WorkerTests {
	
	@Test public void createWorkerOK() {
		String name = "createWorkerOK", nif1 = nif + "A", nif2 = nif + "B";
		this.setASs(name, nif1, nif2);
		assertTrue(fullTimeId > 0 && partTimeId > 0);
	}
	
	@Test public void createWorkerKOSyntaxError() {
		String name = " 2 ";
		warehouseId = 0;
		double hourPrice = 0.0;
		int hours = 0;
		double salary = 0.0;
		fullTime = new FullTimeWorkerTransfer(nif, name, warehouseId, salary);
		partTime = new PartTimeWorkerTransfer(nif, name, warehouseId, hourPrice, hours);
		assertEquals(workerAS.createFullTimeWorker(fullTime), Errors.SyntaxError);
		assertEquals(workerAS.createPartTimeWorker(partTime), Errors.SyntaxError);
		name = "nameOK";
		fullTime.setName(name);
		partTime.setName(name);
		assertEquals(workerAS.createFullTimeWorker(fullTime), Errors.SyntaxError);
		assertEquals(workerAS.createPartTimeWorker(partTime), Errors.SyntaxError);
		fullTime.setNif(nif + "C");
		partTime.setNif(nif + "D");
		assertEquals(workerAS.createFullTimeWorker(fullTime), Errors.SyntaxError);
		assertEquals(workerAS.createPartTimeWorker(partTime), Errors.SyntaxError);
		warehouseId = 1;
		fullTime.setWarehouseId(warehouseId);
		partTime.setWarehouseId(warehouseId);
		assertEquals(workerAS.createFullTimeWorker(fullTime), Errors.SyntaxError);
		assertEquals(workerAS.createPartTimeWorker(partTime), Errors.SyntaxError);
		salary = 1;
		fullTime.setSalary(salary);
		partTime.setHourPrice(salary);
		assertEquals(workerAS.createPartTimeWorker(partTime), Errors.SyntaxError);
		partTime.setHours(1);
		
	}
	
	@Test public void createWorkerKONonexistentWarehouse() {
		String name = "nameOK", nif1 = nif + "E", nif2 = nif + "F";
		this.setASs(name, nif1, nif2);
		warehouseId = INF;
		fullTime.setWarehouseId(warehouseId);
		partTime.setWarehouseId(warehouseId);
		assertEquals(workerAS.createFullTimeWorker(fullTime), Errors.NonexistentWarehouse);
		assertEquals(workerAS.createPartTimeWorker(partTime), Errors.NonexistentWarehouse);
	}
	
	@Test public void createWorkerKOInactiveWarehouse() {
		String name = "createWorkerKOInactiveWarehouse", nif1 = nif + "G", nif2 = nif + "H";
		this.setASs(name, nif1, nif2);
		warehouse = new WarehouseTransfer(name + "versionTwo", city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		warehouseAS.deleteWarehouse(warehouseId);
		fullTime.setWarehouseId(warehouseId);
		partTime.setWarehouseId(warehouseId);
		fullTimeId = workerAS.createFullTimeWorker(fullTime);
		partTimeId = workerAS.createPartTimeWorker(partTime);
		assertEquals(fullTimeId, Errors.InactiveWarehouse);
		assertEquals(partTimeId, Errors.InactiveWarehouse);
	}
	
	@Test public void createWorkerKOActiveWorker() {
		String name = "createWorkerKOActiveWorker", nif1 = nif + "I", nif2 = nif + "J";
		this.setASs(name, nif1, nif2);
		assertEquals(workerAS.createFullTimeWorker(fullTime), Errors.ActiveWorker);
		assertEquals(workerAS.createPartTimeWorker(partTime), Errors.ActiveWorker);
	}
	
	@Test public void createWorkerKOInactiveWorker() {
		String name = "createWorkerKOInactiveWorker", nif1 = nif + "K", nif2 = nif + "L";
		this.setASs(name, nif1, nif2);
		workerAS.deleteWorker(fullTimeId);
		workerAS.deleteWorker(partTimeId);
		assertEquals(workerAS.createFullTimeWorker(fullTime), Errors.InactiveWorker);
		assertEquals(workerAS.createPartTimeWorker(partTime), Errors.InactiveWorker);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
