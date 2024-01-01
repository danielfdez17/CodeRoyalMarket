package business.product;

import java.util.List;

public interface ProductAS {
	
	public int createProduct(ProductTransfer product);
	public ProductTransfer readProduct(int productId);
	public List<ProductTransfer> readAllProducts();
	public List<ProductTransfer> readAllProductsByProvider(int providerId);
	public List<ProductTransfer> readAllProductsBySale(int saleId);
	public int updateProduct(ProductTransfer product);
	public int deleteProduct(int productId);
}