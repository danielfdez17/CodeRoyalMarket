package business.sale;

import org.junit.BeforeClass;

import business.businessFactory.BusinessFactory;
import business.client.ClientAS;
import business.client.ClientTransfer;
import business.entityManagerFactory.EMFFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.saleLine.SaleLineTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class SaleTests {
	
	protected static final double balance = 200.3, price = 3.2;
	protected static final String city = "city", nif = "12354678";
	protected static final int stock = 38, INF = 999999999;
	
	protected static BusinessFactory bf;
	protected static SaleAS saleAS;
	protected static ProductAS productAS;
	protected static WarehouseAS warehouseAS;
	protected static ClientAS clientAS;
	
	protected SaleTransfer sale;
	protected ShoppingCartTransfer shoppingCart;
	protected ProductTransfer product;
	protected WarehouseTransfer warehouse;
	protected ClientTransfer client;
	protected SaleLineTransfer saleLine;
	
	protected int saleId,
				productId,
				warehouseId,
				clientId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		saleAS = bf.createSaleAS();
		productAS = bf.createProductAS();
		warehouseAS = bf.createWarehouseAS();
		clientAS = bf.createClientAS();
		EMFFactory.getInstance();
	}
	
	protected void setASs(String name, String nif) {
		client = new ClientTransfer(nif, name, balance);
		clientId = clientAS.createClient(client);
		
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
		
		sale = new SaleTransfer(clientId);
		shoppingCart = new ShoppingCartTransfer(sale);
	}
}
