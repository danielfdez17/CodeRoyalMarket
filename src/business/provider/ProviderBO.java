package business.provider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.persistence.NamedQueries;

import java.util.List;
import business.product.ProductBO;
import javax.persistence.ManyToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.provider.ProviderBO.findByid", query = "select obj from ProviderBO obj where :id = obj.id "),
		@NamedQuery(name = "business.provider.ProviderBO.findByversion", query = "select obj from ProviderBO obj where :version = obj.version "),
		@NamedQuery(name = "business.provider.ProviderBO.findByname", query = "select obj from ProviderBO obj where :name = obj.name "),
		@NamedQuery(name = "business.provider.ProviderBO.findByphoneNumber", query = "select obj from ProviderBO obj where :phoneNumber = obj.phoneNumber "),
		@NamedQuery(name = "business.provider.ProviderBO.findByactive", query = "select obj from ProviderBO obj where :active = obj.active "),
		@NamedQuery(name = "business.provider.ProviderBO.findByproductBO", query = "select obj from ProviderBO obj where :productBO MEMBER OF obj.productBO "),
		@NamedQuery(name = "business.provider.ProviderBO.findAll", query = "select obj from ProviderBO"),
})
public class ProviderBO implements Serializable {
	private static final long serialVersionUID = 0;

	public ProviderBO() {}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Version
	private int version;
	@Column(unique = true, nullable = false)
	private String name;
	private int phoneNumber;
	private boolean active;
	@ManyToMany(mappedBy = "providers")
	private List<ProductBO> products;
	
	public ProviderBO(String name, int phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.active = true;
	}
	
	public ProviderBO(int id, String name, int phoneNumber, boolean active) {
		this(name, phoneNumber);
		this.id = id;
		this.active = active;
	}

	public ProviderBO(ProviderTransfer provider) {
		this(provider.getId(), provider.getName(), provider.getPhoneNumber(), provider.isActive());
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public ProviderTransfer toTransfer() {
		return new ProviderTransfer(id, name, phoneNumber, active);
	}
}