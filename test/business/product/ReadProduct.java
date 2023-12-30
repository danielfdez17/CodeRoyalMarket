package business.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;


public class ReadProduct extends ProductTests {
	
	@Test
	public void readOK() {
		String name = "readProductOK";
		setAS(name);
		assertNotNull(productAS.readProduct(productId));
	}
	
	@Test
	public void readKO() {
		assertNull(productAS.readProduct(INF));
	}
}
