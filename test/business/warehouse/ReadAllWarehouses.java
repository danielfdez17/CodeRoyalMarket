package business.warehouse;

import static org.junit.Assert.assertFalse;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadAllWarehouses extends WarehouseTests {
	
	@Test public void readAllOK() {
		String name = "readAllWarehousesOK";
		this.setASs(name);
		assertFalse(warehouseAS.readWarehouses().isEmpty());
	}
	
//	@Test public void readAllKO() {
//		assertTrue(as.readWarehouses().isEmpty());
//	}
}
