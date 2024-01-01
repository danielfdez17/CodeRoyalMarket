package presentation.view.product;

import javax.swing.SwingUtilities;

import presentation.controller.view.Context;
import presentation.view.Frame;

public class ReadAllProductsBySaleFrame extends Frame {
	
	private static final String FromWhere = ReadAllProductsBySaleFrame.class.getSimpleName();
	
	private static ReadAllProductsBySaleFrame instance;
	
	public ReadAllProductsBySaleFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllProductsBySaleFrame getInstance() {
		if (instance == null) instance = new ReadAllProductsBySaleFrame();
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
