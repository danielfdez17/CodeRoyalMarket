package business.warehouse;

import static org.junit.Assert.assertFalse;

import org.junit.Test;


public class ReadAllWarehouses extends WarehouseTests {
	
	@Test public void readAllOK() {
		String name = "readAllWarehousesOK";
		this.setASs(name);
		assertFalse(warehouseAS.readAllWarehouses().isEmpty());
	}
	
//	@Test public void readAllKO() {
//		assertTrue(as.readWarehouses().isEmpty());
//	}
}
