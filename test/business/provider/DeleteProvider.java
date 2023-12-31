package business.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utilities.Errors;

public class DeleteProvider extends ProviderTests {
	
	@Test public void deleteOK() {
		String name = "deleteProviderOK";
		this.setASs(name);
		assertEquals(providerAS.deleteProvider(providerId), providerId);
	}
	
	@Test public void deleteKONonexistentProvider() {
		assertEquals(providerAS.deleteProvider(providerId), Errors.NonexistentProvider);
	}
	
	@Test public void deleteKOInactiveProvider() {
		String name = "deleteProviderKOInactiveProvider";
		this.setASs(name);
		assertEquals(providerAS.deleteProvider(providerId), providerId);
		assertEquals(providerAS.deleteProvider(providerId), Errors.InactiveProvider);
	}
	
	@Test public void deleteProviderKOActiveProductAssigned() {
		String name = "deleteProviderKOActiveProductAssigned";
		this.setASs(name);
		assertTrue(providerAS.assignProduct(providerProduct) > 0);
		assertEquals(providerAS.deleteProvider(providerId), Errors.ActiveProductsAssigned);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
