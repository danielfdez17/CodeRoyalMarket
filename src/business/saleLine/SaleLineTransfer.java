package business.saleLine;

import utilities.Utils;

public class SaleLineTransfer {
	
	private int saleId;
	private int productId;
	private double price;
	private int amount;
	
	public SaleLineTransfer(int saleId, int productId, double price, int amount) {
		super();
		this.saleId = saleId;
		this.productId = productId;
		this.price = price;
		this.amount = amount;
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

	@Override
	public String toString() {
		return "Sale ID: " + this.saleId + Utils.JUMP +
				"Product ID: " + this.productId + Utils.JUMP +
				"Product price: " + this.price + Utils.JUMP +
				"Amount: " + this.amount + Utils.JUMP;
	}
	
}