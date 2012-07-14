package org.wso2.developerstudio.eclipse.gmf.esb.internal.persistence;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.synapse.endpoints.Endpoint;
import org.apache.synapse.mediators.base.SequenceMediator;
import org.apache.synapse.util.xpath.SynapseXPath;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbNode;
import org.wso2.developerstudio.eclipse.gmf.esb.HeaderMediator;
import org.wso2.developerstudio.eclipse.gmf.esb.persistence.TransformationInfo;

public class HeaderMediatorTransformer extends AbstractEsbNodeTransformer{

	public void transform(TransformationInfo information, EsbNode subject)
			throws Exception {
		information.getParentSequence().addChild(createHeaderMediator(subject));
		/* 
		 * Transform the Header mediator output data flow path.
		 */
		doTransform(information,((HeaderMediator) subject).getOutputConnector());		
	}

	public void createSynapseObject(TransformationInfo info, EObject subject,
			List<Endpoint> endPoints) {
		
	}

	public void transformWithinSequence(TransformationInfo information,
			EsbNode subject, SequenceMediator sequence) throws Exception {
		sequence.addChild(createHeaderMediator(subject));
		doTransformWithinSequence(information,((HeaderMediator) subject).getOutputConnector().getOutgoingLink(),sequence);	
	}
	
	private org.apache.synapse.mediators.transform.HeaderMediator createHeaderMediator(EsbNode subject) throws Exception{
		/*
		 *  Check subject.
		 */
		Assert.isTrue(subject instanceof HeaderMediator, "Invalid subject.");
		HeaderMediator visualHeader = (HeaderMediator) subject;
		/* 
		 * Configure Header mediator.
		 */
		org.apache.synapse.mediators.transform.HeaderMediator headerMediator = new org.apache.synapse.mediators.transform.HeaderMediator();	
		{
			if(!visualHeader.getHeaderName().getNamespaces().keySet().isEmpty()){
				String prefix=(String) visualHeader.getHeaderName().getNamespaces().keySet().toArray()[0];
				String namespaceUri=visualHeader.getHeaderName().getNamespaces().get(visualHeader.getHeaderName().getNamespaces().keySet().toArray()[0]);			
				String localPart=visualHeader.getHeaderName().getPropertyValue();
				headerMediator.setQName(new QName(namespaceUri, localPart, prefix));	
			}
			else{
				headerMediator.setQName(new QName(visualHeader.getHeaderName().getPropertyValue()));
			}
			headerMediator.setAction(visualHeader.getHeaderAction().getValue());
			
			if(visualHeader.getValueType().getValue()==0){
				headerMediator.setValue(visualHeader.getValueLiteral());
			}else{
				SynapseXPath synapseXPath=new SynapseXPath(visualHeader.getValueExpression().getPropertyValue());
				headerMediator.setExpression(synapseXPath);
			}
		}		
		return headerMediator;
	}
}
