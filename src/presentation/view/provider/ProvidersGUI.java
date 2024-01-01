package presentation.view.provider;

import javax.swing.JFrame;

public class ProvidersGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static ProvidersGUI instance;

	public static synchronized ProvidersGUI getInstance() {
		if (instance == null) instance = new ProvidersGUIImp();
		return instance;
	}

}
