package business.product;

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
import business.provider.ProviderBO;
import javax.persistence.ManyToMany;
import business.warehouse.WarehouseBO;
import javax.persistence.ManyToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.product.ProductBO.findByid", 
					query = "select obj from ProductBO obj where :id = obj.id "),
		@NamedQuery(name = "business.product.ProductBO.findByversion", 
					query = "select obj from ProductBO obj where :version = obj.version "),
		@NamedQuery(name = "business.product.ProductBO.findByname", 
					query = "select obj from ProductBO obj where :name = obj.name "),
		@NamedQuery(name = "business.product.ProductBO.findBystock", 
					query = "select obj from ProductBO obj where :stock = obj.stock "),
		@NamedQuery(name = "business.product.ProductBO.findByprice", 
					query = "select obj from ProductBO obj where :price = obj.price "),
		@NamedQuery(name = "business.product.ProductBO.findByactive", 
					query = "select obj from ProductBO obj where :active = obj.active "),
//		@NamedQuery(name = "business.product.ProductBO.findByproviderBO", 
//					query = "select obj from ProductBO obj where :providerBO MEMBER OF obj.providerBO "),
		@NamedQuery(name = "business.product.ProductBO.findBywarehouseBO", 
					query = "select obj from ProductBO obj where :warehouseBO = obj.warehouseBO "),
		@NamedQuery(name = "business.product.ProductBO.findAll", 
					query = "select obj from ProductBO obj"),
//		@NamedQuery(name = "business.product.ProductBO.findAllBySale", 
//					query = "select sl from SaleLineBO sl where "
//							+ "sl.saleBO.id = :saleId"),
})
public class ProductBO implements Serializable {
	private static final long serialVersionUID = 0;

	public ProductBO() {}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Version
	private int version;
	@Column(unique = true, nullable = false)
	private String name;
	private int stock;
	private double price;
	private boolean active;
	
	@ManyToMany
	private List<ProviderBO> providers;
	@ManyToOne
	private WarehouseBO warehouseBO;
	
	public ProductBO(String name, int stock, double price) {
		super();
		this.setName(name);
		this.setStock(stock);
		this.setPrice(price);
		this.setActive(true);
	}
	
	public ProductBO(int id, String name, int stock, double price, boolean active) {
		this(name, stock, price);
		this.setId(id);
		this.setActive(active);
	}

	public ProductBO(ProductTransfer product) {
		this(product.getId(), product.getName(), product.getStock(), product.getPrice(), product.isActive());
	}
	
	public ProductBO(ProductTransfer product, WarehouseBO warehouseBO) {
		this(product.getId(), product.getName(), product.getStock(), product.getPrice(), product.isActive());
		this.warehouseBO = warehouseBO;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ProviderBO> getProviders() {
		return providers;
	}

	public void setProviders(List<ProviderBO> providers) {
		this.providers = providers;
	}

	public WarehouseBO getWarehouseBO() {
		return warehouseBO;
	}

	public void setWarehouseBO(WarehouseBO warehouseBO) {
		this.warehouseBO = warehouseBO;
	}

	public ProductTransfer toTransfer() {
		return new ProductTransfer(id, name, stock, price, warehouseBO.getId(), active);
	}
}