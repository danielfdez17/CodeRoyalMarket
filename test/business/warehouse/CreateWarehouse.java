package business.warehouse;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class CreateWarehouse extends WarehouseTests {
	
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
		warehouse = new WarehouseTransfer(name + "vtwo", city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		warehouseAS.deleteWarehouse(warehouseId);
		assertEquals(warehouseAS.createWarehouse(warehouse), Errors.InactiveWarehouse);
	}
	
}
