package business.sale;

import static org.junit.Assert.assertEquals;
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
import utilities.Errors;

public class CloseSale {
	
	private static final double balance = 200.3, price = 3.2;
	private static final String city = "city", nif = "12354678";
	private static final int stock = 38, INF = 999999999;
	
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
	
	@Test public void closeSaleKONonexistentClient() {
		String name = "closeSaleKONonexistentClient";
		sale = new SaleTransfer(0);
		shoppingCart = new ShoppingCartTransfer(sale);
		saleId = saleAS.closeSale(shoppingCart);
		assertEquals(saleId, Errors.NonexistentClient);
	}
	
	@Test public void closeSaleKOInactiveClient() {
		String name = "closeSaleKOInactiveClient";
		this.setASs(name, nif + "B");
		assertEquals(clientId, clientAS.deleteClient(clientId));
		saleId = saleAS.closeSale(shoppingCart);
		assertEquals(saleId, Errors.InactiveClient);
	}
	
	@Test public void closeSaleKONonexistentProduct() {
		String name = "closeSaleKONonexistentProduct";
		this.setASs(name, nif + "C");
		saleLine = new SaleLineTransfer(INF, 5);
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertEquals(saleId, Errors.NonexistentProduct);
	}
	
	@Test public void closeSaleKOInactiveProduct() {
		String name = "closeSaleKOInactiveProduct";
		this.setASs(name, nif + "D");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		assertEquals(productId, productAS.deleteProduct(productId));
		saleId = saleAS.closeSale(shoppingCart);
		assertEquals(saleId, Errors.InactiveProduct);
	}
	
	@Test public void closeSaleKOInsufficientStock() {
		String name = "closeSaleKOInsufficientStock";
		this.setASs(name, nif + "E");
		saleLine = new SaleLineTransfer(productId, product.getStock() * 2);
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertEquals(saleId, Errors.InsufficientStock);
	}
	
	@Test public void closeSaleKONotEnoughBalance() {
		String name = "closeSaleKONotEnoughBalance";
		this.setASs(name, nif + "F");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		client.setBalance(1.0);
		assertEquals(clientId, clientAS.updateClient(client));
		saleId = saleAS.closeSale(shoppingCart);
		assertEquals(saleId, Errors.NotEnoughBalance);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
