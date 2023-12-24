package business.worker;

import utilities.Utils;

public class WorkerTransfer {
	
	protected int id;
	protected String nif;
	protected String name;
	protected int warehouseId;
	protected boolean active;
	
	public WorkerTransfer(String nif, String name, int warehouseId) {
		super();
		this.nif = nif;
		this.name = name;
		this.warehouseId = warehouseId;
	}

	public WorkerTransfer(int id, String nif, String name, int warehouseId, boolean active) {
		super();
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.warehouseId = warehouseId;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "ID: " + this.id + Utils.JUMP +
				"Nif: " + this.nif + Utils.JUMP +
				"Name: " + this.name + Utils.JUMP +
				"Warehouse ID: " + this.warehouseId + Utils.JUMP;
	}
	
}