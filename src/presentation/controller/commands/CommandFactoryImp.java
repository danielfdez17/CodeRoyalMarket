package presentation.controller.commands;

import presentation.controller.Events;
import presentation.controller.commands.main.CommandMainGUI;
import presentation.view.GUIMSG;
import utilities.Utils;

public class CommandFactoryImp extends CommandFactory {
	
	private static final String FromWhere = CommandFactoryImp.class.getSimpleName();
	
	private static Command commands[] = {
		new CommandMainGUI(),
		
	};

	@Override
	public Command createCommand(Events id) {
		for (Command c : commands) 
			if (c.getId() == id)
				return c;
		GUIMSG.showMessage(Utils.NotConsideredCommand, FromWhere , true);
		return null;
	}

}
