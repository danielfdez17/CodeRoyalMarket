package presentation.gui.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.controller.view.Context;
import presentation.gui.GUIMSG;
import presentation.gui.Panel;
import utilities.Utils;
import utilities.gui.FieldPanel;

public class CreateClientPanel extends Panel {

	private static final long serialVersionUID = 1L;
	private static final String FromWhere = "CreateClientPanel";
	
	private static CreateClientPanel instance;
	
	private JTextField nameText, nifText, balanceText;
	
	public CreateClientPanel() {
		this.initPanel();
	}
	
	public synchronized static CreateClientPanel getInstance() {
		if (instance == null) instance = new CreateClientPanel();
		return instance;
	}
	
	private void initPanel() {
		this.setTitle(Utils.CreateClient);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		FieldPanel namePanel = new FieldPanel(Utils.Name);
		FieldPanel nifPanel = new FieldPanel(Utils.Nif);
		FieldPanel balancePanel = new FieldPanel(Utils.Balance);
	}

	@Override
	public void update(Context context) {
		switch (context.getEvent()) {
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
