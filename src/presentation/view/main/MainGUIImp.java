package presentation.view.main;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MainGUIImp extends MainGUI {
	
	private static final long serialVersionUID = 1L;
	private static final int BUTTONS = 6;
	
	public MainGUIImp() {
		this.initGUI();
	}
	
	private void initGUI() {
		
		Dimension dims_frame = this.getContentPane().getSize();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = new Dimension((int) dims_frame.getWidth(), (int) dims_frame.getHeight());
		
		this.setPreferredSize(new Dimension(400, 400));
		this.setLocation(dim.width / 2 - frame.getSize().width / 2 - 200, dim.height / 2 - frame.getSize().height / 2 - 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(false);
	}

}
