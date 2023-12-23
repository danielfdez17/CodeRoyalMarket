package business.product;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Set;
import business.provider.ProviderBO;
import javax.persistence.ManyToMany;
import business.warehouse.WarehouseBO;
import javax.persistence.ManyToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.product.ProductBO.findByid", query = "select obj from ProductBO obj where :id = obj.id "),
		@NamedQuery(name = "business.product.ProductBO.findByversion", query = "select obj from ProductBO obj where :version = obj.version "),
		@NamedQuery(name = "business.product.ProductBO.findByname", query = "select obj from ProductBO obj where :name = obj.name "),
		@NamedQuery(name = "business.product.ProductBO.findBystock", query = "select obj from ProductBO obj where :stock = obj.stock "),
		@NamedQuery(name = "business.product.ProductBO.findByprice", query = "select obj from ProductBO obj where :price = obj.price "),
		@NamedQuery(name = "business.product.ProductBO.findByactive", query = "select obj from ProductBO obj where :active = obj.active "),
		@NamedQuery(name = "business.product.ProductBO.findByproviderBO", query = "select obj from ProductBO obj where :providerBO MEMBER OF obj.providerBO "),
		@NamedQuery(name = "business.product.ProductBO.findBywarehouseBO", query = "select obj from ProductBO obj where :warehouseBO = obj.warehouseBO ") })
public class ProductBO implements Serializable {
	private static final long serialVersionUID = 0;

	public ProductBO() {
	}

	@Id
	private int id;
	private int version;
	private String name;
	private int stock;
	private double price;
	private boolean active;
	@ManyToMany(mappedBy = "productBO")
	private Set<ProviderBO> providerBO;
	@ManyToOne
	private WarehouseBO warehouseBO;

	public ProductBO(ProductTransfer product) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public ProductTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}