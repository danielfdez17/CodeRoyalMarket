package business.product;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ReadAllProducts {
	
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
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
	}
	
	@Test
	public void readAllOK() {
		String name = "readAllProductsOK";
		setAS(name);
		List<ProductTransfer> res = productAS.readProducts();
		assertFalse(res.isEmpty());
	}
	
//	@Test
//	public void readAllKO() {
//		// Make sense when productbo table is empty
//		assertTrue(as.readProducts().isEmpty());
//	}
}
