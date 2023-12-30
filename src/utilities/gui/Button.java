package utilities.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Button extends JButton {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private JButton button;
	
	public Button(String name) {
		this.name = name;
		this.initButton();
	}
	
	private void initButton() {
		this.button = new JButton(this.name);
		
		this.button.setBackground(Color.BLACK);
		this.button.setForeground(Color.WHITE);
		
		this.button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		this.button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		
	}
	
	public JButton getJButton() {
		return button;
	}
	
}
