package business.provider;

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

public class UnassignProduct {
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
	
	@Test public void unassignProductOK() {
		String name = "unassignProductOK";
		this.setASs(name);
		providerAS.assignProduct(providerProduct);
		assertTrue(providerAS.unassignProduct(providerProduct) > 0);
	}
	
	@Test public void unassignProductKONonexistentProvider() {
		providerProduct = new ProviderProductTransfer(INF, productId, amount);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void unassignProductKOInactiveProvider() {
		String name = "unassignProductKOInactiveProvider";
		this.setASs(name);
		providerAS.deleteProvider(providerId);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.InactiveProvider);
	}
	
	@Test public void unassignProductKONonexistentProduct() {
		providerProduct = new ProviderProductTransfer(providerId, INF, amount);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void unassignProductKOInactiveProduct() {
		String name = "unassignProductKOInactiveProduct";
		this.setASs(name);
		productAS.deleteProduct(productId);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.InactiveProduct);
	}
	
	@Test public void unassignProductKOProductAlreadyUnassigned() {
		String name = "unassignProductKOProductAlreadyUnassigned";
		this.setASs(name);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.ProductAlreadyUnassigned);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
