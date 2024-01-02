package presentation.controller.commands.client;

import business.businessFactory.BusinessFactory;
import business.client.ClientTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class UpdateClientCommand implements Command {

	@Override
	public Context execute(Object data) {
		ClientTransfer client = (ClientTransfer)data;
		int res = BusinessFactory.getInstance().createClientAS().updateClient(client);
		if (res > 0) return new Context(Events.UpdateClientOK, client);
		return new Context(Events.UpdateClientKO, res);
	}

	@Override
	public Events getId() {
		return Events.UpdateClient;
	}

}
