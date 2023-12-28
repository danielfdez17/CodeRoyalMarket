package business.sale;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import business.product.ProductBO;
import business.client.ClientBO;
import business.entityManagerFactory.EMFFactory;
import business.saleLine.SaleLineBO;
import business.saleLine.SaleLineTransfer;
import utilities.BusinessException;
import utilities.Errors;

public class SaleASImp implements SaleAS {

	@Override
	public ShoppingCartTransfer readSale(int saleId) {
		ShoppingCartTransfer res = null;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			SaleBO saleBO = em.find(SaleBO.class, saleId, LockModeType.OPTIMISTIC);
			
			if (saleBO == null) {
				throw be;
			}
			
			TypedQuery<SaleLineBO> query = em.createNamedQuery("business.saleLine.SaleLineBO.findBysaleBO", SaleLineBO.class);
			query.setParameter("saleBO", saleBO);
			
			List<SaleLineTransfer> lines = new ArrayList<SaleLineTransfer>();
			
			for (SaleLineBO lineBO : query.getResultList()) {
				em.lock(lineBO, LockModeType.OPTIMISTIC);
				lines.add(lineBO.toTransfer());
			}
			
			et.commit();
			res = new ShoppingCartTransfer(saleBO.toTransfer(), lines);
			
			
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
	public List<SaleTransfer> readSales() {
		List<SaleTransfer> res = new ArrayList<SaleTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		TypedQuery<SaleBO> saleQuery = em.createNamedQuery("business.sale.SaleBO.findAll", SaleBO.class);
		
		for (SaleBO saleBO : saleQuery.getResultList()) {
			res.add(saleBO.toTransfer());
		}
		
		em.close();
		return res;
	}

	@Override
	public List<SaleTransfer> readSalesByClient(int clientId) {
		List<SaleTransfer> res = new ArrayList<SaleTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProductBO clientBO = em.find(ProductBO.class, clientId, LockModeType.OPTIMISTIC);
			
			if (clientBO == null) {
				throw be;
			}
			
			TypedQuery<SaleBO> saleQuery = em.createNamedQuery("business.sale.SaleBO.findByclientBO", SaleBO.class);
			saleQuery.setParameter("clientBO", clientBO);
			
			for (SaleBO saleBO : saleQuery.getResultList()) {
				em.lock(saleBO, LockModeType.OPTIMISTIC);
				res.add(saleBO.toTransfer());
			}
			
			et.commit();
			
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			res = new ArrayList<SaleTransfer>();
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public List<SaleTransfer> readSalesByProduct(int productId) {
		List<SaleTransfer> res = new ArrayList<SaleTransfer>();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			ProductBO productBO = em.find(ProductBO.class, productId, LockModeType.OPTIMISTIC);
			
			if (productBO == null) {
				throw be;
			}
			
			TypedQuery<SaleLineBO> query = em.createNamedQuery("business.saleLine.SaleLineBO.findByproductBO", SaleLineBO.class);
			query.setParameter("productBO", productBO);
			
			for (SaleLineBO lineBO : query.getResultList()) {
				em.lock(lineBO, LockModeType.OPTIMISTIC);
				res.add(lineBO.getSaleBO().toTransfer());
			}
			
			et.commit();
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			res = new ArrayList<SaleTransfer>();
		} finally {
			em.close();
		}
		return res;
	}

	@Override
	public int returnSale(int saleId) {
		int res = Errors.NonexistentSale;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			SaleBO saleBO = em.find(SaleBO.class, saleId);
			
			if (saleBO == null) {
				throw be;
			}
			
			TypedQuery<SaleLineBO> query = em.createNamedQuery("business.saleLine.SaleLineBO.findBysaleBO", SaleLineBO.class);
			query.setParameter("saleBO", saleBO);
			
			for (SaleLineBO saleLineBO : query.getResultList()) {
				ProductBO productBO = saleLineBO.getProductBO();
				productBO.setStock(productBO.getStock() + saleLineBO.getAmount());
				productBO.setActive(true);
			}
			
			for (SaleLineBO saleLineBO : query.getResultList()) {
				em.remove(saleLineBO);
			}
			em.merge(saleBO.getClientBO());
			saleBO.getClientBO().setActive(true);
			saleBO.getClientBO().setBalance(saleBO.getClientBO().getBalance() + saleBO.getCost());
			saleBO.setActive(false);
			saleBO.setCost(0.0);
			et.commit();
			res = saleId;
			
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
	public int returnProduct(SaleLineTransfer returnedProduct) {
		int res = Errors.NonexistentSale,
				saleId = returnedProduct.getSaleId(),
				productId = returnedProduct.getProductId(),
				amount = returnedProduct.getAmount();
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			BusinessException be = new BusinessException();
			SaleBO saleBO = em.find(SaleBO.class, saleId);
			ProductBO productBO = em.find(ProductBO.class, productId);
			
			if (saleBO == null) {
				throw be;
			}
			
			if (productBO == null) {
				res = Errors.NonexistentProduct;
				throw be;
			}
			
			TypedQuery<SaleLineBO> query = em.createNamedQuery("business.saleLine.saleLineBO.findBySaleAndProduct", SaleLineBO.class);
			query.setParameter("saleBO", saleBO);
			query.setParameter("productBO", productBO);
			
			if (query.getResultList().isEmpty()) {
				res = Errors.ProductNotBought;
				throw be;
			}
			
			SaleLineBO saleLineBO = query.getResultList().get(0);
			if (amount > saleLineBO.getAmount()) {
				res = Errors.MoreAmountThanBought;
				throw be;
			}

			productBO.setStock(productBO.getStock() + amount);
			productBO.setActive(true);
			
			saleLineBO.setAmount(saleLineBO.getAmount() - amount);
			
			if (saleLineBO.getAmount() == 0) {
				em.remove(saleLineBO);
			}
			
			saleBO.setActive(false);
			em.merge(saleBO.getClientBO());
			saleBO.getClientBO().setBalance(saleBO.getClientBO().getBalance() + (amount * saleLineBO.getPrice()));
			saleBO.setCost(saleBO.getCost() - (amount * saleLineBO.getPrice()));
			et.commit();
			res = saleId;
			
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
	public int closeSale(ShoppingCartTransfer shoppingCart) {
		int res = Errors.UnexpectedError;
		EntityManager em = EMFFactory.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		try {
			BusinessException be = new BusinessException();
			ClientBO clientBO = em.find(ClientBO.class, shoppingCart.getSale().getClientId(), LockModeType.OPTIMISTIC);
			
			if (clientBO == null) {
				res = Errors.NonexistentClient;
				throw be;
			}
			
			if (!clientBO.isActive()) {
				res = Errors.InactiveClient;
				throw be;
			}
			
			SaleBO saleBO = new SaleBO(shoppingCart.getSale());
			
			for (SaleLineTransfer line : shoppingCart.getLines()) {
				ProductBO productBO = em.find(ProductBO.class, line.getProductId());
				
				if (productBO == null) {
					res = Errors.NonexistentProduct;
					throw be;
				}
				
				if (!productBO.isActive()) {
					res = Errors.InactiveProduct;
					throw be;
				}
				
				if (line.getAmount() > productBO.getStock()) {
					res = Errors.InsufficientStock;
					throw be;
				}
				
				productBO.setStock(productBO.getStock() - line.getAmount());
				line.setPrice(productBO.getPrice());
				saleBO.setCost(saleBO.getCost() + (productBO.getPrice() * line.getAmount()));
				
			}
			
			
			if (saleBO.getCost() > clientBO.getBalance()) {
				res = Errors.NotEnoughBalance;
				throw be;
			}

			saleBO.setClientBO(clientBO);
			em.persist(saleBO);
			em.flush();
			for (SaleLineTransfer line : shoppingCart.getLines()) {
				ProductBO productBO = em.find(ProductBO.class, line.getProductId(), LockModeType.OPTIMISTIC);
				SaleLineBO saleLineBO = new SaleLineBO(saleBO, productBO, productBO.getPrice(), line.getAmount());
				em.persist(saleLineBO);
			}
			
			
			clientBO.setBalance(clientBO.getBalance() - saleBO.getCost());
			et.commit();
			res = saleBO.getId();
			shoppingCart.getSale().setId(res);
			
			
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				et.rollback();
			}
			else {
				System.out.println(e.getMessage());
				res = Errors.UnexpectedError;
			}
		} finally {
			em.close();
		}
		
		return res;
	}
	
}