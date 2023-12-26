package business.client;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class CreateClient {
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
	public void createOK() {
		client = new ClientTransfer(nif + "A", "createClientOK", balance);
		assertTrue(clientAS.createClient(client) > 0);
	}
	
	@Test 
	public void createKOSintaxError() {
		client = new ClientTransfer(nif, "createClientKOSintaxError", balance);
		assertTrue(clientAS.createClient(client) == Errors.SintaxError);
		client.setNif(nif + "A");
		client.setBalance(-1);
		assertTrue(clientAS.createClient(client) == Errors.SintaxError);
		client.setBalance(balance);
		client.setName("  3  ");
		assertTrue(clientAS.createClient(client) == Errors.SintaxError);
	}
	
	@Test
	public void createKOActiveClient() {
		client = new ClientTransfer(nif + "B", "createClientKOActiveClient", balance);
		clientAS.createClient(client);
		assertTrue(clientAS.createClient(client) == Errors.ActiveClient);
	}
	
	@Test 
	public void createKOInactiveClient() {
		client = new ClientTransfer(nif + "C", "createClientKOInactiveClient", balance);
		clientAS.createClient(client);
		clientAS.deleteClient(client.getId());
		assertTrue(clientAS.createClient(client) == Errors.InactiveClient);
	}
}
