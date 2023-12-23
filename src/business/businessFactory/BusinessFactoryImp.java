package business.businessFactory;

import business.client.ClientAS;
import business.client.ClientASImp;
import business.product.ProductAS;
import business.product.ProductASImp;
import business.provider.ProviderAS;
import business.provider.ProviderASImp;
import business.sale.SaleAS;
import business.sale.SaleASImp;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseASImp;
import business.worker.WorkerAS;
import business.worker.WorkerASImp;

public class BusinessFactoryImp extends BusinessFactory {

	@Override
	public ClientAS createClientAS() {
		return new ClientASImp();
	}

	@Override
	public ProductAS createProductAS() {
		return new ProductASImp();
	}

	@Override
	public ProviderAS createProviderAS() {
		return new ProviderASImp();
	}

	@Override
	public SaleAS createSaleAS() {
		return new SaleASImp();
	}

	@Override
	public WarehouseAS createWarehouseAS() {
		return new WarehouseASImp();
	}

	@Override
	public WorkerAS createWorkerAS() {
		return new WorkerASImp();
	}
}