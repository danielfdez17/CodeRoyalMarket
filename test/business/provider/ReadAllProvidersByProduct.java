package business.provider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;


public class ReadAllProvidersByProduct extends ProviderTests {
	
	@Test public void readProvidersByProductOK() {
		String name = "readAllProvidersByProductOK";
		this.setASs(name);
		assertTrue(providerAS.assignProduct(providerProduct) > 0);
		List<ProviderTransfer> res = providerAS.readProvidersByProduct(productId);
		assertFalse(res.isEmpty());
	}
	
	@Test public void readProvidersByProductKO() {
		String name = "readAllProvidersByProductKO";
		this.setASs(name);
		List<ProviderTransfer> res = providerAS.readProvidersByProduct(INF);
		assertTrue(res.isEmpty());
		productId = productAS.deleteProduct(productId);
		res = providerAS.readProvidersByProduct(productId);
		assertTrue(res.isEmpty());
	}
}
