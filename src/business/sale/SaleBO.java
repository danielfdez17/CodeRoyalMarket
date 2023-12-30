package business.sale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.NamedQueries;
import business.client.ClientBO;
import business.saleLine.SaleLineBO;

import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.sale.SaleBO.findByid", 
					query = "select obj from SaleBO obj where :id = obj.id "),
		@NamedQuery(name = "business.sale.SaleBO.findByversion", 
					query = "select obj from SaleBO obj where :version = obj.version "),
		@NamedQuery(name = "business.sale.SaleBO.findBycost", 
					query = "select obj from SaleBO obj where :cost = obj.cost "),
		@NamedQuery(name = "business.sale.SaleBO.findBydate", 
					query = "select obj from SaleBO obj where :date = obj.date "),
		@NamedQuery(name = "business.sale.SaleBO.findByactive", 
					query = "select obj from SaleBO obj where :active = obj.active "),
		@NamedQuery(name = "business.sale.SaleBO.findByclientBO", 
					query = "select obj from SaleBO obj where :clientBO = obj.clientBO "),
		@NamedQuery(name = "business.sale.SaleBO.findAll", 
					query = "select obj from SaleBO obj"),
})

public class SaleBO implements Serializable {
	private static final long serialVersionUID = 0;

	public SaleBO() {}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Version
	private int version;
	private double cost;
	private Date date;
	private boolean active;
	@OneToOne
	private ClientBO clientBO;
	@OneToMany(mappedBy = "saleBO", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleLineBO> saleLines;
	
	
	public SaleBO(int id, double cost, Date date, boolean active) {
		super();
		this.id = id;
		this.cost = cost;
		this.date = date;
		this.active = active;
	}

	public SaleBO(SaleTransfer sale) {
		this(sale.getId(), sale.getCost(), sale.getDate(), sale.isActive());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ClientBO getClientBO() {
		return clientBO;
	}

	public void setClientBO(ClientBO clientBO) {
		this.clientBO = clientBO;
	}

	public SaleTransfer toTransfer() {
		return new SaleTransfer(id, cost, date, clientBO.getId(), active);
	}
}