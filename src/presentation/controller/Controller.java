package presentation.controller;

import presentation.controller.view.Context;

public abstract class Controller {
	
	private static Controller instance;
	
	public synchronized static Controller getInstance() {
		if (instance == null) instance = new ControllerImp();
		return instance;
	}
	
	public abstract void action(Context context);

}
