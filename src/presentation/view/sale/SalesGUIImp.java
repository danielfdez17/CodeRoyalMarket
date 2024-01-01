package presentation.view.sale;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utilities.Utils;

public class SalesGUIImp extends SalesGUI {

	private static final long serialVersionUID = 1L;
	private static final int ROWS = 10, COLS = 2;
	
	public SalesGUIImp() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}

	private void initGUI() {
		this.setTitle(Utils.SalesTitle);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		JButton openSaleButton = new JButton(Utils.OpenSale);
		openSaleButton.addActionListener(l -> {
			OpenSaleFrame.getInstance().setVisible(true);
		});
		JButton readSaleButton = new JButton(Utils.ReadSale);
		readSaleButton.addActionListener(l -> {
			ReadSaleFrame.getInstance().setVisible(true);
		});
		JButton readAllButton = new JButton(Utils.ReadAllSales);
		readAllButton.addActionListener(l -> {
			ReadAllSalesFrame.getInstance().setVisible(true);
		});
		JButton readAllByClientButton = new JButton(Utils.ReadAllSalesByClient);
		readAllByClientButton.addActionListener(l -> {
			ReadAllSalesByClientFrame.getInstance().setVisible(true);
		});
		JButton readAllByProductButton = new JButton(Utils.ReadAllSalesByProduct);
		readAllByProductButton.addActionListener(l -> {
			ReadAllSalesByProductFrame.getInstance().setVisible(true);
		});
		JButton addProductButton = new JButton(Utils.AddProduct);
		addProductButton.addActionListener(l -> {
			AddProductFrame.getInstance().setVisible(true);
		});
		JButton removeProductButton = new JButton(Utils.RemoveProduct);
		removeProductButton.addActionListener(l -> {
			RemoveProductFrame.getInstance().setVisible(true);
		});
		JButton returnSaleButton = new JButton(Utils.ReturnSale);
		returnSaleButton.addActionListener(l -> {
			ReturnSaleFrame.getInstance().setVisible(true);
		});
		JButton returnProductButton = new JButton(Utils.ReturnProduct);
		returnProductButton.addActionListener(l -> {
			ReturnProductFrame.getInstance().setVisible(true);
		});
		JButton closeSaleButton = new JButton(Utils.CloseSale);
		closeSaleButton.addActionListener(l -> {
			CloseSaleFrame.getInstance().setVisible(true);
		});
		
		buttonsPanel.add(openSaleButton);
		buttonsPanel.add(readSaleButton);
		buttonsPanel.add(readAllButton);
		buttonsPanel.add(readAllByClientButton);
		buttonsPanel.add(readAllByProductButton);
		buttonsPanel.add(addProductButton);
		buttonsPanel.add(removeProductButton);
		buttonsPanel.add(returnSaleButton);
		buttonsPanel.add(returnProductButton);
		buttonsPanel.add(closeSaleButton);
		
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
