package business.worker;

import utilities.Utils;

public class FullTimeWorkerTransfer extends WorkerTransfer {

	private double salary;

	public FullTimeWorkerTransfer(String nif, String name, int warehouseId, double salary) {
		super(nif, name, warehouseId);
		this.setSalary(salary);
	}
	
	public FullTimeWorkerTransfer(int id, String nif, String name, int warehouseId, boolean active, double salary) {
		super(id, nif, name, warehouseId, active);
		this.setSalary(salary);
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString() + 
				"Salary: " + this.salary + Utils.JUMP +
				"Active: " + (this.active ? "yes" : "no") + Utils.JUMP;
	}

	
}