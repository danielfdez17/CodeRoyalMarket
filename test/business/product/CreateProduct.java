package business.product;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.client.ClientAS;
import business.client.ClientTransfer;
import business.provider.ProviderAS;
import business.provider.ProviderTransfer;
import business.sale.SaleAS;
import business.sale.SaleTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class CreateProduct {
	
	private static final String name = "name", city = "city", nif = "12345678T";
	private static final int stock = 4, phoneNumber = 123456789;
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
	}
	
	private void setAS(String name) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
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
