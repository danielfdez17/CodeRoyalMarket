package presentation.controller.commands.client;

import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ClientsGUICommand implements Command {

	@Override
	public Context execute(Object data) {
		return new Context(this.getId(), null);
	}

	@Override
	public Events getId() {
		return Events.ClientsGUI;
	}

}
