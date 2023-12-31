package business.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;


public class ReadAllClients extends ClientTests {
	
	@Test
	public void readAllOK() {
		client = new ClientTransfer(nif + "E", "readClientsOK", balance);
		clientAS.createClient(client);
		List<ClientTransfer> res = clientAS.readAllClients();
		assertNotNull(res);
		assertFalse(res.isEmpty());
	}
	
//	@Test
//	public void readAllKO() {
//		Make sense when clientbo table is empty
//		assertTrue(clientAS.readClients().isEmpty());
//	}
}
