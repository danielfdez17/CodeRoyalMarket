package business.saleLine;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.NamedQuery;
import business.sale.SaleBO;
import javax.persistence.NamedQueries;
import business.product.ProductBO;
import javax.persistence.ManyToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByid", query = "select obj from SaleLineBO obj where :id = obj.id "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findBysale", query = "select obj from SaleLineBO obj where :sale = obj.sale "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByproduct", query = "select obj from SaleLineBO obj where :product = obj.product "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByprice", query = "select obj from SaleLineBO obj where :price = obj.price "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByamount", query = "select obj from SaleLineBO obj where :amount = obj.amount "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findBysaleBO", query = "select obj from SaleLineBO obj where :saleBO = obj.saleBO "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByproductBO", query = "select obj from SaleLineBO obj where :productBO = obj.productBO ") })
public class SaleLineBO implements Serializable {
	private static final long serialVersionUID = 0;

	public SaleLineBO() {
	}

	@OneToOne
	@Id
	private SaleLineBOEmbeddable id;
	@OneToOne
	private SaleBO sale;
	@OneToOne
	private ProductBO product;
	private double price;
	private int amount;
	@ManyToOne
	private SaleBO saleBO;
	@ManyToOne
	private ProductBO productBO;

	public SaleLineBO(SaleBO sale, ProductBO product, double price, int amount) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public SaleLineTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}