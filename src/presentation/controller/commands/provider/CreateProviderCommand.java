package presentation.controller.commands.provider;

import business.businessFactory.BusinessFactory;
import business.provider.ProviderTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class CreateProviderCommand implements Command {

	@Override
	public Context execute(Object data) {
		ProviderTransfer provider = (ProviderTransfer)data;
		int res = BusinessFactory.getInstance().createProviderAS().createProvider(provider);
		if (res  > 0) return new Context(Events.CreateProviderOK, provider);
		return new Context(Events.CreateProviderKO, res);
	}

	@Override
	public Events getId() {
		return Events.CreateProvider;
	}

}
