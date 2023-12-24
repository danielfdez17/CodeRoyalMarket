package business.sale;

import java.sql.Date;

import utilities.Utils;

public class SaleTransfer {
	private int id;
	private double cost;
	private Date date;
	private int clientId;
	private boolean active;
	
	public SaleTransfer(int clientId) {
		super();
		this.clientId = clientId;
		this.setCost(0.0);
		this.setDate(new Date(System.currentTimeMillis()));
		this.setActive(true);
	}

	public SaleTransfer(int id, double cost, Date date, int clientId, boolean active) {
		super();
		this.id = id;
		this.cost = cost;
		this.date = date;
		this.clientId = clientId;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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
				"Cost: " + this.cost + Utils.JUMP +
				"Date: " + this.date.toString() + Utils.JUMP +
				"Client ID: " + this.clientId + Utils.JUMP +
				"Active: " + (this.active ? "yes" : "no") + Utils.JUMP;
	}
	
}