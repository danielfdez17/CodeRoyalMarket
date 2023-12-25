package presentation.controller.commands;

import presentation.controller.Events;
import presentation.gui.GUIMSG;

public class CommandFactoryImp extends CommandFactory {
	
	private static Command commands[] = {
			
	};

	@Override
	public Command createCommand(Events id) {
		for (Command c : commands) 
			if (c.getId() == id)
				return c;
		GUIMSG.showMessage("Nonexistent command, add it to the list", "CommandFactoryImp", true);
		return null;
	}

}
