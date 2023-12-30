package business.worker;

import org.junit.BeforeClass;

import business.businessFactory.BusinessFactory;
import business.warehouse.WarehouseAS;
import business.warehouse.WarehouseTransfer;

public class WorkerTests {
	
	protected static final String nif = "12345678", city = "city";
	protected static final int hours = 38, INF = 999999999;
	protected static final double hourPrice = 42, salary = 2889;
	
	protected static BusinessFactory bf;
	protected static WorkerAS workerAS;
	protected static WarehouseAS warehouseAS;
	
	protected FullTimeWorkerTransfer fullTime;
	protected PartTimeWorkerTransfer partTime;
	protected WarehouseTransfer warehouse;
	
	protected int warehouseId, fullTimeId, partTimeId;
	
	@BeforeClass public static void setUp() {
		bf = BusinessFactory.getInstance();
		workerAS = bf.createWorkerAS();
		warehouseAS = bf.createWarehouseAS();
	}
	
	protected void setASs(String name, String nif1, String nif2) {
		warehouse = new WarehouseTransfer(name, city);
		warehouseId = warehouseAS.createWarehouse(warehouse);
		
		fullTime = new FullTimeWorkerTransfer(nif1, name, warehouseId, salary);
		fullTimeId = workerAS.createFullTimeWorker(fullTime);
		
		partTime = new PartTimeWorkerTransfer(nif2, name, warehouseId, hourPrice, hours);
		partTimeId = workerAS.createPartTimeWorker(partTime);
	}
}
