package presentation.controller.commands.sale;

import business.sale.SaleTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class OpenSaleCommand implements Command {

	@Override
	public Context execute(Object data) {
		int clientId = (int)data;
		if (clientId > 0) return new Context(Events.OpenSaleOK, new SaleTransfer(clientId));
		return new Context(Events.OpenSaleKO, clientId);
	}

	@Override
	public Events getId() {
		return Events.OpenSale;
	}

}
