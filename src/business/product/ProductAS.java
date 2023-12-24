package business.product;

import java.util.List;

public interface ProductAS {
	
	public static final int UnespectedError = 0;
	public static final int SintaxError = -1;
	public static final int NonexistentWarehouse = -2;
	public static final int InactiveWarehouse = -3;
	public static final int NonexistentProduct = -4;
	public static final int ActiveProduct = -5;
	public static final int InactiveProduct = -6;
	
	public int createProduct(ProductTransfer product);
	public ProductTransfer readProduct(int productId);
	public List<ProductTransfer> readProducts();
	public List<ProductTransfer> readProductsByProvider(int providerId);
	public List<ProductTransfer> readProductsBySale(int saleId);
	public int updateProduct(ProductTransfer product);
	public int deleteProduct(int productId);
}