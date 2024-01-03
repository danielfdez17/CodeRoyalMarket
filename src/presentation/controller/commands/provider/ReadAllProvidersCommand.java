package presentation.controller.commands.provider;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.provider.ProviderTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadAllProvidersCommand implements Command {

	@Override
	public Context execute(Object data) {
		List<ProviderTransfer> list = BusinessFactory.getInstance().createProviderAS().readAllProviders();
		return new Context(getId(), list);
	}

	@Override
	public Events getId() {
		return Events.ReadAllProviders;
	}

}
