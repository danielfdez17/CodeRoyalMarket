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

public class CreateClientFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private static final String FromWhere = "CreateClientFrame";
	private static final int ROWS = 3, COLS = 1;
	
	private static CreateClientFrame instance;
	
	private JTextField nameText, nifText, balanceText;
	
	private CreateClientFrame() {
		this.initGUI();
	}
	
	public synchronized static CreateClientFrame getInstance() {
		if (instance == null) instance = new CreateClientFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.CreateClient);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel createPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel namePanel = new FieldPanel(Utils.Name);
		this.nameText = namePanel.getJTextField();
		createPanel.add(namePanel.getJPanel());
		
		FieldPanel nifPanel = new FieldPanel(Utils.Nif);
		this.nifText = nifPanel.getJTextField();
		createPanel.add(nifPanel.getJPanel());
		
		FieldPanel balancePanel = new FieldPanel(Utils.Balance);
		this.balanceText = balancePanel.getJTextField();
		createPanel.add(balancePanel.getJPanel());
		
		Button createButton = new Button(Utils.CreateClient);
		createButton.getJButton().addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					String name = this.nameText.getText(),
							nif = this.nifText.getText();
					double balance = Double.parseDouble(this.balanceText.getText());
					this.setVisible(false);
					this.clearData();
					Controller.getInstance().action(new Context(Events.CreateClient, new ClientTransfer(nif, name, balance)));
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
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(createButton.getJButton());
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
		case CreateClientOK:
			ClientTransfer client = (ClientTransfer)context.getData();
			GUIMSG.showMessage(Utils.ClientSuccessfullyCreated + client.toString(), FromWhere, false);
			Controller.getInstance().action(new Context(Events.ReadClients, null));
			break;
		case CreateClientKO:
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
		this.nifText.setText(t);
		this.balanceText.setText(t);
	}

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
		case Errors.SyntaxError:
			return Utils.SyntaxError;
		case Errors.ActiveClient:
			return "The client is already active";
		case Errors.InactiveClient:
			return "Inactive client, now client is active";
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

}
