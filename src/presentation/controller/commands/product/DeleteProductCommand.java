package presentation.controller.commands.product;

import business.businessFactory.BusinessFactory;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class DeleteProductCommand implements Command {

	@Override
	public Context execute(Object data) {
		int productId = (int)data;
		int res = BusinessFactory.getInstance().createProductAS().deleteProduct(productId);
		if (res > 0) return new Context(Events.DeleteProductOK, productId);
		return new Context(Events.DeleteProductKO, res);
	}

	@Override
	public Events getId() {
		return Events.DeleteProduct;
	}

}
