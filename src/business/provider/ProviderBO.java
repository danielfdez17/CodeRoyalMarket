package business.provider;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Set;
import business.product.ProductBO;
import javax.persistence.ManyToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.provider.ProviderBO.findByid", query = "select obj from ProviderBO obj where :id = obj.id "),
		@NamedQuery(name = "business.provider.ProviderBO.findByversion", query = "select obj from ProviderBO obj where :version = obj.version "),
		@NamedQuery(name = "business.provider.ProviderBO.findByname", query = "select obj from ProviderBO obj where :name = obj.name "),
		@NamedQuery(name = "business.provider.ProviderBO.findByphoneNumber", query = "select obj from ProviderBO obj where :phoneNumber = obj.phoneNumber "),
		@NamedQuery(name = "business.provider.ProviderBO.findByactive", query = "select obj from ProviderBO obj where :active = obj.active "),
		@NamedQuery(name = "business.provider.ProviderBO.findByproductBO", query = "select obj from ProviderBO obj where :productBO MEMBER OF obj.productBO ") })
public class ProviderBO implements Serializable {
	private static final long serialVersionUID = 0;

	public ProviderBO() {
	}

	@Id
	private int id;
	private int version;
	private String name;
	private int phoneNumber;
	private boolean active;
	@ManyToMany
	private Set<ProductBO> productBO;

	public ProviderBO(ProviderTransfer provider) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public ProviderTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}