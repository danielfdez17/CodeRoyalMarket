package presentation.view.product;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import presentation.view.client.CreateClientFrame;
import presentation.view.client.DeleteClientFrame;
import presentation.view.client.UpdateClientFrame;
import utilities.Utils;

public class ProductsGUIImp extends ProductsGUI {

	private static final long serialVersionUID = 1L;
	private static final int ROWS = 7, COLS = 1;
	
	public ProductsGUIImp() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
this.setTitle(Utils.ProductsTitle);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		JButton createButton = new JButton(Utils.CreateProduct);
		createButton.addActionListener(l -> {
			CreateProductFrame.getInstance().setVisible(true);
		});
		JButton readButton = new JButton(Utils.ReadProduct);
		readButton.addActionListener(l -> {
			ReadProductFrame.getInstance().setVisible(true);
		});
		JButton readAllButton = new JButton(Utils.ReadAllProducts);
		readAllButton.addActionListener(l -> {
			ReadAllProductsFrame.getInstance().setVisible(true);
		});
		JButton readAllByProviderButton = new JButton(Utils.ReadAllProductsByProvider);
		readAllByProviderButton.addActionListener(l -> {
			ReadAllProductsByProviderFrame.getInstance().setVisible(true);
		});
		JButton readAllProductsBySaleButton = new JButton(Utils.ReadAllProductsBySale);
		readAllProductsBySaleButton.addActionListener(l -> {
			ReadAllProductsBySaleFrame.getInstance().setVisible(true);
		});
		JButton updateButton = new JButton(Utils.UpdateProduct);
		updateButton.addActionListener(l -> {
			UpdateProductFrame.getInstance().setVisible(true);
		});
		JButton deleteButton = new JButton(Utils.DeleteProduct);
		deleteButton.addActionListener(l -> {
			DeleteProductFrame.getInstance().setVisible(true);
		});
		
		buttonsPanel.add(createButton);
		buttonsPanel.add(readButton);
		buttonsPanel.add(readAllButton);
		buttonsPanel.add(readAllByProviderButton);
		buttonsPanel.add(readAllProductsBySaleButton);
		buttonsPanel.add(updateButton);
		buttonsPanel.add(deleteButton);
		
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);
		
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
