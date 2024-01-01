package presentation.view.provider;

import javax.swing.SwingUtilities;

import presentation.controller.view.Context;
import presentation.view.Frame;

public class ReadAllProvidersByProductFrame extends Frame {

	private static final long serialVersionUID = 1L;

	private static final String FromWhere = ReadAllProvidersByProductFrame.class.getSimpleName();
	
	private static ReadAllProvidersByProductFrame instance;
	
	public ReadAllProvidersByProductFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllProvidersByProductFrame getInstance() {
		if (instance == null) instance = new ReadAllProvidersByProductFrame();
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
