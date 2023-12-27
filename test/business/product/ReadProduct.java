package business.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ReadProduct {
	
	private static final String city = "city";
	private static final int stock = 4;
	private static final double price = 4.5;
	private static BusinessFactory bf;
	private static ProductAS productAS;
	private static WarehouseAS warehouseAS;
	private ProductTransfer product;
	private static WarehouseTransfer warehouse;
	private static int warehouseId;
	
	@BeforeClass
	public static void setUp() {
		bf = BusinessFactory.getInstance();
		productAS = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
	}
	
	private void setAS(String name) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
	}
	
	@Test
	public void readOK() {
		String name = "readProductOK";
		setAS(name);
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
		assertNotNull(productAS.readProduct(product.getId()));
	}
	
	@Test
	public void readKO() {
		assertNull(productAS.readProduct(0));
	}
}
