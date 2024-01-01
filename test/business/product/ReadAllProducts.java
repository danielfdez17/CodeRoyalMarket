package business.product;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;


public class ReadAllProducts  extends ProductTests {
	
	@Test
	public void readAllOK() {
		String name = "readAllProductsOK";
		setAS(name);
		List<ProductTransfer> res = productAS.readAllProducts();
		assertFalse(res.isEmpty());
	}
	
//	@Test
//	public void readAllKO() {
//		// Make sense when productbo table is empty
//		assertTrue(as.readProducts().isEmpty());
//	}
}
