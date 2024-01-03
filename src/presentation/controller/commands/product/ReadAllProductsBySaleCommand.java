package presentation.controller.commands.product;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.product.ProductTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadAllProductsBySaleCommand implements Command {

	@Override
	public Context execute(Object data) {
		int saleId = (int)data;
		List<ProductTransfer> res = BusinessFactory.getInstance().createProductAS().readAllProductsBySale(saleId);
		if (saleId > 0) return new Context(Events.ReadAllProductsBySaleOK, res);
		return new Context(Events.ReadAllProductsBySaleKO, saleId);
	}

	@Override
	public Events getId() {
		return Events.ReadAllProductsBySale;
	}

}
