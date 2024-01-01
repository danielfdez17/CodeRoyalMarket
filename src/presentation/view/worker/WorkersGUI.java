package presentation.view.worker;

import javax.swing.JFrame;

public class WorkersGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private static WorkersGUI instance;
	
	public static synchronized WorkersGUI getInstance() {
		if (instance == null) instance = new WorkersGUIImp();
		return instance;
	}
}
