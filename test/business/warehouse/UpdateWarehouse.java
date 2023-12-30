package business.warehouse;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utilities.Errors;

public class UpdateWarehouse extends WarehouseTests {
	
	@Test public void updateOK() {
		String name = "updateWarehouseOK";
		this.setASs(name);
		assertTrue(warehouseAS.updateWarehouse(warehouse) == warehouseId);
	}
	
	@Test public void updateKOSyntaxError() {
		warehouse = new WarehouseTransfer(" 2 ", " 4 ");
		assertTrue(warehouseAS.updateWarehouse(warehouse) == Errors.SyntaxError);
		warehouse.setName("nameOK");
		assertTrue(warehouseAS.updateWarehouse(warehouse) == Errors.SyntaxError);
	}
	
	@Test public void updateKONonexistentWarehouse() {
		warehouse = new WarehouseTransfer("updateWarehouseKONonexistentWarhouse", city);
		assertTrue(warehouseAS.updateWarehouse(warehouse) == Errors.NonexistentWarehouse);
	}
}
