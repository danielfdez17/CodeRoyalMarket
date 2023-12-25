package utilities;

public class Errors {
	
	public static final int UnespectedError = 0;
	public static final int SintaxError = -1;
	
	public static final int NonexistentClient = -100;
	public static final int InactiveClient = -101;
	public static final int ActiveClient = -102;
	public static final int NotEnoughBalance = -103;
	
	public static final int NonexistentProduct = -200;
	public static final int InactiveProduct = -201;
	public static final int ActiveProduct = -202;
	
	public static final int NonexistentProvider = -300;
	public static final int InactiveProvider = -301;
	public static final int ActiveProvider = -302;
	public static final int ProductAlreadyAssigned = -303;
	public static final int ProductAlreadyUnassigned = -304;
	public static final int ActiveProductsAssigned = -305;
	
	public static final int NonexistentSale = -400;
	public static final int ProductNotBought = -401;
	public static final int MoreAmountThanBought = -402;
	public static final int InsufficientStock = -403;
	
	public static final int NonexistentWarehouse = -500;
	public static final int InactiveWarehouse = -501;
	public static final int ActiveWarehouse = -502;
	public static final int WarehouseWithActiveProducts = -503;
	public static final int WarehouseWithActiveWorkers = -504;
	
	public static final int NonexistentWorker = -600;
	public static final int InactiveWorker = -601;
	public static final int ActiveWorker = -602;
	

}
