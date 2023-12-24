package business.product;

import java.util.List;

public interface ProductAS {
	
	public int createProduct(ProductTransfer product);
	public ProductTransfer readProduct(int productId);
	public List<ProductTransfer> readProducts();
	public List<ProductTransfer> readProductsByProvider(int providerId);
	public List<ProductTransfer> readProductsBySale(int saleId);
	public int updateProduct(ProductTransfer product);
	public int deleteProduct(int productId);
}