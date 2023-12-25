package presentation.controller.commands.main;

import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class CommandMainGUI implements Command {

	@Override
	public Context execute(Object data) {
		return new Context(this.getId(), null);
	}

	@Override
	public Events getId() {
		return Events.MainGUI;
	}

}
