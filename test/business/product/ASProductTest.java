package business.product;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
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

public class ASProductTest {
	
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
	}
	
	@Test
	public void createOK() {
		String name = "createProductOK";
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		assertTrue(productAS.createProduct(product) > 0);
	}
	
	@Test
	public void createKOSintaxError() {
		warehouse = new WarehouseTransfer("createKOSintaxError", city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(" 2 ", -1, 0.0, 0);
		assertTrue(productAS.createProduct(product) == Errors.SintaxError);
		product.setName("nameOK");
		assertTrue(productAS.createProduct(product) == Errors.SintaxError);
		product.setStock(0);
		assertTrue(productAS.createProduct(product) == Errors.SintaxError);
		product.setPrice(1);
		assertTrue(productAS.createProduct(product) == Errors.SintaxError);
		
	}

	@Test
	public void createKONonexistentWarehouse() {
		product = new ProductTransfer("createProductKONonexistentWarehouse", stock, price, 9999999);
		assertTrue(productAS.createProduct(product) == Errors.NonexistentWarehouse);
	}
	
	@Test
	public void createKOInactiveWarehouse() {
		String name = "createKOInactiveWarehouse";
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		warehouseAS.deleteWarehouse(warehouseId);
		product = new ProductTransfer(name, stock, price, warehouseId);
		assertTrue(productAS.createProduct(product) == Errors.InactiveWarehouse);
	}
	
	@Test
	public void createKOActiveProduct() {
		String name = "createKOActiveProduct";
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
		assertTrue(productAS.createProduct(product) == Errors.ActiveProduct);
	}
	
	@Test
	public void createKOInactiveProduct() {
		String name = "createKOInactiveProduct";
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
		assertTrue(productAS.createProduct(product) == Errors.ActiveProduct);
	}
	
	@Test
	public void readOK() {
		String name = "readProductOK";
		warehouse = new WarehouseTransfer(name, name);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
		assertNotNull(productAS.readProduct(product.getId()));
	}
	
	@Test
	public void readKO() {
		assertNull(productAS.readProduct(0));
	}
	
	@Test
	public void readAllOK() {
		String name = "readAllProductsOK";
		warehouse = new WarehouseTransfer(name, name);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
		List<ProductTransfer> res = productAS.readProducts();
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
		assertTrue(false);
	}
	
	@Test public void readByProvidersKO() {
		assertTrue(false);
	}
	
	@Test public void readBySaleOK() {
		assertTrue(false);
	}
	
	@Test public void updateOK() {
		String name = "updateProductOK";
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		product = new ProductTransfer(name, stock, price, warehouseId);
		assertTrue(productAS.createProduct(product) > 0);
		assertTrue(productAS.updateProduct(product) == product.getId());
	}
	
	@Test public void updateKOSintaxError() {
		String name = "updateProductKOSintaxError";
		setAS(name);
		product = new ProductTransfer(" 2 ", -1, 0.0, 0);
		assertTrue(productAS.createProduct(product) == Errors.SintaxError);
		product.setName("nameOK");
		assertTrue(productAS.createProduct(product) == Errors.SintaxError);
		product.setStock(0);
		assertTrue(productAS.createProduct(product) == Errors.SintaxError);
		product.setPrice(1);
		assertTrue(productAS.createProduct(product) == Errors.SintaxError);
	}
	
	@Test public void updateKONonexistentProduct() {
		product = new ProductTransfer(name, stock, price, warehouseId);
		assertTrue(productAS.updateProduct(product) == Errors.NonexistentProduct);
	}
	
	@Test public void updateKONonexistentWarehouse() {
		String name = "updateProductKONonexistentWarehouse";
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
		product.setWarehouseId(999999999);
		assertTrue(productAS.updateProduct(product) == Errors.NonexistentWarehouse);
	}
	
	@Test public void updateKOInactiveWarehouse() {
		String name = "updateProductKOInactiveWarehouse";
		setAS(name);
		product = new ProductTransfer(name, stock, price, warehouseId);
		productAS.createProduct(product);
		assertTrue(warehouseAS.deleteWarehouse(warehouseId) > 0);
		assertTrue(productAS.updateProduct(product) == Errors.InactiveWarehouse);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
