package business.product;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.client.ClientAS;
import business.client.ClientTransfer;
import business.provider.ProviderAS;
import business.provider.ProviderTransfer;
import business.sale.SaleAS;
import business.sale.SaleTransfer;
import business.sale.ShoppingCartTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class ASProductTest {
	
	private static final String name = "name", city = "city", nif = "12345678T";
	private static final int stock = 4, phoneNumber = 123456789;
	private static final double price = 4.5, balance = 48;
	private BusinessFactory bf;
	private ProductAS as;
	private WarehouseAS warehouseAS;
	private ProviderAS providerAS;
	private SaleAS saleAS;
	private ClientAS clientAS;
	private ProductTransfer product;
	private WarehouseTransfer warehouse;
	private ProviderTransfer provider;
	private SaleTransfer sale;
	private ClientTransfer client;
	private int warehouseId, providerId, saleId, clientId;
	
	@BeforeClass
	public void setUp() {
		bf = BusinessFactory.getInstance();
		as = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
		providerAS = bf.createProviderAS();
		saleAS = bf.createSaleAS();
		clientAS = bf.createClientAS();
		
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
		
		client = new ClientTransfer(nif, name, balance);
		clientId = clientAS.createClient(client);
		
		sale = new SaleTransfer(clientId);
		saleId = saleAS.closeSale(new ShoppingCartTransfer(sale));
	}
	
	@Test
	public void createOK() {
		String name = "createProductOK";
		product = new ProductTransfer(name, stock, price, warehouseId);
		assertTrue(as.createProduct(product) > 0);
	}
	
	@Test
	public void createKOSintaxError() {
		warehouse = new WarehouseTransfer("createKOSintaxError", city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(" 2 ", -1, 0.0, 0);
		assertTrue(as.createProduct(product) == Errors.SintaxError);
		product.setName("nameOK");
		assertTrue(as.createProduct(product) == Errors.SintaxError);
		product.setStock(0);
		assertTrue(as.createProduct(product) == Errors.SintaxError);
		product.setPrice(1);
		assertTrue(as.createProduct(product) == Errors.SintaxError);
		
	}

	@Test
	public void createKONonexistentWarehouse() {
		product = new ProductTransfer("createProductKONonexistentWarehouse", stock, price, 9999999);
		assertTrue(as.createProduct(product) == Errors.NonexistentWarehouse);
	}
	
	@Test
	public void createKOInactiveWarehouse() {
		String name = "createKOInactiveWarehouse";
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		warehouseAS.deleteWarehouse(warehouseId);
		product = new ProductTransfer(name, stock, price, warehouseId);
		assertTrue(as.createProduct(product) == Errors.InactiveWarehouse);
	}
	
	@Test
	public void createKOActiveProduct() {
		String name = "createKOActiveProduct";
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		as.createProduct(product);
		assertTrue(as.createProduct(product) == Errors.ActiveProduct);
	}
	
	@Test
	public void createKOInactiveProduct() {
		String name = "createKOInactiveProduct";
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		as.createProduct(product);
		assertTrue(as.createProduct(product) == Errors.ActiveProduct);
	}
	
	@Test
	public void readOK() {
		String name = "readProductOK";
		warehouse = new WarehouseTransfer(name, name);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		as.createProduct(product);
		assertNotNull(as.readProduct(product.getId()));
	}
	
	@Test
	public void readKO() {
		assertNull(as.readProduct(0));
	}
	
	@Test
	public void readAllOK() {
		String name = "readAllProductsOK";
		warehouse = new WarehouseTransfer(name, name);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		as.createProduct(product);
		List<ProductTransfer> res = as.readProducts();
		assertFalse(res.isEmpty());
	}
	
//	@Test
//	public void readAllKO() {
//		// Make sense when productbo table is empty
//		assertTrue(as.readProducts().isEmpty());
//	}
	
	@Test
	public void readByProviderOK() {
		String name = "readProductsByProviderOK";
		product = new ProductTransfer(name, stock, price, warehouseId);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
