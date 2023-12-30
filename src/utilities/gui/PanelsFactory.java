package utilities.gui;

import javax.swing.JPanel;

public abstract class PanelsFactory {

	private static PanelsFactory instance;
	
	public static synchronized PanelsFactory getInstance() {
		if (instance == null) instance = new PanelsFactoryImp();
		return instance;
	}
	
	public abstract FieldPanel createIdPanel();
	public abstract FieldPanel createIdPanel(String entity);
	public abstract FieldPanel createNamePanel();
	public abstract FieldPanel createNifPanel();
	public abstract FieldPanel createBalancePanel();
	public abstract FieldPanel createPricePanel();
	public abstract FieldPanel createStockPanel();
	public abstract FieldPanel createPhoneNumberPanel();
	public abstract FieldPanel createCityPanel();
	public abstract FieldPanel createSalaryPanel();
	public abstract FieldPanel createHourPricePanel();
	public abstract FieldPanel createHoursPanel();
}
