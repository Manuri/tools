/*
 * Copyright (c) 2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.developerstudio.eclipse.carbonserver.base;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.wso2.developerstudio.eclipse.carbonserver.base.manager.CarbonServerManager;
import org.wso2.developerstudio.eclipse.carbonserver.base.manager.ICarbonOperationManager;
import org.wso2.developerstudio.eclipse.server.base.core.ServerController;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.wso2.tools.eclipse.carbonserver.base";
	private static final String WSAS_REGISTER_EXTENION_POINT_ID = "org.wso2.developerstudio.eclipse.carbonserver.base.register";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		CarbonServerManager.initiateAppServerManagementOperations();
		ServerController.getInstance().registerServerManager(CarbonServerManager.getInstance());
		runWSASRegisterExtension();

	}
	private void runWSASRegisterExtension() {
		try {
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(WSAS_REGISTER_EXTENION_POINT_ID);
			for (IConfigurationElement e : config) {
				ICarbonOperationManager o;
				try{
					Object classObj = e.createExecutableExtension("class");
					if (classObj instanceof ICarbonOperationManager){
						o=(ICarbonOperationManager)classObj;
						String serverId = e.getAttribute("server-id");
						CarbonServerManager.registerAppServerPlugin(serverId, o);
					}
				}catch(Exception e1){}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		CarbonServerManager.deInitiateAppServerManagementOperations();
		ServerController.getInstance().unregisterServerManager(CarbonServerManager.getInstance());
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
