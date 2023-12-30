package business.sale;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import business.saleLine.SaleLineTransfer;

public class ReadSale extends SaleTests {
	
	@Test public void readSaleOK() {
		String name = "readSaleOK";
		this.setASs(name, nif + "J");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
		assertNotNull(saleAS.readSale(saleId));
	}
	
	@Test public void readSaleKO() {
		assertNull(saleAS.readSale(INF));
	}
}
