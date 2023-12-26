package business.client;

import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class UpdateClient {
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
	public void updateOK() {
		client = new ClientTransfer(nif + "F", "updateClientOK", balance);
		clientAS.createClient(client);
		assertTrue(clientAS.updateClient(client) == client.getId());
	}
	
	@Test
	public void updateKOSintaxError() {
		client = new ClientTransfer(nif, " 2 ", -1);
		assertTrue(clientAS.updateClient(client) == Errors.SintaxError);
		client.setNif(nif + "A");
		assertTrue(clientAS.updateClient(client) == Errors.SintaxError);
		client.setName("updateKOSintaxError");
		assertTrue(clientAS.updateClient(client) == Errors.SintaxError);
		
	}
	
	@Test
	public void updateKONonexistentClient() {
		client = new ClientTransfer(nif + "A", "clientUpdateClientKONonexistentClient", balance);
		assertTrue(clientAS.updateClient(client) == Errors.NonexistentClient);
	}
}
