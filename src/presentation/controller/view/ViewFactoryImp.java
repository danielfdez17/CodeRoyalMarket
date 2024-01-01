package presentation.controller.view;

import java.util.List;

import business.client.ClientTransfer;
import presentation.view.GUIMSG;
import presentation.view.client.ClientGUI;
import presentation.view.client.CreateClientFrame;
import presentation.view.main.MainGUI;
import utilities.Utils;

public class ViewFactoryImp extends ViewFactory {
	
	private static final String FromWhere = "ViewFactory.createView()";

	@Override
	public void createView(Context context) {
		switch (context.getEvent()) {
		case MainGUI:
			MainGUI.getInstance().setVisible(true); 
			break;
			
		case ClientsGUI:
			ClientGUI.getInstance().setVisible(true);
			break;
		case CreateClientOK:
		case CreateClientKO:
			CreateClientFrame.getInstance().update(context);
			break;
		case ReadAllClientsOK:
		case ReadAllClientsKO:
			ClientGUI.getInstance().update((List<ClientTransfer>) context.getData());
			break;
			
		default:
			GUIMSG.showMessage(Utils.NotConsideredView, FromWhere, true);
		}
	}
}