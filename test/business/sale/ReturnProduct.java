package business.sale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import business.saleLine.SaleLineTransfer;
import utilities.Errors;

public class ReturnProduct extends SaleTests {

	@Test public void returnProductOK() {
		String name = "returnProductOK";
		this.setASs(name, nif + "K");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
		saleLine = new SaleLineTransfer(saleId, productId, price, product.getStock());
		assertEquals(saleId, saleAS.returnProduct(saleLine));
	}
	
	@Test public void returnProductKONonexistentSale() {
		assertEquals(saleAS.returnProduct(new SaleLineTransfer(INF, productId, price, INF)), Errors.NonexistentSale);
	}
	
	@Test public void returnProductKONonexistentProduct() {
		String name = "returnProductKONonexistentProduct";
		this.setASs(name, nif + "L");
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
		saleLine = new SaleLineTransfer(saleId, INF, price, INF);
		assertEquals(saleAS.returnProduct(saleLine), Errors.NonexistentProduct);
	}
	
	@Test public void returnProductKOMoreAmountThanBought() {
		String name = "returnProductKOMoreAmountThanBought";
		this.setASs(name, nif + "M");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
		saleLine = new SaleLineTransfer(saleId, productId, price, product.getStock() * 2);
		assertEquals(saleAS.returnProduct(saleLine), Errors.MoreAmountThanBought);
	}
}
