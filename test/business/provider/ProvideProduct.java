package business.provider;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import business.businessFactory.BusinessFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class ProvideProduct {
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
	
	@Test public void provideProductOK() {
		String name = "provideProductOK";
		this.setASs(name);
		assertTrue(providerAS.assignProduct(providerProduct) > 0);
		assertTrue(providerAS.provideProduct(providerProduct) > 0);
		product = productAS.readProduct(productId);
		assertEquals(product.getStock(), stock + amount);
	}
	
	@Test public void provideProductKONonexistentProvider() {
		providerProduct = new ProviderProductTransfer(INF, productId, amount);
		assertTrue(providerAS.provideProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void provideProductKOInactiveProvider() {
		String name = "provideProductKOInactiveProvider";
		this.setASs(name);
		providerAS.deleteProvider(providerId);
		assertEquals(providerAS.provideProduct(providerProduct), Errors.InactiveProvider);
	}
	
	@Test public void provideProductKONonexistentProduct() {
		providerProduct = new ProviderProductTransfer(providerId, INF, amount);
		assertTrue(providerAS.provideProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void provideProductKOInactiveProduct() {
		String name = "provideProductKOInactiveProduct";
		this.setASs(name);
		productAS.deleteProduct(productId);
		assertEquals(providerAS.provideProduct(providerProduct), Errors.InactiveProduct);
	}
	
	@Test public void providerProductKOProductAlreadyUnassigned() {
		String name = "providerProductKOProductAlreadyUnassigned";
		this.setASs(name);
		providerAS.assignProduct(providerProduct);
		providerAS.unassignProduct(providerProduct);
		assertEquals(providerAS.provideProduct(providerProduct), Errors.ProductAlreadyUnassigned);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
