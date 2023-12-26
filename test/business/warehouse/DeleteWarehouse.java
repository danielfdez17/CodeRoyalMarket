package business.warehouse;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.eclipse.persistence.jpa.jpql.Assert.AssertException;
import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import utilities.Errors;

public class DeleteWarehouse {
	
	private static final String city = "city";
	private static final int stock = 19;
	private static final double price = 38;
	
	private static BusinessFactory bf;
	private static WarehouseAS warehouseAS;
	private static ProductAS productAS;
	private WarehouseTransfer warehouse;
	private ProductTransfer product;
	private int warehouseId, productId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		warehouseAS = bf.createWarehouseAS();
		productAS = bf.createProductAS();
	}
	
	private void setASs(String name) {
		warehouse = new WarehouseTransfer(name, name);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
	}

	@Test public void deleteOK() {
		warehouse = new WarehouseTransfer("deleteWarehouseOK", city);
		warehouseAS.createWarehouse(warehouse);
		assertTrue(warehouseAS.deleteWarehouse(warehouse.getId()) == warehouse.getId());
	}
	
	@Test public void deleteKONonexistentWarehouse() {
		assertTrue(warehouseAS.deleteWarehouse(0) == Errors.NonexistentWarehouse);
	}
	
	@Test public void deleteKOInactiveWarehouse() {
		warehouse = new WarehouseTransfer("deleteWarehouseKOInactiveWarehouse", city);
		warehouseAS.createWarehouse(warehouse);
		warehouseAS.deleteWarehouse(warehouse.getId());
		assertTrue(warehouseAS.deleteWarehouse(warehouse.getId()) == Errors.InactiveWarehouse);
	}
	
	@Test public void deleteKOWithActiveProducts() {
		String name = "deleteWarehouseKOWarehouseWithActivePrducts";
		this.setASs(name);
		assertEquals(warehouseAS.deleteWarehouse(warehouseId), Errors.WarehouseWithActiveProducts);
	}
	
	@Test public void deleteKOWithActiveWorkers() {
		assertTrue(false);
	}
}
