package presentation.controller.commands.provider;

import business.businessFactory.BusinessFactory;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class DeleteProviderCommand implements Command {

	@Override
	public Context execute(Object data) {
		int providerId = (int)data;
		int res = BusinessFactory.getInstance().createProviderAS().deleteProvider(providerId);
		if (res > 0) return new Context(Events.DeleteProviderOK, providerId);
		return new Context(Events.DeleteProviderKO, res);
	}

	@Override
	public Events getId() {
		return Events.DeleteProvider;
	}

}
