package business.product;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ReadAllProducts  extends ProductTests {
	
	@Test
	public void readAllOK() {
		String name = "readAllProductsOK";
		setAS(name);
		List<ProductTransfer> res = productAS.readProducts();
		assertFalse(res.isEmpty());
	}
	
//	@Test
//	public void readAllKO() {
//		// Make sense when productbo table is empty
//		assertTrue(as.readProducts().isEmpty());
//	}
}
