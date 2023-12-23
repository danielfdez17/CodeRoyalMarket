package business.saleLine;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "business.saleLine.SaleLineBOEmbeddable.findBysaleId", query = "select obj from SaleLineBOEmbeddable obj where :saleId = obj.saleId "),
		@NamedQuery(name = "business.saleLine.SaleLineBOEmbeddable.findByproductId", query = "select obj from SaleLineBOEmbeddable obj where :productId = obj.productId ") })
public class SaleLineBOEmbeddable implements Serializable {
	private static final long serialVersionUID = 0;

	public SaleLineBOEmbeddable() {
	}

	@Id
	private int saleId;
	private int productId;
}