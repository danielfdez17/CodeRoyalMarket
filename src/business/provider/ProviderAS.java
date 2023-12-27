package business.provider;

import java.util.List;

import business.providerProduct.ProviderProductTransfer;

public interface ProviderAS {
	
	public int createProvider(ProviderTransfer provider);
	public ProviderTransfer readProvider(int providerId);
	public List<ProviderTransfer> readProviders();
	public List<ProviderTransfer> readProvidersByProduct(int productId);
	public int assignProduct(ProviderProductTransfer providerProduct);
	public int unassignProduct(ProviderProductTransfer providerProduct);
	public int provideProduct(ProviderProductTransfer providerProduct);
	public int updateProvider(ProviderTransfer provider);
	public int deleteProvider(int providerId);
	
}