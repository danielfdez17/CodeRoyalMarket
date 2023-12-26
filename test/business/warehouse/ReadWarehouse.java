package business.warehouse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadWarehouse {
	private static final String city = "city";
	
	private BusinessFactory bf;
	private WarehouseAS as;
	private WarehouseTransfer warehouse;
	
	@BeforeClass public void setUp() {
		bf = BusinessFactory.getInstance();
		as = bf.createWarehouseAS();
	}
	
	@Test public void readOK() {
		warehouse = new WarehouseTransfer("readWarehouseOK", city);
		as.createWarehouse(warehouse);
		assertNotNull(as.readWarehouse(warehouse.getId()));
	}
	
	@Test public void readKO() {
		assertNull(as.readWarehouse(-1));
	}
}
