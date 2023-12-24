package business.saleLine;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Embeddable
public class SaleLineBOEmbeddable implements Serializable {
	private static final long serialVersionUID = 0;

	public SaleLineBOEmbeddable() {}

	private int saleId;
	private int productId;

	public SaleLineBOEmbeddable(int saleId, int productId) {
		super();
		this.saleId = saleId;
		this.productId = productId;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}