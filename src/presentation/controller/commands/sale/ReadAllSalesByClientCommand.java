package presentation.controller.commands.sale;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.sale.SaleTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadAllSalesByClientCommand implements Command {

	@Override
	public Context execute(Object data) {
		int clientId = (int)data;
		List<SaleTransfer> res = BusinessFactory.getInstance().createSaleAS().readAllSalesByClient(clientId);
		if (clientId > 0) return new Context(Events.ReadAllSalesByClientOK, res);
		return new Context(Events.ReadAllSalesByClientKO, clientId);
	}

	@Override
	public Events getId() {
		return Events.ReadAllSalesByClient;
	}

}
