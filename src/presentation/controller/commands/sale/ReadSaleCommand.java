package presentation.controller.commands.sale;

import business.businessFactory.BusinessFactory;
import business.sale.ShoppingCartTransfer;
import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class ReadSaleCommand implements Command {

	@Override
	public Context execute(Object data) {
		int saleId = (int)data;
		ShoppingCartTransfer shoppingCart = BusinessFactory.getInstance().createSaleAS().readSale(saleId);
		if (shoppingCart != null) return new Context(Events.ReadSaleOK, shoppingCart);
		return new Context(Events.ReadSaleKO, saleId);
	}

	@Override
	public Events getId() {
		return Events.ReadSale;
	}

}
