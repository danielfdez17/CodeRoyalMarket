package business.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.client.ClientAS;
import business.client.ClientTransfer;
import business.entityManagerFactory.EMFFactory;
import business.provider.ProviderAS;
import business.provider.ProviderTransfer;
import business.sale.SaleAS;
import business.sale.SaleTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class UpdateProduct {
	
	private static final String name = "name", city = "city", nif = "12345678T";
	private static final int stock = 4, phoneNumber = 123456789, INF = 999999999;
	private static final double price = 4.5, balance = 48;
	private static BusinessFactory bf;
	private static ProductAS productAS;
	private static WarehouseAS warehouseAS;
	private static ProviderAS providerAS;
	private static SaleAS saleAS;
	private static ClientAS clientAS;
	private ProductTransfer product;
	private static WarehouseTransfer warehouse;
	private static ProviderTransfer provider;
	private static SaleTransfer sale;
	private static ClientTransfer client;
	private static int warehouseId;
	private static int providerId;
	private static int productId;
	private static int saleId;
	private static int clientId;
	
	@BeforeClass
	public static void setUp() {
		bf = BusinessFactory.getInstance();
		productAS = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
		providerAS = bf.createProviderAS();
		saleAS = bf.createSaleAS();
		clientAS = bf.createClientAS();
		EMFFactory.getInstance();
	}
	
	private void setAS(String name) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
		
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
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
