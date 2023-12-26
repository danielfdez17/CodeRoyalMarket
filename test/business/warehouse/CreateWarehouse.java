package business.warehouse;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class CreateWarehouse {
	private static final String city = "city";
	
	private BusinessFactory bf;
	private WarehouseAS as;
	private WarehouseTransfer warehouse;
	
	@BeforeClass public void setUp() {
		bf = BusinessFactory.getInstance();
		as = bf.createWarehouseAS();
	}
	
	@Test public void createOK() {
		warehouse = new WarehouseTransfer("createWarehouseOK", city);
		assertTrue(as.createWarehouse(warehouse) > 0);
	}
	
	@Test public void createKOSintaxError() {
		warehouse = new WarehouseTransfer(" 2 ", " 4 ");
		assertTrue(as.createWarehouse(warehouse) == Errors.SintaxError);
		warehouse.setName("nameOK");
		assertTrue(as.createWarehouse(warehouse) == Errors.SintaxError);
	}
	
	@Test public void createWarhouseKOActiveWarehouse() {
		assertTrue(false);
	}
	
	@Test public void createWarehouseKOInactiveWarehouse() {
		assertTrue(false);
	}
	
}
