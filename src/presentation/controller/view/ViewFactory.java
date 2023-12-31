package presentation.controller.view;

public abstract class ViewFactory {
	private static ViewFactory instance;

	public static synchronized ViewFactory getInstance() {
		if (instance == null) instance = new ViewFactoryImp();
		return instance;
	}

	public abstract void createView(Context context);
}