/**
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
 *
 * $Id$
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
import org.wso2.developerstudio.eclipse.esb.mediators.MediatorsPackage;
import org.wso2.developerstudio.eclipse.esb.mediators.SmooksOutConfiguration;
import org.wso2.developerstudio.eclipse.esb.provider.EsbEditPlugin;
import org.wso2.developerstudio.eclipse.esb.provider.ModelObjectItemProvider;

/**
 * This is the item provider adapter for a {@link org.wso2.developerstudio.eclipse.esb.mediators.SmooksOutConfiguration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SmooksOutConfigurationItemProvider
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
	public SmooksOutConfigurationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @!generated
	 */
	
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		SmooksOutConfiguration outConfiguration=(SmooksOutConfiguration)object;
		if (itemPropertyDescriptors != null) {
			itemPropertyDescriptors.clear();
		}
		super.getPropertyDescriptors(object);

		addTypePropertyDescriptor(object);
		addOutputMethodPropertyDescriptor(object);
		switch(outConfiguration.getOutputMethod()){
			case EXPRESSION:
				addExpressionPropertyDescriptor(object);
				addActionPropertyDescriptor(object);
				break;
			case PROPERTY:
				addPropertyPropertyDescriptor(object);	
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SmooksOutConfiguration_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SmooksOutConfiguration_type_feature", "_UI_SmooksOutConfiguration_type"),
				 MediatorsPackage.Literals.SMOOKS_OUT_CONFIGURATION__TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Expression feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SmooksOutConfiguration_expression_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SmooksOutConfiguration_expression_feature", "_UI_SmooksOutConfiguration_type"),
				 MediatorsPackage.Literals.SMOOKS_OUT_CONFIGURATION__EXPRESSION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Property feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPropertyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SmooksOutConfiguration_property_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SmooksOutConfiguration_property_feature", "_UI_SmooksOutConfiguration_type"),
				 MediatorsPackage.Literals.SMOOKS_OUT_CONFIGURATION__PROPERTY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Action feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SmooksOutConfiguration_action_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SmooksOutConfiguration_action_feature", "_UI_SmooksOutConfiguration_type"),
				 MediatorsPackage.Literals.SMOOKS_OUT_CONFIGURATION__ACTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Output Method feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutputMethodPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SmooksOutConfiguration_outputMethod_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SmooksOutConfiguration_outputMethod_feature", "_UI_SmooksOutConfiguration_type"),
				 MediatorsPackage.Literals.SMOOKS_OUT_CONFIGURATION__OUTPUT_METHOD,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns SmooksOutConfiguration.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SmooksOutConfiguration"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @!generated
	 */
	
	public String getText(Object object) {
//		ModelObjectState labelValue = ((SmooksOutConfiguration)object).getObjectState();
//		String label = labelValue == null ? null : labelValue.toString();
//		return label == null || label.length() == 0 ?
//			getString("_UI_SmooksOutConfiguration_type") :
//			getString("_UI_SmooksOutConfiguration_type") + " " + label;
		SmooksOutConfiguration outConfiguration=((SmooksOutConfiguration)object);
		return getString("_UI_SmooksOutConfiguration_type")+" ("+outConfiguration.getType().toString().toLowerCase()+")";
			
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

		switch (notification.getFeatureID(SmooksOutConfiguration.class)) {
			case MediatorsPackage.SMOOKS_OUT_CONFIGURATION__TYPE:
			case MediatorsPackage.SMOOKS_OUT_CONFIGURATION__EXPRESSION:
			case MediatorsPackage.SMOOKS_OUT_CONFIGURATION__PROPERTY:
			case MediatorsPackage.SMOOKS_OUT_CONFIGURATION__ACTION:
			case MediatorsPackage.SMOOKS_OUT_CONFIGURATION__OUTPUT_METHOD:
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
