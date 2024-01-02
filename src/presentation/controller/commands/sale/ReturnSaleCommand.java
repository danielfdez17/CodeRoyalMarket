package presentation.controller.commands.sale;

import business.businessFactory.BusinessFactory;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReturnSaleCommand implements Command {

	@Override
	public Context execute(Object data) {
		int saleId = (int)data;
		int res = BusinessFactory.getInstance().createSaleAS().returnSale(saleId);
		if (res > 0) return new Context(Events.ReturnSaleOK, saleId);
		return new Context(Events.ReturnSaleOK, res);
	}

	@Override
	public Events getId() {
		return Events.ReturnSale;
	}

}
