package presentation.controller.commands.sale;

import presentation.controller.Events;
import presentation.controller.commands.Command;
import presentation.controller.view.Context;

public class RemoveProductCommand implements Command {

	@Override
	public Context execute(Object data) {
	}

	@Override
	public Events getId() {
		return Events.RemoveProduct;
	}

}
