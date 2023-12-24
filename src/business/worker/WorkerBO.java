package business.worker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.persistence.NamedQueries;
import business.warehouse.WarehouseBO;
import javax.persistence.ManyToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.worker.WorkerBO.findByid", query = "select obj from WorkerBO obj where :id = obj.id "),
		@NamedQuery(name = "business.worker.WorkerBO.findByversion", query = "select obj from WorkerBO obj where :version = obj.version "),
		@NamedQuery(name = "business.worker.WorkerBO.findBynif", query = "select obj from WorkerBO obj where :nif = obj.nif "),
		@NamedQuery(name = "business.worker.WorkerBO.findByname", query = "select obj from WorkerBO obj where :name = obj.name "),
		@NamedQuery(name = "business.worker.WorkerBO.findByactive", query = "select obj from WorkerBO obj where :active = obj.active "),
		@NamedQuery(name = "business.worker.WorkerBO.findBywarehouseBO", query = "select obj from WorkerBO obj where :warehouseBO = obj.warehouseBO ") })
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class WorkerBO implements Serializable {
	private static final long serialVersionUID = 0;

	public WorkerBO() {}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Version
	protected int version;
	@Column(unique = true, nullable = false)
	protected String nif;
	@Column(nullable = false)
	protected String name;
	protected boolean active;
	
	@ManyToOne
	protected WarehouseBO warehouseBO;
	
	public WorkerBO(int id, String nif, String name, boolean active) {
		super();
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.active = active;
	}

	public WorkerBO(int id, String nif, String name, boolean active, WarehouseBO warehouseBO) {
		super();
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.active = active;
		this.warehouseBO = warehouseBO;
	}
	
	public WorkerBO(WorkerTransfer worker) {
		this(worker.getId(), worker.getNif(), worker.getName(), worker.isActive());
	}

	public abstract WorkerTransfer toTransfer();
}