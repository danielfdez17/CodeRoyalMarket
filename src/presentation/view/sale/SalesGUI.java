package presentation.view.sale;

import javax.swing.JFrame;

public class SalesGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static SalesGUI instance;
	
	public static synchronized SalesGUI getInstance() {
		if (instance == null) instance = new SalesGUIImp();
		return instance;
	}

}
