package business.sale;

import java.util.List;

import business.saleLine.SaleLineTransfer;

public interface SaleAS {
	
	public ShoppingCartTransfer readSale(int saleId);
	public List<SaleTransfer> readAllSales();
	public List<SaleTransfer> readAllSalesByClient(int clientId);
	public List<SaleTransfer> readAllSalesByProduct(int productId);
	public int returnSale(int saleId);
	public int returnProduct(SaleLineTransfer returnedProduct);
	public int closeSale(ShoppingCartTransfer shoppingCart);
	
}