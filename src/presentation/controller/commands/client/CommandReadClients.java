package presentation.controller.commands.client;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.client.ClientTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class CommandReadClients implements Command {

	@Override
	public Context execute(Object data) {
		List<ClientTransfer> res = BusinessFactory.getInstance().createClientAS().readAllClients();
		if (res.isEmpty()) return new Context(Events.ReadAllClientsKO, res);
		return new Context(Events.ReadAllClientsOK, res);
	}

	@Override
	public Events getId() {
		return Events.ReadAllClients;
	}

}
