package presentation.controller.commands.client;

import business.businessFactory.BusinessFactory;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class DeleteClientCommand implements Command {

	@Override
	public Context execute(Object data) {
		int id = (int)data;
		int res = BusinessFactory.getInstance().createClientAS().deleteClient(id);
		if (res > 0) return new Context(Events.DeleteClientOK, res);
		return new Context(Events.DeleteClientKO, res);
	}

	@Override
	public Events getId() {
		return Events.DeleteClient;
	}

}
