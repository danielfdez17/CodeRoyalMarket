package presentation.view.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import business.client.ClientTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.Frame;
import presentation.view.GUIMSG;
import utilities.Utils;
import utilities.gui.FieldPanel;

public class ReadClientFrame extends Frame {
	
	private static final long serialVersionUID = 1L;
	private static final String FromWhere = ReadClientFrame.class.getSimpleName();
	private static final int ROWS = 1, COLS = 1;
	
	private static ReadClientFrame instance;
	
	private JTextField idText;
	
	public ReadClientFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadClientFrame getInstance() {
		if (instance == null) instance = new ReadClientFrame();
		return instance;
	}

	private void initGUI() {
		this.setTitle(Utils.ReadClient);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel deletePanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel idPanel = new FieldPanel(Utils.Id);
		this.idText = idPanel.getJTextField();
		deletePanel.add(idPanel.getJPanel());
		
		JButton readButton = new JButton(Utils.DeleteClient);
		readButton.addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					int id = Integer.parseInt(this.idText.getText());
					this.clearData();
					this.setVisible(false);
					Controller.getInstance().action(new Context(Events.ReadClient, id));
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
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(readButton);
		buttonsPanel.add(emptyButton);
		
		mainPanel.add(deletePanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);
		
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
		case ReadClientOK:
			ClientTransfer client = (ClientTransfer)context.getData();
			GUIMSG.showMessage(Utils.ExistentClient + client.toString(), FromWhere, false);
			break;
		case ReadClientKO:
			break;
		default:
			GUIMSG.showMessage(Utils.NotConsideredResponse, FromWhere, true);
			break;
		}
	}

	@Override
	public void clearData() {
		this.idText.setText("");
	}

	@Override
	public String getErrorMsg(int error) {
		return "";
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return this.idText.getText().isBlank();
	}

}
