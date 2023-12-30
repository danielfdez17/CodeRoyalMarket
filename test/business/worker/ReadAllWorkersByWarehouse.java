package business.worker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;


public class ReadAllWorkersByWarehouse extends WorkerTests {
	
	@Test public void readAllWorkersByWarehouseOK() {
		String name = "readAllWorkersOK", nif1 = nif + "S", nif2 = nif + "T";
		this.setASs(name, nif1, nif2);
		List<WorkerTransfer> res = workerAS.readWorkersByWarehouse(warehouseId);
		assertFalse(res.isEmpty());
	}
	
	@Test public void readAllWorkersByWarehouseKO() {
		List<WorkerTransfer> res = workerAS.readWorkersByWarehouse(warehouseId);
		assertTrue(res.isEmpty());
	}
}
