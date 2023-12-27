package business.warehouse;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class UpdateWarehouse {
	private static final String city = "city";
	
	private static BusinessFactory bf;
	private static WarehouseAS as;
	private WarehouseTransfer warehouse;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		as = bf.createWarehouseAS();
	}
	
	@Test public void updateOK() {
		warehouse = new WarehouseTransfer("updateWarehouseOK", city);
		as.createWarehouse(warehouse);
		assertTrue(as.updateWarehouse(warehouse) == warehouse.getId());
	}
	
	@Test public void updateKOSyntaxError() {
		warehouse = new WarehouseTransfer(" 2 ", " 4 ");
		assertTrue(as.updateWarehouse(warehouse) == Errors.SyntaxError);
		warehouse.setName("nameOK");
		assertTrue(as.updateWarehouse(warehouse) == Errors.SyntaxError);
	}
	
	@Test public void updateKONonexistentWarehouse() {
		warehouse = new WarehouseTransfer("updateWarehouseKONonexistentWarhouse", city);
		assertTrue(as.updateWarehouse(warehouse) == Errors.NonexistentWarehouse);
	}
}
