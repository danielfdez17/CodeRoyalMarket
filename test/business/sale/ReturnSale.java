package business.sale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import business.saleLine.SaleLineTransfer;
import utilities.Errors;

public class ReturnSale extends SaleTests {

	@Test public void returnSaleOK() {
		String name = "returnSaleOK";
		this.setASs(name, nif + "O");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
		assertEquals(saleId, saleAS.returnSale(saleId));
	}
	
	@Test public void returnSaleKO() {
		assertEquals(saleAS.returnSale(INF), Errors.NonexistentSale);
	}
}
