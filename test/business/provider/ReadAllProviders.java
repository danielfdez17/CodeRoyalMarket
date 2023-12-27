package business.provider;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadAllProviders {
	private static final int phoneNumber = 123456879;
	
	private static BusinessFactory bf;
	private static ProviderAS providerAS;
	
	private ProviderTransfer provider;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		providerAS = bf.createProviderAS();
	}
	
	private void setASs(String name) {
		provider = new ProviderTransfer(name, phoneNumber);
		providerAS.createProvider(provider);
	}
	
	@Test public void readAllProvidersOK() {
		String name = "readAllProvidersOK";
		this.setASs(name);
		List<ProviderTransfer> res = providerAS.readProviders();
		assertFalse(res.isEmpty());
	}
	
//	@Test public void readAllProvidersKO() {
//		// Make sense when providerbo table is empty
//		assertTrue(providerAS.readProviders().isEmpty());
//	}
}
