package business.client;

import java.util.Set;

public interface ClientAS {
	public int createClient(ClientTransfer client);

	public ClientTransfer readClient(int clientId);

	public Set<ClientTransfer> readAllCllients();

	public int updateClient(ClientTransfer client);

	public int deleteClient(int clientId);
}