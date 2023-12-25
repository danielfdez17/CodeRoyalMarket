package business.provider;

import utilities.Utils;

public class ProviderTransfer {
	private int id;
	private String name;
	private int phoneNumber;
	private boolean active;
	
	public ProviderTransfer(String name, int phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.setActive(true);
	}

	public ProviderTransfer(int id, String name, int phoneNumber, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
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
				"Phone number: " + this.phoneNumber + Utils.JUMP +
				"Active: " + (this.active ? "yes" : "no") + Utils.JUMP;
	}
	
}