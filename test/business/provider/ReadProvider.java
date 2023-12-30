package business.provider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadProvider extends ProviderTests {
	
	@Test public void readOK() {
		String name = "readProviderOK";
		setASs(name);
		assertNotNull(providerAS.readProvider(providerId));
	}
	
	@Test public void readKO() {
		assertNull(providerAS.readProvider(INF));
	}
}
