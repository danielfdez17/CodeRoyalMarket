package main;

import business.entityManagerFactory.EMFFactory;
import presentation.controller.Controller;
import presentation.controller.Events;
import presentation.controller.view.Context;

public class Main {
	public static void main(String[] args) {
		Controller.getInstance().action(new Context(Events.MainGUI, null));
	}
}