package business.worker;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.worker.FullTimeWorkerBO.findByid", query = "select obj from FullTimeWorkerBO obj where :id = obj.id "),
		@NamedQuery(name = "business.worker.FullTimeWorkerBO.findByversion", query = "select obj from FullTimeWorkerBO obj where :version = obj.version "),
		@NamedQuery(name = "business.worker.FullTimeWorkerBO.findBysalary", query = "select obj from FullTimeWorkerBO obj where :salary = obj.salary ") })
public class FullTimeWorkerBO extends WorkerBO implements Serializable {
	private static final long serialVersionUID = 0;

	public FullTimeWorkerBO() {
	}

	@Id
	private int id;
	private Object version;
	private double salary;

	public FullTimeWorkerBO(FullTimeWorkerTransfer fullTime) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}
}