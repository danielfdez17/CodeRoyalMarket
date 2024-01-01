package presentation.view.product;

import javax.swing.JFrame;

public class ProductsGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static ProductsGUI instance;

	public static synchronized ProductsGUI getInstance() {
		if (instance == null) instance = new ProductsGUIImp();
		return instance;
	}

}
