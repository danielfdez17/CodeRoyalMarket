package business.sale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import business.saleLine.SaleLineTransfer;

public class ReadAllSalesByProduct extends SaleTests {
	
	@Test public void readAllSalesByProductOK() {
		String name = "readAllSalesByProductOK";
		this.setASs(name, nif + "I");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
		List<SaleTransfer> res = saleAS.readAllSalesByProduct(productId);
		assertFalse(res.isEmpty());
	}
	
	@Test public void readAllSalesByProductKO() {
		assertTrue(saleAS.readAllSalesByProduct(productId).isEmpty());
	}
}
