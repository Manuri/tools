/*
 * Copyright 2009-2010 WSO2, Inc. (http://wso2.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.developerstudio.eclipse.esb.impl;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.w3c.dom.Element;
import org.wso2.developerstudio.eclipse.esb.EsbPackage;
import org.wso2.developerstudio.eclipse.esb.ProxyOutSequenceConfiguration;
import org.wso2.developerstudio.eclipse.esb.RegistryKeyProperty;
import org.wso2.developerstudio.eclipse.esb.core.utils.ESBMediaTypeConstants;
import org.wso2.developerstudio.eclipse.esb.util.ObjectValidator;
import org.wso2.developerstudio.eclipse.platform.core.utils.CSProviderConstants;
import org.wso2.developerstudio.eclipse.platform.core.utils.DeveloperStudioProviderUtils;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Proxy Out Sequence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ProxyOutSequenceConfigurationImpl extends AbstractProxySequenceConfigurationImpl implements ProxyOutSequenceConfiguration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected ProxyOutSequenceConfigurationImpl() {
		super();
		
		// Sequence key.
		RegistryKeyProperty sequenceKey = getEsbFactory().createRegistryKeyProperty();
		//Set filter properties to filter in only sequences
		DeveloperStudioProviderUtils.addFilter((Map<String, List<String>>)sequenceKey.getFilters(), CSProviderConstants.FILTER_MEDIA_TYPE, ESBMediaTypeConstants.MEDIA_TYPE_SEQUENCE);

		sequenceKey.setPrettyName("Out Sequence");
		sequenceKey.setKeyName("outSequence");
		sequenceKey.setKeyValue(DEFAULT_SEQUENCE_REFERENCE_REGISTRY_KEY);
		setSequenceKey(sequenceKey);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasSourceRepresentation() {
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected void doLoad(Element self) throws Exception {
		loadContent(self, "outSequence");
	}

	/**
	 * {@inheritDoc}
	 */
	protected Element doSave(Element parent) throws Exception {
		Element elem=saveContent(parent, "outSequence");
		addComments(elem);
		return elem;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	protected EClass eStaticClass() {
		return EsbPackage.Literals.PROXY_OUT_SEQUENCE_CONFIGURATION;
	}

	
    public Map<String, ObjectValidator> validate() {
	    // TODO Auto-generated method stub
	    return null;
    }

} //ProxyOutSequenceImpl
