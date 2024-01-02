package utilities.gui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import presentation.view.Frame;

public abstract class ReadAllFrame extends Frame {

	private static final long serialVersionUID = 1L;
	protected JTable table;
	protected DefaultTableModel model;
	@Override
	public void clearData() {}
	@Override
	public String getErrorMsg(int error) {
		return "";
	}
	@Override
	public boolean areTextFieldsEmpty() {
		return false;
	}

	
}
