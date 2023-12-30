package utilities.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FieldPanel {

	private JPanel panel;
	private JTextField text;
	
	public FieldPanel(String fieldName) {
		this.initPanel(fieldName);
	}
	private void initPanel(String fieldName) {
		this.panel = new JPanel();
		Dimension dimension = new Dimension(100, 30);
		this.text = new JTextField(10);
		this.text.setPreferredSize(dimension);
		JLabel label = new JLabel(" " + fieldName + " ");
		this.panel.add(label);
		this.panel.add(text);
	}
	public JPanel getJPanel() {
		return this.panel;
	}
	public JTextField getJTextField() {
		return this.text;
	}
}
