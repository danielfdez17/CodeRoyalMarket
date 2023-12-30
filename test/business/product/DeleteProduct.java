package business.product;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.provider.ProviderAS;
import business.provider.ProviderTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class DeleteProduct extends ProductTests {
	
	@Test public void deleteOK() {
		String name = "deleteProductOK";
		this.setAS(name);
		assertTrue(productAS.deleteProduct(productId) == product.getId());
	}
	
	@Test public void deleteKOInactiveProduct() {
		String name = "deleteProductKOInactiveProduct";
		this.setAS(name);
		productAS.deleteProduct(productId);
		assertTrue(productAS.deleteProduct(productId) == Errors.InactiveProduct);
	}
	
	@Test public void deleteKONonexistentProduct() {
		assertEquals(productAS.deleteProduct(0), Errors.NonexistentProduct);
	}
	
	@Test public void deleteProductKOWithActiveProviders() {
		String name = "deleteProductKOWithActiveProviders";
		this.setAS(name);
		assertTrue(providerAS.assignProduct(new ProviderProductTransfer(providerId, productId, 0)) > 0);
		assertEquals(productAS.deleteProduct(productId), Errors.ProductWithActiveProviders);
	}
}
