package business.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.entityManagerFactory.EMFFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class UpdateProduct {
	
	private static final String name = "name", city = "city";
	private static final int stock = 4;
	private static final double price = 4.5;
	private static BusinessFactory bf;
	private static ProductAS productAS;
	private static WarehouseAS warehouseAS;
	private ProductTransfer product;
	private static WarehouseTransfer warehouse;
	private static int warehouseId;
	private static int productId;
	
	@BeforeClass
	public static void setUp() {
		bf = BusinessFactory.getInstance();
		productAS = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
		EMFFactory.getInstance();
	}
	
	private void setAS(String name) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
		
	}
	
	@Test public void updateOK() {
		String name = "updateProductOK";
		setAS(name);
		assertTrue(productAS.updateProduct(product) == product.getId());
	}
	
	@Test public void updateKOSyntaxError() {
		String name = "updateProductKOSyntaxError";
		setAS(name);
		product = new ProductTransfer(" 2 ", -1, 0.0, 0);
		assertTrue(productAS.createProduct(product) == Errors.SyntaxError);
		product.setName("nameOK");
		assertTrue(productAS.createProduct(product) == Errors.SyntaxError);
		product.setStock(0);
		assertTrue(productAS.createProduct(product) == Errors.SyntaxError);
		product.setPrice(1);
		assertTrue(productAS.createProduct(product) == Errors.SyntaxError);
	}
	
	@Test public void updateKONonexistentProduct() {
		product = new ProductTransfer(name, stock, price, warehouseId);
		assertTrue(productAS.updateProduct(product) == Errors.NonexistentProduct);
	}
	
	@Test public void updateKONonexistentWarehouse() {
		String name = "updateProductKONonexistentWarehouse";
		this.setAS(name);
		product.setWarehouseId(999999999);
		assertTrue(productAS.updateProduct(product) == Errors.NonexistentWarehouse);
	}
	
	@Test public void updateKOInactiveWarehouse() {
		String name = "updateProductKOInactiveWarehouse";
		setAS(name);
		assertEquals(productAS.deleteProduct(productId), productId);
		assertTrue(warehouseAS.deleteWarehouse(warehouseId) > 0);
		assertTrue(productAS.updateProduct(product) == Errors.InactiveWarehouse);
	}
}
