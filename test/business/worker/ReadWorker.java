package business.worker;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class ReadWorker extends WorkerTests {

	@Test public void readWorkerOK() {
		String name = "readWorkerOK", nif1 = nif + "U", nif2 = nif + "V";
		this.setASs(name, nif1, nif2);
		assertTrue(fullTimeId > 0);
		assertTrue(partTimeId > 0);
		assertNotNull(workerAS.readWorker(fullTimeId));
		assertNotNull(workerAS.readWorker(partTimeId));
	}
	
	@Test public void readWorkerKO() {
		assertNull(workerAS.readWorker(INF));
	}
}
