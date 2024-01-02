package presentation.controller.commands.client;

import business.businessFactory.BusinessFactory;
import business.client.ClientTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class CreateClientCommand implements Command {

	@Override
	public Context execute(Object data) {
		ClientTransfer client = (ClientTransfer)data;
		int res = BusinessFactory.getInstance().createClientAS().createClient(client);
		if (res > 0) return new Context(Events.CreateClientOK, client);
		return new Context(Events.CreateClientKO, res);
	}

	@Override
	public Events getId() {
		return Events.CreateClient;
	}

}
