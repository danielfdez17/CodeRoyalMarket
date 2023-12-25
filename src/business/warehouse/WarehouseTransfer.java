package business.warehouse;

import utilities.Utils;

public class WarehouseTransfer {
	
	private int id;
	private String name;
	private String city;
	private boolean active;
	
	public WarehouseTransfer(String name, String city) {
		super();
		this.name = name;
		this.city = city;
		this.setActive(true);
	}

	public WarehouseTransfer(int id, String name, String city, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
				"City: " + this.city + Utils.JUMP +
				"Active: " + (this.active ? "yes" : "no") + Utils.JUMP;
	}
	
}