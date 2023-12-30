package business.provider;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import business.businessFactory.BusinessFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class ProvideProduct extends ProviderTests {
	
	@Test public void provideProductOK() {
		String name = "provideProductOK";
		this.setASs(name);
		assertTrue(providerAS.assignProduct(providerProduct) > 0);
		assertTrue(providerAS.provideProduct(providerProduct) > 0);
		product = productAS.readProduct(productId);
		assertEquals(product.getStock(), stock + amount);
	}
	
	@Test public void provideProductKONonexistentProvider() {
		providerProduct = new ProviderProductTransfer(INF, productId, amount);
		assertTrue(providerAS.provideProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void provideProductKOInactiveProvider() {
		String name = "provideProductKOInactiveProvider";
		this.setASs(name);
		providerAS.deleteProvider(providerId);
		assertEquals(providerAS.provideProduct(providerProduct), Errors.InactiveProvider);
	}
	
	@Test public void provideProductKONonexistentProduct() {
		providerProduct = new ProviderProductTransfer(providerId, INF, amount);
		assertTrue(providerAS.provideProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void provideProductKOInactiveProduct() {
		String name = "provideProductKOInactiveProduct";
		this.setASs(name);
		productAS.deleteProduct(productId);
		assertEquals(providerAS.provideProduct(providerProduct), Errors.InactiveProduct);
	}
	
	@Test public void providerProductKOProductAlreadyUnassigned() {
		String name = "providerProductKOProductAlreadyUnassigned";
		this.setASs(name);
		providerAS.assignProduct(providerProduct);
		providerAS.unassignProduct(providerProduct);
		assertEquals(providerAS.provideProduct(providerProduct), Errors.ProductAlreadyUnassigned);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
