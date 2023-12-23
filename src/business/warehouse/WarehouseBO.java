/**
 * 
 */
package business.warehouse;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Set;
import business.product.ProductBO;
import javax.persistence.OneToMany;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author danie
* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByid", query = "select obj from WarehouseBO obj where :id = obj.id "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByversion", query = "select obj from WarehouseBO obj where :version = obj.version "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByname", query = "select obj from WarehouseBO obj where :name = obj.name "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findBycity", query = "select obj from WarehouseBO obj where :city = obj.city "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByactive", query = "select obj from WarehouseBO obj where :active = obj.active "),
		@NamedQuery(name = "business.warehouse.WarehouseBO.findByproducts", query = "select obj from WarehouseBO obj where :products MEMBER OF obj.products ") })
public class WarehouseBO implements Serializable {
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
	public WarehouseBO() {
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
	private String city;
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
	@OneToMany
	private Set<ProductBO> products;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param warehouse
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public WarehouseBO(WarehouseTransfer warehouse) {
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
	public WarehouseTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}