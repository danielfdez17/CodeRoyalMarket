package business.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadClient {
	private BusinessFactory bf;
	private ClientAS clientAS;
	private ClientTransfer client;
	private static final String nif = "12345678";
	private static final double balance = 500;
	
	@Before
	public void setUp() {
		bf = BusinessFactory.getInstance();
		clientAS = bf.createClientAS();
	}
	
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
