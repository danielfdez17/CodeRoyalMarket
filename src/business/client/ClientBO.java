package business.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.client.ClientBO.findByid", query = "select obj from ClientBO obj where :id = obj.id "),
		@NamedQuery(name = "business.client.ClientBO.findBynif", query = "select obj from ClientBO obj where :nif = obj.nif "),
		@NamedQuery(name = "business.client.ClientBO.findByversion", query = "select obj from ClientBO obj where :version = obj.version "),
		@NamedQuery(name = "business.client.ClientBO.findByname", query = "select obj from ClientBO obj where :name = obj.name "),
		@NamedQuery(name = "business.client.ClientBO.findBybalance", query = "select obj from ClientBO obj where :balance = obj.balance "),
		@NamedQuery(name = "business.client.ClientBO.findByactive", query = "select obj from ClientBO obj where :active = obj.active "), 
		@NamedQuery(name = "business.client.ClientBO.findAll", query = "select obj from ClientBO obj"),
		})
public class ClientBO implements Serializable {
	private static final long serialVersionUID = 0;

	public ClientBO() {}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Version
	private int version;
	@Column(unique = true, nullable = false)
	private String nif;
	@Column(nullable = false)
	private String name;
	private double balance;
	private boolean active;
	
	public ClientBO(String nif, String name, double balance) {
		super();
		this.setNif(nif);
		this.setName(name);
		this.setBalance(balance);
		this.setActive(true);
	}
	
	public ClientBO(int id, String nif, String name, double balance, boolean active) {
		this(nif, name, balance);
		this.setId(id);
		this.setActive(active);
	}

	public ClientBO(ClientTransfer client) {
		this(client.getId(), client.getNif(), client.getName(), client.getBalance(), client.isActive());
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}


	public ClientTransfer toTransfer() {
		return new ClientTransfer(id, nif, name, balance, active);
	}
	
}