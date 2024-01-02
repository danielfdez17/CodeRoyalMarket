package presentation.controller.commands.sale;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.sale.SaleTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadAllSalesByProductCommand implements Command {

	@Override
	public Context execute(Object data) {
		int productId = (int)data;
		List<SaleTransfer> res = BusinessFactory.getInstance().createSaleAS().readAllSalesByProduct(productId);
		if (productId > 0) return new Context(Events.ReadAllSalesByProductOK, res);
		return new Context(Events.ReadAllSalesByProductKO, productId);
	}

	@Override
	public Events getId() {
		return Events.ReadAllSalesByProduct;
	}

}
