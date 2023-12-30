package business.provider;

import org.junit.BeforeClass;

import business.businessFactory.BusinessFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ProviderTests {
	protected static final int phoneNumber = 123456879,
			stock = 4,
			amount = 10,
			INF = 999999999;
	protected static final String city = "city";
	protected static final double price = 84;
	
	protected static BusinessFactory bf;
	protected static ProviderAS providerAS;
	protected static ProductAS productAS;
	protected static WarehouseAS warehouseAS;
	
	protected ProviderTransfer provider;
	protected ProductTransfer product;
	protected WarehouseTransfer warehouse;
	protected ProviderProductTransfer providerProduct;
	
	protected int providerId, productId, warehouseId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		providerAS = bf.createProviderAS();
		productAS = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
	}
	
	protected void setASs(String name) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
		
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
		
		providerProduct = new ProviderProductTransfer(providerId, productId, amount);
	}
}
