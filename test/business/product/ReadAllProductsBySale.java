package business.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.client.ClientAS;
import business.client.ClientTransfer;
import business.provider.ProviderAS;
import business.provider.ProviderTransfer;
import business.sale.SaleAS;
import business.sale.SaleTransfer;
import business.sale.ShoppingCartTransfer;
import business.saleLine.SaleLineTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

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
		List<ProductTransfer> res = productAS.readProductsBySale(saleId);
		assertFalse(res.isEmpty());
	}
}
