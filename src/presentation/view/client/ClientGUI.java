package presentation.view.client;

import java.util.List;

import javax.swing.JFrame;

import business.client.ClientTransfer;

public abstract class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static ClientGUI instance;

	public synchronized static ClientGUI getInstance() {
		if (instance == null) instance = new ClientGUIImp();
		return instance;
	}
	
	public abstract void update(List<ClientTransfer>list);
}
