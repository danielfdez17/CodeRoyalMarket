package presentation.controller.commands.product;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.product.ProductTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadAllProductsCommand implements Command {

	@Override
	public Context execute(Object data) {
		List<ProductTransfer> res = BusinessFactory.getInstance().createProductAS().readAllProducts();
		return new Context(this.getId(), res);
	}

	@Override
	public Events getId() {
		return Events.ReadAllProducts;
	}

}
