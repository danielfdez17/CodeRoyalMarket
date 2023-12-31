package business.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import business.entityManagerFactory.EMFFactory;
import business.provider.ProviderBO;
import business.sale.SaleBO;
import business.saleLine.SaleLineBO;
import business.syntaxChecker.SyntaxChecker;
import business.warehouse.WarehouseBO;
import utilities.BusinessException;
import utilities.Errors;

import javax.persistence.LockModeType;

public class ProductASImp implements ProductAS {

	@Override
	public int createProduct(ProductTransfer product) {
		int res = Errors.SyntaxError;
		if (this.isValid(product)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				BusinessException be = new BusinessException();
				WarehouseBO warehouseBO = em.find(WarehouseBO.class, product.getWarehouseId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				if (warehouseBO == null) {
					res = Errors.NonexistentWarehouse;
					throw be;
				}
				
				if (!warehouseBO.isActive()) {
					res = Errors.InactiveWarehouse;
					throw be;
				}
				
				TypedQuery<ProductBO> query = em.createNamedQuery("business.product.ProductBO.findByname", ProductBO.class);
				query.setParameter("name", product.getName());
				ProductBO productBO = null;
				if (query.getResultList().isEmpty()) {
					productBO = new ProductBO(product, warehouseBO);
					em.persist(productBO);
					warehouseBO.getProducts().add(productBO);
					et.commit();
					res = productBO.getId();
					product.setId(res);
				}
				else {
					productBO = query.getResultList().get(0);
					if (productBO.isActive()) {
						res = Errors.ActiveProduct;
						throw be;
					}
					
					if (!productBO.getWarehouseBO().equals(warehouseBO)) {
						productBO.getWarehouseBO().getProducts().remove(productBO);
						productBO.setWarehouseBO(warehouseBO);
						warehouseBO.getProducts().add(productBO);
					}
					
					product.setActive(true);
					productBO = new ProductBO(product.getName(), product.getStock(), product.getPrice());
					et.commit();
					res = Errors.InactiveProduct;
				}
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
	public ProductTransfer readProduct(int productId) {
		ProductTransfer res = null;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProductBO productBO = em.find(ProductBO.class, productId, LockModeType.OPTIMISTIC);
			if (productBO == null) {
				throw be;
			}
			res = productBO.toTransfer();
			et.commit();
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				res = null;
			}
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public List<ProductTransfer> readAllProducts() {
		List<ProductTransfer> res = new ArrayList<ProductTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		TypedQuery<ProductBO> query = em.createNamedQuery("business.product.ProductBO.findAll", ProductBO.class);
		for (ProductBO p : query.getResultList()) {
			res.add(p.toTransfer());
		}
		em.close();
		return res;
	}

	@Override
	public List<ProductTransfer> readAllProductsByProvider(int providerId) {
		List<ProductTransfer> res = new ArrayList<ProductTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProviderBO providerBO = em.find(ProviderBO.class, providerId, LockModeType.OPTIMISTIC);
			if (providerBO == null) {
				providerId = Errors.NonexistentProvider;
				throw be;
			}
			
			if (!providerBO.isActive()) {
				providerId = Errors.InactiveProvider;
				throw be;
			}
			
			for (ProductBO p : providerBO.getProducts()) {
				em.lock(p, LockModeType.OPTIMISTIC);
				res.add(p.toTransfer());
			}
			et.commit();
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				providerId = Errors.UnexpectedError;
			}
			res = new ArrayList<ProductTransfer>();
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public List<ProductTransfer> readAllProductsBySale(int saleId) {
		List<ProductTransfer> res = new ArrayList<ProductTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			SaleBO saleBO = em.find(SaleBO.class, saleId, LockModeType.OPTIMISTIC);
			if (saleBO == null) {
				saleId = Errors.NonexistentSale;
				throw be;
			}
			TypedQuery<SaleLineBO> query = em.createNamedQuery("business.saleLine.SaleLineBO.findBySaleId", SaleLineBO.class);
			query.setParameter("saleId", saleId);
			
			for (SaleLineBO s : query.getResultList()) {
				em.lock(s, LockModeType.OPTIMISTIC);
				res.add(s.getProductBO().toTransfer());
			}
			et.commit();
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				saleId = Errors.UnexpectedError;
			}
			System.out.println(e.getMessage());
			res = new ArrayList<ProductTransfer>();
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public int updateProduct(ProductTransfer product) {
		int res = Errors.SyntaxError;
		if (this.isValid(product)) {
			EntityManager em = EMFFactory.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {
				BusinessException be = new BusinessException();
				ProductBO productBO = em.find(ProductBO.class, product.getId());
				WarehouseBO warehouseBO = em.find(WarehouseBO.class, product.getWarehouseId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				if (productBO == null) {
					res = Errors.NonexistentProduct;
					throw be;
				}
				
				if (warehouseBO == null) {
					res = Errors.NonexistentWarehouse;
					throw be;
				}
				
				if (!warehouseBO.isActive()) {
					res = Errors.InactiveWarehouse;
					throw be;
				}
				
				if (!productBO.getWarehouseBO().equals(warehouseBO)) {
					productBO.getWarehouseBO().getProducts().remove(productBO);
					productBO.setWarehouseBO(warehouseBO);
					warehouseBO.getProducts().add(productBO);
				}
				
				product.setActive(true);
				productBO = new ProductBO(product);
				et.commit();
				res = product.getId();
				
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
	public int deleteProduct(int productId) {
		int res = Errors.SyntaxError;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProductBO productBO = em.find(ProductBO.class, productId);
			if (productBO == null) {
				res = Errors.NonexistentProduct;
				throw be;
			}
			
			if (!productBO.isActive()) {
				res = Errors.InactiveProduct;
				throw be;
			}
			
			boolean productWithActiveProviders = false;
			for (ProviderBO p : productBO.getProviders()) {
				if (p.isActive()) {
					productWithActiveProviders = true;
					break;
				}
			}
			if (productWithActiveProviders) {
				res = Errors.ProductWithActiveProviders;
				throw be;
			}
			productBO.getProviders().removeAll(productBO.getProviders());
			productBO.setActive(false);
			et.commit();
			res = productId;
			
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
	private boolean isValid(ProductTransfer product) {
		return product != null &&
				SyntaxChecker.isName(product.getName()) &&
				product.getStock() >= 0 && 
				product.getPrice() > 0.0 &&
				product.getWarehouseId() > 0;
	}
}