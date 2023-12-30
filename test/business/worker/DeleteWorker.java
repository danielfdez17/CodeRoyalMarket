package business.worker;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;
import utilities.Errors;

public class DeleteWorker extends WorkerTests {
	
	@Test public void deleteWorkerOK() {
		String name = "deleteWorkerOK", nif1 = nif + "M", nif2 = nif + "N";
		this.setASs(name, nif1, nif2);
		assertEquals(workerAS.deleteWorker(fullTimeId), fullTimeId);
		assertEquals(workerAS.deleteWorker(partTimeId), partTimeId);
	}
	
	@Test public void deleteWorkerKONonexistentWorker() {
		assertEquals(workerAS.deleteWorker(INF), Errors.NonexistentWorker);
	}
	
	@Test public void deleteWorkerKOInactiveWorker() {
		String name = "deleteWorkerKOInactiveWorker", nif1 = nif + "O", nif2 = nif + "P";
		this.setASs(name, nif1, nif2);
		workerAS.deleteWorker(fullTimeId);
		workerAS.deleteWorker(partTimeId);
		assertEquals(workerAS.deleteWorker(fullTimeId), Errors.InactiveWorker);
		assertEquals(workerAS.deleteWorker(partTimeId), Errors.InactiveWorker);
	}
}
