package business.warehouse;

import java.util.List;

public interface WarehouseAS {
	
	public int createWarehouse(WarehouseTransfer warehouse);
	public WarehouseTransfer readWarehouse(int warehouseId);
	public List<WarehouseTransfer> readWarehouses();
	public int updateWarehouse(WarehouseTransfer warehouse);
	public int deleteWarehouse(int warehouseId);
	
}