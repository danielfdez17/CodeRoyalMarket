/**
 * 
 */
package business.client;

import java.util.Set;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author danie
* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public interface ClientAS {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param client
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int createClient(ClientTransfer client);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param clientId
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public ClientTransfer readClient(int clientId);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Set<ClientTransfer> readAllCllients();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param client
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int updateClient(ClientTransfer client);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param clientId
	* @return
	* @generated "UML to JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int deleteClient(int clientId);
}