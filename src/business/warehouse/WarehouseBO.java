package business.warehouse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.List;
import business.product.ProductBO;
import business.worker.WorkerBO;

import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByid", query = "select obj from WarehouseBO obj where :id = obj.id "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByversion", query = "select obj from WarehouseBO obj where :version = obj.version "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByname", query = "select obj from WarehouseBO obj where :name = obj.name "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findBycity", query = "select obj from WarehouseBO obj where :city = obj.city "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByactive", query = "select obj from WarehouseBO obj where :active = obj.active "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByproducts", query = "select obj from WarehouseBO obj where :products MEMBER OF obj.products "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findAll", query = "select obj from WarehouseBO obj"),
})
public class WarehouseBO implements Serializable {
	private static final long serialVersionUID = 0;

	public WarehouseBO() {}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Version
	private int version;
	private String name;
	private String city;
	private boolean active;
	@OneToMany
	private List<ProductBO> products;
	@OneToMany
	private List<WorkerBO> workers;
	
	public WarehouseBO(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	}
	
	public WarehouseBO(int id, String name, String city, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.active = active;
	}

	public WarehouseBO(WarehouseTransfer warehouse) {
		this(warehouse.getId(), warehouse.getName(), warehouse.getCity(), warehouse.isActive());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ProductBO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductBO> products) {
		this.products = products;
	}
	
	public List<WorkerBO> getWorkers() {
		return workers;
	}

	public void setWorkers(List<WorkerBO> workers) {
		this.workers = workers;
	}

	public WarehouseTransfer toTransfer() {
		return new WarehouseTransfer(id, name, city, active);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WarehouseBO other = (WarehouseBO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}