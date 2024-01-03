package presentation.controller.commands.provider;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.provider.ProviderTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadAllProvidersByProductCommand implements Command {

	@Override
	public Context execute(Object data) {
		int productId = (int)data;
		List<ProviderTransfer> list = BusinessFactory.getInstance().createProviderAS().readAllProvidersByProduct(productId);
		if (productId > 0) return new Context(Events.ReadAllProvidersByProductOK, list);
		return new Context(Events.ReadAllProvidersByProductKO, productId);
	}

	@Override
	public Events getId() {
		return Events.ReadAllProvidersByProduct;
	}

}
