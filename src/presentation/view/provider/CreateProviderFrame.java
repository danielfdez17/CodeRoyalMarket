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

public class CreateProviderFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private static final int ROWS = 2, COLS = 1;
	private static final String FromWhere = CreateProviderFrame.class.getSimpleName();
	
	private static CreateProviderFrame instance;
	private JTextField nameText, phoneNumberText;
	
	public CreateProviderFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized CreateProviderFrame getInstance() {
		if (instance == null) instance = new CreateProviderFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.CreateProvider);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel createPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel namePanel = new FieldPanel(Utils.Name);
		this.nameText = namePanel.getJTextField();
		createPanel.add(namePanel.getJPanel());
		
		FieldPanel phoneNumberPanel = new FieldPanel(Utils.PhoneNumber);
		this.phoneNumberText = phoneNumberPanel.getJTextField();
		createPanel.add(phoneNumberPanel.getJPanel());
		
		JButton createButton = new JButton(Utils.CreateProvider);
		createButton.addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					String name = this.nameText.getText();
					int phoneNumber = Integer.parseInt(this.phoneNumberText.getText());
					this.setVisible(false);
					this.clearData();
					Controller.getInstance().action(new Context(Events.CreateProvider, new ProviderTransfer(name, phoneNumber)));
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
		
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(createButton);
		buttonsPanel.add(emptyButton);
		
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
		case CreateProviderOK:
			ProviderTransfer provider = (ProviderTransfer)context.getData();
			GUIMSG.showMessage(Utils.ProviderSuccessfullyCreated + provider.toString(), FromWhere, false);
			break;
		case CreateProviderKO:
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

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
		case Errors.SyntaxError:
			return Utils.SyntaxError;
		case Errors.ActiveProvider:
			return Utils.ActiveProvider;
		case Errors.InactiveProvider:
			return Utils.InactiveProvider + ", now is active";
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
