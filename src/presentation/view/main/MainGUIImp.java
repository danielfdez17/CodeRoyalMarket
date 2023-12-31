package presentation.view.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;

import presentation.view.client.ClientGUI;
import utilities.Utils;
import utilities.gui.Button;

public class MainGUIImp extends MainGUI {
	
	private static final long serialVersionUID = 1L;
	private static final int ROWS = 6, COLS = 1;
	
	public MainGUIImp() {
		this.initGUI();
	}
	
	private void initGUI() {
		this.setTitle(Utils.RoyalMarket);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		Button clientsButton = new Button(Utils.ClientsTitle);
		clientsButton.getJButton().addActionListener(l -> {
			ClientGUI.getInstance().setVisible(true);
		});
		buttonsPanel.add(clientsButton.getJButton());
		
		Button productsButton = new Button(Utils.ProductsTitle);
		productsButton.getJButton().addActionListener(l -> {
			
		});
		buttonsPanel.add(productsButton.getJButton());
		
		Button providersButton = new Button(Utils.ProvidersTitle);
		providersButton.getJButton().addActionListener(l -> {
			
		});
		buttonsPanel.add(providersButton.getJButton());
		
		Button salesButton = new Button(Utils.SalesTitle);
		salesButton.getJButton().addActionListener(l -> {
			
		});
		buttonsPanel.add(salesButton.getJButton());
		
		Button warehousesButton = new Button(Utils.WarehousesTitle);
		warehousesButton.getJButton().addActionListener(l -> {
			
		});
		buttonsPanel.add(warehousesButton.getJButton());
		
		Button workersButton = new Button(Utils.WorkersTitle);
		workersButton.getJButton().addActionListener(l -> {
			
		});
		buttonsPanel.add(workersButton.getJButton());
		
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);
		
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
