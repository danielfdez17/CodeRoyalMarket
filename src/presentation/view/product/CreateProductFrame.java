package presentation.view.product;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import business.product.ProductTransfer;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;
import presentation.view.Frame;
import presentation.view.GUIMSG;
import utilities.Errors;
import utilities.Utils;
import utilities.gui.FieldPanel;

public class CreateProductFrame extends Frame {
	
	private static final long serialVersionUID = 1L;
	private static final String FromWhere = CreateProductFrame.class.getSimpleName();
	private static final int ROWS = 1, COLS = 3;
	
	private static CreateProductFrame instance;
	private JTextField nameText,
						priceText,
						warehouseIdText;
					   
	
	public CreateProductFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized CreateProductFrame getInstance() {
		if (instance == null) instance = new CreateProductFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.CreateProduct);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel createPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel namePanel = new FieldPanel(Utils.Name);
		this.nameText = namePanel.getJTextField();
		createPanel.add(namePanel.getJPanel());
		
		FieldPanel pricePanel = new FieldPanel(Utils.Price);
		this.priceText = pricePanel.getJTextField();
		createPanel.add(pricePanel.getJPanel());
		
		FieldPanel warehouseIdPanel = new FieldPanel(Utils.WarehouseId);
		this.warehouseIdText = warehouseIdPanel.getJTextField();
		createPanel.add(warehouseIdPanel.getJPanel());
		
		JButton createButton = new JButton(Utils.CreateClient);
		createButton.addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					String name = this.nameText.getText();
					int stock = 0;
					double price = Double.parseDouble(this.priceText.getText());
					int warehouseId = Integer.parseInt(this.warehouseIdText.getText());
					this.setVisible(false);
					this.clearData();
					Controller.getInstance().action(new Context(Events.CreateProduct, new ProductTransfer(name, stock, price, warehouseId)));
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
		case CreateProductOK:
			ProductTransfer product = (ProductTransfer)context.getData();
			GUIMSG.showMessage(Utils.ProductSuccessfullyCreated + product.toString(), FromWhere, false);
			break;
		case CreateProductKO:
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
		this.priceText.setText(t);
		this.warehouseIdText.setText(t);
	}

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
		case Errors.SyntaxError:
			return Utils.SyntaxError;
		case Errors.NonexistentWarehouse:
			return Utils.NonexistentWarehouse;
		case Errors.InactiveWarehouse:
			return Utils.InactiveWarehouse;
		case Errors.ActiveProduct:
			return Utils.ActiveProduct;
		case Errors.InactiveProduct:
			return "Inactive product, now is active";
		case Errors.UnexpectedError:
			return Utils.UnexpectedError;
		default:
			return Utils.NotConsideredResponse;
		}
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return this.nameText.getText().isBlank() &&
				this.priceText.getText().isBlank() &&
				this.warehouseIdText.getText().isBlank();
	}

}
