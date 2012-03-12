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
package org.wso2.carbonstudio.eclipse.gmf.esb.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.synapse.endpoints.FailoverEndpoint;
import org.wso2.carbonstudio.eclipse.gmf.esb.AggregateMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.CacheMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.CalloutMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.ClassMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.CloneMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.DBLookupMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.DBReportMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.EntitlementMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.EventMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.FailoverEndPoint;
import org.wso2.carbonstudio.eclipse.gmf.esb.FaultMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.HeaderMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.IterateMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.LoadBalanceEndPoint;
import org.wso2.carbonstudio.eclipse.gmf.esb.MessageMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.OAuthMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.RMSequenceMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.RuleMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.ScriptMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.SendMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.Sequence;
import org.wso2.carbonstudio.eclipse.gmf.esb.SmooksMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.SpringMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.SwitchMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.ThrottleMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.TransactionMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.WSDLEndPoint;
import org.wso2.carbonstudio.eclipse.gmf.esb.XQueryMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.XSLTMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.AddressEndPoint;
import org.wso2.carbonstudio.eclipse.gmf.esb.DefaultEndPoint;
import org.wso2.carbonstudio.eclipse.gmf.esb.DropMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.EnrichMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.EsbNode;
import org.wso2.carbonstudio.eclipse.gmf.esb.FilterMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.LogMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.PropertyMediator;
import org.wso2.carbonstudio.eclipse.gmf.esb.ProxyService;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.AddressEndPointTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.AggregateMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.CacheMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.CalloutMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.ClassMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.CloneMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.DBLookupMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.DBReportMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.DropMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.DefaultEndPointTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.EnrichMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.EntitlementMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.EventMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.FailoverEndPointTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.FaultMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.FilterMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.HeaderMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.IterateMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.LoadBalanceEndPointTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.LogMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.MessageMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.OAuthMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.PropertyMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.ProxyServiceTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.RMSequenceMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.RuleMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.ScriptMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.SendMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.SequenceMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.SmooksMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.SpringMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.SwitchMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.ThrottleMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.TransactionMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.WSDLEndPointTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.XQueryMediatorTransformer;
import org.wso2.carbonstudio.eclipse.gmf.esb.internal.persistence.XSLTMediatorTransformer;

/**
 * A registry of visual model object transformers.
 */
public class EsbTransformerRegistry {
	/**
	 * Singleton instance.
	 */
	private static EsbTransformerRegistry singleton;		
	
	/**
	 * Visual model type to transformers map.
	 */
	private Map<Class<?>, EsbNodeTransformer> transformersMap;
	
	/**
	 * Creates a new transformer registry.
	 */
	private EsbTransformerRegistry() {
		transformersMap = new HashMap<Class<?>, EsbNodeTransformer>();
		addTransformer(ProxyService.class, new ProxyServiceTransformer());
		addTransformer(DefaultEndPoint.class, new DefaultEndPointTransformer());
		addTransformer(AddressEndPoint.class, new AddressEndPointTransformer());
		addTransformer(DropMediator.class, new DropMediatorTransformer());
		addTransformer(FilterMediator.class, new FilterMediatorTransformer());
		addTransformer(LogMediator.class, new LogMediatorTransformer());
		addTransformer(PropertyMediator.class, new PropertyMediatorTransformer());
		addTransformer(EnrichMediator.class, new EnrichMediatorTransformer());
		addTransformer(XSLTMediator.class, new XSLTMediatorTransformer());
		addTransformer(SwitchMediator.class, new SwitchMediatorTransformer());
		addTransformer(MessageMediator.class, new MessageMediatorTransformer());
		addTransformer(ClassMediator.class, new ClassMediatorTransformer());
		addTransformer(FaultMediator.class, new FaultMediatorTransformer());
		addTransformer(EventMediator.class, new EventMediatorTransformer());
		addTransformer(FailoverEndPoint.class, new FailoverEndPointTransformer());
		addTransformer(WSDLEndPoint.class, new WSDLEndPointTransformer());
		addTransformer(LoadBalanceEndPoint.class, new LoadBalanceEndPointTransformer());
		addTransformer(XQueryMediator.class, new XQueryMediatorTransformer());
		addTransformer(Sequence.class, new SequenceMediatorTransformer());
		addTransformer(DBLookupMediator.class, new DBLookupMediatorTransformer());
		addTransformer(DBReportMediator.class, new DBReportMediatorTransformer());
		addTransformer(HeaderMediator.class, new HeaderMediatorTransformer());
		addTransformer(CacheMediator.class, new CacheMediatorTransformer());
		addTransformer(AggregateMediator.class, new AggregateMediatorTransformer());
		addTransformer(CalloutMediator.class, new CalloutMediatorTransformer());
		addTransformer(TransactionMediator.class, new TransactionMediatorTransformer());
		addTransformer(RMSequenceMediator.class, new RMSequenceMediatorTransformer());
		addTransformer(IterateMediator.class, new IterateMediatorTransformer());
		addTransformer(CloneMediator.class, new CloneMediatorTransformer());
		addTransformer(ThrottleMediator.class, new ThrottleMediatorTransformer());
		addTransformer(OAuthMediator.class, new OAuthMediatorTransformer());
		addTransformer(RuleMediator.class, new RuleMediatorTransformer());
		addTransformer(SendMediator.class, new SendMediatorTransformer());
		addTransformer(SpringMediator.class, new SpringMediatorTransformer());
		addTransformer(ScriptMediator.class, new ScriptMediatorTransformer());
		addTransformer(SmooksMediator.class, new SmooksMediatorTransformer());
		addTransformer(EntitlementMediator.class, new EntitlementMediatorTransformer());
		
	}
	
	/**
	 * @return singleton instance.
	 */
	public static EsbTransformerRegistry getInstance() {
		if (null == singleton) {
			singleton = new EsbTransformerRegistry();
		}
		return singleton;
	}
	
	/**
	 * Adds a new transformer into this registry.
	 * 
	 * @param <K>
	 * @param visualModelClass
	 * @param transformer
	 */
	public <K extends EsbNode>  void addTransformer(Class<K> visualModelClass, EsbNodeTransformer transformer) {
		transformersMap.put(visualModelClass, transformer);
	}
	
	/**
	 * Attempts to locate a transformer corresponding to the specified visual model object.
	 * 
	 * @param <K>
	 * @param esbNode
	 * @return
	 */
	public <K extends EsbNode>  EsbNodeTransformer getTransformer(K esbNode) {
		return transformersMap.get(esbNode.eClass().getInstanceClass());
	}
}
