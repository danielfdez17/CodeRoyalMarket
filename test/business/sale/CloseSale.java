package business.sale;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.client.ClientAS;
import business.client.ClientTransfer;
import business.entityManagerFactory.EMFFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.saleLine.SaleLineTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class CloseSale {
	
	private static final double balance = 200.3, price = 3.2;
	private static final String city = "city", nif = "12354678";
	private static final int stock = 38;
	
	private static BusinessFactory bf;
	private static SaleAS saleAS;
	private static ProductAS productAS;
	private static WarehouseAS warehouseAS;
	private static ClientAS clientAS;
	
	private SaleTransfer sale;
	private ShoppingCartTransfer shoppingCart;
	private ProductTransfer product;
	private WarehouseTransfer warehouse;
	private ClientTransfer client;
	private SaleLineTransfer saleLine;
	
	private int saleId,
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
	
	private void setASs(String name, String nif) {
		client = new ClientTransfer(nif, name, balance);
		clientId = clientAS.createClient(client);
		
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);
		
		sale = new SaleTransfer(clientId);
		shoppingCart = new ShoppingCartTransfer(sale);
	}
	
	@Test public void closeSaleOK() {
		String name = "closeSaleOK";
		this.setASs(name, nif + "A");
		saleLine = new SaleLineTransfer(productId, stock);
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
	}

}
