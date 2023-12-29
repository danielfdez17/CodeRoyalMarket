package business.saleLine;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import business.sale.SaleBO;
import javax.persistence.NamedQueries;
import business.product.ProductBO;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByid", 
					query = "select obj from SaleLineBO obj where :id = obj.id "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findBysale", 
					query = "select obj from SaleLineBO obj where :sale = obj.sale "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByproduct", 
					query = "select obj from SaleLineBO obj where :product = obj.product "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByprice", 
					query = "select obj from SaleLineBO obj where :price = obj.price "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByamount", 
					query = "select obj from SaleLineBO obj where :amount = obj.amount "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findBysaleBO", 
					query = "select obj from SaleLineBO obj where :saleBO = obj.saleBO "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByproductBO", 
					query = "select obj from SaleLineBO obj where :productBO = obj.productBO "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findAll", 
					query = "select obj from SaleLineBO obj"),
		@NamedQuery(name = "business.saleLine.saleLineBO.findBySaleAndProduct",
					query = "select line from SaleLineBO line"
							+ "where saleBO = :saleBO"
							+ "and"
							+ "productBO = :productBO"),
})
public class SaleLineBO implements Serializable {
	private static final long serialVersionUID = 0;

	public SaleLineBO() {}

	@EmbeddedId
	private SaleLineBOEmbeddable id;
	
	@ManyToOne
	@MapsId("saleId")
	private SaleBO saleBO;
	
	@ManyToOne
	@MapsId("productId")
	private ProductBO productBO;
	
	@Version
	private int version;

	private double price;
	private int amount;
	
	
	public SaleLineBO(final SaleBO sale, final ProductBO product, final double price, final int amount) {
		this.saleBO = sale;
		this.productBO = product;
		this.price = price;
		this.amount = amount;
		this.id = new SaleLineBOEmbeddable();
	}
	
	public SaleLineBOEmbeddable getId() {
		return id;
	}

	public void setId(SaleLineBOEmbeddable id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public SaleBO getSaleBO() {
		return saleBO;
	}

	public void setSaleBO(SaleBO saleBO) {
		this.saleBO = saleBO;
	}

	public ProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(ProductBO productBO) {
		this.productBO = productBO;
	}

	public SaleLineTransfer toTransfer() {
		return new SaleLineTransfer(saleBO.getId(), productBO.getId(), price, amount);
	}
}