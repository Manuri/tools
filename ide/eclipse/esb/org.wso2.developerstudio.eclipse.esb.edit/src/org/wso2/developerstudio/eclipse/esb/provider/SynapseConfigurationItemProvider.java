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
package org.wso2.developerstudio.eclipse.esb.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.wso2.developerstudio.eclipse.esb.EsbFactory;
import org.wso2.developerstudio.eclipse.esb.EsbPackage;
import org.wso2.developerstudio.eclipse.esb.ModelObjectState;
import org.wso2.developerstudio.eclipse.esb.SynapseConfiguration;
import org.wso2.developerstudio.eclipse.esb.mediators.MediatorsFactory;

/**
 * This is the item provider adapter for a {@link org.wso2.developerstudio.eclipse.esb.SynapseConfiguration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SynapseConfigurationItemProvider
	extends ModelObjectItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynapseConfigurationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addSchemaLocationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Schema Location feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSchemaLocationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SynapseConfiguration_schemaLocation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SynapseConfiguration_schemaLocation_feature", "_UI_SynapseConfiguration_type"),
				 EsbPackage.Literals.SYNAPSE_CONFIGURATION__SCHEMA_LOCATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns SynapseConfiguration.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SynapseConfiguration"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	
	public String getText(Object object) {
		return getString("_UI_SynapseConfiguration_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(SynapseConfiguration.class)) {
			case EsbPackage.SYNAPSE_CONFIGURATION__SCHEMA_LOCATION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case EsbPackage.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);				
		newChildDescriptors.add
			(createChildParameter
				(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
				 EsbFactory.eINSTANCE.createMediatorSequence()));

		newChildDescriptors.add
			(createChildParameter
				(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
				 EsbFactory.eINSTANCE.createDefaultEndPoint()));

		newChildDescriptors.add
			(createChildParameter
				(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
				 EsbFactory.eINSTANCE.createAddressEndPoint()));

		newChildDescriptors.add
			(createChildParameter
				(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
				 EsbFactory.eINSTANCE.createWsdlEndPoint()));

		newChildDescriptors.add
			(createChildParameter
				(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
				 EsbFactory.eINSTANCE.createFailoverEndPoint()));

		newChildDescriptors.add
			(createChildParameter
				(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
				 EsbFactory.eINSTANCE.createLoadBalanceEndPoint()));
		
		newChildDescriptors.add
		(createChildParameter
			(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
			 EsbFactory.eINSTANCE.createDynamicLoadBalanceEndPoint()));

		newChildDescriptors.add
			(createChildParameter
				(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
				 EsbFactory.eINSTANCE.createProxyService()));
		
		newChildDescriptors.add
		(createChildParameter
			(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
			 EsbFactory.eINSTANCE.createLocalEntry()));
		
		newChildDescriptors.add
		(createChildParameter
			(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
			 EsbFactory.eINSTANCE.createTask()));
		
		newChildDescriptors.add
		(createChildParameter
			(EsbPackage.Literals.SYNAPSE_CONFIGURATION__CONFIGURATION_ELEMENTS,
			 EsbFactory.eINSTANCE.createSynapseAPI()));
	}
}