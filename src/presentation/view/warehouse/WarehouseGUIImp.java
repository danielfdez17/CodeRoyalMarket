package presentation.view.warehouse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utilities.Utils;

public class WarehouseGUIImp extends WarehouseGUI {

	private static final long serialVersionUID = 1L;
	private static final int ROWS = 5, COLS = 1;
	
	public WarehouseGUIImp() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setTitle(Utils.WarehousesTitle);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel buttonsPanel = new JPanel(new GridLayout(ROWS, COLS));
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);
		
		JButton createButton = new JButton(Utils.CreateWarehouse);
		createButton.addActionListener(l -> {
			CreateWarehouseFrame.getInstance().setVisible(true);
		});
		JButton readButton = new JButton(Utils.ReadWarehouse);
		readButton.addActionListener(l -> {
			ReadWarehouseFrame.getInstance().setVisible(true);
		});
		JButton readAllButtons = new JButton(Utils.ReadAllWarehouses);
		readAllButtons.addActionListener(l -> {
			ReadAllWarehousesFrame.getInstance().setVisible(true);
		});
		JButton updateButton = new JButton(Utils.UpdateWarehouse);
		updateButton.addActionListener(l -> {
			UpdateWarehouseFrame.getInstance().setVisible(true);
		});
		JButton deleteButton = new JButton(Utils.DeleteWarehouse);
		deleteButton.addActionListener(l -> {
			DeleteWarehouseFrame.getInstance().setVisible(true);
		});
		
		buttonsPanel.add(createButton);
		buttonsPanel.add(readButton);
		buttonsPanel.add(readAllButtons);
		buttonsPanel.add(updateButton);
		buttonsPanel.add(deleteButton);
		
		Dimension dims_frame = this.getContentPane().getSize();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = new Dimension((int) dims_frame.getWidth(), (int) dims_frame.getHeight());
		
		this.setPreferredSize(new Dimension(400, 400));
		this.setLocation(dim.width / 2 - frame.getSize().width / 2 - 200, dim.height / 2 - frame.getSize().height / 2 - 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(false);
	}

}
