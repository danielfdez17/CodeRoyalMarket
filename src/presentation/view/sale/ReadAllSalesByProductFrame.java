package presentation.view.sale;

import javax.swing.SwingUtilities;

import presentation.controller.view.Context;
import presentation.view.Frame;

public class ReadAllSalesByProductFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private static final String FromWhere = ReadAllSalesByProductFrame.class.getSimpleName();
	
	private static ReadAllSalesByProductFrame instance;
	
	public ReadAllSalesByProductFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllSalesByProductFrame getInstance() {
		if (instance == null) instance = new ReadAllSalesByProductFrame();
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
