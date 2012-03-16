/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wso2.developerstudio.eclipse.humantask.model.ht;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TText</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wso2.developerstudio.eclipse.humantask.model.ht.TText#getLang <em>Lang</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wso2.developerstudio.eclipse.humantask.model.ht.HTPackage#getTText()
 * @model extendedMetaData="name='tText' kind='mixed'"
 * @generated
 */
public interface TText extends TExtensibleMixedContentElements {
	/**
	 * Returns the value of the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Attempting to install the relevant ISO 2- and 3-letter
	 *          codes as the enumerated possible values is probably never
	 *          going to be a realistic possibility.  See
	 *          RFC 3066 at http://www.ietf.org/rfc/rfc3066.txt and the IANA registry
	 *          at http://www.iana.org/assignments/lang-tag-apps.htm for
	 *          further information.
	 * 
	 *          The union allows for the 'un-declaration' of xml:lang with
	 *          the empty string.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lang</em>' attribute.
	 * @see #setLang(String)
	 * @see org.wso2.developerstudio.eclipse.humantask.model.ht.HTPackage#getTText_Lang()
	 * @model dataType="org.eclipse.emf.ecore.xml.namespace.LangType"
	 *        extendedMetaData="kind='attribute' name='lang' namespace='http://www.w3.org/XML/1998/namespace'"
	 * @generated
	 */
	String getLang();

	/**
	 * Sets the value of the '{@link org.wso2.developerstudio.eclipse.humantask.model.ht.TText#getLang <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lang</em>' attribute.
	 * @see #getLang()
	 * @generated
	 */
	void setLang(String value);

} // TText
