package business.sale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import business.saleLine.SaleLineTransfer;
import utilities.Errors;

public class CloseSale extends SaleTests {
	
	@Test public void closeSaleOK() {
		String name = "closeSaleOK";
		this.setASs(name, nif + "A");
		saleLine = new SaleLineTransfer(productId, stock);
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
	}
	
	@Test public void closeSaleKONonexistentClient() {
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
