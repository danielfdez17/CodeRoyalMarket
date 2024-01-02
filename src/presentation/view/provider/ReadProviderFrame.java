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

public class ReadProviderFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private static final int ROWS = 1, COLS = 1;
	private static final String FromWhere = ReadProviderFrame.class.getSimpleName();
	
	private static ReadProviderFrame instance;
	private JTextField idText;
	
	public ReadProviderFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadProviderFrame getInstance() {
		if (instance == null) instance = new ReadProviderFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.ReadProvider);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel readPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel namePanel = new FieldPanel(Utils.Name);
		this.idText = namePanel.getJTextField();
		readPanel.add(namePanel.getJPanel());
		
		
		JButton readButton = new JButton(Utils.ReadProvider);
		readButton.addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					int id = Integer.parseInt(this.idText.getText());
					this.setVisible(false);
					Controller.getInstance().action(new Context(Events.ReadProvider, id));
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
		buttonsPanel.add(readButton);
		buttonsPanel.add(emptyButton);
		
		mainPanel.add(readPanel, BorderLayout.CENTER);
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
		case ReadProviderOK:
			ProviderTransfer provider = (ProviderTransfer)context.getData();
			GUIMSG.showMessage(Utils.ExistentProvider + provider.toString(), FromWhere, rootPaneCheckingEnabled);
			this.clearData();
			break;
		case ReadProviderKO:
			int id = (int)context.getData();
			GUIMSG.showMessage(String.format(Utils.NonexistentProvider, id), FromWhere, rootPaneCheckingEnabled);
			this.clearData();
			break;
		default:
			GUIMSG.showMessage(Utils.NotConsideredResponse, FromWhere, rootPaneCheckingEnabled);
			break;
		}
	}

	@Override
	public void clearData() {
		String t = "";
		this.idText.setText(t);
	}

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
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
		return this.idText.getText().isBlank();
	}

}
