package presentation.view.product;

import javax.swing.SwingUtilities;

import presentation.controller.view.Context;
import presentation.view.Frame;

public class ReadAllProductsFrame extends Frame {
	
	private static final String FromWhere = ReadAllProductsFrame.class.getSimpleName();
	
	private static ReadAllProductsFrame instance;
	
	public ReadAllProductsFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllProductsFrame getInstance() {
		if (instance == null) instance = new ReadAllProductsFrame();
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
