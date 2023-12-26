package business.worker;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

import business.warehouse.WarehouseBO;

import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.worker.PartTimeWorkerBO.findByid", query = "select obj from PartTimeWorkerBO obj where :id = obj.id "),
		@NamedQuery(name = "business.worker.PartTimeWorkerBO.findByversion", query = "select obj from PartTimeWorkerBO obj where :version = obj.version "),
		@NamedQuery(name = "business.worker.PartTimeWorkerBO.findByhourPrice", query = "select obj from PartTimeWorkerBO obj where :hourPrice = obj.hourPrice "),
		@NamedQuery(name = "business.worker.PartTimeWorkerBO.findByhours", query = "select obj from PartTimeWorkerBO obj where :hours = obj.hours ") })
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class PartTimeWorkerBO extends WorkerBO implements Serializable {
	private static final long serialVersionUID = 0;

	public PartTimeWorkerBO() {}

	private double hourPrice;
	private int hours;

	public PartTimeWorkerBO(int id, String nif, String name, boolean active, WarehouseBO warehouseBO, double hourPrice,
			int hours) {
		super(id, nif, name, active, warehouseBO);
		this.hourPrice = hourPrice;
		this.hours = hours;
	}
	
	public PartTimeWorkerBO(String nif, String name, double hourPrice, int hours) {
		super(nif, name);
		this.hourPrice = hourPrice;
		this.hours = hours;
	}

	public PartTimeWorkerBO(String nif, String name, double hourPrice, int hours, WarehouseBO warehouseBO) {
		super(nif, name, warehouseBO);
		this.hourPrice = hourPrice;
		this.hours = hours;
	}
	
	public PartTimeWorkerBO(int id, String nif, String name, boolean active, double hourPrice, int hours) {
		super(id, nif, name, active);
		this.hourPrice = hourPrice;
		this.hours = hours;
	}

	public PartTimeWorkerBO(PartTimeWorkerTransfer partTime) {
		this(partTime.getId(), partTime.getNif(), partTime.getName(), partTime.isActive(), partTime.getHourPrice(), partTime.getHours());
	}
	
	public PartTimeWorkerBO(PartTimeWorkerTransfer partTime, WarehouseBO warehouseBO) {
		this(partTime.getId(), partTime.getNif(), partTime.getName(), partTime.isActive(), partTime.getHourPrice(), partTime.getHours());
		this.warehouseBO = warehouseBO;
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
	public WorkerTransfer toTransfer() {
		return new PartTimeWorkerTransfer(id, nif, name, warehouseBO.getId(), active, hourPrice, hours);
	}
}
	
