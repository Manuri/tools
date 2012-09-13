package org.wso2.developerstudio.eclipse.artifact.proxyservice.validators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMException;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.wso2.developerstudio.eclipse.esb.core.utils.SynapseEntryType;
import org.wso2.developerstudio.eclipse.esb.core.utils.SynapseFileUtils;
import org.wso2.developerstudio.eclipse.platform.core.model.AbstractListDataProvider;
import org.wso2.developerstudio.eclipse.platform.core.project.model.ProjectDataModel;

public class EndPointsList extends AbstractListDataProvider {

	
	public List<ListData> getListData(String modelProperty,ProjectDataModel model) {
		List<ListData> list = new ArrayList<ListData>();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();
		for (IProject project : projects) {
			try{
				if(project.isOpen()){
					if(project.hasNature("org.wso2.developerstudio.eclipse.esb.project.nature")){
						IResource resources[] = project.members();
						for (IResource res : resources) {
							if(res.getName().endsWith(".xml")){
								addToList(res,list);
							}
						}
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private void addToList(IResource resource,List<ListData> list) throws Exception{
		if(resource.getType()==IResource.FILE){
			if (SynapseFileUtils.isSynapseConfGiven(resource.getLocation().toFile(),SynapseEntryType.END_POINT)) {
				List<OMElement> endPoints =SynapseFileUtils.synapseFileProcessing(resource.getLocation().toFile().getPath(),SynapseEntryType.END_POINT);
				for(OMElement om: endPoints){
					String name = om.getAttributeValue(new QName("name"));
					list.add(createListData(name, name));
				}
			} else if (isEndpoint(resource.getLocation().toFile())) {
				OMElement om = new StAXOMBuilder(new FileInputStream(resource.getLocation().toFile())).getDocumentElement();
				String name = om.getAttributeValue(new QName("name"));
				list.add(createListData(name, name));
			} 
		} else if(resource.getType()==IResource.FOLDER){
			IResource resources[] = ((IFolder)resource).members();
			for (IResource res : resources) {
				addToList(res,list);
			}
		}	
	}
	

	
	private boolean isEndpoint(File endPoint) throws XMLStreamException, IOException,OMException, Exception {
		OMElement documentElement = new StAXOMBuilder(new FileInputStream(endPoint)).getDocumentElement();
		String localName = documentElement.getLocalName();
		if(localName.equals("endpoint")){
			return true;
		}
		return false;
		
	}

}
