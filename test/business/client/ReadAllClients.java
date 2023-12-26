package business.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import business.businessFactory.BusinessFactory;

public class ReadAllClients {
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
	public void readAllOK() {
		client = new ClientTransfer(nif + "E", "readClientsOK", balance);
		clientAS.createClient(client);
		List<ClientTransfer> res = clientAS.readClients();
		assertNotNull(res);
		assertFalse(res.isEmpty());
	}
	
//	@Test
//	public void readAllKO() {
//		Make sense when clientbo table is empty
//		assertTrue(clientAS.readClients().isEmpty());
//	}
}
