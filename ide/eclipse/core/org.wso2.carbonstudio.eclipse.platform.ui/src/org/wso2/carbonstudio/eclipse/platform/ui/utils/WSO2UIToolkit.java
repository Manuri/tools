/*
 * Copyright (c) 2011, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbonstudio.eclipse.platform.ui.utils;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.wso2.carbonstudio.eclipse.platform.core.model.AbstractComposite;
import org.wso2.carbonstudio.eclipse.platform.core.model.ICompositeProvider;
import org.wso2.carbonstudio.eclipse.platform.core.project.model.ProjectDataModel;
import org.wso2.carbonstudio.eclipse.platform.core.project.model.ProjectOptionData;
import org.wso2.carbonstudio.eclipse.platform.ui.interfaces.IFieldControlData;
import org.wso2.carbonstudio.eclipse.platform.ui.interfaces.IOnAction;
import org.wso2.carbonstudio.eclipse.platform.ui.interfaces.UIControl;
import org.wso2.carbonstudio.eclipse.platform.ui.startup.RegisterUIControl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WSO2UIToolkit {

	public static Button createOption(Composite container, String label, int columns,
	        Integer verticalIndent, Integer horizontalIndent) {
		Button optButton = new Button(container, SWT.RADIO);
		optButton.setText(label);
		if (columns != -1) {
			GridData gridData = new GridData();
			gridData.horizontalSpan = columns;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			if (verticalIndent != null) {
				gridData.verticalIndent = verticalIndent;
			}
			if (horizontalIndent != null) {
				gridData.horizontalIndent = horizontalIndent;
			}
			optButton.setLayoutData(gridData);
		}
		return optButton;
	}

	public static Button createChoice(Composite container, String label, int columns,
	        Integer verticalIndent, Integer horizontalIndent) {
		Button chkButton = new Button(container, SWT.CHECK);
		chkButton.setText(label);
		if (columns != -1) {
			GridData gridData = new GridData();
			gridData.horizontalSpan = columns;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			if (verticalIndent != null) {
				gridData.verticalIndent = verticalIndent;
			}
			if (horizontalIndent != null) {
				gridData.horizontalIndent = horizontalIndent;
			}
			chkButton.setLayoutData(gridData);
		}
		return chkButton;
	}

	public static Combo createCombo(Composite container, String label, int columns,
	        boolean isEditable, Integer verticalIndent, Integer horizontalIndent) {
		final Label lblCaption = new Label(container, SWT.None);
		lblCaption.setText(label);
		GridData gridData = new GridData();
		if (verticalIndent != null) {
			gridData.verticalIndent = verticalIndent;
		}
		if (horizontalIndent != null) {
			gridData.horizontalIndent = horizontalIndent;
		}
		lblCaption.setLayoutData(gridData);
		final Combo cmbValue =
		        new Combo(container, isEditable ? SWT.BORDER : SWT.BORDER | SWT.READ_ONLY);
		propagateControlStatus(cmbValue, lblCaption);
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns - 1;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			cmbValue.setLayoutData(gridData);
		}
		return cmbValue;
	}

	public static CheckboxTableViewer createList(Composite container, String label, int columns,
	        Integer verticalIndent, Integer horizontalIndent) {
		final Label lblCaption = new Label(container, SWT.None);
		lblCaption.setText(label);
		if (columns != -1) {
			GridData gridData = new GridData();
			gridData.horizontalSpan = columns;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
//			gridData.heightHint = 30;
			if (verticalIndent != null) {
				gridData.verticalIndent = verticalIndent;
			}
			if (horizontalIndent != null) {
				gridData.horizontalIndent = horizontalIndent;
			}
			lblCaption.setLayoutData(gridData);
		}
		final CheckboxTableViewer cmbValue =
		        CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION);
		propagateControlStatus(cmbValue.getTable(), lblCaption);
		if (columns != -1) {
			GridData gridData = new GridData();
			gridData.horizontalSpan = columns;
			gridData.verticalSpan = 5;
			gridData.horizontalIndent = 10;
			gridData.heightHint = 50;
			if (horizontalIndent != null) {
				gridData.horizontalIndent += horizontalIndent;
			}
			gridData.grabExcessHorizontalSpace = true;
//			gridData.grabExcessVerticalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			gridData.verticalAlignment = SWT.FILL;
			cmbValue.getTable().setLayoutData(gridData);
		}
		return cmbValue;
	}

	public static Link createLink(Composite container, String label, int columns,
	        int horizontalAlignment, Integer verticalIndent, Integer horizontalIndent) {
		Link linkButton = new Link(container, SWT.CHECK);
		linkButton.setText(label);
		if (columns != -1) {
			GridData gridData = new GridData();
			gridData.horizontalSpan = columns;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = horizontalAlignment; // SWT.FILL;
			if (verticalIndent != null) {
				gridData.verticalIndent = verticalIndent;
			}
			if (horizontalIndent != null) {
				gridData.horizontalIndent = horizontalIndent;
			}
			linkButton.setLayoutData(gridData);
		}
		return linkButton;
	}

	public static Label createLabel(Composite container, String label, int columns,
	        int horizontalAlignment, Integer verticalIndent, Integer horizontalIndent) {
		Label lbl = new Label(container, SWT.CHECK);
		lbl.setText(label);
		if (columns != -1) {
			GridData gridData = new GridData();
			gridData.horizontalSpan = columns;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = horizontalAlignment; // SWT.FILL;
			if (verticalIndent != null) {
				gridData.verticalIndent = verticalIndent;
			}
			if (horizontalIndent != null) {
				gridData.horizontalIndent = horizontalIndent;
			}
			lbl.setLayoutData(gridData);
		}
		return lbl;
	}

	public static Label createTitleLabel(Composite container, String label, int columns,
	        int horizontalAlignment, Integer verticalIndent, Integer horizontalIndent) {
		Label lblCaption = new Label(container, SWT.CHECK);
		lblCaption.setText(label);
		GridData gridData = new GridData();
		if (verticalIndent != null) {
			gridData.verticalIndent = verticalIndent;
		}
		if (horizontalIndent != null) {
			gridData.horizontalIndent = horizontalIndent;
		}
		lblCaption.setLayoutData(gridData);
		Label lbl = new Label(container, SWT.CHECK);
		lbl.setText(label);
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns - 1;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = horizontalAlignment; // SWT.FILL;
			if (verticalIndent != null) {
				gridData.verticalIndent = verticalIndent;
			}
			if (horizontalIndent != null) {
				gridData.horizontalIndent = horizontalIndent;
			}
			lbl.setLayoutData(gridData);
		}
		propagateControlStatus(lbl, lblCaption);
		return lbl;
	}
	
	public static IFieldControlData createRegistryBrowserControl(String id,
																 Composite container,
																 int columns,
																 Integer verticalIndent,
																 Integer horizontalIndent,
																 boolean isTextReadonly,
																 final Shell shell,
																 final String label, 
																 String fileButtonCaption,
																 int selectedOption) {
		IFieldControlData fieldControl = null;
		List<UIControl> uiControlList = RegisterUIControl.getUiControlList();
		for (UIControl uiControl : uiControlList) {
			if(uiControl instanceof UIControl){
				fieldControl = uiControl.createUIField(id, container, columns, verticalIndent, horizontalIndent, isTextReadonly, shell, label, fileButtonCaption, selectedOption);
			}
		}
		return fieldControl;
	}
	
	
	public static IFieldControlData createComposite(
			Composite container,
			int columns,
			final ICompositeProvider iCompositeProvider,
			ProjectDataModel model,
			ProjectOptionData optionData,
			WizardPage wizardPage) {
		
		AbstractComposite composite = iCompositeProvider.createComposite(container,model,optionData,wizardPage);
		
		GridData gridData = new GridData();
		if (null != optionData.getVerticalIndent()) {
			gridData.verticalIndent = optionData.getVerticalIndent();
		}
		if (null != optionData.getHorizontalIndent()) {
			gridData.horizontalIndent = optionData.getHorizontalIndent();
		}
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			gridData.heightHint= -1;
			gridData.grabExcessVerticalSpace=true;
			
			}
			composite.setLayoutData(gridData);
		
			FieldControlDataImpl fieldControl = new FieldControlDataImpl(composite) {
	
				public Composite getControl() {
					return (Composite)super.getControl();	
				}
				
				public void setData(Object data) {
				getControl().setData(data);
				}
			
				public Object getData() {
				return getControl().getData();
				}
				
		};
			
		return fieldControl;
	}
	

	public static IFieldControlData createText(Composite container, String label, int columns,
	        boolean isTextReadonly, Integer verticalIndent, Integer horizontalIndent,boolean multiline) {
		int flags = (multiline)?(SWT.BORDER|SWT.MULTI|SWT.WRAP):SWT.BORDER;
		Label lblCaption = new Label(container, SWT.None);
		lblCaption.setText(label);
		GridData gridData = new GridData();
		if (verticalIndent != null) {
			gridData.verticalIndent = verticalIndent;
		}
		if (horizontalIndent != null) {
			gridData.horizontalIndent = horizontalIndent;
		}
		lblCaption.setLayoutData(gridData);
		Text txtValue = new Text(container, flags);;
		txtValue.setEditable(!isTextReadonly);
		propagateControlStatus(txtValue, lblCaption);
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns - 1;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			if(multiline){
			gridData.heightHint= 65;
			gridData.grabExcessVerticalSpace=true;
			}
			txtValue.setLayoutData(gridData);
		}
		FieldControlDataImpl feildControl = createFieldControlForString(txtValue);
		return feildControl;
	}

	private static FieldControlDataImpl createFieldControlForString(Text txtValue) {
		FieldControlDataImpl feildControl = new FieldControlDataImpl(txtValue) {
			
			public Text getControl() {
				return (Text) super.getControl();
			}

			
			public Object getData() {
				return getControl().getText();
			}

			
			public void setData(Object data) {
				if (data == null) {
					data = "";
				}
				getControl().setText(data.toString());
			}

			
			public void setOnAction(IOnAction action) {
				super.setOnAction(action);
				getControl().addModifyListener(new ModifyListener() {
					
					public void modifyText(ModifyEvent arg0) {
						getOnAction().onModifyAction();

					}

				});
			}

		};
		return feildControl;
	}

	public static void createLine(Composite container, int columns, Integer verticalIndent,
	        Integer horizontalIndent) {
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		if (columns != -1) {
			GridData gridData = new GridData();
			gridData.horizontalSpan = columns;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			if (verticalIndent != null) {
				gridData.verticalIndent = verticalIndent;
			}
			if (horizontalIndent != null) {
				gridData.horizontalIndent = horizontalIndent;
			}
			label.setLayoutData(gridData);
		}
	}

	public static Composite createContainer(final Composite container, String label, int columns,
	        boolean isCollapsible, boolean isExpanded, Integer verticalIndent,
	        Integer horizontalIndent) {
		Composite composite;
		if (label == null) {
			createLine(container, columns, verticalIndent, horizontalIndent);
			verticalIndent = null;
			composite = new Composite(container, SWT.NONE);
		} else {
			if (isCollapsible) {
				ExpandableComposite expandableComposite =
				        new ExpandableComposite(container, SWT.BOLD);
				expandableComposite.setText(label);
				expandableComposite.setFont(new Font(container.getDisplay(), "Sans", 8, SWT.BOLD));
				expandableComposite.setExpanded(isExpanded);
				GridData gridData = new GridData();
				gridData.horizontalSpan = columns;
				gridData.grabExcessHorizontalSpace = true;
				if (verticalIndent != null) {
					gridData.verticalIndent = verticalIndent;
					verticalIndent = null;
				}
				gridData.horizontalAlignment = SWT.FILL;
				expandableComposite.setLayoutData(gridData);
				composite = new Composite(expandableComposite, SWT.NONE);
				expandableComposite.setClient(composite);
				expandableComposite.addExpansionListener(new ExpansionAdapter() {
					
					public void expansionStateChanged(ExpansionEvent e) {
						layout(container);
					}
				});
			} else {
				composite = new Group(container, SWT.NONE);
				((Group) composite).setText(label);
			}
		}
		if (columns != -1) {
			GridData gridData = new GridData();
			gridData.horizontalSpan = columns;
			gridData.grabExcessHorizontalSpace = true;
			if (verticalIndent != null) {
				gridData.verticalIndent = verticalIndent;
			}
			if (horizontalIndent != null) {
				gridData.horizontalIndent = horizontalIndent;
			}
			gridData.horizontalAlignment = SWT.FILL;
			gridData.verticalIndent = 10;
			composite.setLayoutData(gridData);
		}
		return composite;
	}

	public static IFieldControlData createFileDirectoryBrowser(Composite container, final Shell shell,
	        final String label, final String filter, boolean isTextReadonly,
	        String fileButtonCaption, String dirButtonCaption, int columns, Integer verticalIndent,
	        Integer horizontalIndent) {
		Label lblCaption = new Label(container, SWT.None);
		lblCaption.setText(label);
		GridData gridData = new GridData();
		if (verticalIndent != null) {
			gridData.verticalIndent = verticalIndent;
		}
		if (horizontalIndent != null) {
			gridData.horizontalIndent = horizontalIndent;
		}
		lblCaption.setLayoutData(gridData);
		final Text txtValue = new Text(container, SWT.BORDER);
		txtValue.setEditable(!isTextReadonly);
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns - 3;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			txtValue.setLayoutData(gridData);
		}
		Button btnFileBrowse =
		        addFileBrowseButton(container, shell, label, filter, fileButtonCaption, txtValue);
		Button btnDirBrowse =
		        addDirBrowseButton(container, shell, label, dirButtonCaption, txtValue);
		propagateControlStatus(txtValue, lblCaption, btnFileBrowse, btnDirBrowse);
		FileldControlTextDataImple feildControl = createFileBrowserFieldController(txtValue);
		return feildControl;
	}

	private static FileldControlTextDataImple createFileBrowserFieldController(
			final Text txtValue) {
		FileldControlTextDataImple feildControl = new FileldControlTextDataImple(txtValue) {
			
//			
//			public Text getControl() {
//				return (Text)super.getControl();
//			}
			
			
			public void setData(Object data) {
				if (data==null){
					data="";
				}
				getControl().setText(data.toString());
				
			}
			
			
			public Object getData() {
				return new File(getControl().getText());
			}
			
//			
//			public void setOnAction(IOnAction action) {
//				super.setOnAction(action);
//				getControl().addModifyListener(new ModifyListener() {
//					
//					public void modifyText(ModifyEvent arg0) {
//						getOnAction().onModifyAction();
//
//					}
//
//				});
//			}
		};
		return feildControl;
	}

	public static Text createWorkspaceFileBrowser(Composite container, final Shell shell,
	        final String label, boolean isTextReadonly, String buttonCaption, int columns,
	        final ViewerFilter viewerFilter, Integer verticalIndent, Integer horizontalIndent) {
		Label lblCaption = new Label(container, SWT.None);
		lblCaption.setText(label);
		GridData gridData = new GridData();
		if (verticalIndent != null) {
			gridData.verticalIndent = verticalIndent;
		}
		if (horizontalIndent != null) {
			gridData.horizontalIndent = horizontalIndent;
		}
		lblCaption.setLayoutData(gridData);
		final Text txtValue = new Text(container, SWT.BORDER);
		txtValue.setEditable(!isTextReadonly);
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns - 2;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			txtValue.setLayoutData(gridData);
		}
		Button btnFileBrowse = new Button(container, SWT.None);
		btnFileBrowse.setText(buttonCaption);
		btnFileBrowse.addSelectionListener(new SelectionListener() {
			
			public void widgetDefaultSelected(SelectionEvent event) {
				List<ViewerFilter> viewerFilters = null;
				if (viewerFilter == null) {
					viewerFilters = new ArrayList<ViewerFilter>();
				} else {
					viewerFilters = Arrays.asList(new ViewerFilter[] { viewerFilter });
				}
				IFile[] openFileSelection =
				        WorkspaceResourceDialog.openFileSelection(shell, "Select file...", label,
				                                                  false, new Object[] {},
				                                                  viewerFilters);
				if (openFileSelection != null) {
					txtValue.setText(openFileSelection[0].getFullPath().toPortableString()
					        .substring(1));
				}
			}

			
			public void widgetSelected(SelectionEvent event) {
				widgetDefaultSelected(event);
			}
		});
		propagateControlStatus(txtValue, lblCaption, btnFileBrowse);
		return txtValue;
	}

	public static Text createWorkspaceFolderBrowser(Composite container, final Shell shell,
	        final String label, boolean isTextReadonly, String buttonCaption, int columns,
	        final ViewerFilter viewerFilter, Integer verticalIndent, Integer horizontalIndent) {
		Label lblCaption = new Label(container, SWT.None);
		lblCaption.setText(label);
		GridData gridData = new GridData();
		if (verticalIndent != null) {
			gridData.verticalIndent = verticalIndent;
		}
		if (horizontalIndent != null) {
			gridData.horizontalIndent = horizontalIndent;
		}
		lblCaption.setLayoutData(gridData);
		final Text txtValue = new Text(container, SWT.BORDER);
		txtValue.setEditable(!isTextReadonly);
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns - 2;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			txtValue.setLayoutData(gridData);
		}
		Button btnFileBrowse = new Button(container, SWT.None);
		btnFileBrowse.setText(buttonCaption);
		btnFileBrowse.addSelectionListener(new SelectionListener() {
			
			public void widgetDefaultSelected(SelectionEvent event) {
				List<ViewerFilter> viewerFilters = null;
				if (viewerFilter == null) {
					viewerFilters = new ArrayList<ViewerFilter>();
				} else {
					viewerFilters = Arrays.asList(new ViewerFilter[] { viewerFilter });
				}
				IContainer[] openFolderSelection =
				        WorkspaceResourceDialog.openFolderSelection(shell, "Select folder...",
				                                                    label, false, new Object[] {},
				                                                    viewerFilters);
				if (openFolderSelection != null && openFolderSelection.length != 0) {
					txtValue.setText(openFolderSelection[0].getFullPath().toPortableString()
					        .substring(1));
				}
			}

			
			public void widgetSelected(SelectionEvent event) {
				widgetDefaultSelected(event);
			}
		});
		propagateControlStatus(txtValue, lblCaption, btnFileBrowse);
		return txtValue;
	}

	public static Text createWorkspaceBrowser(Composite container, final Shell shell,
	        final String label, boolean isTextReadonly, String buttonCaption, int columns,
	        final ViewerFilter viewerFilter, Integer verticalIndent, Integer horizontalIndent) {
		Label lblCaption = new Label(container, SWT.None);
		lblCaption.setText(label);
		GridData gridData = new GridData();
		if (verticalIndent != null) {
			gridData.verticalIndent = verticalIndent;
		}
		if (horizontalIndent != null) {
			gridData.horizontalIndent = horizontalIndent;
		}
		lblCaption.setLayoutData(gridData);
		final Text txtValue = new Text(container, SWT.BORDER);
		txtValue.setEditable(!isTextReadonly);
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns - 2;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			txtValue.setLayoutData(gridData);
		}
		Button btnFileBrowse = new Button(container, SWT.None);
		btnFileBrowse.setText(buttonCaption);
		btnFileBrowse.addSelectionListener(new SelectionListener() {
			
			public void widgetDefaultSelected(SelectionEvent event) {
				// TODO
			}

			
			public void widgetSelected(SelectionEvent event) {
				widgetDefaultSelected(event);
			}
		});
		propagateControlStatus(txtValue, lblCaption, btnFileBrowse);
		return txtValue;
	}

	public static IFieldControlData createFileBrowser(Composite container, final Shell shell,
	        final String label, final String filter, boolean isTextReadonly, String buttonCaption,
	        int columns, Integer verticalIndent, Integer horizontalIndent) {
		Label lblCaption = new Label(container, SWT.None);
		lblCaption.setText(label);
		GridData gridData = new GridData();
		if (verticalIndent != null) {
			gridData.verticalIndent = verticalIndent;
		}
		if (horizontalIndent != null) {
			gridData.horizontalIndent = horizontalIndent;
		}
		lblCaption.setLayoutData(gridData);
		final Text txtValue = new Text(container, SWT.BORDER);
		txtValue.setEditable(!isTextReadonly);
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns - 2;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			txtValue.setLayoutData(gridData);
		}
		Button btnFileBrowse =
		        addFileBrowseButton(container, shell, label, filter, buttonCaption, txtValue);
		propagateControlStatus(txtValue, lblCaption, btnFileBrowse);
		FieldControlDataImpl feildControl = createFileBrowserFieldController(txtValue);
		return feildControl;
	}

	private static Button addFileBrowseButton(Composite container, final Shell shell,
	        final String label, final String filter, String buttonCaption, final Text txtValue) {
		Button btnFileBrowse = new Button(container, SWT.None);
		btnFileBrowse.setText(buttonCaption);
		btnFileBrowse.addSelectionListener(new SelectionListener() {
			
			public void widgetDefaultSelected(SelectionEvent event) {
				FileDialog fileDialog = new FileDialog(shell);
				fileDialog.setFilterExtensions(filter.split(","));
				// fileDialog.setFilterPath(txtValue.getText());
				fileDialog.setText(label);
				if (fileDialog.open() != null) {
					String fileName =
					        new File(fileDialog.getFilterPath(), fileDialog.getFileName())
					                .toString();
					txtValue.setText(fileName);
				}
			}

			
			public void widgetSelected(SelectionEvent event) {
				widgetDefaultSelected(event);
			}
		});
		return btnFileBrowse;
	}

	public static IFieldControlData createDirectoryBrowser(Composite container, final Shell shell,
	        final String label, boolean isTextReadonly, String buttonCaption, int columns,
	        Integer verticalIndent, Integer horizontalIndent) {
		Label lblCaption = new Label(container, SWT.None);
		lblCaption.setText(label);
		GridData gridData = new GridData();
		if (verticalIndent != null) {
			gridData.verticalIndent = verticalIndent;
		}
		if (horizontalIndent != null) {
			gridData.horizontalIndent = horizontalIndent;
		}
		lblCaption.setLayoutData(gridData);
		final Text txtValue = new Text(container, SWT.BORDER);
		txtValue.setEditable(!isTextReadonly);
		if (columns != -1) {
			gridData = new GridData();
			gridData.horizontalSpan = columns - 2;
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			txtValue.setLayoutData(gridData);
		}
		Button btnDirBrowse = addDirBrowseButton(container, shell, label, buttonCaption, txtValue);
		propagateControlStatus(txtValue, lblCaption, btnDirBrowse);
		FieldControlDataImpl feildControl = createFileBrowserFieldController(txtValue);
		return feildControl;
	}

	private static Button addDirBrowseButton(Composite container, final Shell shell,
	        final String label, String buttonCaption, final Text txtValue) {
		Button btnDirBrowse = new Button(container, SWT.None);
		btnDirBrowse.setText(buttonCaption);
		btnDirBrowse.addSelectionListener(new SelectionListener() {
			
			public void widgetDefaultSelected(SelectionEvent event) {
				DirectoryDialog dirDialog = new DirectoryDialog(shell);
				// dirDialog.setFilterPath(txtValue.getText());
				dirDialog.setText(label);
				String path = dirDialog.open();
				if (path != null) {
					// String fileName = new File(dirDialog.getFilterPath(),
					// path).toString();
					txtValue.setText(path);
				}
			}

			
			public void widgetSelected(SelectionEvent event) {
				widgetDefaultSelected(event);
			}
		});
		return btnDirBrowse;
	}

	private static void propagateControlStatus(Control watchControl, Control... controls) {
		propagateEnability(watchControl, controls);
		propagateVisibility(watchControl, controls);
	}

	private static void propagateEnability(final Control watchControl, final Control... controls) {
		watchControl.addPaintListener(new PaintListener() {
			
			public void paintControl(PaintEvent event) {
				for (Control control : controls) {
					control.setEnabled(watchControl.getEnabled());
				}
			}
		});
	}

	private static Map<Control, Control[]> visibilityControls = new HashMap<Control, Control[]>();

	public static void updateControlVisibilityStatus(Control watchControl) {
		if (visibilityControls.containsKey(watchControl)) {
			Control[] controls = visibilityControls.get(watchControl);
			if (watchControl.isDisposed()) {
				visibilityControls.remove(watchControl);
			} else {
				boolean visibleField = watchControl.getVisible();
				for (Control control : controls) {
					Object layoutData = control.getLayoutData();
					if (layoutData == null) {
						layoutData = new GridData();
					}
					if (layoutData instanceof GridData) {
						((GridData) layoutData).exclude = !visibleField;
						control.setLayoutData(layoutData);
					}
					control.setVisible(visibleField);
				}
				layout(watchControl.getParent());
			}
		}
	}

	private static void propagateVisibility(final Control watchControl, final Control... controls) {
		visibilityControls.put(watchControl, controls);
		watchControl.addDisposeListener(new DisposeListener() {
			
			public void widgetDisposed(DisposeEvent arg0) {
				visibilityControls.remove(watchControl);
			}
		});
		// watchControl.getParent().addPaintListener(new PaintListener() {
		// 
		// public void paintControl(PaintEvent event) {
		// if (watchControl.isDisposed()) {
		// watchControl.getParent().removePaintListener(this);
		// } else {
		// boolean visibleField = watchControl.getVisible();
		// for (Control control : controls) {
		// Object layoutData = control.getLayoutData();
		// if (layoutData == null) {
		// layoutData = new GridData();
		// }
		// if (layoutData instanceof GridData) {
		// ((GridData) layoutData).exclude = !visibleField;
		// control.setLayoutData(layoutData);
		// }
		// control.setVisible(visibleField);
		// }
		// }
		// }
		// });
	}

	public static void layout(final Composite container) {
		Composite parentLayout = container;
		while (parentLayout != null) {
			parentLayout.layout();
			parentLayout = parentLayout.getParent();
		}
	}


	private static abstract class FileldControlTextDataImple extends FieldControlDataImpl{

		public FileldControlTextDataImple(Control control) {
			super(control);
		}
		
		
		public Text getControl() {
			return (Text)super.getControl();
		}
		
		
		public void setOnAction(IOnAction action) {
			super.setOnAction(action);
			getControl().addModifyListener(new ModifyListener() {
				
				public void modifyText(ModifyEvent arg0) {
					getOnAction().onModifyAction();

				}

			});
		}
		
	}
	
	private static abstract class FieldControlDataImpl implements IFieldControlData {
		private Control control=null;
		private IOnAction onAction;

		
		public void setOnAction(IOnAction action) {
			this.onAction = action;
		}

		public IOnAction getOnAction() {
			return onAction;
		}

		public FieldControlDataImpl(Control control) {
			this.setControl(control);
		}

		
		public Control getControl() {
			return control;
		}

		public void setControl(Control control) {
			this.control = control;
		}

	}
}
