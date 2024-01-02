package presentation.controller.commands.client;

import business.businessFactory.BusinessFactory;
import business.client.ClientTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadClientForUpdateCommand implements Command {

	@Override
	public Context execute(Object data) {
		int id = (int)data;
		ClientTransfer res = BusinessFactory.getInstance().createClientAS().readClient(id);
		if (res != null) return new Context(Events.ReadClientForUpdateOK, res);
		return new Context(Events.ReadClientForUpdateKO, id);
	}

	@Override
	public Events getId() {
		return Events.ReadClientForUpdate;
	}

}
