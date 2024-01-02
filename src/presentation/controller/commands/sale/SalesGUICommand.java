package presentation.controller.commands.sale;

import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class SalesGUICommand implements Command {

	@Override
	public Context execute(Object data) {
		return new Context(this.getId(), null);
	}

	@Override
	public Events getId() {
		return Events.SalesGUI;
	}

}
