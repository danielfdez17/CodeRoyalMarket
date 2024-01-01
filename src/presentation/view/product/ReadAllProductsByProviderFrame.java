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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import business.product.ProductTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.Frame;
import presentation.view.GUIMSG;
import utilities.Errors;
import utilities.Utils;
import utilities.gui.FieldPanel;

public class ReadAllProductsByProviderFrame extends Frame {
	
	private static final long serialVersionUID = 1L;

	private static final String FromWhere = ReadAllProductsByProviderFrame.class.getSimpleName();
	
	private static ReadAllProductsByProviderFrame instance;
	private JTable table;
	private DefaultTableModel model;
	private JTextField providerIdText;
	
	public ReadAllProductsByProviderFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllProductsByProviderFrame getInstance() {
		if (instance == null) instance = new ReadAllProductsByProviderFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.ReadAllProductsByProvider);
		
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
    	
    	FieldPanel providerIdPanel = new FieldPanel(Utils.ProviderId);
    	this.providerIdText = providerIdPanel.getJTextField();
    	
    	// LIST BUTTON
    	JButton readAllButton = new JButton(Utils.ReadAllProductsByProvider);
    	readAllButton.addActionListener(l -> {
    		if (!this.areTextFieldsEmpty()) {
    			try {
    				int providerId = Integer.parseInt(this.providerIdText.getText());
    				this.setVisible(false);
    				Controller.getInstance().action(new Context(Events.ReadAllProductsByProvider, providerId));
    			} catch(NumberFormatException nfe) {
    				GUIMSG.showMessage(Utils.OnlyNumbersFields, FromWhere, true);
    			}
    		}
    		else {
    			GUIMSG.showMessage(Utils.SomeTextFieldsAreEmpty, FromWhere, true);
    		}
    	});
    	
    	JButton emptyButton = new JButton(Utils.EmptyTextFields);
    	emptyButton.addActionListener(l -> {
    		this.clearData();
    	});
    	
		// BUTTONS PANEL
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		buttonsPanel.add(readAllButton);
		buttonsPanel.add(emptyButton);
		
		mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);
		mainPanel.add(providerIdPanel.getJPanel(), BorderLayout.LINE_START);
		mainPanel.add(new JScrollPane(this.table), BorderLayout.CENTER);
		
		Dimension dims_frame = this.getContentPane().getSize();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = new Dimension((int) dims_frame.getWidth(), (int) dims_frame.getHeight());
		
		this.setPreferredSize(new Dimension(400, 400));
		this.setLocation(dim.width / 2 - frame.getSize().width / 2 - 200, dim.height / 2 - frame.getSize().height / 2 - 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(false);
 	}

	@Override
	public void update(Context context) {
		switch (context.getEvent()) {
		case ReadAllProductsByProviderOK:
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
		case ReadAllProductsByProviderKO:
			GUIMSG.showMessage(this.getErrorMsg((int)context.getData()), FromWhere, true);
			break;
		default:
			GUIMSG.showMessage(Utils.NotConsideredResponse, FromWhere, true);
			break;
		}
	}

	@Override
	public void clearData() {
		String t = "";
		this.providerIdText.setText(t);
	}

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
		case Errors.NonexistentProvider:
			return String.format(Utils.NonexistentProvider, this.providerIdText.getText());
		case Errors.InactiveProvider:
			return String.format(Utils.InactiveProvider, this.providerIdText.getText());
		case Errors.UnexpectedError:
			return Utils.UnexpectedError;
		default:
			return Utils.NotConsideredResponse;
		}
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return this.providerIdText.getText().isBlank();
	}

}
