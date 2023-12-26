package business.product;

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
import business.provider.ProviderAS;
import business.provider.ProviderTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.sale.SaleAS;
import business.sale.SaleTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class ReadAllProductsByProvider {
	
	private static final String name = "name", city = "city", nif = "12345678T";
	private static final int stock = 4, phoneNumber = 123456789, amount = 10;
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
	private ProviderProductTransfer providerProduct;
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
	}
	
	private void setAS(String name) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
		
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
		
		providerProduct = new ProviderProductTransfer(providerId, productId, amount);
		
	}
	
	@Test
	public void readByProviderOK() {
		String name = "readProductsByProviderOK";
		this.setAS(name);
		providerAS.assingProduct(providerProduct);
		List<ProductTransfer> res = productAS.readProductsByProvider(providerId);
		assertFalse(res.isEmpty());
	}
	
	@Test public void readByProviderKO() {
		List<ProductTransfer> res = productAS.readProductsByProvider(providerId);
		assertTrue(res.isEmpty());
		providerAS.deleteProvider(providerId);
		res = productAS.readProductsByProvider(providerId);
		assertTrue(res.isEmpty());
	}
}
