package presentation.view.product;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import business.product.ProductTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.Frame;
import presentation.view.GUIMSG;
import utilities.Utils;

public class ReadAllProductsFrame extends Frame {
	
	private static final long serialVersionUID = 1L;

	private static final String FromWhere = ReadAllProductsFrame.class.getSimpleName();
	
	private static ReadAllProductsFrame instance;
	private JTable table;
	private DefaultTableModel model;
	
	public ReadAllProductsFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllProductsFrame getInstance() {
		if (instance == null) instance = new ReadAllProductsFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.ReadAllProducts);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		// TABLE MODEL
    	this.model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
    		public boolean isCellEditable(int row, int col) { return false; }
    	};
    	this.model.setColumnCount(0);
    	for (String s : Utils.ProductsHeaders)
    		this.model.addColumn(s);
    	
    	// MAIN TABLE
    	this.table = new JTable(this.model);
    	
    	// LIST BUTTON
    	JButton readAllButton = new JButton(Utils.ReadAllProducts);
    	readAllButton.addActionListener(l -> {
    		this.setVisible(false);
    		Controller.getInstance().action(new Context(Events.ReadAllProducts, null));
    	});
		// BUTTONS PANEL
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		buttonsPanel.add(readAllButton);
		
		mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);
		mainPanel.add(new JScrollPane(this.table), BorderLayout.CENTER);
		
		Dimension dims_frame = this.getContentPane().getSize();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = new Dimension((int) dims_frame.getWidth(), (int) dims_frame.getHeight());
		
		this.setPreferredSize(new Dimension(400, 400));
		this.setLocation(dim.width / 2 - frame.getSize().width / 2 - 200, dim.height / 2 - frame.getSize().height / 2 - 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
				
 	}

	@Override
	public void update(Context context) {
		switch (context.getEvent()) {
		case ReadAllProducts:
			this.model.setRowCount(0);
			@SuppressWarnings("unchecked") List<ProductTransfer> list = (List<ProductTransfer>) context.getData();
			for (ProductTransfer p : list) {
				String id = "" + p.getId(),
						name = p.getName(),
						stock = "" + p.getStock(),
						price = "" + p.getPrice(),
						warehouseId = "" + p.getWarehouseId();
				if (p.isActive()) {
					id = this.toBold(id);
					name = this.toBold(name);
					stock = this.toBold(stock);
					price = this.toBold(price);
					warehouseId = this.toBold(warehouseId);
					DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
					renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
					table.getColumnModel().getColumn(1).setCellRenderer(renderer);
				}
				this.model.addRow(new Object[] {id, name, stock, price, warehouseId});
			}
			break;
		default:
			GUIMSG.showMessage(Utils.NotConsideredResponse, FromWhere, true);
			break;
		}
	}

	@Override
	public void clearData() {}

	@Override
	public String getErrorMsg(int error) {
		return "";
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return false;
	}

}
