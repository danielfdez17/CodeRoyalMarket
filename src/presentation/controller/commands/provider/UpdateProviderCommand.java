package presentation.controller.commands.provider;

import business.businessFactory.BusinessFactory;
import business.provider.ProviderTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class UpdateProviderCommand implements Command {

	@Override
	public Context execute(Object data) {
		ProviderTransfer provider = (ProviderTransfer)data;
		int res = BusinessFactory.getInstance().createProviderAS().updateProvider(provider);
		if (res > 0) return new Context(Events.UpdateProductOK, provider);
		return new Context(Events.UpdateProductKO, res);
	}

	@Override
	public Events getId() {
		return Events.UpdateProvider;
	}

}
