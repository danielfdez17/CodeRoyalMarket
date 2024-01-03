package presentation.controller.commands.product;

import business.businessFactory.BusinessFactory;
import business.product.ProductTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadProductForUpdateCommand implements Command {

	@Override
	public Context execute(Object data) {
		int productId = (int)data;
		ProductTransfer res = BusinessFactory.getInstance().createProductAS().readProduct(productId);
		if (res != null) return new Context(Events.ReadProductForUpdateOK, res);
		return new Context(Events.ReadProductForUpdateKO, productId);
	}

	@Override
	public Events getId() {
		return Events.ReadProductForUpdate;
	}

}
