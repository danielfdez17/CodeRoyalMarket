package presentation.controller;

import presentation.controller.commands.Command;
import presentation.controller.commands.CommandFactory;
import presentation.controller.view.Context;
import presentation.controller.view.ViewFactory;

public class ControllerImp extends Controller{

	@Override
	public void action(Context context) {
		Command command = CommandFactory.getInstance().createCommand(context.getEvent());
		if (command != null) {
			ViewFactory.getInstance().createView(command.execute(context.getData()));
		}
	}

}
