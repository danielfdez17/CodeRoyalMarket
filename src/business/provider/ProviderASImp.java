package business.provider;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import business.entityManagerFactory.EMFFactory;
import business.product.ProductBO;
import business.providerProduct.ProviderProductTransfer;
import business.syntaxChecker.SyntaxChecker;
import utilities.BusinessException;
import utilities.Errors;

public class ProviderASImp implements ProviderAS {

	@Override
	public int createProvider(ProviderTransfer provider) {
		int res = Errors.SyntaxError;
		if (this.isValid(provider)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				
				BusinessException be = new BusinessException();
				TypedQuery<ProviderBO> query = em.createNamedQuery("business.provider.ProviderBO.findByname", ProviderBO.class);
				query.setParameter("name", provider.getName());
				ProviderBO providerBO = null;
				if (query.getResultList().isEmpty()) {
					providerBO = new ProviderBO(provider);
					em.persist(providerBO);
					et.commit();
					res = providerBO.getId();
					provider.setId(res);
				}
				else {
					providerBO = query.getResultList().get(0);
					
					if (providerBO.isActive()) {
						res = Errors.ActiveProvider;
						throw be;
					}
					
					providerBO.setActive(true);
					provider.setActive(true);
					providerBO = new ProviderBO(provider.getName(), provider.getPhoneNumber());
					et.commit();
					provider.setId(providerBO.getId());
					res = Errors.InactiveProvider;
					
				}
				
			} catch (Exception e) {
				if (e instanceof BusinessException) {
					et.rollback();
				}
				else {
					res = Errors.UnexpectedError;
				}
			} finally {
				em.clear();
			}
		}
		return res;
	}

	@Override
	public ProviderTransfer readProvider(int providerId) {
		ProviderTransfer res = null;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProviderBO providerBO = em.find(ProviderBO.class, providerId, LockModeType.OPTIMISTIC);
			
			if (providerBO == null) {
				throw be;
			}
			
			et.commit();
			res = providerBO.toTransfer();
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			res = null;
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public List<ProviderTransfer> readAllProviders() {
		List<ProviderTransfer> res = new ArrayList<ProviderTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		TypedQuery<ProviderBO> query = em.createNamedQuery("business.provider.ProviderBO.findAll", ProviderBO.class);
		for (ProviderBO p : query.getResultList()) {
			res.add(p.toTransfer());
		}
		em.close();
		return res;
	}

	@Override
	public List<ProviderTransfer> readAllProvidersByProduct(int productId) {
		List<ProviderTransfer> res = new ArrayList<ProviderTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			
			BusinessException be = new BusinessException();
			ProductBO productBO = em.find(ProductBO.class, productId, LockModeType.OPTIMISTIC);
			
			if (productBO == null || !productBO.isActive()) {
				throw be;
			}
			
			for (ProviderBO p : productBO.getProviders()) {
				em.lock(p, LockModeType.OPTIMISTIC);
				res.add(p.toTransfer());
			}
			
			et.commit();
			
		} catch(Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			res = new ArrayList<ProviderTransfer>();
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public int assignProduct(ProviderProductTransfer providerProduct) {
		int res = 1;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProviderBO providerBO = em.find(ProviderBO.class, providerProduct.getProviderId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			ProductBO productBO = em.find(ProductBO.class, providerProduct.getProductId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if (providerBO == null) {
				res = Errors.NonexistentProvider;
				throw be;
			}
			
			if (!providerBO.isActive()) {
				res = Errors.InactiveProvider;
				throw be;
			}
			
			if (productBO == null) {
				res = Errors.NonexistentProduct;
				throw be;
			}
			
			if (!productBO.isActive()) {
				res = Errors.InactiveProduct;
				throw be;
			}
			
			if (providerBO.getProducts().contains(productBO) && productBO.getProviders().contains(providerBO)) {
				res = Errors.ProductAlreadyAssigned;
				throw be;
			}
			
			providerBO.getProducts().add(productBO);
			productBO.getProviders().add(providerBO);
			et.commit();
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				res = Errors.UnexpectedError;
			}
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public int unassignProduct(ProviderProductTransfer providerProduct) {
		int res = 1;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProviderBO providerBO = em.find(ProviderBO.class, providerProduct.getProviderId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			ProductBO productBO = em.find(ProductBO.class, providerProduct.getProductId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if (providerBO == null) {
				res = Errors.NonexistentProvider;
				throw be;
			}
			
			if (!providerBO.isActive()) {
				res = Errors.InactiveProvider;
				throw be;
			}
			
			if (productBO == null) {
				res = Errors.NonexistentProduct;
				throw be;
			}
			
			if (!productBO.isActive()) {
				res = Errors.InactiveProduct;
				throw be;
			}
			
			if (!providerBO.getProducts().contains(productBO) && !productBO.getProviders().contains(providerBO)) {
				res = Errors.ProductAlreadyUnassigned;
				throw be;
			}
			
			providerBO.getProducts().remove(productBO);
			productBO.getProviders().remove(providerBO);
			et.commit();
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				res = Errors.UnexpectedError;
			}
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public int provideProduct(ProviderProductTransfer providerProduct) {
		int res = 1;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProviderBO providerBO = em.find(ProviderBO.class, providerProduct.getProviderId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			ProductBO productBO = em.find(ProductBO.class, providerProduct.getProductId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if (providerBO == null) {
				res = Errors.NonexistentProvider;
				throw be;
			}
			
			if (!providerBO.isActive()) {
				res = Errors.InactiveProvider;
				throw be;
			}
			
			if (productBO == null) {
				res = Errors.NonexistentProduct;
				throw be;
			}
			
			if (!productBO.isActive()) {
				res = Errors.InactiveProduct;
				throw be;
			}
			
			if (!providerBO.getProducts().contains(productBO) && !productBO.getProviders().contains(providerBO)) {
				res = Errors.ProductAlreadyUnassigned;
				throw be;
			}
			
			productBO.setStock(productBO.getStock() + providerProduct.getAmount());
			et.commit();
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				res = Errors.UnexpectedError;
			}
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public int updateProvider(ProviderTransfer provider) {
		int res = Errors.SyntaxError;
		if (this.isValid(provider)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				BusinessException be = new BusinessException();
				ProviderBO providerBO = em.find(ProviderBO.class, provider.getId());
				
				if (providerBO == null) {
					res = Errors.NonexistentProvider;
					throw be;
				}
				
				providerBO.setActive(true);
				providerBO.setName(provider.getName());
				providerBO.setPhoneNumber(provider.getPhoneNumber());
				et.commit();
				res = providerBO.getId();
				
			} catch (Exception e) {
				if (e instanceof BusinessException) {
					et.rollback();
				}
				else {
					res = Errors.UnexpectedError;
				}
			} finally {
				em.close();
			}
		}
		return res;
	}

	@Override
	public int deleteProvider(int providerId) {
		int res = Errors.NonexistentProvider;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProviderBO providerBO = em.find(ProviderBO.class, providerId);
			
			if (providerBO == null) {
				res = Errors.NonexistentProvider;
				throw be;
			}
			
			if (!providerBO.isActive()) {
				res = Errors.InactiveProvider;
				throw be;
			}
			
			boolean providerWithActiveProducts = false;
			
			for (ProductBO p : providerBO.getProducts()) {
				em.lock(p, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				if (p.isActive()) {
					providerWithActiveProducts = true;
					break;
				}
				else {
					p.getProviders().remove(providerBO);
				}
			}
			
			if (providerWithActiveProducts) {
				res = Errors.ActiveProductsAssigned;
				throw be;
			}
			
			providerBO.setActive(false);
			providerBO.getProducts().removeAll(providerBO.getProducts());
			et.commit();
			res = providerId;
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				res = Errors.UnexpectedError;
			}
		} finally {
			em.close();
		}
		return res;
	}
	
	private boolean isValid(ProviderTransfer provider) {
		return provider != null &&
				SyntaxChecker.isName(provider.getName()) &&
				SyntaxChecker.isPhoneNumber(provider.getPhoneNumber() + "");
		}
}