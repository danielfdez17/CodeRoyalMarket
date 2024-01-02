package presentation.view.sale;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import business.sale.SaleTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.GUIMSG;
import utilities.Utils;
import utilities.gui.ReadAllFrame;

public class ReadAllSalesByClientFrame extends ReadAllFrame {

	private static final long serialVersionUID = 1L;
	private static final String FromWhere = ReadAllSalesByClientFrame.class.getSimpleName();
	
	private static ReadAllSalesByClientFrame instance;
	private JTextField clientIdText;
	
	public ReadAllSalesByClientFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllSalesByClientFrame getInstance() {
		if (instance == null) instance = new ReadAllSalesByClientFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.ReadAllSalesByClient);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		// TABLE MODEL
    	this.model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
    		public boolean isCellEditable(int row, int col) { return false; }
    	};
    	this.model.setColumnCount(0);
    	for (String s : Utils.SalesHeaders)
    		this.model.addColumn(s);
    	
    	// MAIN TABLE
    	this.table = new JTable(this.model);
    	
		JButton readAllButton = new JButton(Utils.ReadAllSalesByClient);
		readAllButton.addActionListener(l -> {
			Controller.getInstance().action(new Context(Events.ReadAllSalesByClient, null));
		});
		
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
		this.setVisible(false);
	}

	@Override
	public void update(Context context) {
		switch (context.getEvent()) {
		case ReadAllSalesByClientOK:
			this.model.setRowCount(0);
			@SuppressWarnings("unchecked") List<SaleTransfer> list = (List<SaleTransfer>) context.getData();
			for (SaleTransfer s : list) {
				String id = "" + s.getId(),
						cost = "" + s.getCost(),
						date = s.getDate().toString(),
						clientId = "" + s.getClientId();
				this.model.addRow(new Object[] {id, cost, date, clientId});
			}
			break;
		case ReadAllSalesByClientKO:
			GUIMSG.showMessage(this.getErrorMsg((int)context.getData()), FromWhere, true);
			break;
		default:
			GUIMSG.showMessage(Utils.NotConsideredResponse, FromWhere, true);
			break;
		}
	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getErrorMsg(int error) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean areTextFieldsEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
