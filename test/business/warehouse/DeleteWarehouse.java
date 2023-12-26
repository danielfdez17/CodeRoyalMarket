package business.warehouse;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class DeleteWarehouse {
	private static final String city = "city";
	
	private BusinessFactory bf;
	private WarehouseAS as;
	private WarehouseTransfer warehouse;
	
	@BeforeClass public void setUp() {
		bf = BusinessFactory.getInstance();
		as = bf.createWarehouseAS();
	}

	@Test public void deleteOK() {
		warehouse = new WarehouseTransfer("deleteWarehouseOK", city);
		as.createWarehouse(warehouse);
		assertTrue(as.deleteWarehouse(warehouse.getId()) == warehouse.getId());
	}
	
	@Test public void deleteKONonexistentWarehouse() {
		assertTrue(as.deleteWarehouse(0) == Errors.NonexistentWarehouse);
	}
	
	@Test public void deleteKOInactiveWarehouse() {
		warehouse = new WarehouseTransfer("deleteWarehouseKOInactiveWarehouse", city);
		as.createWarehouse(warehouse);
		as.deleteWarehouse(warehouse.getId());
		assertTrue(as.deleteWarehouse(warehouse.getId()) == Errors.InactiveWarehouse);
	}
	
	@Test public void deleteKOWithActiveProducts() {
		assertTrue(false);
	}
	
	@Test public void deleteKOWithActiveWorkers() {
		assertTrue(false);
	}
}
