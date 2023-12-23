package business.client;

import java.util.List;

public interface ClientAS {
	
	public static final int UnespectedError = 0;
	public static final int SintaxError = -1;
	public static final int ActiveClient = -2;
	public static final int InactiveClient = -3;
	public static final int NonexistentClient = -4;
	
	public int createClient(ClientTransfer client);

	public ClientTransfer readClient(int clientId);

	public List<ClientTransfer> readAllCllients();

	public int updateClient(ClientTransfer client);

	public int deleteClient(int clientId);
}