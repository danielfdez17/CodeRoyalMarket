package business.provider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadProvider {
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
	
	@Test public void readOK() {
		String name = "readProviderOK";
		setASs(name);
		assertNotNull(providerAS.readProvider(providerId));
	}
	
	@Test public void readKO() {
		assertNull(providerAS.readProvider(0));
	}
}
