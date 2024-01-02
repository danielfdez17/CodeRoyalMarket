package presentation.view.provider;

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

import business.provider.ProviderTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.GUIMSG;
import utilities.Errors;
import utilities.Utils;
import utilities.gui.FieldPanel;
import utilities.gui.ReadAllFrame;

public class ReadAllProvidersByProductFrame extends ReadAllFrame {

	private static final long serialVersionUID = 1L;
	private static final String FromWhere = ReadAllProvidersByProductFrame.class.getSimpleName();
	
	private static ReadAllProvidersByProductFrame instance;
	private JTextField productIdText;
	
	public ReadAllProvidersByProductFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllProvidersByProductFrame getInstance() {
		if (instance == null) instance = new ReadAllProvidersByProductFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.ReadAllProvidersByProduct);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		FieldPanel productIdPanel = new FieldPanel(Utils.ProductId);
		this.productIdText = productIdPanel.getJTextField();
		
		// TABLE MODEL
    	this.model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
    		public boolean isCellEditable(int row, int col) { return false; }
    	};
    	this.model.setColumnCount(0);
    	for (String s : Utils.ProvidersHeaders)
    		this.model.addColumn(s);
    	
    	// MAIN TABLE
    	this.table = new JTable(this.model);
    	
    	// LIST BUTTON
    	JButton readAllButton = new JButton(Utils.ReadAllProvidersByProduct);
    	readAllButton.addActionListener(l -> {
    		
    		this.setVisible(false);
    		Controller.getInstance().action(new Context(Events.ReadAllProvidersByProduct, null));
    	});
		// BUTTONS PANEL
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
		buttonsPanel.add(readAllButton);
		
		mainPanel.add(productIdPanel.getJPanel(), BorderLayout.LINE_START);
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
		case ReadAllProvidersByProductOK:
			this.model.setRowCount(0);
			@SuppressWarnings("unchecked") List<ProviderTransfer> list = (List<ProviderTransfer>) context.getData();
			for (ProviderTransfer p : list) {
				String id = "" + p.getId(),
						name = p.getName(),
						phoneNumber = "" + p.getPhoneNumber();
				if (p.isActive()) {
					id = this.toBold(id);
					name = this.toBold(name);
					phoneNumber = this.toBold(phoneNumber);
					DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
					renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
					table.getColumnModel().getColumn(1).setCellRenderer(renderer);
				}
				this.model.addRow(new Object[] {id, name, phoneNumber});
			}
		case ReadAllProvidersByProductKO:
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
		this.productIdText.setText(t);
	}

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
		case Errors.NonexistentProduct:
			return String.format(Utils.NonexistentProduct, this.productIdText.getText());
		case Errors.InactiveProduct:
			return String.format(Utils.InactiveProduct, this.productIdText.getText());
		case Errors.UnexpectedError:
			return Utils.UnexpectedError;
		default:
			return Utils.NotConsideredResponse;
		}
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return this.productIdText.getText().isBlank();
	}

}
