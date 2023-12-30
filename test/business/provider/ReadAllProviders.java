package business.provider;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadAllProviders extends ProviderTests {
	
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
