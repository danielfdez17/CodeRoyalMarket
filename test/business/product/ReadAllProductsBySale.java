package business.product;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import business.client.ClientTransfer;
import business.sale.SaleTransfer;
import business.sale.ShoppingCartTransfer;
import business.saleLine.SaleLineTransfer;

public class ReadAllProductsBySale extends ProductTests {
	
	private void setAS(String name, String nif) {
		super.setAS(name);
		
		client = new ClientTransfer(nif, name, balance);
		clientId = clientAS.createClient(client);
		
		sale = new SaleTransfer(clientId);
		shoppingCart = new ShoppingCartTransfer(sale);
		shoppingCart.getLines().add(new SaleLineTransfer(productId, product.getStock()));
		saleId = saleAS.closeSale(shoppingCart);
	}
	
	@Test public void readBySaleOK() {
		String name = "readProductBySaleOK";
		this.setAS(name, nif + "A");
		List<ProductTransfer> res = productAS.readAllProductsBySale(saleId);
		assertFalse(res.isEmpty());
	}
}
