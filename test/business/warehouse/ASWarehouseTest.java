package business.warehouse;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class ASWarehouseTest {
	
	private static final String city = "city";
	
	private BusinessFactory bf;
	private WarehouseAS as;
	private WarehouseTransfer warehouse;
	
	@Before
	public void setUp() {
		bf = BusinessFactory.getInstance();
		as = bf.createWarehouseAS();
	}
	
	@Test
	public void createOK() {
		warehouse = new WarehouseTransfer("createWarehouseOK", city);
		assertTrue(as.createWarehouse(warehouse) > 0);
	}
	
	@Test
	public void createKOSintaxError() {
		warehouse = new WarehouseTransfer(" 2 ", " 4 ");
		assertTrue(as.createWarehouse(warehouse) == Errors.SintaxError);
		warehouse.setName("nameOK");
		assertTrue(as.createWarehouse(warehouse) == Errors.SintaxError);
	}
	
	@Test
	public void readOK() {
		warehouse = new WarehouseTransfer("readWarehouseOK", city);
		as.createWarehouse(warehouse);
		assertNotNull(as.readWarehouse(warehouse.getId()));
	}
	
	@Test
	public void readKO() {
		assertNull(as.readWarehouse(-1));
	}
	
	@Test
	public void readAllOK() {
		warehouse = new WarehouseTransfer("readAllWarehousesOK", city);
		as.createWarehouse(warehouse);
		assertFalse(as.readWarehouses().isEmpty());
	}
	
	@Test
	public void readAllKO() {
		assertTrue(as.readWarehouses().isEmpty());
	}
	
	@Test
	public void updateOK() {
		warehouse = new WarehouseTransfer("updateWarehouseOK", city);
		as.createWarehouse(warehouse);
		assertTrue(as.updateWarehouse(warehouse) == warehouse.getId());
	}
	
	@Test
	public void updateKOSintaxError() {
		warehouse = new WarehouseTransfer(" 2 ", " 4 ");
		assertTrue(as.updateWarehouse(warehouse) == Errors.SintaxError);
		warehouse.setName("nameOK");
		assertTrue(as.updateWarehouse(warehouse) == Errors.SintaxError);
	}
	
	@Test
	public void updateKONonexistentWarehouse() {
		warehouse = new WarehouseTransfer("updateWarehouseKONonexistentWarhouse", city);
		assertTrue(as.updateWarehouse(warehouse) == Errors.NonexistentWarehouse);
	}

	@Test
	public void deleteOK() {
		warehouse = new WarehouseTransfer("deleteWarehouseOK", city);
		as.createWarehouse(warehouse);
		assertTrue(as.deleteWarehouse(warehouse.getId()) == warehouse.getId());
	}
	
	@Test
	public void deleteKONonexistentWarehouse() {
		assertTrue(as.deleteWarehouse(0) == Errors.NonexistentWarehouse);
	}
	
	@Test
	public void deleteKOInactiveWarehouse() {
		warehouse = new WarehouseTransfer("deleteWarehouseKOInactiveWarehouse", city);
		as.createWarehouse(warehouse);
		as.deleteWarehouse(warehouse.getId());
		assertTrue(as.deleteWarehouse(warehouse.getId()) == Errors.InactiveWarehouse);
	}
	
	@Test
	public void deleteKOWithActiveProducts() {
		assertTrue(false);
	}
	
	@Test
	public void deleteKOWithActiveWorkers() {
		assertTrue(false);
	}
	
}
