package business.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;


public class ReadClient extends ClientTests {
	
	@Test
	public void readOK() {
		client = new ClientTransfer(nif + "D", "readClientOK", balance);
		clientAS.createClient(client);
		assertNotNull(clientAS.readClient(client.getId()));
	}
	
	@Test
	public void readKO() {
		assertNull(clientAS.readClient(-1));
	}
}
