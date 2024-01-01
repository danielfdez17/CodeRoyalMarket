package presentation.view.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.Frame;
import utilities.Utils;

public class ReadAllClientsFrame extends Frame {
	
	private static final long serialVersionUID = 1L;
	private static final String Headers[] = {};
	private static final String FromWhere = ReadAllClientsFrame.class.getSimpleName();
	
	private JTable table;
	private DefaultTableModel model;
	
	public ReadAllClientsFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setTitle(Utils.ReadAllClients);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		// TABLE MODEL
    	this.model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
    		public boolean isCellEditable(int row, int col) { return false; }
    	};
    	this.model.setColumnCount(0);
    	for (String s : Headers)
    		this.model.addColumn(s);
    	
    	// MAIN TABLE
    	this.table = new JTable(this.model);
    	
    	// LIST BUTTON
    	JButton readAllButton = new JButton(Utils.ReadAllClients);
    	readAllButton.addActionListener(l -> {
    		this.setVisible(false);
    		Controller.getInstance().action(new Context(Events.ReadAllClients, null));
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
		// TODO Auto-generated method stub
		
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
