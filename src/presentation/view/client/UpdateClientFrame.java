package presentation.view.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JTextField;

import business.client.ClientTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.Frame;
import presentation.view.GUIMSG;
import utilities.Errors;
import utilities.Utils;
import utilities.gui.Button;
import utilities.gui.FieldPanel;

public class UpdateClientFrame extends Frame {
	
	private static final long serialVersionUID = 1L;
	private static final String FromWhere = "UpdateClientFrame";
	private static final int ROWS = 4, COLS = 1;
	
	private static UpdateClientFrame instance;
	
	private JTextField idText, nameText, nifText, balanceText;
	private ClientTransfer client;
	
	private UpdateClientFrame() {
		this.initPanel();
	}
	
	public static synchronized UpdateClientFrame getInstance() {
		if (instance == null) instance = new UpdateClientFrame();
		return instance;
	}
	
	private void initPanel() {
this.setTitle(Utils.UpdateClient);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel createPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel idPanel = new FieldPanel(Utils.Id);
		this.idText = idPanel.getJTextField();
		createPanel.add(idPanel.getJPanel());
		
		FieldPanel namePanel = new FieldPanel(Utils.Name);
		this.nameText = namePanel.getJTextField();
		createPanel.add(namePanel.getJPanel());
		
		FieldPanel nifPanel = new FieldPanel(Utils.Nif);
		this.nifText = nifPanel.getJTextField();
		createPanel.add(nifPanel.getJPanel());
		
		FieldPanel balancePanel = new FieldPanel(Utils.Balance);
		this.balanceText = balancePanel.getJTextField();
		createPanel.add(balancePanel.getJPanel());
		
		this.setEditable(false);
		
		Button findButton = new Button(Utils.ReadClient);
		findButton.getJButton().addActionListener(l -> {
			if (!this.idText.getText().isBlank()) {
				try {
					int id = Integer.parseInt(this.idText.getText());
					this.clearData();
					Controller.getInstance().action(new Context(Events.ReadClientForUpdate, id));
				} catch(NumberFormatException nfe) {
					GUIMSG.showMessage(Utils.OnlyNumbersFields, FromWhere, true);
				}
			}
			else {
				GUIMSG.showMessage(Utils.SomeTextFieldsAreEmpty, FromWhere, true);
			}
		});
		
		Button createButton = new Button(Utils.CreateClient);
		createButton.getJButton().addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					int id = Integer.parseInt(this.idText.getText());
					String name = this.nameText.getText(),
							nif = this.nifText.getText();
					double balance = Double.parseDouble(this.balanceText.getText());
					boolean active = this.client.isActive();
					this.setVisible(false);
					this.clearData();
					Controller.getInstance().action(new Context(Events.UpdateClient, new ClientTransfer(id, nif, name, balance, active)));
				} catch(NumberFormatException nfe) {
					GUIMSG.showMessage(Utils.OnlyNumbersFields, FromWhere, true);
				}
			}
			else {
				GUIMSG.showMessage(Utils.SomeTextFieldsAreEmpty, FromWhere, true);
			}
		});
		Button emptyButton = new Button(Utils.EmptyTextFields);
		emptyButton.getJButton().addActionListener(l -> {
			this.clearData();
		});
		
		Button restoreButton = new Button(Utils.RestoreValues);
		restoreButton.getJButton().addActionListener(l -> {
			this.restoreData();
		});
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(createButton.getJButton());
		buttonsPanel.add(restoreButton.getJButton());
		buttonsPanel.add(emptyButton.getJButton());
		
		mainPanel.add(createPanel, BorderLayout.CENTER);
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
		case UpdateClientOK:
			client = (ClientTransfer)context.getData();
			GUIMSG.showMessage(Utils.ClientSuccessfullyUpdated + client.toString(), FromWhere, false);
			client = null;
			break;
		case UpdateClientKO:
			GUIMSG.showMessage(this.getErrorMsg((int)context.getData()), FromWhere, true);
			this.idText.setEditable(true);
			this.setEditable(false);
			break;
		case ReadClientForUpdateOK:
			client = (ClientTransfer)context.getData();
			GUIMSG.showMessage(Utils.ExistentClient + client.toString(), FromWhere, true);
			this.restoreData();
			this.idText.setEditable(false);
			this.setEditable(true);
			break;
		case ReadClientForUpdateKO:
			GUIMSG.showMessage(String.format(Utils.NonexistentClient, (int)context.getData()), FromWhere, true);
			this.idText.setEditable(true);
			this.setEditable(false);
			client = null;
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
		this.nifText.setText(t);
		this.balanceText.setText(t);
	}

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
		case Errors.SyntaxError:
			return Utils.SyntaxError;
		case Errors.NonexistentClient:
			return Utils.NonexistentClient;
		case Errors.UnexpectedError:
			return Utils.UnexpectedError;
		default:
			return Utils.NotConsideredResponse;
		}
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return this.nameText.getText().isBlank() &&
				this.nifText.getText().isBlank() &&
				this.balanceText.getText().isBlank();
	}
	
//	public void setClient(ClientTransfer client) {
//		this.client = client;
//	}
	
	private void restoreData() {
		this.idText.setText("" + client.getId());
		this.nameText.setText(client.getName());
		this.nifText.setText(client.getNif());
		this.balanceText.setText("" + client.getBalance());
	}
	
	private void setEditable(boolean b) {
		this.nameText.setEditable(b);
		this.nifText.setEditable(b);
		this.balanceText.setEditable(b);
	}

}
