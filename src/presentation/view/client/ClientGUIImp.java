package presentation.view.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utilities.Utils;

public class ClientGUIImp extends ClientGUI {
	
	private static final long serialVersionUID = 1L;
	private static final int ROWS = 5, COLS = 1;
	
	public ClientGUIImp() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}

	private void initGUI() {
		this.setTitle(Utils.ClientsTitle);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		JButton createButton = new JButton(Utils.CreateClient);
		createButton.addActionListener(l -> {
			CreateClientFrame.getInstance().setVisible(true);
		});
		JButton readButton = new JButton(Utils.ReadClient);
		readButton.addActionListener(l -> {
			ReadClientFrame.getInstance().setVisible(true);
		});
		JButton readAllButton = new JButton(Utils.ReadAllClients);
		readAllButton.addActionListener(l -> {
			ReadAllClientsFrame.getInstance().setVisible(true);
		});
		JButton updateButton = new JButton(Utils.UpdateClient);
		updateButton.addActionListener(l -> {
			UpdateClientFrame.getInstance().setVisible(true);
		});
		JButton deleteButton = new JButton(Utils.DeleteClient);
		deleteButton.addActionListener(l -> {
			DeleteClientFrame.getInstance().setVisible(true);
		});
		
		buttonsPanel.add(createButton);
		buttonsPanel.add(updateButton);
		buttonsPanel.add(readButton);
		buttonsPanel.add(readAllButton);
		buttonsPanel.add(deleteButton);
		
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);
		
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
