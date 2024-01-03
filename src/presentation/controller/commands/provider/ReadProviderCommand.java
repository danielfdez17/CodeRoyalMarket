package presentation.controller.commands.provider;

import business.businessFactory.BusinessFactory;
import business.provider.ProviderTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadProviderCommand implements Command {

	@Override
	public Context execute(Object data) {
		int providerId = (int)data;
		ProviderTransfer res = BusinessFactory.getInstance().createProviderAS().readProvider(providerId);
		if (res != null) return new Context(Events.ReadProviderOK, res);
		return new Context(Events.ReadProviderKO, providerId);

	}

	@Override
	public Events getId() {
		return Events.ReadProvider;
	}

}
