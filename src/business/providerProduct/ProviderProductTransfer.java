package business.providerProduct;

import utilities.Utils;

public class ProviderProductTransfer {
	
	private int providerId;
	private int productId;
	private int amount;
	
	public ProviderProductTransfer(int providerId, int productId, int amount) {
		super();
		this.providerId = providerId;
		this.productId = productId;
		this.amount = amount;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return Utils.ProviderId + this.providerId + Utils.JUMP +
				Utils.ProductId + this.productId + Utils.JUMP +
				(this.amount != 0 ? Utils.Amount + this.amount + Utils.JUMP : "");
	}
	
}
