package presentation.controller.commands.product;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.product.ProductTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadAllProductsByProviderCommand implements Command {

	@Override
	public Context execute(Object data) {
		int providerId = (int)data;
		List<ProductTransfer> res = BusinessFactory.getInstance().createProductAS().readAllProductsByProvider(providerId);
		if (providerId > 0) return new Context(Events.ReadAllProductsByProviderOK, res);
		return new Context(Events.ReadAllProductsByProviderKO, providerId);
	}

	@Override
	public Events getId() {
		return Events.ReadAllProductsByProvider;
	}

}
