package business.sale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import business.saleLine.SaleLineTransfer;

public class ReadAllSales extends SaleTests {
	
	@Test public void readAllSalesOK() {
		String name = "readAllSalesOK";
		this.setASs(name, nif + "G");
		saleLine = new SaleLineTransfer(productId, product.getStock());
		shoppingCart.getLines().add(saleLine);
		saleId = saleAS.closeSale(shoppingCart);
		assertTrue(saleId > 0);
		List<SaleTransfer> res = saleAS.readSales();
		assertFalse(res.isEmpty());
	}
	
//	@Test public void readAllSalesKO() {
//		assertTrue(saleAS.readSales().isEmpty());
//	}
}
