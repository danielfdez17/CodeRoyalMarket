package presentation.view.warehouse;

import javax.swing.JFrame;

public class WarehouseGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static WarehouseGUI instance;
	
	public static synchronized WarehouseGUI getInstance() {
		if (instance == null) instance = new WarehouseGUIImp();
		return instance;
	}

}
