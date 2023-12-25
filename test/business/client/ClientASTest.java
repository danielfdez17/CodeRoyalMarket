package business.client;

import static org.junit.Assert.assertTrue;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import business.businessFactory.BusinessFactory;
import utilities.Errors;

public class ClientASTest {
	
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
	
	@Test
	public void readAllOK() {
		client = new ClientTransfer(nif + "E", "readClientsOK", balance);
		clientAS.createClient(client);
		List<ClientTransfer> res = clientAS.readClients();
		assertNotNull(res);
		assertFalse(res.isEmpty());
	}
	
	@Test
	public void readAllKO() {
		assertTrue(clientAS.readClients().isEmpty());
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
