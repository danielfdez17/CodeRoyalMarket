package presentation.gui;

import javax.swing.JFrame;
import presentation.controller.view.Context;

public abstract class Panel extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public abstract void update(Context context);

	public abstract void clearData();

	public abstract String getErrorMsg(int error);

	public abstract boolean areTextFieldsEmpty();
}