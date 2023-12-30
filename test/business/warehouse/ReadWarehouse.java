package business.warehouse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadWarehouse extends WarehouseTests {
	
	@Test public void readOK() {
		String name = "readWarehouseOK";
		this.setASs(name);
		assertNotNull(warehouseAS.readWarehouse(warehouseId));
	}
	
	@Test public void readKO() {
		assertNull(warehouseAS.readWarehouse(-1));
	}
}
