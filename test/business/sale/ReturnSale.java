package business.sale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import business.saleLine.SaleLineTransfer;

public class ReturnSale extends SaleTests {

	@Test public void returnSaleOK() {
		String name = "returnSaleOK";
		this.setASs(name, nif + "A");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
		assertEquals(saleId, saleAS.returnSale(saleId));
	}
}
