package business.client;

import static org.junit.Assert.assertTrue;


import org.junit.Test;

import utilities.Errors;

public class UpdateClient extends ClientTests {
	
	@Test
	public void updateOK() {
		client = new ClientTransfer(nif + "F", "updateClientOK", balance);
		clientAS.createClient(client);
		assertTrue(clientAS.updateClient(client) == client.getId());
	}
	
	@Test
	public void updateKOSyntaxError() {
		client = new ClientTransfer(nif, " 2 ", -1);
		assertTrue(clientAS.updateClient(client) == Errors.SyntaxError);
		client.setNif(nif + "A");
		assertTrue(clientAS.updateClient(client) == Errors.SyntaxError);
		client.setName("updateKOSyntaxError");
		assertTrue(clientAS.updateClient(client) == Errors.SyntaxError);
		
	}
	
	@Test
	public void updateKONonexistentClient() {
		client = new ClientTransfer(nif + "A", "clientUpdateClientKONonexistentClient", balance);
		assertTrue(clientAS.updateClient(client) == Errors.NonexistentClient);
	}
}
