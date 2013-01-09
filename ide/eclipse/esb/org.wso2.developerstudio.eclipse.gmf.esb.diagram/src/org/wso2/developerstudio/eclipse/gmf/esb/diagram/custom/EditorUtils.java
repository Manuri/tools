/*
 * Copyright 2012 WSO2, Inc. (http://wso2.com)
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

package org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.wso2.developerstudio.eclipse.gmf.esb.APIResource;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbDiagram;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbServer;
import org.wso2.developerstudio.eclipse.gmf.esb.ProxyService;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.APIResourceEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.APIResourceFaultInputConnectorEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.ComplexEndpointsEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.ComplexEndpointsOutputConnectorEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.EsbDiagramEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.MediatorFlowMediatorFlowCompartmentEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.ProxyFaultInputConnectorEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.ProxyServiceEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.part.EsbDiagramEditor;

public class EditorUtils {
	
	public static final String DIAGRAM_FILE_EXTENSION = ".esb_diagram";
	public static final String DOMAIN_FILE_EXTENSION = ".esb";
	public static final String SYNAPSE_CONFIG_DIR = "src/main/synapse-config";
	public static final String SYNAPSE_RESOURCE_DIR = "src/main/graphical-synapse-config";
	public static final String SEQUENCE_RESOURCE_DIR = "src/main/graphical-synapse-config/sequences";
	public static final String PROXY_RESOURCE_DIR = "src/main/graphical-synapse-config/proxy-services";
	public static final String ENDPOINT_RESOURCE_DIR = "src/main/graphical-synapse-config/endpoints";
	public static final String LOCAL_ENTRY_RESOURCE_DIR = "src/main/graphical-synapse-config/local-entries";
	public static final String TEMPLATE_RESOURCE_DIR = "src/main/graphical-synapse-config/templates";
	public static final String TASK_RESOURCE_DIR = "src/main/graphical-synapse-config/tasks";
	public static final String API_RESOURCE_DIR = "src/main/graphical-synapse-config/api";
	public static final String COMPLEX_ENDPOINT_RESOURCE_DIR = "src/main/graphical-synapse-config/complex_endpoints";
	
	public static AbstractInputConnectorEditPart getInputConnector(ShapeNodeEditPart parent){
		for(int i=0;i<parent.getChildren().size();++i){					
			if(parent.getChildren().get(i) instanceof AbstractInputConnectorEditPart){
				return (AbstractInputConnectorEditPart) parent.getChildren().get(i);
			}
		}
		return null;
	}
	
	public static AbstractOutputConnectorEditPart getOutputConnector(ShapeNodeEditPart parent){
		for(int i=0;i<parent.getChildren().size();++i){					
			if(parent.getChildren().get(i) instanceof AbstractOutputConnectorEditPart){
				return (AbstractOutputConnectorEditPart) parent.getChildren().get(i);
			}
		}
		return null;
	}
	
	public static AbstractMediatorInputConnectorEditPart getMediatorInputConnector(ShapeNodeEditPart parent){
		if(parent!=null){
			for(int i=0;i<parent.getChildren().size();++i){					
				if(parent.getChildren().get(i) instanceof AbstractMediatorInputConnectorEditPart){
					return (AbstractMediatorInputConnectorEditPart) parent.getChildren().get(i);
				}
			}
		}
		return null;
	}
	
	public static AbstractMediatorOutputConnectorEditPart getMediatorOutputConnector(ShapeNodeEditPart parent){
		for(int i=0;i<parent.getChildren().size();++i){					
			if(parent.getChildren().get(i) instanceof AbstractMediatorOutputConnectorEditPart){
				return (AbstractMediatorOutputConnectorEditPart) parent.getChildren().get(i);
			}
		}
		return null;
	}
	
	public static ArrayList<AdditionalOutputConnector> getMediatorAdditionalOutputConnectors(ShapeNodeEditPart parent){
		ArrayList<AdditionalOutputConnector> connectors=new ArrayList<AdditionalOutputConnector>();
		for(int i=0;i<parent.getChildren().size();++i){					
			if(parent.getChildren().get(i) instanceof AdditionalOutputConnector){
				connectors.add((AdditionalOutputConnector) parent.getChildren().get(i));
			}
		}
		return connectors;
	}
	
	
	public static AbstractEndpointInputConnectorEditPart getEndpointInputConnector(ShapeNodeEditPart parent){
		for(int i=0;i<parent.getChildren().size();++i){					
			if(parent.getChildren().get(i) instanceof AbstractEndpointInputConnectorEditPart){
				return (AbstractEndpointInputConnectorEditPart) parent.getChildren().get(i);
			}
		}
		return null;
	}
	
	public static AbstractEndpointOutputConnectorEditPart getEndpointOutputConnector(ShapeNodeEditPart parent){
		for(int i=0;i<parent.getChildren().size();++i){					
			if(parent.getChildren().get(i) instanceof AbstractEndpointOutputConnectorEditPart){
				return (AbstractEndpointOutputConnectorEditPart) parent.getChildren().get(i);
			}
		}
		return null;
	}
	
	public static AbstractProxyServiceContainerEditPart getProxyContainer(ShapeNodeEditPart parent){
		for(int i=0;i<parent.getChildren().size();++i){					
			if(parent.getChildren().get(i) instanceof AbstractProxyServiceContainerEditPart){
				return (AbstractProxyServiceContainerEditPart) parent.getChildren().get(i);
			}
		}
		return null;
	}
	
	
	public static AbstractInputConnectorEditPart getProxyFaultInputConnector(ShapeNodeEditPart parent){
		for(int i=0;i<parent.getChildren().size();++i){					
			if(parent.getChildren().get(i) instanceof ProxyFaultInputConnectorEditPart){
				return (ProxyFaultInputConnectorEditPart) parent.getChildren().get(i);
			}else if(parent.getChildren().get(i) instanceof APIResourceFaultInputConnectorEditPart){
				return (APIResourceFaultInputConnectorEditPart) parent.getChildren().get(i);
			}
		}
		return null;
	}
	
	public static ArrayList<ComplexEndpointsOutputConnectorEditPart> getComplexEndpointsOutputConnectors(ComplexEndpointsEditPart parent){
		ArrayList<ComplexEndpointsOutputConnectorEditPart> outputConnectors=new ArrayList<ComplexEndpointsOutputConnectorEditPart>();
		
		for(int i=0;i<parent.getChildren().size();++i){					
			if(parent.getChildren().get(i) instanceof ComplexEndpointsOutputConnectorEditPart){
				outputConnectors.add((ComplexEndpointsOutputConnectorEditPart) parent.getChildren().get(i));
			}
		}
		return outputConnectors;
	}
	
	public static AbstractMediator getMediator(EditPart compartment){
		EditPart child=compartment;
		while ((child.getParent()!=null)&&!(child.getParent() instanceof AbstractMediator)){
			child=child.getParent();
		}		
		if(child.getParent()!=null){
			return (AbstractMediator) child.getParent();
		}else{
			return null;
		}
	}
	
	/*
	 * You can get the MediatorEditPart of the entered ConnectorEditPart using this method.
	 */
	public static AbstractMediator getMediator(AbstractConnectorEditPart connector){
		EditPart temp=connector;
		while((temp !=null)&&(!(temp instanceof AbstractMediator))){
			temp=temp.getParent();			
		}
		if(temp instanceof AbstractMediator){
			return (AbstractMediator) temp;
		}
		else{
			return null;
		}
	}
	
	public static AbstractEndpoint getEndpoint(AbstractConnectorEditPart connector){
		EditPart temp=connector;
		while((temp !=null)&&(!(temp instanceof AbstractEndpoint))){
			temp=temp.getParent();			
		}
		if(temp instanceof AbstractEndpoint){
			return (AbstractEndpoint) temp;
		}
		else{
			return null;
		}
	}
	
	public static ProxyServiceEditPart getProxy(AbstractConnectorEditPart connector){
		EditPart temp=connector;
		while((temp !=null)&&(!(temp instanceof ProxyServiceEditPart))){
			temp=temp.getParent();			
		}
		if(temp instanceof ProxyServiceEditPart){
			return (ProxyServiceEditPart) temp;
		}
		else{
			return null;
		}
	}
	
	public static ProxyServiceEditPart getProxy(EditPart child){
		while ((child.getParent()!=null)&&!(child.getParent() instanceof ProxyServiceEditPart)){
			child=child.getParent();
		}		
		if(child.getParent()!=null){
			return (ProxyServiceEditPart) child.getParent();
		}else{
			return null;
		}
	}
	
	public static IGraphicalEditPart getRootContainer(EditPart child) {
		while ((child.getParent() != null)
				&& !(child.getParent() instanceof AbstractBaseFigureEditPart)) {
			child = child.getParent();
		}
		if (child.getParent() != null) {
			return (IGraphicalEditPart) child.getParent();
		} else {
			return null;
		}
	}
	
	public static EObject getRootContainerModel(EObject child) {
		while ((child.eContainer() != null)
				&& !(child.eContainer() instanceof ProxyService || child.eContainer() instanceof APIResource)) {
			child = child.eContainer();
		}
		if (child.eContainer() != null) {
			return (EObject) child.eContainer();
		} else {
			return null;
		}
	}
	
	public static MediatorFlowMediatorFlowCompartmentEditPart getSequenceAndEndpointCompartmentEditPart(EditPart child){
		while ((child.getParent()!=null)&&!(child.getParent() instanceof MediatorFlowMediatorFlowCompartmentEditPart)){
			child=child.getParent();
		}		
		if(child.getParent()!=null){
			return (MediatorFlowMediatorFlowCompartmentEditPart) child.getParent();
		}else{
			return null;
		}
	}	
	
	public static AbstractSequencesEditPart getSequence(AbstractConnectorEditPart connector){
		EditPart temp=connector;
		while((temp !=null)&&(!(temp instanceof AbstractSequencesEditPart))){
			temp=temp.getParent();			
		}
		if(temp instanceof AbstractSequencesEditPart){
			return (AbstractSequencesEditPart) temp;
		}
		else{
			return null;
		}
	}
	
	public static ComplexEndpointsEditPart getComplexEndpoint(AbstractConnectorEditPart connector){
		EditPart temp=connector;
		while((temp !=null)&&(!(temp instanceof ComplexEndpointsEditPart))){
			temp=temp.getParent();			
		}
		if(temp instanceof ComplexEndpointsEditPart){
			return (ComplexEndpointsEditPart) temp;
		}
		else{
			return null;
		}
	}
	
	public static APIResourceEditPart getAPIResource(AbstractConnectorEditPart connector){
		EditPart temp=connector;
		while((temp !=null)&&(!(temp instanceof APIResourceEditPart))){
			temp=temp.getParent();			
		}
		if(temp instanceof APIResourceEditPart){
			return (APIResourceEditPart) temp;
		}
		else{
			return null;
		}
	}
	
	/**
	 * Sets the status of the lock attribute.
	 * @param editor
	 * @param lockmode
	 */
	public static void setLockmode(EsbDiagramEditor editor, boolean lockmode) {
		EsbServer esbServer = getEsbServer(editor);
		if (esbServer != null) {
			esbServer.setLockmode(lockmode);
		}

	}

	/**
	 * Returns the status of the lock attribute.
	 * @param editor
	 * @return
	 */
	public static boolean isLockmode(EsbDiagramEditor editor) {
		EsbServer esbServer = getEsbServer(editor);
		if (esbServer != null) {
			return esbServer.isLockmode();
		}
		return false;
	}
	
	/**
	 * Sets the status of the lock attribute.
	 * @param editPart
	 * @param lockmode
	 */
	public static void setLockmode(GraphicalEditPart editPart, boolean lockmode) {
		EsbServer esbServer = getEsbServer(editPart);
		if (esbServer != null) {
			esbServer.setLockmode(lockmode);
		}

	}

	/**
	 * Returns the status of the lock attribute.
	 * @param editPart
	 * @return
	 */
	public static boolean isLockmode(GraphicalEditPart editPart) {
		EsbServer esbServer = getEsbServer(editPart);
		if (esbServer != null) {
			return esbServer.isLockmode();
		}
		return false;
	}

	/**
	 * Returns the EsbServer model
	 * @param editor
	 * @return
	 */
	public static EsbServer getEsbServer(EsbDiagramEditor editor) {
		Diagram diagram = editor.getDiagram();
		EsbDiagram esbDiagram = (EsbDiagram) diagram.getElement();
		EsbServer esbServer = esbDiagram.getServer();
		return esbServer;
	}
	
	/**
	 * Returns the EsbServer model
	 * @param editPart
	 * @return
	 */
	public static EsbServer getEsbServer(GraphicalEditPart editPart) {
		RootEditPart root = editPart.getRoot();
		if (root.getChildren().size() == 1
				&& root.getChildren().get(0) instanceof EsbDiagramEditPart) {
			EsbDiagramEditPart EsbDiagramEditPart = (EsbDiagramEditPart) root.getChildren().get(0);
			EsbDiagram esbDiagram = (EsbDiagram) ((View) EsbDiagramEditPart.getModel())
					.getElement();
			EsbServer esbServer = esbDiagram.getServer();
			return esbServer;
		}
		return null;
	}

	
}
