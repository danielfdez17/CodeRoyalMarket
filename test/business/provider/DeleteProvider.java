package business.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class DeleteProvider {
	private static final int phoneNumber = 123456879,
			stock = 4,
			amount = 10;
	private static final String city = "city";
	private static final double price = 84;
	
	private static BusinessFactory bf;
	private static ProviderAS providerAS;
	private static ProductAS productAS;
	private static WarehouseAS warehouseAS;
	
	private ProviderTransfer provider;
	private ProductTransfer product;
	private WarehouseTransfer warehouse;
	private ProviderProductTransfer providerProduct;
	
	private int providerId, productId, warehouseId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		providerAS = bf.createProviderAS();
		productAS = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
	}
	
	private void setASs(String name) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
		
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
		
		providerProduct = new ProviderProductTransfer(providerId, productId, amount);
	}
	
	@Test public void deleteOK() {
		String name = "deleteProviderOK";
		this.setASs(name);
		assertEquals(providerAS.deleteProvider(providerId), providerId);
	}
	
	@Test public void deleteKONonexistentProvider() {
		assertEquals(providerAS.deleteProvider(providerId), Errors.NonexistentProvider);
	}
	
	@Test public void deleteKOInactiveProvider() {
		String name = "deleteProviderKOInactiveProvider";
		this.setASs(name);
		assertEquals(providerAS.deleteProvider(providerId), providerId);
		assertEquals(providerAS.deleteProvider(providerId), Errors.InactiveProvider);
	}
	
	@Test public void deleteProviderKOActiveProductAssigned() {
		String name = "deleteProviderKOActiveProductAssigned";
		this.setASs(name);
		assertTrue(providerAS.assignProduct(providerProduct) > 0);
		assertEquals(providerAS.deleteProvider(providerId), Errors.ActiveProductsAssigned);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
