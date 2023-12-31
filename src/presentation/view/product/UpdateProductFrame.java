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

public class UpdateProductFrame extends Frame {
	
	private static final long serialVersionUID = 1L;
	private static final int ROWS = 1, COLS = 4;
	private static final String FromWhere = UpdateProductFrame.class.getSimpleName();
	
	private static UpdateProductFrame instance;
	private JTextField idText,
						nameText,
						priceText,
						warehouseIdText;
	private ProductTransfer product;
	
	public UpdateProductFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized UpdateProductFrame getInstance() {
		if (instance == null) instance = new UpdateProductFrame();
		return instance;
	}
	
	private void initGUI() {
		this.setTitle(Utils.UpdateProduct);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel updatePanel = new JPanel(new GridLayout(ROWS, COLS));
		
		FieldPanel idPanel = new FieldPanel(Utils.Id);
		this.idText = idPanel.getJTextField();
		updatePanel.add(idPanel.getJPanel());
		
		FieldPanel namePanel = new FieldPanel(Utils.Name);
		this.nameText = namePanel.getJTextField();
		updatePanel.add(namePanel.getJPanel());
		
		FieldPanel pricePanel = new FieldPanel(Utils.Price);
		this.priceText = pricePanel.getJTextField();
		updatePanel.add(pricePanel.getJPanel());
		
		FieldPanel warehouseIdPanel = new FieldPanel(Utils.WarehouseId);
		this.warehouseIdText = warehouseIdPanel.getJTextField();
		updatePanel.add(warehouseIdPanel.getJPanel());
		
		JButton searchButton = new JButton(Utils.ReadProduct);
		searchButton.addActionListener(l -> {
			if (!this.idText.getText().isBlank()) {
				try {
					int id = Integer.parseInt(this.idText.getText());
					Controller.getInstance().action(new Context(Events.ReadProductForUpdate, id));
				} catch(NumberFormatException nfe) {
					GUIMSG.showMessage(Utils.OnlyNumbersFields, FromWhere, true);
				}
			}
			else {
				GUIMSG.showMessage(Utils.SomeTextFieldsAreEmpty, FromWhere, true);
			}
		});
		
		JButton updateButton = new JButton(Utils.UpdateProduct);
		updateButton.addActionListener(l -> {
			if (!this.areTextFieldsEmpty()) {
				try {
					int id = Integer.parseInt(this.idText.getText());
					String name = this.nameText.getText();
					int stock = 0;
					double price = Double.parseDouble(this.priceText.getText());
					int warehouseId = Integer.parseInt(this.warehouseIdText.getText());
					this.setVisible(false);
					boolean active = true;
					this.clearData();
					Controller.getInstance().action(new Context(Events.UpdateProduct, new ProductTransfer(id, name, stock, price, warehouseId, active)));
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
		case UpdateProductOK:
			this.product = (ProductTransfer)context.getData();
			GUIMSG.showMessage(Utils.ProductSuccessfullyUpdated + this.product.toString(), FromWhere, false);
			break;
		case UpdateProductKO:
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
	
	private void restoreData() {
		if (this.product != null) {
			this.idText.setText("" + this.product.getId());
			this.nameText.setText(this.product.getName());
			this.priceText.setText("" + this.product.getPrice());
			this.warehouseIdText.setText("" + this.product.getWarehouseId());
		}
	}

	@Override
	public String getErrorMsg(int error) {
		switch (error) {
		case Errors.SyntaxError:
			return Utils.SyntaxError;
		case Errors.NonexistentWarehouse:
			return String.format(Utils.NonexistentWarehouse, this.product.getWarehouseId());
		case Errors.InactiveWarehouse:
			return String.format(Utils.InactiveWarehouse, this.product.getWarehouseId());
		case Errors.NonexistentProduct:
			return String.format(Utils.NonexistentProduct, this.product.getId());
		case Errors.UnexpectedError:
			return Utils.UnexpectedError;
		default:
			return Utils.NotConsideredResponse;
		}
	}

	@Override
	public boolean areTextFieldsEmpty() {
		return this.idText.getText().isBlank() && 
				this.nameText.getText().isBlank() &&
				this.priceText.getText().isBlank() &&
				this.warehouseIdText.getText().isBlank();
	}

}
