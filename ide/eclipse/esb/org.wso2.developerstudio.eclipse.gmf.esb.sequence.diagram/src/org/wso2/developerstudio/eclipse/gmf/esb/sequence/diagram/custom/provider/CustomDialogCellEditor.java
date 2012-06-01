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
package org.wso2.developerstudio.eclipse.gmf.esb.sequence.diagram.custom.provider;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbFactory;

/**
 * An abstract {@link DialogCellEditor} extension providing utility useful
 * methods for implementation classes.
 * 
 */
public abstract class CustomDialogCellEditor extends DialogCellEditor {
	/**
	 * {@link EsbFactory} reference.
	 */
	private EsbFactory esbFactory = EsbFactory.eINSTANCE;
	
	/**
	 * Constructs a new {@link CustomDialogCellEditor}.
	 * 
	 * @param parent parent {@link Composite}. 
	 */
	public CustomDialogCellEditor(Composite parent) {
		super(parent);
	}
	
	/**
	 * Utility method for retreiving a reference to the {@link EsbFactory}.
	 * 
	 * @return reference to {@link EsbFactory} instance.
	 */
	protected EsbFactory getEsbFactory() {
		return esbFactory;
	}
}
