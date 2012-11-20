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

import java.util.Map;

import org.apache.synapse.config.xml.SwitchCase;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.mediators.base.SequenceMediator;
import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbFactory;
import org.wso2.developerstudio.eclipse.gmf.esb.NamespacedProperty;
import org.wso2.developerstudio.eclipse.gmf.esb.SwitchCaseBranchOutputConnector;
import org.wso2.developerstudio.eclipse.gmf.esb.SwitchCaseContainer;
import org.wso2.developerstudio.eclipse.gmf.esb.SwitchMediator;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom.utils.SwitchMediatorUtils;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.parts.SwitchMediatorEditPart;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.providers.EsbElementTypes;
import static org.wso2.developerstudio.eclipse.gmf.esb.EsbPackage.Literals.*;

public class SwitchMediatorDeserializer extends
		AbstractEsbNodeDeserializer<AbstractMediator, SwitchMediator> {

	@Override
	public SwitchMediator createNode(IGraphicalEditPart part, AbstractMediator mediator) {

		Assert.isTrue(mediator instanceof org.apache.synapse.mediators.filters.SwitchMediator,
				"Unsupported mediator passed in for deserialization at " + this.getClass());

		org.apache.synapse.mediators.filters.SwitchMediator switchMediator = (org.apache.synapse.mediators.filters.SwitchMediator) mediator;

		org.wso2.developerstudio.eclipse.gmf.esb.SwitchMediator VisualSwitchMediator = (org.wso2.developerstudio.eclipse.gmf.esb.SwitchMediator) DeserializerUtils
				.createNode(part, EsbElementTypes.SwitchMediator_3498);
		setElementToEdit(VisualSwitchMediator);
		refreshEditPartMap();
		
		if(switchMediator.getSource()!=null){
			NamespacedProperty sourceXPath = EsbFactory.eINSTANCE.createNamespacedProperty();
			sourceXPath.setPropertyValue(switchMediator.getSource().toString());
			@SuppressWarnings("unchecked")
			Map<String,String> namespaces = (Map<String,String>)switchMediator.getSource().getNamespaces();
			sourceXPath.setNamespaces(namespaces);
			executeSetValueCommand(SWITCH_MEDIATOR__SOURCE_XPATH, sourceXPath);
		}
		
		SequenceMediator defaultSequence = new SequenceMediator();
		defaultSequence.addAll(switchMediator.getDefaultCase().getCaseMediator().getList());
		IGraphicalEditPart defaultCompartment = (IGraphicalEditPart) getEditpart(
				VisualSwitchMediator.getSwitchContainer().getSwitchDefaultContainer()
						.getMediatorFlow()).getChildren().get(0);
		deserializeSequence((IGraphicalEditPart) defaultCompartment, defaultSequence,
				VisualSwitchMediator.getDefaultBranch());

		if (switchMediator.getCases() != null && !switchMediator.getCases().isEmpty()) {
			int count=0;
			for (SwitchCase switchCase : switchMediator.getCases()) {
				SwitchCaseContainer switchContainer = null;
				SwitchCaseBranchOutputConnector connector = null;
				if (count++ == 0
						&& VisualSwitchMediator.getSwitchContainer().getSwitchCaseContainer()
								.size() == 1) {
					switchContainer = VisualSwitchMediator.getSwitchContainer().getSwitchCaseContainer().get(0);
					connector = VisualSwitchMediator.getCaseBranches().get(0);
				} else {
					switchContainer = (SwitchCaseContainer) DeserializerUtils.createNode(
							(IGraphicalEditPart) getEditpart(VisualSwitchMediator
									.getSwitchContainer()),
							EsbElementTypes.SwitchCaseContainer_3501);
					connector = (SwitchCaseBranchOutputConnector) DeserializerUtils.createNode(
							(IGraphicalEditPart) getEditpart(VisualSwitchMediator),
							EsbElementTypes.SwitchCaseBranchOutputConnector_3043);
				}

				if (switchCase.getRegex() != null
						&& DeserializerUtils.isValidRegex(switchCase.getRegex().toString())) {
					executeSetValueCommand(connector,
							SWITCH_CASE_BRANCH_OUTPUT_CONNECTOR__CASE_REGEX, switchCase.getRegex()
									.toString());
				}

				if (switchCase.getCaseMediator().getList() != null
						&& !switchCase.getCaseMediator().getList().isEmpty()) {
					refreshEditPartMap();
					SequenceMediator sequence = new SequenceMediator();
					sequence.addAll(switchCase.getCaseMediator().getList());
					IGraphicalEditPart compartment = (IGraphicalEditPart) getEditpart(
							switchContainer.getMediatorFlow()).getChildren().get(0);
					deserializeSequence((IGraphicalEditPart) compartment, sequence, connector);
				}

			}

		}
		EditPart editpart = getEditpart(VisualSwitchMediator);
		if (((SwitchMediatorEditPart) editpart).reversed) {
			SwitchMediatorUtils.reorderWhenRevered(editpart);
		} else {
			SwitchMediatorUtils.reorderWhenForward(editpart);
		}

		return VisualSwitchMediator;
	}

}
