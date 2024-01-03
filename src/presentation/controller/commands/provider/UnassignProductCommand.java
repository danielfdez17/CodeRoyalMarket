package presentation.controller.commands.provider;

import business.businessFactory.BusinessFactory;
import business.providerProduct.ProviderProductTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class UnassignProductCommand implements Command {

	@Override
	public Context execute(Object data) {
		ProviderProductTransfer providerProduct = (ProviderProductTransfer)data;
		int res = BusinessFactory.getInstance().createProviderAS().unassignProduct(providerProduct);
		if (res > 0) return new Context(Events.UnassignProductOK, providerProduct);
		return new Context(Events.UnassignProductKO, res);
	}

	@Override
	public Events getId() {
		return Events.UnassignProduct;
	}

}
