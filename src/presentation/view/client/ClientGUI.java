package presentation.view.client;

import javax.swing.JFrame;

public abstract class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static ClientGUI instance;

	public synchronized static ClientGUI getInstance() {
		if (instance == null) instance = new ClientGUIImp();
		return instance;
	}
	
}
