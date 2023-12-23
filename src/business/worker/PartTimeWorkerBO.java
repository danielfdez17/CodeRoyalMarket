package business.worker;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.worker.PartTimeWorkerBO.findByid", query = "select obj from PartTimeWorkerBO obj where :id = obj.id "),
		@NamedQuery(name = "business.worker.PartTimeWorkerBO.findByversion", query = "select obj from PartTimeWorkerBO obj where :version = obj.version "),
		@NamedQuery(name = "business.worker.PartTimeWorkerBO.findByhourPrice", query = "select obj from PartTimeWorkerBO obj where :hourPrice = obj.hourPrice "),
		@NamedQuery(name = "business.worker.PartTimeWorkerBO.findByhours", query = "select obj from PartTimeWorkerBO obj where :hours = obj.hours ") })
public class PartTimeWorkerBO extends WorkerBO implements Serializable {
	private static final long serialVersionUID = 0;

	public PartTimeWorkerBO() {
	}

	@Id
	private int id;
	private Object version;
	private double hourPrice;
	private int hours;

	public void PartTimeWorker(PartTimeWorkerTransfer partTime) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}