package business.product;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;


public class ReadAllProductsByProvider extends ProductTests {
	
	@Test
	public void readByProviderOK() {
		String name = "readProductsByProviderOK";
		this.setAS(name);
		providerAS.assignProduct(providerProduct);
		List<ProductTransfer> res = productAS.readProductsByProvider(providerId);
		assertFalse(res.isEmpty());
	}
	
	@Test public void readByProviderKO() {
		List<ProductTransfer> res = productAS.readProductsByProvider(providerId);
		assertTrue(res.isEmpty());
		providerAS.deleteProvider(providerId);
		res = productAS.readProductsByProvider(providerId);
		assertTrue(res.isEmpty());
	}
}
