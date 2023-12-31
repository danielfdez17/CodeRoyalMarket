package presentation.controller.commands;

import presentation.controller.Events;
import presentation.controller.commands.client.CommandCreateClient;
import presentation.controller.commands.client.CommandReadClients;
import presentation.controller.commands.main.CommandMainGUI;
import presentation.view.GUIMSG;

public class CommandFactoryImp extends CommandFactory {
	
	private static Command commands[] = {
		new CommandMainGUI(),
		
		new CommandCreateClient(),
		new CommandReadClients(),
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
