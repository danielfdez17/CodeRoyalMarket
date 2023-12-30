package presentation.gui.client;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import utilities.Utils;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static ClientGUI instance;
	
	public ClientGUI() {
		this.initGUI();
	}
	
	public synchronized static ClientGUI getInstance() {
		if (instance == null) instance = new ClientGUI();
		return instance;
	}

	private void initGUI() {
		this.setTitle(Utils.ClientsTitle);
		
		
		
		Dimension dims_frame = this.getContentPane().getSize();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = new Dimension((int) dims_frame.getWidth(), (int) dims_frame.getHeight());
		
		this.setPreferredSize(new Dimension(400, 400));
		this.setLocation(dim.width / 2 - frame.getSize().width / 2 - 200, dim.height / 2 - frame.getSize().height / 2 - 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(false);
	}
}
