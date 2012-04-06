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
package org.wso2.developerstudio.eclipse.esb.mediators.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.wso2.developerstudio.eclipse.esb.ModelObjectState;
import org.wso2.developerstudio.eclipse.esb.mediators.IterateTarget;
import org.wso2.developerstudio.eclipse.esb.mediators.MediatorsPackage;
import org.wso2.developerstudio.eclipse.esb.provider.AbstractCommonTargetItemProvider;
import org.wso2.developerstudio.eclipse.esb.provider.EsbEditPlugin;

/**
 * This is the item provider adapter for a {@link org.wso2.developerstudio.eclipse.esb.mediators.IterateTarget} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IterateTargetItemProvider
	extends AbstractCommonTargetItemProvider
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
	public IterateTargetItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors != null) {
			itemPropertyDescriptors.clear();
		}			
		super.getPropertyDescriptors(object);

		// These two properties should come before super class's properties.
		// We have customized add property descriptor methods to achive this.
		addSoapActionPropertyDescriptor(object);
		addToAddressPropertyDescriptor(object);				
				
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Soap Action feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected void addSoapActionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(0, createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IterateTarget_soapAction_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IterateTarget_soapAction_feature", "_UI_IterateTarget_type"),
				 MediatorsPackage.Literals.ITERATE_TARGET__SOAP_ACTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the To Address feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected void addToAddressPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(0, createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IterateTarget_toAddress_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IterateTarget_toAddress_feature", "_UI_IterateTarget_type"),
				 MediatorsPackage.Literals.ITERATE_TARGET__TO_ADDRESS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns IterateTarget.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IterateTarget"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	
	public String getText(Object object) {
		return getString("_UI_IterateTarget_type");
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

		switch (notification.getFeatureID(IterateTarget.class)) {
			case MediatorsPackage.ITERATE_TARGET__SOAP_ACTION:
			case MediatorsPackage.ITERATE_TARGET__TO_ADDRESS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	public ResourceLocator getResourceLocator() {
		return EsbEditPlugin.INSTANCE;
	}

}
