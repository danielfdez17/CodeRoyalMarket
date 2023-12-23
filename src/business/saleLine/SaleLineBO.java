/**
 * 
 */
package business.saleLine;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.NamedQuery;
import business.sale.SaleBO;
import javax.persistence.NamedQueries;
import business.product.ProductBO;
import javax.persistence.ManyToOne;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author danie
* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByid", query = "select obj from SaleLineBO obj where :id = obj.id "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findBysale", query = "select obj from SaleLineBO obj where :sale = obj.sale "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByproduct", query = "select obj from SaleLineBO obj where :product = obj.product "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByprice", query = "select obj from SaleLineBO obj where :price = obj.price "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByamount", query = "select obj from SaleLineBO obj where :amount = obj.amount "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findBysaleBO", query = "select obj from SaleLineBO obj where :saleBO = obj.saleBO "),
		@NamedQuery(name = "business.saleLine.SaleLineBO.findByproductBO", query = "select obj from SaleLineBO obj where :productBO = obj.productBO ") })
public class SaleLineBO implements Serializable {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public SaleLineBO() {
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@OneToOne
	@Id
	private SaleLineBOEmbeddable id;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@OneToOne
	private SaleBO sale;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@OneToOne
	private ProductBO product;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private double price;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private int amount;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@ManyToOne
	private SaleBO saleBO;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@ManyToOne
	private ProductBO productBO;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param sale
	* @param product
	* @param price
	* @param amount
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public SaleLineBO(SaleBO sale, ProductBO product, double price, int amount) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public SaleLineTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}