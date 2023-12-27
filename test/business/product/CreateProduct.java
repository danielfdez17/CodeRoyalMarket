package business.product;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class CreateProduct {
	
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
	public void createOK() {
		String name = "createProductOK";
		setAS(name);
		product = new ProductTransfer(name, stock, price, warehouseId);
		assertTrue(productAS.createProduct(product) > 0);
	}
	
	@Test
	public void createKOSyntaxError() {
		warehouse = new WarehouseTransfer("createKOSyntaxError", city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(" 2 ", -1, 0.0, 0);
		assertTrue(productAS.createProduct(product) == Errors.SyntaxError);
		product.setName("nameOK");
		assertTrue(productAS.createProduct(product) == Errors.SyntaxError);
		product.setStock(0);
		assertTrue(productAS.createProduct(product) == Errors.SyntaxError);
		product.setPrice(1);
		assertTrue(productAS.createProduct(product) == Errors.SyntaxError);
		
	}

	@Test
	public void createKONonexistentWarehouse() {
		product = new ProductTransfer("createProductKONonexistentWarehouse", stock, price, 9999999);
		assertTrue(productAS.createProduct(product) == Errors.NonexistentWarehouse);
	}
	
	@Test
	public void createKOInactiveWarehouse() {
		String name = "createKOInactiveWarehouse";
		setAS(name);
		warehouseAS.deleteWarehouse(warehouseId);
		product = new ProductTransfer(name, stock, price, warehouseId);
		assertTrue(productAS.createProduct(product) == Errors.InactiveWarehouse);
	}
	
	@Test
	public void createKOActiveProduct() {
		String name = "createKOActiveProduct";
		setAS(name);
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
		assertTrue(productAS.createProduct(product) == Errors.ActiveProduct);
	}
	
	@Test
	public void createKOInactiveProduct() {
		String name = "createKOInactiveProduct";
		setAS(name);
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
		assertTrue(productAS.createProduct(product) == Errors.ActiveProduct);
	}
}
