package presentation.controller.commands.product;

import business.businessFactory.BusinessFactory;
import business.product.ProductTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class CreateProductCommand implements Command {

	@Override
	public Context execute(Object data) {
		ProductTransfer product = (ProductTransfer)data;
		int res = BusinessFactory.getInstance().createProductAS().createProduct(product);
		if (res > 0) return new Context(Events.CreateProductOK, product);
		return new Context(Events.CreateProductKO, res);
	}

	@Override
	public Events getId() {
		return Events.CreateProduct;
	}

}
