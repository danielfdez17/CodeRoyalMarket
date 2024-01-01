package presentation.view.worker;

import javax.swing.SwingUtilities;

import presentation.controller.view.Context;
import presentation.view.Frame;

public class DeleteWorkerFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private static final String FromWhere = DeleteWorkerFrame.class.getSimpleName();
	
	private static DeleteWorkerFrame instance;
	
	public DeleteWorkerFrame() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public static synchronized DeleteWorkerFrame getInstance() {
		if (instance == null) instance = new DeleteWorkerFrame();
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
