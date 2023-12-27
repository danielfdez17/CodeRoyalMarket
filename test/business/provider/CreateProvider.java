package business.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class CreateProvider {
	private static final int phoneNumber = 123456879;
	
	private static BusinessFactory bf;
	private static ProviderAS providerAS;
	
	private ProviderTransfer provider;
	
	private int providerId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		providerAS = bf.createProviderAS();
	}
	
	private void setASs(String name) {
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
	}
	
	@Test public void createOK() {
		String name = "createProviderOK";
		provider = new ProviderTransfer(name, phoneNumber);
		providerId = providerAS.createProvider(provider);
		assertTrue(providerId > 0);
	}
	
	@Test public void createKOSyntaxError() {
		String name = " 43 ";
		int phoneNumber = 12345678;
		provider = new ProviderTransfer(name, phoneNumber);
		assertTrue(providerAS.createProvider(provider) == Errors.SyntaxError);
		name = "nameOK";
		provider.setName(name);
		assertTrue(providerAS.createProvider(provider) == Errors.SyntaxError);
		
	}
	
	@Test public void createKOActiveProvider() {
		String name = "createProviderKOActiveProvider";
		setASs(name);
		providerId = providerAS.createProvider(provider);
		assertEquals(providerId, Errors.ActiveProvider);
	}
	
	@Test public void createKOInactiveProvider() {
		String name = "createProviderKOInactiveProvider";
		setASs(name);
		providerId = providerAS.deleteProvider(providerId);
		providerId = providerAS.createProvider(provider);
		assertEquals(providerId, Errors.InactiveProvider);
	}
}
