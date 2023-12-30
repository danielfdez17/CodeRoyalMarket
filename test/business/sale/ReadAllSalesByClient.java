package business.sale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import business.saleLine.SaleLineTransfer;

public class ReadAllSalesByClient extends SaleTests {
	
	@Test public void readAllSalesByClientOK() {
		String name = "readAllSalesByClientOK";
		this.setASs(name, nif + "H");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
		List<SaleTransfer> res = saleAS.readSalesByClient(clientId);
		assertFalse(res.isEmpty());
	}
	
	@Test public void readAllSalesByClientKO() {
		assertTrue(saleAS.readSalesByClient(clientId).isEmpty());
	}
}
