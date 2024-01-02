package presentation.view.provider;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import business.providerProduct.ProviderProductTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.Frame;
import presentation.view.GUIMSG;
import utilities.Errors;
import utilities.Utils;
import utilities.gui.FieldPanel;

public class ProvideProductFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private static final int ROWS = 3, COLS = 1;
	private static final String FromWhere = ProvideProductFrame.class.getSimpleName();
	
	private static ProvideProductFrame instance;
	private JTextField providerIdText, productIdText, amountText;
	
	public ProvideProductFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ProvideProductFrame getInstance() {
		if (instance == null) instance = new ProvideProductFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.ProvideProduct);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel provideProductPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel providerIdPanel = new FieldPanel(Utils.ProviderId);
		this.providerIdText = providerIdPanel.getJTextField();
		provideProductPanel.add(providerIdPanel.getJPanel());
		
		FieldPanel productIdPanel = new FieldPanel(Utils.ProductId);
		this.productIdText = productIdPanel.getJTextField();
		provideProductPanel.add(productIdPanel.getJPanel());
		
		FieldPanel amountPanel = new FieldPanel(Utils.Amount);
		this.amountText = amountPanel.getJTextField();
		provideProductPanel.add(amountPanel.getJPanel());
		
		JButton provideProductButton = new JButton(Utils.ProvideProduct);
		provideProductButton.addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					int providerId = Integer.parseInt(this.providerIdText.getText()),
							productId = Integer.parseInt(this.productIdText.getText()), 
							amount = Integer.parseInt(this.amountText.getText());
					this.setVisible(false);
					this.clearData();
					Controller.getInstance().action(new Context(Events.ProvideProduct, new ProviderProductTransfer(providerId, productId, amount)));
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
		buttonsPanel.add(provideProductButton);
		buttonsPanel.add(emptyButton);
		
		mainPanel.add(provideProductPanel, BorderLayout.CENTER);
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
		case ProvideProductOK:
			ProviderProductTransfer pp = (ProviderProductTransfer)context.getData();
			GUIMSG.showMessage(Utils.ProductSuccessfullyProvided + pp.toString(), FromWhere, false);
			break;
		case ProvideProductKO:
			GUIMSG.showMessage(this.getErrorMsg((int)context.getData()), FromWhere, rootPaneCheckingEnabled);
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
		this.providerIdText.setText(t);
		this.amountText.setText(t);
	}

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
		case Errors.NonexistentProvider:
			return String.format(Utils.NonexistentProvider, this.providerIdText.getText());
		case Errors.InactiveProvider:
			return String.format(Utils.InactiveProvider, this.providerIdText.getText());
		case Errors.NonexistentProduct:
			return String.format(Utils.NonexistentProduct, this.productIdText.getText());
		case Errors.InactiveProduct:
			return String.format(Utils.InactiveProduct, this.productIdText.getText());
		case Errors.ProductAlreadyUnassigned:
			return String.format(Utils.ProductAlreadyUnassigned, this.providerIdText.getText(), this.productIdText.getText());
		case Errors.UnexpectedError:
			return Utils.UnexpectedError;
		default:
			return Utils.NotConsideredResponse;
		}
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return this.productIdText.getText().isBlank() &&
				this.providerIdText.getText().isBlank() &&
				this.amountText.getText().isBlank();
	}

}
