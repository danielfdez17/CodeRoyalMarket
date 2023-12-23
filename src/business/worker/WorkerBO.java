/**
 * 
 */
package business.worker;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
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
		@NamedQuery(name = "business.worker.WorkerBO.findByid", query = "select obj from WorkerBO obj where :id = obj.id "),
		@NamedQuery(name = "business.worker.WorkerBO.findByversion", query = "select obj from WorkerBO obj where :version = obj.version "),
		@NamedQuery(name = "business.worker.WorkerBO.findBynif", query = "select obj from WorkerBO obj where :nif = obj.nif "),
		@NamedQuery(name = "business.worker.WorkerBO.findByname", query = "select obj from WorkerBO obj where :name = obj.name "),
		@NamedQuery(name = "business.worker.WorkerBO.findByactive", query = "select obj from WorkerBO obj where :active = obj.active "),
		@NamedQuery(name = "business.worker.WorkerBO.findBywarehouseBO", query = "select obj from WorkerBO obj where :warehouseBO = obj.warehouseBO ") })
public class WorkerBO implements Serializable {
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
	public WorkerBO() {
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
	protected String nif;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	protected String name;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	protected boolean active;
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
	* @param worker
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public WorkerBO(WorkerTransfer worker) {
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
	public WorkerTransfer toTransfer() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}