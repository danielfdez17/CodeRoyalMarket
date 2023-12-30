package business.worker;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;


public class ReadAllWorkers extends WorkerTests {
	
	@Test public void readAllWorkersOK() {
		String name = "readAllWorkersOK", nif1 = nif + "Q", nif2 = nif + "R";
		this.setASs(name, nif1, nif2);
		List<WorkerTransfer> res = workerAS.readWorkers();
		assertFalse(res.isEmpty());
	}
	
//	@Test public void readAllWorkersKO() {
//		List<WorkerTransfer> res = workerAS.readWorkers();
//		assertTrue(res.isEmpty());
//	}
}
