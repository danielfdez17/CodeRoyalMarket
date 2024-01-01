package presentation.view.worker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utilities.Utils;

public class WorkersGUIImp extends WorkersGUI {

	private static final long serialVersionUID = 1L;
	
	private static final int ROWS = 8, COLS = 2;
	
	public WorkersGUIImp() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setTitle(Utils.WorkersTitle);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);

		JPanel buttonsPanel = new JPanel(new GridLayout(ROWS, COLS));
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);
		
		JButton createFullTimeButton = new JButton(Utils.CreateFullTimeWorker);
		createFullTimeButton.addActionListener(l -> {
			CreateFullTimeWorkerFrame.getInstance().setVisible(true);
		});
		JButton createPartTimeButton = new JButton(Utils.CreatePartTimeWorker);
		createPartTimeButton.addActionListener(l -> {
			CreatePartTimeWorkerFrame.getInstance().setVisible(true);
		});
		JButton readButton = new JButton(Utils.ReadWorker);
		readButton.addActionListener(l -> {
			ReadWorkerFrame.getInstance().setVisible(true);
		});
		JButton readAllButton = new JButton(Utils.ReadAllWorkers);
		readAllButton.addActionListener(l -> {
			ReadAllWorkersFrame.getInstance().setVisible(true);
		});
		JButton readAllByWarehouseButton = new JButton(Utils.ReadAllWorkersByWarehouse);
		readAllByWarehouseButton.addActionListener(l -> {
			ReadAllWorkersByWarehouseFrame.getInstance().setVisible(true);
		});
		JButton updateFullTimeButton = new JButton(Utils.UpdateFullTimeWorker);
		updateFullTimeButton.addActionListener(l -> {
			UpdateFullTimeWorkerFrame.getInstance().setVisible(true);
		});
		JButton updatePartTimeButton = new JButton(Utils.UpdatePartTimeWorker);
		updatePartTimeButton.addActionListener(l -> {
			UpdatePartTimeWorkerFrame.getInstance().setVisible(true);
		});
		JButton deleteButton = new JButton(Utils.DeleteWorker);
		deleteButton.addActionListener(l -> {
			DeleteWorkerFrame.getInstance().setVisible(true);
		});
		
		buttonsPanel.add(createFullTimeButton);
		buttonsPanel.add(createPartTimeButton);
		buttonsPanel.add(readButton);
		buttonsPanel.add(readAllButton);
		buttonsPanel.add(readAllByWarehouseButton);
		buttonsPanel.add(updateFullTimeButton);
		buttonsPanel.add(updatePartTimeButton);
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
