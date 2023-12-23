package business.client;

import javax.persistence.Entity;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.client.ClientBO.findByid", query = "select obj from ClientBO obj where :id = obj.id "),
		@NamedQuery(name = "business.client.ClientBO.findBynif", query = "select obj from ClientBO obj where :nif = obj.nif "),
		@NamedQuery(name = "business.client.ClientBO.findByversion", query = "select obj from ClientBO obj where :version = obj.version "),
		@NamedQuery(name = "business.client.ClientBO.findByname", query = "select obj from ClientBO obj where :name = obj.name "),
		@NamedQuery(name = "business.client.ClientBO.findBybalance", query = "select obj from ClientBO obj where :balance = obj.balance "),
		@NamedQuery(name = "business.client.ClientBO.findByactive", query = "select obj from ClientBO obj where :active = obj.active ") })
public class ClientBO implements Serializable {
	private static final long serialVersionUID = 0;

	public ClientBO() {
	}

	@Id
	private int id;
	private String nif;
	private int version;
	private String name;
	private double balance;
	private boolean active;

	public void ClienBO(ClientTransfer client) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public ClientTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}