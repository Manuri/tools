package org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.AggregateMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.BuilderMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.CacheMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.CallTemplateMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.CalloutMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.ClassMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.CloneMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.CommandMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.DBLookupMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.DBReportMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.DropMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.EnrichMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.EntitlementMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.EventMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.FaultMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.FilterMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.HeaderMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.IterateMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.LogMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.OAuthMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.PayloadFactoryMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.PropertyMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.RMSequenceMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.RuleMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.ScriptMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.SendMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.SequenceCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.SmooksMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.SpringMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.StoreMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.SwitchMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.ThrottleMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.TransactionMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.XQueryMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.edit.commands.XSLTMediatorCreateCommand;
import org.wso2.developerstudio.eclipse.gmf.esb.diagram.providers.EsbElementTypes;

/**
 * @generated
 */
public class MediatorFlowMediatorFlowCompartment8ItemSemanticEditPolicy extends
		EsbBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public MediatorFlowMediatorFlowCompartment8ItemSemanticEditPolicy() {
		super(EsbElementTypes.MediatorFlow_3538);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (EsbElementTypes.DropMediator_3491 == req.getElementType()) {
			return getGEFWrapper(new DropMediatorCreateCommand(req));
		}
		if (EsbElementTypes.PropertyMediator_3492 == req.getElementType()) {
			return getGEFWrapper(new PropertyMediatorCreateCommand(req));
		}
		if (EsbElementTypes.ThrottleMediator_3493 == req.getElementType()) {
			return getGEFWrapper(new ThrottleMediatorCreateCommand(req));
		}
		if (EsbElementTypes.FilterMediator_3494 == req.getElementType()) {
			return getGEFWrapper(new FilterMediatorCreateCommand(req));
		}
		if (EsbElementTypes.LogMediator_3495 == req.getElementType()) {
			return getGEFWrapper(new LogMediatorCreateCommand(req));
		}
		if (EsbElementTypes.EnrichMediator_3496 == req.getElementType()) {
			return getGEFWrapper(new EnrichMediatorCreateCommand(req));
		}
		if (EsbElementTypes.XSLTMediator_3497 == req.getElementType()) {
			return getGEFWrapper(new XSLTMediatorCreateCommand(req));
		}
		if (EsbElementTypes.SwitchMediator_3498 == req.getElementType()) {
			return getGEFWrapper(new SwitchMediatorCreateCommand(req));
		}
		if (EsbElementTypes.Sequence_3503 == req.getElementType()) {
			return getGEFWrapper(new SequenceCreateCommand(req));
		}
		if (EsbElementTypes.EventMediator_3504 == req.getElementType()) {
			return getGEFWrapper(new EventMediatorCreateCommand(req));
		}
		if (EsbElementTypes.EntitlementMediator_3505 == req.getElementType()) {
			return getGEFWrapper(new EntitlementMediatorCreateCommand(req));
		}
		if (EsbElementTypes.ClassMediator_3506 == req.getElementType()) {
			return getGEFWrapper(new ClassMediatorCreateCommand(req));
		}
		if (EsbElementTypes.SpringMediator_3507 == req.getElementType()) {
			return getGEFWrapper(new SpringMediatorCreateCommand(req));
		}
		if (EsbElementTypes.ScriptMediator_3508 == req.getElementType()) {
			return getGEFWrapper(new ScriptMediatorCreateCommand(req));
		}
		if (EsbElementTypes.FaultMediator_3509 == req.getElementType()) {
			return getGEFWrapper(new FaultMediatorCreateCommand(req));
		}
		if (EsbElementTypes.XQueryMediator_3510 == req.getElementType()) {
			return getGEFWrapper(new XQueryMediatorCreateCommand(req));
		}
		if (EsbElementTypes.CommandMediator_3511 == req.getElementType()) {
			return getGEFWrapper(new CommandMediatorCreateCommand(req));
		}
		if (EsbElementTypes.DBLookupMediator_3512 == req.getElementType()) {
			return getGEFWrapper(new DBLookupMediatorCreateCommand(req));
		}
		if (EsbElementTypes.DBReportMediator_3513 == req.getElementType()) {
			return getGEFWrapper(new DBReportMediatorCreateCommand(req));
		}
		if (EsbElementTypes.SmooksMediator_3514 == req.getElementType()) {
			return getGEFWrapper(new SmooksMediatorCreateCommand(req));
		}
		if (EsbElementTypes.SendMediator_3515 == req.getElementType()) {
			return getGEFWrapper(new SendMediatorCreateCommand(req));
		}
		if (EsbElementTypes.HeaderMediator_3516 == req.getElementType()) {
			return getGEFWrapper(new HeaderMediatorCreateCommand(req));
		}
		if (EsbElementTypes.CloneMediator_3517 == req.getElementType()) {
			return getGEFWrapper(new CloneMediatorCreateCommand(req));
		}
		if (EsbElementTypes.CacheMediator_3518 == req.getElementType()) {
			return getGEFWrapper(new CacheMediatorCreateCommand(req));
		}
		if (EsbElementTypes.IterateMediator_3519 == req.getElementType()) {
			return getGEFWrapper(new IterateMediatorCreateCommand(req));
		}
		if (EsbElementTypes.CalloutMediator_3520 == req.getElementType()) {
			return getGEFWrapper(new CalloutMediatorCreateCommand(req));
		}
		if (EsbElementTypes.TransactionMediator_3521 == req.getElementType()) {
			return getGEFWrapper(new TransactionMediatorCreateCommand(req));
		}
		if (EsbElementTypes.RMSequenceMediator_3522 == req.getElementType()) {
			return getGEFWrapper(new RMSequenceMediatorCreateCommand(req));
		}
		if (EsbElementTypes.RuleMediator_3523 == req.getElementType()) {
			return getGEFWrapper(new RuleMediatorCreateCommand(req));
		}
		if (EsbElementTypes.OAuthMediator_3524 == req.getElementType()) {
			return getGEFWrapper(new OAuthMediatorCreateCommand(req));
		}
		if (EsbElementTypes.AggregateMediator_3525 == req.getElementType()) {
			return getGEFWrapper(new AggregateMediatorCreateCommand(req));
		}
		if (EsbElementTypes.StoreMediator_3588 == req.getElementType()) {
			return getGEFWrapper(new StoreMediatorCreateCommand(req));
		}
		if (EsbElementTypes.BuilderMediator_3591 == req.getElementType()) {
			return getGEFWrapper(new BuilderMediatorCreateCommand(req));
		}
		if (EsbElementTypes.CallTemplateMediator_3594 == req.getElementType()) {
			return getGEFWrapper(new CallTemplateMediatorCreateCommand(req));
		}
		if (EsbElementTypes.PayloadFactoryMediator_3597 == req.getElementType()) {
			return getGEFWrapper(new PayloadFactoryMediatorCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}