package business.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.provider.ProviderAS;
import business.provider.ProviderTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ReadAllProductsByProvider {
	
	private static final String city = "city";
	private static final int stock = 4, phoneNumber = 123456789, amount = 10;
	private static final double price = 4.5;
	private static BusinessFactory bf;
	private static ProductAS productAS;
	private static WarehouseAS warehouseAS;
	private static ProviderAS providerAS;
	private ProductTransfer product;
	private static WarehouseTransfer warehouse;
	private static ProviderTransfer provider;
	private ProviderProductTransfer providerProduct;
	private static int warehouseId;
	private static int providerId;
	private static int productId;
	
	@BeforeClass
	public static void setUp() {
		bf = BusinessFactory.getInstance();
		productAS = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
		providerAS = bf.createProviderAS();
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
		providerAS.assignProduct(providerProduct);
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
