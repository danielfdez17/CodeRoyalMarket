package presentation.view.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.Frame;
import presentation.view.GUIMSG;
import utilities.Errors;
import utilities.Utils;
import utilities.gui.Button;
import utilities.gui.FieldPanel;

public class DeleteClientFrame extends Frame {
	
	private static final long serialVersionUID = 1L;
	private static final String FromWhere = "DeleteClientFrame";
	private static final int ROWS = 3, COLS = 1;
	
	private static DeleteClientFrame instance;
	
	private JTextField idText;
	
	private DeleteClientFrame() {
		this.initPanel();
	}
	
	public static synchronized DeleteClientFrame getInstance() {
		if (instance == null) instance = new DeleteClientFrame();
		return instance;
	}
	
	private void initPanel() {
		this.setTitle(Utils.DeleteClient);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel deletePanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel idPanel = new FieldPanel(Utils.Id);
		this.idText = idPanel.getJTextField();
		deletePanel.add(idPanel.getJPanel());
		
		Button deleteButton = new Button(Utils.DeleteClient);
		deleteButton.getJButton().addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					int id = Integer.parseInt(this.idText.getText());
					this.clearData();
					this.setVisible(false);
					Controller.getInstance().action(new Context(Events.DeleteClient, id));
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
		buttonsPanel.add(deleteButton.getJButton());
		buttonsPanel.add(emptyButton.getJButton());
		
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
		case DeleteClientOK:
			GUIMSG.showMessage(Utils.ClientSuccessfullyDeactivated, FromWhere, false);
			break;
		case DeleteClientKO:
			GUIMSG.showMessage(this.getErrorMsg((int)context.getData()), FromWhere, true);
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
		switch (error) {
		case Errors.SyntaxError:
			return Utils.SyntaxError;
		case Errors.NonexistentClient:
			return Utils.NonexistentClient;
		case Errors.InactiveClient:
			return "The client is already inactive";
		case Errors.UnexpectedError:
			return Utils.UnexpectedError;
		default:
			return Utils.NotConsideredResponse;
		}
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return this.idText.getText().isBlank();
	}

}
