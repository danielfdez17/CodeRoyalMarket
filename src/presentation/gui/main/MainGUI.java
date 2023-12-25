package presentation.gui.main;

import javax.swing.JFrame;

public class MainGUI extends JFrame {
	private static MainGUI instance;
	
	public synchronized static MainGUI getInstance() {
		if (instance == null) instance = new MainGUIImp();
		return instance;
	}
	
}
