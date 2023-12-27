package business.provider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ReadAllProvidersByProduct {
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
	
	@Test public void readProvidersByProductOK() {
		String name = "readAllProvidersByProductOK";
		this.setASs(name);
		assertTrue(providerAS.assignProduct(providerProduct) > 0);
		List<ProviderTransfer> res = providerAS.readProvidersByProduct(productId);
		assertFalse(res.isEmpty());
	}
	
	@Test public void readProvidersByProductKO() {
		String name = "readAllProvidersByProductKO";
		this.setASs(name);
		List<ProviderTransfer> res = providerAS.readProvidersByProduct(INF);
		assertTrue(res.isEmpty());
		productId = productAS.deleteProduct(productId);
		res = providerAS.readProvidersByProduct(productId);
		assertTrue(res.isEmpty());
	}
}
