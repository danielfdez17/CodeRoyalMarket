package presentation.view.worker;

import javax.swing.SwingUtilities;

import presentation.controller.view.Context;
import presentation.view.Frame;

public class ReadAllWorkersByWarehouseFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private static final String FromWhere = ReadAllWorkersByWarehouseFrame.class.getSimpleName();
	
	private static ReadAllWorkersByWarehouseFrame instance;
	
	public ReadAllWorkersByWarehouseFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized ReadAllWorkersByWarehouseFrame getInstance() {
		if (instance == null) instance = new ReadAllWorkersByWarehouseFrame();
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
