package business.warehouse;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Set;
import business.product.ProductBO;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByid", query = "select obj from WarehouseBO obj where :id = obj.id "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByversion", query = "select obj from WarehouseBO obj where :version = obj.version "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByname", query = "select obj from WarehouseBO obj where :name = obj.name "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findBycity", query = "select obj from WarehouseBO obj where :city = obj.city "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByactive", query = "select obj from WarehouseBO obj where :active = obj.active "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByproducts", query = "select obj from WarehouseBO obj where :products MEMBER OF obj.products ") })
public class WarehouseBO implements Serializable {
	private static final long serialVersionUID = 0;

	public WarehouseBO() {
	}

	@Id
	private int id;
	private int version;
	private String name;
	private String city;
	private boolean active;
	@OneToMany
	private Set<ProductBO> products;

	public WarehouseBO(WarehouseTransfer warehouse) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public WarehouseTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}