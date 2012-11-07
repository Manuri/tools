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

package org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom.deserializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.mediators.base.SequenceMediator;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.impl.ConnectorImpl;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbConnector;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbDiagram;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbNode;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbPackage;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbServer;
import org.wso2.developerstudio.eclipse.gmf.esb.MediatorFlow;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom.AbstractInputConnectorEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom.AbstractOutputConnectorEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom.ConnectionUtils;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom.EditorUtils;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.EsbLinkEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.ProxyOutputConnectorEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.part.EsbDiagramEditor;


public abstract class AbstractEsbNodeDeserializer<T,R extends EsbNode> implements IEsbNodeDeserializer<T,R> {
	private EsbDiagramEditor diagramEditor;

	public EsbDiagramEditor getDiagramEditor() {
		return diagramEditor;
	}

	public void setDiagramEditor(EsbDiagramEditor diagramEditor) {
		this.diagramEditor = diagramEditor;
	}

	protected void deserializeSequence(MediatorFlow mediatorFlow, SequenceMediator sequence, EsbConnector connector) {
		LinkedList<EsbNode> mediatorList = new LinkedList<EsbNode>();
		AbstractOutputConnectorEditPart outputConnector = null;
		AbstractInputConnectorEditPart inputConnector = null;
		
		Diagram diagram = getDiagramEditor().getDiagram();
		EsbDiagram esbDiagram = (EsbDiagram) diagram.getElement();
		EsbServer esbServer = esbDiagram.getServer();
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(esbServer);

		EsbNode node = null;
		AddCommand addCmd = null;

		
		for (int i = 0; i < sequence.getList().size(); ++i) {
			AbstractMediator mediator = (AbstractMediator) sequence.getList().get(i);
			IEsbNodeDeserializer deserializer = EsbDeserializerRegistry.getInstance()
					.getDeserializer(mediator);
			node = deserializer.createNode(mediator);
			mediatorList.add(node);

			
			addCmd = new AddCommand(domain, mediatorFlow,
					EsbPackage.Literals.MEDIATOR_FLOW__CHILDREN, node);
			domain.getCommandStack().execute(addCmd);

		}

		addCmd = new AddCommand(domain, esbServer, EsbPackage.Literals.ESB_SERVER__CHILDREN,
				connector.eContainer());
		domain.getCommandStack().execute(addCmd);

		EditPart proxyOutputConnectorEditpart = EditorUtils.getEditpart(getDiagramEditor(),
				connector);
		if (proxyOutputConnectorEditpart instanceof ProxyOutputConnectorEditPart) {
			outputConnector = (AbstractOutputConnectorEditPart) EditorUtils.getEditpart(
					getDiagramEditor(), connector);
		}

		for (EsbNode mediatornode : mediatorList) {
			AbstractOutputConnectorEditPart nextOutputConnector = null;
			inputConnector = null;

			EditPart editpart = EditorUtils.getEditpart(getDiagramEditor(), mediatornode);
			if (editpart instanceof ShapeNodeEditPart) {
				inputConnector = EditorUtils.getInputConnector((ShapeNodeEditPart) editpart);
				nextOutputConnector = EditorUtils
						.getOutputConnector((ShapeNodeEditPart) editpart);

			}

			if (inputConnector != null && outputConnector != null) {
				List sourceConnections = outputConnector.getSourceConnections();
				Iterator iterator = sourceConnections.iterator();
				
				CompoundCommand ccModel = new CompoundCommand();
				org.eclipse.gef.commands.CompoundCommand ccView = new org.eclipse.gef.commands.CompoundCommand();
				
				while (iterator.hasNext()) {
					EsbLinkEditPart linkEditPart = (EsbLinkEditPart) iterator.next();

					Collection linkCollection = new ArrayList();
					linkCollection.add(((ConnectorImpl) linkEditPart.getModel()).getElement());

					org.eclipse.emf.edit.command.DeleteCommand modelDeleteCommand = new org.eclipse.emf.edit.command.DeleteCommand(
							outputConnector.getEditingDomain(), linkCollection);
					if (modelDeleteCommand.canExecute()) {
						ccModel.append(modelDeleteCommand);
					}

					DeleteCommand viewDeleteCommand = new DeleteCommand(
							linkEditPart.getNotationView());
					if (viewDeleteCommand.canExecute()) {
						ccView.add(new ICommandProxy(viewDeleteCommand));
					}
				}

				if (ccModel.canExecute()) {
					outputConnector.getEditingDomain().getCommandStack().execute(ccModel);
				}
				if (ccView.canExecute()) {
					outputConnector.getDiagramEditDomain().getDiagramCommandStack()
							.execute(ccView);
				}

				ConnectionUtils.createConnection(inputConnector, outputConnector);
			}
			outputConnector = nextOutputConnector;
		}
	}
	
	protected static String join(Iterable<? extends CharSequence> s, String delimiter) {
	    @SuppressWarnings("unchecked")
		Iterator<String> iter = (Iterator<String>) s.iterator();
	    StringBuffer buffer = new StringBuffer(iter.next());
	    while (iter.hasNext()) buffer.append(delimiter).append(iter.next());
	    return buffer.toString();
	}
	
	/*@Override
	public EsbNode createNode(Object object) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
