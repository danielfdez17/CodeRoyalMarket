package presentation.controller.commands;

import presentation.controller.Events;
import presentation.controller.view.Context;

public interface Command {
	
	public Context execute(Object data);
	public Events getId();

}
