package presentation.view.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import presentation.view.client.ClientGUI;
import utilities.Utils;

public class MainGUIImp extends MainGUI {
	
	private static final long serialVersionUID = 1L;
	private static final int ROWS = 6, COLS = 1;
	
	public MainGUIImp() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setTitle(Utils.RoyalMarket);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		JButton clientsButton = new JButton(Utils.ClientsTitle);
		clientsButton.addActionListener(l -> {
			ClientGUI.getInstance().setVisible(true);
		});
		buttonsPanel.add(clientsButton);
		
		JButton productsButton = new JButton(Utils.ProductsTitle);
		productsButton.addActionListener(l -> {
			
		});
		buttonsPanel.add(productsButton);
		
		JButton providersButton = new JButton(Utils.ProvidersTitle);
		providersButton.addActionListener(l -> {
			
		});
		buttonsPanel.add(providersButton);
		
		JButton salesButton = new JButton(Utils.SalesTitle);
		salesButton.addActionListener(l -> {
			
		});
		buttonsPanel.add(salesButton);
		
		JButton warehousesButton = new JButton(Utils.WarehousesTitle);
		warehousesButton.addActionListener(l -> {
			
		});
		buttonsPanel.add(warehousesButton);
		
		JButton workersButton = new JButton(Utils.WorkersTitle);
		workersButton.addActionListener(l -> {
			
		});
		buttonsPanel.add(workersButton);
		
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
