package business.client;

import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class DeleteClient {
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
	public void deleteOK() {
		client = new ClientTransfer(nif + "G", "deleteClientOK", balance);
		clientAS.createClient(client);
		assertTrue(clientAS.deleteClient(client.getId()) == client.getId());
	}
	
	@Test
	public void deleteKONonexistentClient() {
		assertTrue(clientAS.deleteClient(-1) == Errors.NonexistentClient);
	}
	
	@Test
	public void deleteKOInactiveClient() {
		client = new ClientTransfer(nif + "H", "deleteCLientKOInactiveClient", balance);
		clientAS.createClient(client);
		clientAS.deleteClient(client.getId());
		assertTrue(clientAS.deleteClient(client.getId()) == Errors.InactiveClient);
	}
}
