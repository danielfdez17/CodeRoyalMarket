package business.product;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.provider.ProviderAS;
import business.provider.ProviderTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class DeleteProduct {
	
	private static final String city = "city";
	private static final int stock = 4, phoneNumber = 123456789;
	private static final double price = 4.5;
	private static BusinessFactory bf;
	private static ProductAS productAS;
	private static WarehouseAS warehouseAS;
	private static ProviderAS providerAS;
	private ProductTransfer product;
	private static WarehouseTransfer warehouse;
	private static ProviderTransfer provider;
	private static int warehouseId;
	private static int providerId;
	private static int productId;
	
	@BeforeClass
	public static void setUp() {
		bf = BusinessFactory.getInstance();
		productAS = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
		providerAS = bf.createProviderAS();
	}
	
	private void setAS(String name) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
		
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
	}
	
	@Test public void deleteOK() {
		String name = "deleteProductOK";
		this.setAS(name);
		assertTrue(productAS.deleteProduct(productId) == product.getId());
	}
	
	@Test public void deleteKOInactiveProduct() {
		String name = "deleteProductKOInactiveProduct";
		this.setAS(name);
		productAS.deleteProduct(productId);
		assertTrue(productAS.deleteProduct(productId) == Errors.InactiveProduct);
	}
	
	@Test public void deleteKONonexistentProduct() {
		assertEquals(productAS.deleteProduct(0), Errors.NonexistentProduct);
	}
	
	@Test public void deleteProductKOWithActiveProviders() {
		String name = "deleteProductKOWithActiveProviders";
		this.setAS(name);
		assertTrue(providerAS.assignProduct(new ProviderProductTransfer(providerId, productId, 0)) > 0);
		assertEquals(productAS.deleteProduct(productId), Errors.ProductWithActiveProviders);
	}
}
