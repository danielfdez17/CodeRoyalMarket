package business.provider;

import static org.junit.Assert.assertTrue;


import org.junit.Test;

import business.providerProduct.ProviderProductTransfer;
import utilities.Errors;

public class UnassignProduct extends ProviderTests {
	
	@Test public void unassignProductOK() {
		String name = "unassignProductOK";
		this.setASs(name);
		providerAS.assignProduct(providerProduct);
		assertTrue(providerAS.unassignProduct(providerProduct) > 0);
	}
	
	@Test public void unassignProductKONonexistentProvider() {
		providerProduct = new ProviderProductTransfer(INF, productId, amount);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void unassignProductKOInactiveProvider() {
		String name = "unassignProductKOInactiveProvider";
		this.setASs(name);
		providerAS.deleteProvider(providerId);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.InactiveProvider);
	}
	
	@Test public void unassignProductKONonexistentProduct() {
		providerProduct = new ProviderProductTransfer(providerId, INF, amount);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void unassignProductKOInactiveProduct() {
		String name = "unassignProductKOInactiveProduct";
		this.setASs(name);
		productAS.deleteProduct(productId);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.InactiveProduct);
	}
	
	@Test public void unassignProductKOProductAlreadyUnassigned() {
		String name = "unassignProductKOProductAlreadyUnassigned";
		this.setASs(name);
		assertTrue(providerAS.unassignProduct(providerProduct) == Errors.ProductAlreadyUnassigned);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
