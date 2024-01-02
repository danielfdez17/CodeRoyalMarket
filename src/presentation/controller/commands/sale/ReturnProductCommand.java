package presentation.controller.commands.sale;

import business.businessFactory.BusinessFactory;
import business.saleLine.SaleLineTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReturnProductCommand implements Command {

	@Override
	public Context execute(Object data) {
		SaleLineTransfer returnedProduct = (SaleLineTransfer)data;
		int res = BusinessFactory.getInstance().createSaleAS().returnProduct(returnedProduct);
		if (res > 0) return new Context(Events.ReturnProductOK, returnedProduct);
		return new Context(Events.ReturnProductKO, res);
	}

	@Override
	public Events getId() {
		return Events.ReturnProduct;
	}

}
