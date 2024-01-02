package presentation.controller.commands.sale;

import java.util.List;

import business.businessFactory.BusinessFactory;
import business.sale.SaleTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadAllSalesCommand implements Command {

	@Override
	public Context execute(Object data) {
		List<SaleTransfer> res = BusinessFactory.getInstance().createSaleAS().readAllSales();
		return new Context(getId(), res);
	}

	@Override
	public Events getId() {
		return Events.ReadAllSales;
	}

}
