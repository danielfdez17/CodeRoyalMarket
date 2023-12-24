package business.worker;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

import business.warehouse.WarehouseBO;

import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.worker.FullTimeWorkerBO.findByid", query = "select obj from FullTimeWorkerBO obj where :id = obj.id "),
		@NamedQuery(name = "business.worker.FullTimeWorkerBO.findByversion", query = "select obj from FullTimeWorkerBO obj where :version = obj.version "),
		@NamedQuery(name = "business.worker.FullTimeWorkerBO.findBysalary", query = "select obj from FullTimeWorkerBO obj where :salary = obj.salary ") })
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class FullTimeWorkerBO extends WorkerBO implements Serializable {
	private static final long serialVersionUID = 0;

	public FullTimeWorkerBO() {}

	private double salary;
	
	public FullTimeWorkerBO(int id, String nif, String name, boolean active, WarehouseBO warehouseBO, double salary) {
		super(id, nif, name, active, warehouseBO);
		this.salary = salary;
	}
	
	public FullTimeWorkerBO(int id, String nif, String name, boolean active, double salary) {
		super(id, nif, name, active);
		this.salary = salary;
	}

	public FullTimeWorkerBO(FullTimeWorkerTransfer fullTime) {
		this(fullTime.getId(), fullTime.getNif(), fullTime.getName(), fullTime.isActive(), fullTime.getSalary());
	}

	@Override
	public WorkerTransfer toTransfer() {
		return new FullTimeWorkerTransfer(id, nif, name, warehouseBO.getId(), active, salary);
	}
}