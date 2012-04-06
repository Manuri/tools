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
package org.wso2.developerstudio.eclipse.esb.mediators.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.wso2.developerstudio.eclipse.esb.impl.AbstractBooleanFeatureImpl;
import org.wso2.developerstudio.eclipse.esb.mediators.MediatorsPackage;
import org.wso2.developerstudio.eclipse.esb.mediators.ValidateFeature;
import org.wso2.developerstudio.eclipse.esb.util.ObjectValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ValidateFeatureImpl extends AbstractBooleanFeatureImpl implements ValidateFeature {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidateFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	protected EClass eStaticClass() {
		return MediatorsPackage.Literals.VALIDATE_FEATURE;
	}

	
    public Map<String, ObjectValidator> validate() {
	    // TODO Auto-generated method stub
	    return null;
    }

} // FeatureImpl
