/**
 * 
 */
package business.product;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Set;
import business.provider.ProviderBO;
import javax.persistence.ManyToMany;
import business.warehouse.WarehouseBO;
import javax.persistence.ManyToOne;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author danie
* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "business.product.ProductBO.findByid", query = "select obj from ProductBO obj where :id = obj.id "),
		@NamedQuery(name = "business.product.ProductBO.findByversion", query = "select obj from ProductBO obj where :version = obj.version "),
		@NamedQuery(name = "business.product.ProductBO.findByname", query = "select obj from ProductBO obj where :name = obj.name "),
		@NamedQuery(name = "business.product.ProductBO.findBystock", query = "select obj from ProductBO obj where :stock = obj.stock "),
		@NamedQuery(name = "business.product.ProductBO.findByprice", query = "select obj from ProductBO obj where :price = obj.price "),
		@NamedQuery(name = "business.product.ProductBO.findByactive", query = "select obj from ProductBO obj where :active = obj.active "),
		@NamedQuery(name = "business.product.ProductBO.findByproviderBO", query = "select obj from ProductBO obj where :providerBO MEMBER OF obj.providerBO "),
		@NamedQuery(name = "business.product.ProductBO.findBywarehouseBO", query = "select obj from ProductBO obj where :warehouseBO = obj.warehouseBO ") })
public class ProductBO implements Serializable {
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
	public ProductBO() {
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@Id
	private int id;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private int version;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private String name;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private int stock;
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
	private boolean active;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@ManyToMany(mappedBy = "productBO")
	private Set<ProviderBO> providerBO;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	@ManyToOne
	private WarehouseBO warehouseBO;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param product
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public ProductBO(ProductTransfer product) {
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
	public ProductTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}