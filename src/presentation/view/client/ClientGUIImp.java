package presentation.view.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import business.client.ClientTransfer;
import utilities.Utils;
import utilities.gui.Button;

public class ClientGUIImp extends ClientGUI {
	
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {Utils.Id, Utils.Nif, Utils.Name, Utils.Balance};
	private static final int BUTTONS = 3;
	private JTable table;
	private DefaultTableModel model;
	
	public ClientGUIImp() {
		this.initGUI();
	}

	private void initGUI() {
		this.setTitle(Utils.ClientsTitle);
		
		this.model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int col) { return false; }
		};
		this.model.setColumnCount(0);
		for (String s : headers)
			this.model.addColumn(s);
		
		this.table = new JTable(this.model);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(BUTTONS, 1));
		
		Button createButton = new Button(Utils.CreateClient);
		createButton.getJButton().addActionListener(l -> {
			CreateClientFrame.getInstance().setVisible(true);
		});
		Button updateButton = new Button(Utils.UpdateClient);
		updateButton.getJButton().addActionListener(l -> {
			UpdateClientFrame.getInstance().setVisible(true);
		});
		Button deleteButton = new Button(Utils.DeleteClient);
		deleteButton.getJButton().addActionListener(l -> {
			DeleteClientFrame.getInstance().setVisible(true);
		});
		
		buttonsPanel.add(createButton.getJButton());
		buttonsPanel.add(updateButton.getJButton());
		buttonsPanel.add(deleteButton.getJButton());
		
		mainPanel.add(new JScrollPane(this.table), BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.LINE_START);
		
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
	public void update(List<ClientTransfer> list) {
		this.model.setRowCount(0);
		for (ClientTransfer c : list) {
			String id = "" + c.getId(),
					nif = c.getNif(),
					name = c.getName(),
					balance = "" + c.getBalance();
			if (c.isActive()) {
				id = this.toBold(id);
				nif = this.toBold(nif);
				name = this.toBold(name);
				balance = this.toBold(balance);
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
				renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
				table.getColumnModel().getColumn(1).setCellRenderer(renderer);
			}
			this.model.addRow(new Object[] {id, nif, name, balance});
		}
		this.model.fireTableStructureChanged();
	}
	
	private String toBold(String s) {
    	return "<html><b>" + s + "</b></html>";
    }
	
	public static void main(String[] args) {
		ClientGUI.getInstance().setVisible(true);
	}

}
