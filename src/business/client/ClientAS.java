package business.client;

import java.util.List;

public interface ClientAS {
	
	public int createClient(ClientTransfer client);
	public ClientTransfer readClient(int clientId);
	public List<ClientTransfer> readAllCllients();
	public int updateClient(ClientTransfer client);
	public int deleteClient(int clientId);
	
}