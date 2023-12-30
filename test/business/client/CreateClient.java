package business.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utilities.Errors;

public class CreateClient extends ClientTests {

	
	@Test
	public void createOK() {
		client = new ClientTransfer(nif + "A", "createClientOK", balance);
		assertTrue(clientAS.createClient(client) > 0);
	}
	
	@Test 
	public void createKOSyntaxError() {
		client = new ClientTransfer(nif, "createClientKOSyntaxError", balance);
		assertTrue(clientAS.createClient(client) == Errors.SyntaxError);
		client.setNif(nif + "A");
		client.setBalance(-1);
		assertTrue(clientAS.createClient(client) == Errors.SyntaxError);
		client.setBalance(balance);
		client.setName("  3  ");
		assertTrue(clientAS.createClient(client) == Errors.SyntaxError);
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
