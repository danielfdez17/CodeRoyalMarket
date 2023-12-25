package presentation.controller.commands;

import presentation.controller.Events;

public abstract class CommandFactory {

	private static CommandFactory instance;
	
	public synchronized static CommandFactory getInstance() {
		if (instance == null) instance = new CommandFactoryImp();
		return instance;
	}
	
	public abstract Command createCommand(Events event);
	
}
