/**
 * 
 */
package business.provider;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Set;
import business.product.ProductBO;
import javax.persistence.ManyToMany;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author danie
* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "business.provider.ProviderBO.findByid", query = "select obj from ProviderBO obj where :id = obj.id "),
		@NamedQuery(name = "business.provider.ProviderBO.findByversion", query = "select obj from ProviderBO obj where :version = obj.version "),
		@NamedQuery(name = "business.provider.ProviderBO.findByname", query = "select obj from ProviderBO obj where :name = obj.name "),
		@NamedQuery(name = "business.provider.ProviderBO.findByphoneNumber", query = "select obj from ProviderBO obj where :phoneNumber = obj.phoneNumber "),
		@NamedQuery(name = "business.provider.ProviderBO.findByactive", query = "select obj from ProviderBO obj where :active = obj.active "),
		@NamedQuery(name = "business.provider.ProviderBO.findByproductBO", query = "select obj from ProviderBO obj where :productBO MEMBER OF obj.productBO ") })
public class ProviderBO implements Serializable {
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
	public ProviderBO() {
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
	private int phoneNumber;
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
	@ManyToMany
	private Set<ProductBO> productBO;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param provider
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public ProviderBO(ProviderTransfer provider) {
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
	public ProviderTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}