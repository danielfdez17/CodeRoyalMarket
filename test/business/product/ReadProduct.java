package business.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

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
