package business.sale;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import business.client.ClientBO;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.sale.SaleBO.findByid", query = "select obj from SaleBO obj where :id = obj.id "),
		@NamedQuery(name = "business.sale.SaleBO.findByversion", query = "select obj from SaleBO obj where :version = obj.version "),
		@NamedQuery(name = "business.sale.SaleBO.findBycost", query = "select obj from SaleBO obj where :cost = obj.cost "),
		@NamedQuery(name = "business.sale.SaleBO.findBydate", query = "select obj from SaleBO obj where :date = obj.date "),
		@NamedQuery(name = "business.sale.SaleBO.findByactive", query = "select obj from SaleBO obj where :active = obj.active "),
		@NamedQuery(name = "business.sale.SaleBO.findByclientBO", query = "select obj from SaleBO obj where :clientBO = obj.clientBO ") })
public class SaleBO implements Serializable {
	private static final long serialVersionUID = 0;

	public SaleBO() {
	}

	@Id
	private int id;
	private int version;
	private double cost;
	private Object date;
	private boolean active;
	@OneToOne
	private ClientBO clientBO;

	public SaleBO(SaleTransfer sale) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public SaleTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}