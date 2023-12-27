package business.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class UpdateProvider {
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
	
	@Test public void updateOK() {
		String name = "updateProviderOK";
		this.setASs(name);
		assertEquals(providerAS.updateProvider(provider), providerId);
	}
	
	@Test public void updateKOSyntaxError() {
		String name = " 43 ";
		int phoneNumber = 12345678;
		provider = new ProviderTransfer(name, phoneNumber);
		assertTrue(providerAS.updateProvider(provider) == Errors.SyntaxError);
		name = "nameOK";
		provider.setName(name);
		assertTrue(providerAS.updateProvider(provider) == Errors.SyntaxError);
	}
	
	@Test public void updateKONonexistentProvider() {
		provider = new ProviderTransfer("doesnotmatter", phoneNumber);
		assertEquals(providerAS.updateProvider(provider), Errors.NonexistentProvider);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
