package business.worker;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
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
public class WorkerBO implements Serializable {
	private static final long serialVersionUID = 0;

	public WorkerBO() {
	}

	@Id
	private int id;
	private int version;
	protected String nif;
	protected String name;
	protected boolean active;
	@ManyToOne
	private WarehouseBO warehouseBO;

	public WorkerBO(WorkerTransfer worker) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public WorkerTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}