package business.warehouse;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class CreateWarehouse {
	private static final String city = "city";
	
	private static BusinessFactory bf;
	private static WarehouseAS warehouseAS;
	private WarehouseTransfer warehouse;
	private int warehouseId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		warehouseAS = bf.createWarehouseAS();
	}
	
	private void setASs(String name) {
		warehouse = new WarehouseTransfer(name, name);
		warehouseId = warehouseAS.createWarehouse(warehouse);
	}
	
	@Test public void createOK() {
		warehouse = new WarehouseTransfer("createWarehouseOK", city);
		assertTrue(warehouseAS.createWarehouse(warehouse) > 0);
	}
	
	@Test public void createKOSyntaxError() {
		warehouse = new WarehouseTransfer(" 2 ", " 4 ");
		assertTrue(warehouseAS.createWarehouse(warehouse) == Errors.SyntaxError);
		warehouse.setName("nameOK");
		assertTrue(warehouseAS.createWarehouse(warehouse) == Errors.SyntaxError);
	}
	
	@Test public void createWarehouseKOActiveWarehouse() {
		String name = "createWarehouseKOActiveWarehouse";
		this.setASs(name);
		assertEquals(warehouseAS.createWarehouse(warehouse), Errors.ActiveWarehouse);
	}
	
	@Test public void createWarehouseKOInactiveWarehouse() {
		String name = "createWarehouseKOInactiveWarehouse";
		this.setASs(name);
		warehouseAS.deleteWarehouse(warehouseId);
		assertEquals(warehouseAS.createWarehouse(warehouse), Errors.InactiveWarehouse);
	}
	
}
