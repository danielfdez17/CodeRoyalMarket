package presentation.controller.commands.provider;

import business.businessFactory.BusinessFactory;
import business.provider.ProviderTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadProviderForUpdateCommand implements Command {

	@Override
	public Context execute(Object data) {
		int providerId = (int)data;
		ProviderTransfer res = BusinessFactory.getInstance().createProviderAS().readProvider(providerId);
		if (res != null) return new Context(Events.ReadProviderForUpdateOK, res);
		return new Context(Events.ReadProviderForUpdateKO, providerId);
	}

	@Override
	public Events getId() {
		return Events.ReadProviderForUpdate;
	}

}
