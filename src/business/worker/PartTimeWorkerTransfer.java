package business.worker;

import utilities.Utils;

public class PartTimeWorkerTransfer extends WorkerTransfer {

	private double hourPrice;
	private int hours;
	
	public PartTimeWorkerTransfer(String nif, String name, int warehouseId, double hourPrice, int hours) {
		super(nif, name, warehouseId);
		this.hourPrice = hourPrice;
		this.hours = hours;
	}

	public PartTimeWorkerTransfer(int id, String nif, String name, int warehouseId, boolean active, double hourPrice,
			int hours) {
		super(id, nif, name, warehouseId, active);
		this.hourPrice = hourPrice;
		this.hours = hours;
	}

	public double getHourPrice() {
		return hourPrice;
	}

	public void setHourPrice(double hourPrice) {
		this.hourPrice = hourPrice;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return super.toString() +
				"Price per hour: " + this.hourPrice + Utils.JUMP +
				"Hours: " + this.hours + Utils.JUMP +
				"Active: " + (this.active ? "yes" : "no") + Utils.JUMP;
	}
	
}