package business.warehouse;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.entityManagerFactory.EMFFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.worker.FullTimeWorkerTransfer;
import business.worker.WorkerAS;
import utilities.Errors;

public class DeleteWarehouse extends WarehouseTests {
	
	private void setASs(String name, String nif) {
		super.setASs(name);
		fullTime = new FullTimeWorkerTransfer(nif, name, warehouseId, salary);
		workerAS.createFullTimeWorker(fullTime);
	}
	
	@Test public void deleteOK() {
		warehouse = new WarehouseTransfer("deleteWarehouseOK", city);
		warehouseAS.createWarehouse(warehouse);
		assertTrue(warehouseAS.deleteWarehouse(warehouse.getId()) == warehouse.getId());
	}
	
	@Test public void deleteKONonexistentWarehouse() {
		assertTrue(warehouseAS.deleteWarehouse(0) == Errors.NonexistentWarehouse);
	}
	
	@Test public void deleteKOInactiveWarehouse() {
		warehouse = new WarehouseTransfer("deleteWarehouseKOInactiveWarehouse", city);
		warehouseAS.createWarehouse(warehouse);
		warehouseAS.deleteWarehouse(warehouse.getId());
		assertTrue(warehouseAS.deleteWarehouse(warehouse.getId()) == Errors.InactiveWarehouse);
	}
	
	@Test public void deleteKOWithActiveProducts() {
		String name = "deleteWarehouseKOWarehouseWithActiveProducts";
		this.setASs(name, nif + "A");
		assertEquals(warehouseAS.deleteWarehouse(warehouseId), Errors.WarehouseWithActiveProducts);
	}
	
	@Test public void deleteWarehouseKOWithActiveWorkers() {
		String name = "deleteWarehouseKOWithActiveWorkers";
		this.setASs(name, nif + "B");
		assertEquals(productAS.deleteProduct(productId), productId);
		assertEquals(warehouseAS.deleteWarehouse(warehouseId), Errors.WarehouseWithActiveWorkers);
	}
}
