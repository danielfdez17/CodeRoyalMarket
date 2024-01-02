package presentation.controller.commands.client;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.client.ClientTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadAllClientsCommand implements Command {

	@Override
	public Context execute(Object data) {
		List<ClientTransfer> res = BusinessFactory.getInstance().createClientAS().readAllClients();
		return new Context(Events.ReadAllClients, res);
	}

	@Override
	public Events getId() {
		return Events.ReadAllClients;
	}

}
