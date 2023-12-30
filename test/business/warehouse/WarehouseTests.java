package business.warehouse;

import org.junit.BeforeClass;

import business.businessFactory.BusinessFactory;
import business.entityManagerFactory.EMFFactory;
import business.product.ProductAS;
import business.product.ProductTransfer;
import business.worker.FullTimeWorkerTransfer;
import business.worker.WorkerAS;

public class WarehouseTests {
	
	protected static final String city = "city", nif = "12345678";
	protected static final int stock = 19;
	protected static final double price = 38, salary = 83;
	
	protected static BusinessFactory bf;
	protected static WarehouseAS warehouseAS;
	protected static ProductAS productAS;
	protected static WorkerAS workerAS;
	protected WarehouseTransfer warehouse;
	protected ProductTransfer product;
	protected FullTimeWorkerTransfer fullTime;
	protected int warehouseId, productId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		warehouseAS = bf.createWarehouseAS();
		productAS = bf.createProductAS();
		workerAS = bf.createWorkerAS();
		EMFFactory.getInstance();
	}
	
	protected void setASs(String name) {
		warehouse = new WarehouseTransfer(name, name);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		product = new ProductTransfer(name, stock, price, warehouseId);
		productId = productAS.createProduct(product);

	}
}
