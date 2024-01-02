package presentation.view.provider;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import business.provider.ProviderTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.Frame;
import presentation.view.GUIMSG;
import utilities.Errors;
import utilities.Utils;
import utilities.gui.FieldPanel;

public class UpdateProviderFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private static final int ROWS = 3, COLS = 1;
	private static final String FromWhere = UpdateProviderFrame.class.getSimpleName();
	
	private static UpdateProviderFrame instance;
	private JTextField idText, nameText, phoneNumberText;
	private ProviderTransfer provider;
	
	public UpdateProviderFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized UpdateProviderFrame getInstance() {
		if (instance == null) instance = new UpdateProviderFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.UpdateProvider);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel updatePanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel idPanel = new FieldPanel(Utils.Id);
		this.idText = idPanel.getJTextField();
		updatePanel.add(idPanel.getJPanel());
		
		FieldPanel namePanel = new FieldPanel(Utils.Name);
		this.nameText = namePanel.getJTextField();
		updatePanel.add(namePanel.getJPanel());
		
		FieldPanel phoneNumberPanel = new FieldPanel(Utils.PhoneNumber);
		this.phoneNumberText = phoneNumberPanel.getJTextField();
		updatePanel.add(phoneNumberPanel.getJPanel());
		
		JButton searchButton = new JButton(Utils.ReadProvider);
		searchButton.addActionListener(l -> {
			if (!this.idText.getText().isBlank()) {
				try {
					int id = Integer.parseInt(this.idText.getText());
					Controller.getInstance().action(new Context(Events.ReadProvider, id));
				} catch (NumberFormatException nfe) {
					GUIMSG.showMessage(Utils.OnlyNumbersFields, FromWhere, true);
				}
			}
			else {
				GUIMSG.showMessage(Utils.SomeTextFieldsAreEmpty, FromWhere, true);
			}
		});
		
		JButton updateButton = new JButton(Utils.UpdateProvider);
		updateButton.addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					String name = this.nameText.getText();
					int phoneNumber = Integer.parseInt(this.phoneNumberText.getText());
					this.setVisible(false);
					this.clearData();
					Controller.getInstance().action(new Context(Events.UpdateProvider, new ProviderTransfer(name, phoneNumber)));
				} catch (NumberFormatException nfe) {
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
		
		JButton restoreButton = new JButton(Utils.RestoreValues);
		restoreButton.addActionListener(l -> {
			this.restoreData();
		});
		
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(searchButton);
		buttonsPanel.add(updateButton);
		buttonsPanel.add(emptyButton);
		buttonsPanel.add(restoreButton);
		
		mainPanel.add(updatePanel, BorderLayout.CENTER);
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
		case UpdateProviderOK:
			this.provider = (ProviderTransfer)context.getData();
			GUIMSG.showMessage(Utils.ProviderSuccessfullyUpdated + this.provider.toString(), FromWhere, false);
			break;
		case UpdateProviderKO:
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
		this.nameText.setText(t);
		this.phoneNumberText.setText(t);
	}
	
	private void restoreData() {
		if (this.provider != null) {
			this.idText.setText("" + this.provider.getId());
			this.nameText.setText(this.provider.getName());
			this.phoneNumberText.setText("" + this.provider.getPhoneNumber());
		}
	}

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
		case Errors.SyntaxError:
			return Utils.SyntaxError;
		case Errors.NonexistentProvider:
			return String.format(Utils.NonexistentProvider, this.idText.getText());
		case Errors.UnexpectedError:
			return Utils.UnexpectedError;
		default:
			return Utils.NotConsideredResponse;
		}
			
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return this.nameText.getText().isBlank() &&
				this.phoneNumberText.getText().isBlank();
	}

}
