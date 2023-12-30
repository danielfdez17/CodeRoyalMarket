package business.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.providerProduct.ProviderProductTransfer;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class AssignProduct extends ProviderTests {

	@Test public void assignProductOK() {
		String name = "assignProductOK";
		this.setASs(name);
		assertTrue(providerAS.assignProduct(providerProduct) > 0);
	}
	
	@Test public void assignProductKONonexistentProvider() {
		providerProduct = new ProviderProductTransfer(INF, productId, amount);
		assertTrue(providerAS.assignProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void assignProductKOInactiveProvider() {
		String name = "assignProductKOInactiveProvider";
		this.setASs(name);
		providerId = providerAS.deleteProvider(providerId);
		assertTrue(providerAS.assignProduct(providerProduct) == Errors.InactiveProvider);
	}
	
	@Test public void assignProductKONonexistentProduct() {
		providerProduct = new ProviderProductTransfer(providerId, INF, amount);
		assertTrue(providerAS.assignProduct(providerProduct) == Errors.NonexistentProvider);
	}
	
	@Test public void assignProductKOInactiveProduct() {
		String name = "assignProductKOInactiveProduct";
		this.setASs(name);
		assertEquals(productAS.deleteProduct(productId), productId);
		assertTrue(providerAS.assignProduct(providerProduct) == Errors.InactiveProduct);
	}
	
	@Test public void assignProductKOProductAlreadyAssigned() {
		String name = "assignProductKOProductAlreadyAssigned";
		this.setASs(name);
		assertTrue(providerAS.assignProduct(providerProduct) > 0);
		assertEquals(providerAS.assignProduct(providerProduct), Errors.ProductAlreadyAssigned);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
