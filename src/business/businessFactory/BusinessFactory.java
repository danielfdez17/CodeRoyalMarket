package business.businessFactory;

import business.client.ClientAS;
import business.product.ProductAS;
import business.provider.ProviderAS;
import business.sale.SaleAS;
import business.warehouse.WarehouseAS;
import business.worker.WorkerAS;

public abstract class BusinessFactory {
	
	private static BusinessFactory instance;

	public static BusinessFactory getInstance() {
		if (instance == null) instance = new BusinessFactoryImp();
		return instance;
	}

	public abstract ClientAS createClientAS();
	public abstract ProductAS createProductAS();
	public abstract ProviderAS createProviderAS();
	public abstract SaleAS createSaleAS();
	public abstract WarehouseAS createWarehouseAS();
	public abstract WorkerAS createWorkerAS();
}