package presentation.view.provider;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utilities.Utils;

public class ProvidersGUIImp extends ProvidersGUI {

	private static final long serialVersionUID = 1L;
	private static final int ROWS = 5, COLS = 2;
	
	public ProvidersGUIImp() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setTitle(Utils.ProvidersTitle);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(ROWS, COLS));
		
		JButton createButton = new JButton(Utils.CreateProvider);
		createButton.addActionListener(l -> {
			CreateProviderFrame.getInstance().setVisible(true);
		});
		JButton readButton = new JButton(Utils.ReadProvider);
		readButton.addActionListener(l -> {
			ReadProviderFrame.getInstance().setVisible(true);
		});
		JButton readAllButton = new JButton(Utils.ReadAllProviders);
		readAllButton.addActionListener(l -> {
			ReadAllProvidersFrame.getInstance().setVisible(true);
		});
		JButton readAllByProviderButton = new JButton(Utils.ReadAllProvidersByProduct);
		readAllByProviderButton.addActionListener(l -> {
			ReadAllProvidersByProductFrame.getInstance().setVisible(true);
		});
		JButton assignProductButton = new JButton(Utils.AssignProduct);
		assignProductButton.addActionListener(l -> {
			AssignProductFrame.getInstance().setVisible(true);
		});
		JButton unassignProductButton = new JButton(Utils.UnassignProduct);
		unassignProductButton.addActionListener(l -> {
			UnassignProductFrame.getInstance().setVisible(true);
		});
		JButton provideProductButton = new JButton(Utils.ProvideProduct);
		provideProductButton.addActionListener(l -> {
			ProvideProductFrame.getInstance().setVisible(true);
		});
		JButton updateButton = new JButton(Utils.UpdateProvider);
		updateButton.addActionListener(l -> {
			UpdateProviderFrame.getInstance().setVisible(true);
		});
		JButton deleteButton = new JButton(Utils.DeleteProvider);
		deleteButton.addActionListener(l -> {
			DeleteProviderFrame.getInstance().setVisible(true);
		});
		
		buttonsPanel.add(createButton);
		buttonsPanel.add(readButton);
		buttonsPanel.add(readAllButton);
		buttonsPanel.add(readAllByProviderButton);
		buttonsPanel.add(assignProductButton);
		buttonsPanel.add(unassignProductButton);
		buttonsPanel.add(provideProductButton);
		buttonsPanel.add(updateButton);
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
