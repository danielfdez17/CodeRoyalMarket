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

public class CreateProvider {
	private static final int phoneNumber = 123456879,
			stock = 4,
			amount = 10,
			INF = 999999999;
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
	
	@Test public void createOK() {
		String name = "createProviderOK";
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
		assertTrue(providerId > 0);
	}
	
	@Test public void createKOSintaxError() {
		String name = " 43 ";
		int phoneNumber = 12345678;
		provider = new ProviderTransfer(name, phoneNumber);
		assertTrue(providerAS.createProvider(provider) == Errors.SintaxError);
		name = "nameOK";
		provider.setName(name);
		assertTrue(providerAS.createProvider(provider) == Errors.SintaxError);
		
	}
	
	@Test public void createKOActiveProvider() {
		String name = "createProviderKOActiveProvider";
		setASs(name);
		providerId = providerAS.createProvider(provider);
		assertEquals(providerId, Errors.ActiveProvider);
	}
	
	@Test public void createKOInactiveProvider() {
		String name = "createProviderKOInactiveProvider";
		setASs(name);
		providerId = providerAS.deleteProvider(providerId);
		providerId = providerAS.createProvider(provider);
		assertEquals(providerId, Errors.InactiveProvider);
	}
}
