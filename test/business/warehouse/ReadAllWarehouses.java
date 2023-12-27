package business.warehouse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadAllWarehouses {
	private static final String city = "city";
	
	private static BusinessFactory bf;
	private static WarehouseAS as;
	private WarehouseTransfer warehouse;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		as = bf.createWarehouseAS();
	}
	
	@Test public void readAllOK() {
		warehouse = new WarehouseTransfer("readAllWarehousesOK", city);
		as.createWarehouse(warehouse);
		assertFalse(as.readWarehouses().isEmpty());
	}
	
//	@Test public void readAllKO() {
//		assertTrue(as.readWarehouses().isEmpty());
//	}
}
