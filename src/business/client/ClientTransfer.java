package business.client;

import utilities.Utils;

public class ClientTransfer {
	private int id;
	private String nif;
	private String name;
	private double balance;
	private boolean active;
	
	
	public ClientTransfer(String nif, String name, double balance) {
		super();
		this.nif = nif;
		this.name = name;
		this.balance = balance;
		this.active = true;
	}

	public ClientTransfer(int id, String nif, String name, double balance, boolean active) {
		super();
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.balance = balance;
		this.active = active;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNif() { return nif; }
	public void setNif(String nif) { this.nif = nif; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public double getBalance() { return balance; }
	public void setBalance(double balance) { this.balance = balance; }

	public boolean isActive() { return active; }
	public void setActive(boolean active) { this.active = active; }

	@Override
	public String toString() {
		return "ID: " + this.id + Utils.JUMP +
				"NIF: " + this.nif + Utils.JUMP +
				"Name: " + this.name + Utils.JUMP + 
				"Balance: " + this.balance + Utils.JUMP + 
				"Active: " + (this.active ? "yes" : "no") + Utils.JUMP;
	}
	
}