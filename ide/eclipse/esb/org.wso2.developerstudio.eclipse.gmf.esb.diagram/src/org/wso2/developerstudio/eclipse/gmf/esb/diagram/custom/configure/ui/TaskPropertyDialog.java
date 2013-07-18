package org.wso2.developerstudio.eclipse.gmf.esb.diagram.custom.configure.ui;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbFactory;
import org.wso2.developerstudio.eclipse.gmf.esb.EsbPackage;
import org.wso2.developerstudio.eclipse.gmf.esb.Task;
import org.wso2.developerstudio.eclipse.gmf.esb.TaskProperty;
import org.wso2.developerstudio.eclipse.gmf.esb.TaskPropertyType;

public class TaskPropertyDialog extends Dialog{
	
	private TransactionalEditingDomain editingDomain;
	private Task task;
	
	/**
	 * Table for add/edit/remove parameters.
	 */
	private Table propertyTable;
	/**
	 * Table Editor for inline property edit
	 */
	private TableEditor propertyTypeEditor;
	/**
	 * Combo box for select parameter type.
	 */
	private Combo cmbPropertyType;
	/**
	 * Button for add new parameter.
	 */
	private Button newPropertyButton;
	/**
	 * Button for remove parameter.
	 */
	private Button removePropertyButton;	
	/**
	 * Command for recording user operations.
	 */
	private CompoundCommand resultCommand;
	
	
	
	public TaskPropertyDialog(Shell parentShell,
			Task task,
			TransactionalEditingDomain editingDomain) {
		super(parentShell);
		this.editingDomain = editingDomain;
		this.task = task;
	}
	
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Task Configuration");
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		FormLayout mainLayout = new FormLayout();
		mainLayout.marginHeight = 5;
		mainLayout.marginWidth = 5;
		container.setLayout(mainLayout);

		// Button for add new parameter.
		newPropertyButton = new Button(container, SWT.NONE);
		newPropertyButton.setText("New...");
		FormData newTaskPropertyButtonLayoutData = new FormData(80, SWT.DEFAULT);
		newTaskPropertyButtonLayoutData.right = new FormAttachment(100);
		newPropertyButton.setLayoutData(newTaskPropertyButtonLayoutData);

		newPropertyButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TableItem item = bindPram(EsbFactory.eINSTANCE
						.createTaskProperty());
				propertyTable.select(propertyTable.indexOf(item));
			}
		});

		// Button for remove Property.
		removePropertyButton = new Button(container, SWT.NONE);
		removePropertyButton.setText("Remove");
		FormData removeTaskPropertyButtonLayoutData = new FormData();
		removeTaskPropertyButtonLayoutData.top = new FormAttachment(
				newPropertyButton, 5);
		removeTaskPropertyButtonLayoutData.right = new FormAttachment(100);
		removeTaskPropertyButtonLayoutData.left = new FormAttachment(
				newPropertyButton, 0, SWT.LEFT);
		removePropertyButton.setLayoutData(removeTaskPropertyButtonLayoutData);

		removePropertyButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int selectedIndex = propertyTable.getSelectionIndex();
				if (-1 != selectedIndex) {
					unbindParam(selectedIndex);

					// Select the next available candidate for deletion.
					if (selectedIndex < propertyTable.getItemCount()) {
						propertyTable.select(selectedIndex);
					} else {
						propertyTable.select(selectedIndex - 1);
					}
				}
			}
		});

		// Table for show the parameters.
		propertyTable = new Table(container, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.HIDE_SELECTION);

		TableColumn nameColumn = new TableColumn(propertyTable, SWT.LEFT);
		TableColumn typeColumn = new TableColumn(propertyTable, SWT.LEFT);
		TableColumn valueColumn = new TableColumn(propertyTable, SWT.LEFT);
		
		nameColumn.setText("Parameter Name");
		nameColumn.setWidth(150);
		valueColumn.setText("Value/Expression");
		valueColumn.setWidth(200);
		typeColumn.setText("Parameter Type");
		typeColumn.setWidth(150);

		propertyTable.setHeaderVisible(true);
		propertyTable.setLinesVisible(true);

		Listener tblPropertiesListener = new Listener() {

			public void handleEvent(Event evt) {
				if (null != evt.item) {
					if (evt.item instanceof TableItem) {
						TableItem item = (TableItem) evt.item;
						editItem(item);
					}
				}
			}
		};

		propertyTable.addListener(SWT.Selection, tblPropertiesListener);

		// Populate Properties.
		for (TaskProperty property : task.getTaskProperties()) {
			bindPram(property);
		}

		setupTableEditor(propertyTable);

		FormData taskPropertiesTableLayoutData = new FormData(SWT.DEFAULT, 150);
		taskPropertiesTableLayoutData.top = new FormAttachment(newPropertyButton, 0, SWT.TOP);
		taskPropertiesTableLayoutData.left = new FormAttachment(0);
		taskPropertiesTableLayoutData.right = new FormAttachment(newPropertyButton, -5);
		taskPropertiesTableLayoutData.bottom = new FormAttachment(100);
		propertyTable.setLayoutData(taskPropertiesTableLayoutData);

		return parent;

	}
	
	protected void okPressed() {

		for (TableItem item : propertyTable.getItems()) {

			TaskProperty param = (TaskProperty) item
					.getData();

			if (param.eContainer() == null) {

				param.setPropertyName(item.getText(0));

				if (item.getText(1).equals("LITERAL")) {
					param.setPropertyValue(item.getText(2));
					param.setPropertyType(TaskPropertyType.LITERAL);
				}

				if (item.getText(1).equals("XML")) {
					param.setPropertyValue(item.getText(2));
					param.setPropertyType(TaskPropertyType.XML);
				}

				AddCommand addCmd = new AddCommand(
						editingDomain,
						task,
						EsbPackage.Literals.TASK__TASK_PROPERTIES,
						param);
				getResultCommand().append(addCmd);

			} else {

				if (!param.getPropertyName().equals(item.getText(0))) {

					SetCommand setCmd = new SetCommand(
							editingDomain,
							param,
							EsbPackage.Literals.NAME_VALUE_TYPE_PROPERTY__PROPERTY_NAME,
							item.getText(0));

					getResultCommand().append(setCmd);
				}
				
				if (!param.getPropertyValue().equals(item.getText(1))) {

					SetCommand setCmd = new SetCommand(
							editingDomain,
							param,
							EsbPackage.Literals.NAME_VALUE_TYPE_PROPERTY__PROPERTY_VALUE,
							item.getText(2));
					getResultCommand().append(setCmd);
				}

				if (item.getText(1).equals("LITERAL")) {

					SetCommand setCmdValueType = new SetCommand(
							editingDomain,
							param,
							EsbPackage.Literals.NAME_VALUE_TYPE_PROPERTY__PROPERTY_TYPE,
							TaskPropertyType.LITERAL);
					getResultCommand().append(setCmdValueType);
				}

				if (item.getText(1).equals("XML")) {

					SetCommand setCmdExpType = new SetCommand(
							editingDomain,
							param,
							EsbPackage.Literals.NAME_VALUE_TYPE_PROPERTY__PROPERTY_TYPE,
							TaskPropertyType.XML);
					getResultCommand().append(setCmdExpType);						
				}
			}
		}

		if (getResultCommand().canExecute()) {
			editingDomain.getCommandStack().execute(getResultCommand());
		}

		super.okPressed();

	}
	
	
	private TableItem bindPram(TaskProperty param) {
		TableItem item = new TableItem(propertyTable, SWT.NONE);
	
		item.setText(new String[] { param.getPropertyName(),
									param.getPropertyType().getLiteral(),
									param.getPropertyValue()
								  });
		
		item.setData(param);
		return item;
	}
	
	private void unbindParam(int itemIndex) {
		TableItem item = propertyTable.getItem(itemIndex);
		TaskProperty param = (TaskProperty) item.getData();
		if (param.eContainer() != null) {
			RemoveCommand removeCmd = new RemoveCommand(
					editingDomain,
					task,
					EsbPackage.Literals.TASK__TASK_PROPERTIES,
					param);
			getResultCommand().append(removeCmd);
		}

		propertyTable.remove(propertyTable.indexOf(item));
	}
	
	
	private void setupTableEditor(final Table table) {
		final TableEditor cellEditor = new TableEditor(table);
		cellEditor.grabHorizontal = true;
		cellEditor.minimumWidth = 50;
		table.addMouseListener(new MouseAdapter() {
			/**
			 * Setup a new cell editor control at double click event.
			 */
			public void mouseDoubleClick(MouseEvent e) {
				// Dispose the old editor control (if one is setup).
				Control oldEditorControl = cellEditor.getEditor();
				if (null != oldEditorControl)
					oldEditorControl.dispose();

				// Mouse location.
				Point mouseLocation = new Point(e.x, e.y);

				// Grab the selected row.
				TableItem item = (TableItem) table.getItem(mouseLocation);
				if (null == item)
					return;

				// Determine which column was selected.
				int selectedColumn = -1;
				for (int i = 0, n = table.getColumnCount(); i < n; i++) {
					if (item.getBounds(i).contains(mouseLocation)) {
						selectedColumn = i;
						break;
					}
				}

				// Setup a new editor control.
				if (-1 != selectedColumn) {
					Text editorControl = new Text(table, SWT.NONE);
					final int editorControlColumn = selectedColumn;
					editorControl.setText(item.getText(selectedColumn));
					editorControl.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent e) {
							Text text = (Text) cellEditor.getEditor();
							cellEditor.getItem().setText(editorControlColumn,
									text.getText());
						}
					});
					editorControl.selectAll();
					editorControl.setFocus();
					cellEditor.setEditor(editorControl, item, selectedColumn);
				}
			}

			/**
			 * Dispose cell editor control at mouse down (otherwise the control
			 * keep showing).
			 */
			public void mouseDown(MouseEvent e) {
				Control oldEditorControl = cellEditor.getEditor();
				if (null != oldEditorControl)
					oldEditorControl.dispose();
			}
		});
	}
	
	private void editItem(final TableItem item) {
		propertyTypeEditor = initTableEditor(propertyTypeEditor,
				item.getParent());
		cmbPropertyType = new Combo(item.getParent(), SWT.READ_ONLY);
		cmbPropertyType.setItems(new String[] { "LITERAL", "XML" });
		cmbPropertyType.setText(item.getText(1));
		propertyTypeEditor.setEditor(cmbPropertyType, item, 1);
		item.getParent().redraw();
		item.getParent().layout();
		cmbPropertyType.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event evt) {
				item.setText(1, cmbPropertyType.getText());
			}
		});
	}
	
	private TableEditor initTableEditor(TableEditor editor, Table table) {
		if (null != editor) {
			Control lastCtrl = editor.getEditor();
			if (null != lastCtrl) {
				lastCtrl.dispose();
			}
		}
		editor = new TableEditor(table);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		return editor;
	}
	
	private CompoundCommand getResultCommand() {
		if (null == resultCommand) {
			resultCommand = new CompoundCommand();
		}
		return resultCommand;
	}
}
