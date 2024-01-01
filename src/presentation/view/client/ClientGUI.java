package presentation.view.client;

import java.util.List;

import javax.swing.JFrame;

import business.client.ClientTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;

public abstract class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static ClientGUI instance;

	public synchronized static ClientGUI getInstance() {
		if (instance == null) instance = new ClientGUIImp();
		Controller.getInstance().action(new Context(Events.ReadAllClients, null));
		return instance;
	}
	
	public abstract void update(List<ClientTransfer>list);
}
