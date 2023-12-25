package business.product;

import utilities.Utils;

public class ProductTransfer {
	private int id;
	private String name;
	private int stock;
	private double price;
	private int warehouseId;
	private boolean active;
	
	public ProductTransfer(String name, int stock, double price, int warehouseId) {
		super();
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.warehouseId = warehouseId;
		this.setActive(true);
	}

	public ProductTransfer(int id, String name, int stock, double price, int warehouseId, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.warehouseId = warehouseId;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
				"Name: " + this.name + Utils.JUMP +
				"Stock: " + this.stock + Utils.JUMP +
				"Price: " + this.price + Utils.JUMP +
				"Warehouse ID: " + this.warehouseId + Utils.JUMP +
				"Active: " + (this.active ? "yes" : "no") + Utils.JUMP;
	}
	
}