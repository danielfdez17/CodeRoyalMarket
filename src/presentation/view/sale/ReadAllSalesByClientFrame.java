package presentation.view.sale;

import javax.swing.SwingUtilities;

import presentation.controller.view.Context;
import presentation.view.Frame;

public class ReadAllSalesByClientFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private static final String FromWhere = ReadAllSalesByClientFrame.class.getSimpleName();
	
	private static ReadAllSalesByClientFrame instance;
	
	public ReadAllSalesByClientFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllSalesByClientFrame getInstance() {
		if (instance == null) instance = new ReadAllSalesByClientFrame();
		return instance;
	}
	
	private void initGUI() {
		
	}

	@Override
	public void update(Context context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearData() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getErrorMsg(int error) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean areTextFieldsEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
