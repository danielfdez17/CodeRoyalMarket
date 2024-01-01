package presentation.controller.view;

import presentation.view.GUIMSG;
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
			
			
		default:
			GUIMSG.showMessage(Utils.NotConsideredView, FromWhere, true);
		}
	}
}