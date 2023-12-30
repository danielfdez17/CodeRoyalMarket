package business.product;

import org.junit.BeforeClass;

import business.businessFactory.BusinessFactory;
import business.client.ClientAS;
import business.client.ClientTests;
import business.client.ClientTransfer;
import business.provider.ProviderAS;
import business.provider.ProviderTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.sale.SaleAS;
import business.sale.SaleTransfer;
import business.sale.ShoppingCartTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ProductTests {
	
	protected static final String city = "city",
								  nif = "12345678";
	protected static final int stock = 4,
								phoneNumber = 123456789,
								amount = 10,
								INF = 999999999;
	protected static final double price = 4.5,
									balance = 200;
	protected static BusinessFactory bf;
	protected static ProductAS productAS;
	protected static WarehouseAS warehouseAS;
	protected static ProviderAS providerAS;
	protected static ClientAS clientAS;
	protected static SaleAS saleAS;
	protected static ProductTransfer product;
	protected static WarehouseTransfer warehouse;
	protected static ProviderTransfer provider;
	protected static ProviderProductTransfer providerProduct;
	protected static ClientTransfer client;
	protected static SaleTransfer sale;
	protected static ShoppingCartTransfer shoppingCart;
	protected static int warehouseId,
						 providerId,
						 productId,
						 clientId,
						 saleId;
	
	@BeforeClass
	public static void setUp() {
		bf = BusinessFactory.getInstance();
		productAS = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
		providerAS = bf.createProviderAS();
		clientAS = bf.createClientAS();
		saleAS = bf.createSaleAS();
	}
	
	protected void setAS(String name) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
		
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
		
		providerProduct = new ProviderProductTransfer(providerId, productId, amount);
	}
	
}
