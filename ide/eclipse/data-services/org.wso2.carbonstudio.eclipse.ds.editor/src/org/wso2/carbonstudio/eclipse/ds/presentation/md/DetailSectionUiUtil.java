package org.wso2.carbonstudio.eclipse.ds.presentation.md;

import java.math.BigInteger;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.wso2.carbonstudio.eclipse.ds.DataService;


public class DetailSectionUiUtil {
	
	public static boolean isFocusedOnDetailSection;
	DataService dataService;
	EditingDomain editingDomain;
	
	protected DetailSectionUiUtil(DataService dataService,EditingDomain editingDomain){
		
		this.dataService = dataService;
		this.editingDomain = editingDomain;
	}
	
	
	public Combo getCustomComboField(Composite detailsclient,
			FormToolkit toolkit,
			Object input,
			String initialValue,
			EAttribute metaObject,
			String [] displayValues){
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING );
		gd.widthHint = 200;
		Combo combo = new Combo(detailsclient, SWT.READ_ONLY);
		
		for (int i= 0;i<displayValues.length;i++){
			
			combo.add(displayValues[i],i);
		}
		if(initialValue != null){
			
			for(int j = 0 ; j<displayValues.length ;j++){
				
				if(initialValue.equals(displayValues[j])){
					combo.select(j);
					break;
				}
			}
		}
		combo.setLayoutData(gd);
		addFocusListner(combo);
		
		
		toolkit.adapt(combo, true, true);
		addModifyListnerForCustomComboFields(combo,input,metaObject);
		return combo;
	}
	/**
	 * @param detailsclient : 
	 * @param toolkit
	 * @param input
	 * @param initialVal
	 * @param metaObject
	 * @return Combo with boolean persistance feature.
	 */
	public Combo getBooleanComboField(Composite detailsclient,FormToolkit toolkit,Object input,boolean initialVal,EAttribute metaObject){
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING );
		gd.widthHint = 200;
		Combo combo = new Combo(detailsclient, SWT.READ_ONLY);
		combo.add("True", 0);
		combo.add("False", 1);
		combo.setLayoutData(gd);
		addFocusListner(combo);
		if(initialVal){
			combo.select(0);
		}else{
			combo.select(1);
		}
		
		toolkit.adapt(combo, true, true);
		addModifylistnersForBooleanComboFields(combo,input,metaObject);
		return combo;
	}
	
	/**
	 * @param detailsclient : Client that hold the creting combo.
	 * @param toolkit
	 * @param input
	 * @param initialVal
	 * @param metaObject
	 * @return : Combo with string persistance feature.
	 */
	public Combo getBooleanComboWithStringPersistance(Composite detailsclient,FormToolkit toolkit,Object input,String initialVal,EAttribute metaObject){
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING );
		gd.widthHint = 200;
		Combo combo = new Combo(detailsclient, SWT.READ_ONLY);
		combo.add("True", 0);
		combo.add("False", 1);
		combo.setLayoutData(gd);
		addFocusListner(combo);
		boolean isSet  = new Boolean(initialVal).booleanValue();
		if(isSet){
			combo.select(0);
		}else{
			combo.select(1);
		}
		
		toolkit.adapt(combo, true, true);
		addModifylistnersForBooleanComboWithStringPersi(combo,input,metaObject);
		return combo;
	}
	
	/**
	 * @param detailsclient: Client that hold the creting text
	 * @param toolkit : Form toolkit
	 * @param input : Input Object currently selected
	 * @param text : Initial value
	 * @param metaObject : Meta Object describes the EMF context
	 * @param dataType : Data Type of the focuesed value
	 * @return Configured Styled Text field
	 */
	public StyledText getAttributeField(Composite detailsclient,FormToolkit toolkit,Object input,String existingVal,EAttribute metaObject,String dataType){
		
		StyledText dtxt = new StyledText(detailsclient,SWT.BORDER );		
		addCommonActions(dtxt);
		dtxt.setEditable(true);
		dtxt.setEnabled(true);
		
		//adding control decoration for validation 
		final ControlDecoration controlDecoration = crateControlDecoration(dtxt);
		controlDecoration.hide();
		toolkit.adapt(dtxt, true, true);
		if(existingVal != null)
		dtxt.setText(existingVal);

		StyleRange styleRange = new StyleRange();
		styleRange.start = 0;
		styleRange.length = "".length();
		styleRange.fontStyle = SWT.NORMAL;
		styleRange.foreground = detailsclient.getDisplay().getSystemColor(SWT.COLOR_BLACK);
		dtxt.setStyleRange(styleRange);
				
		GridData gd = new GridData();
		gd.widthHint = 200;
		dtxt.setLayoutData(gd);
		addModifyListnersForTextFields(dtxt,dataType,input,metaObject,controlDecoration);
		addFocusListner(dtxt);
		return dtxt;
		
	}
	
	private  void addCommonActions(StyledText text){
		
		Listener keyBindListener = new Listener() {
			
			   public void handleEvent( Event event ) {
			      if ( event.stateMask == SWT.CTRL && event.keyCode == 'a') {
			    	  
			         ((StyledText)event.widget).selectAll();
			         
			      }else if(event.stateMask == SWT.CTRL && event.keyCode == 'c'){
			    	  
			    	  ((StyledText)event.widget).copy();
			    	  
			      }else if(event.stateMask == SWT.CTRL && event.keyCode == 'v'){
			    	  
			    	  ((StyledText)event.widget).paste();
			    	  
			      }else if(event.stateMask == SWT.CTRL && event.keyCode == 'x'){
			    	  
			    	  ((StyledText)event.widget).cut();
			    	  
			      }else if(event.stateMask == SWT.CTRL && event.keyCode == 'z'){
			    	  
			    	 // ((StyledText)event.widget).
			      }
			   }
			};
			
			text.addListener(SWT.KeyUp, keyBindListener);
	}

	private  ControlDecoration crateControlDecoration(StyledText dtxt){
	
		ControlDecoration controlDecoration = new ControlDecoration(dtxt, SWT.LEFT | SWT.TOP);
			
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
			
		controlDecoration.setImage(fieldDecoration.getImage());
	
		return controlDecoration;
	}
	
	private void addFocusListner(Composite comp){
		
		comp.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				isFocusedOnDetailSection = false;
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				isFocusedOnDetailSection = true;
			}
		});
	}
	
	private void addModifyListnerForCustomComboFields(final Combo combo,final Object input,final EAttribute metaObject){
		
		combo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				setStringAttribute(input, metaObject,combo.getItem(combo.getSelectionIndex()));
				
			}
		});
    	
	}
	
	private void addModifylistnersForBooleanComboWithStringPersi(final Combo combo,final Object input,final EAttribute metaObject){
		
		combo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				setStringAttribute(input, metaObject,(combo.getSelectionIndex() == 0) ? "true" : "false");
				
			}
		});
	}
 
    private void addModifylistnersForBooleanComboFields(final Combo combo,final Object input,final EAttribute metaObject){
    	
    	combo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				setBooleanAttribute(input, metaObject,(combo.getSelectionIndex() == 0) ? true  : false);
				
			}
		});
    	
    }
 	private  void addModifyListnersForTextFields(StyledText dtxt,String dataType,final Object input,final EAttribute metaObject,final ControlDecoration controlDecoration ){
	 
	 if(dataType.equals(DetailSectionCustomUiConstants.STRING)){
			
			dtxt.addModifyListener(new ModifyListener() {
				
				public void modifyText(ModifyEvent event) {
					
					setStringAttribute(input,metaObject,((StyledText) event.widget).getText());
				}
			});
			
			
		
				
		}else if(dataType.equals(DetailSectionCustomUiConstants.LONG) ){
			
			dtxt.addModifyListener(new ModifyListener() {
				
				public void modifyText(ModifyEvent event) {
					
					String longNum = ((StyledText) event.widget).getText();
					if(isValidLongString(longNum)){
						
						setLongAttribute(input,metaObject,longNum);
						controlDecoration.hide();
						
						}else{
						
						controlDecoration.setDescriptionText("Please enter valid long value");
						controlDecoration.show();
						}
					
				}
			});
		}else if(dataType.equals(DetailSectionCustomUiConstants.DOUBLE)){
			
			dtxt.addModifyListener(new ModifyListener() {
				
				public void modifyText(ModifyEvent event) {
					
					String doubleNum = ((StyledText) event.widget).getText();
					if(isValidDoubleString(doubleNum)){
						
						setDoubleAttribute(input,metaObject,doubleNum);
						controlDecoration.hide();
						
						}else{
						
						controlDecoration.setDescriptionText("Please enter valid double value");
						controlDecoration.show();
						
						}
					
				}
			});
		}else if(dataType.equals(DetailSectionCustomUiConstants.BIGINTEGER)){

			dtxt.addModifyListener(new ModifyListener() {
				
				public void modifyText(ModifyEvent event) {
					
					String bigInt = ((StyledText) event.widget).getText();
					if(isValidDoubleString(bigInt)){
						
						setBigIntAttribute(input,metaObject,bigInt);
						controlDecoration.hide();
						
						}else{
						
						controlDecoration.setDescriptionText("Please enter valid integer value");
						controlDecoration.show();
						}
										
				}
			});
		}
		
 	}
 
 	
    private void setStringAttribute(Object input,EAttribute attributeRef,String text){
    	Command setAttribCommand = SetCommand.create(editingDomain, input,
    			attributeRef, text);
    	if (setAttribCommand.canExecute()) {
    			
    		editingDomain.getCommandStack().execute(setAttribCommand);
			
		} else {
			MessageDialog.openInformation(Display.getCurrent()
					.getActiveShell(),"Problem Occurred!", "Can not modify "+attributeRef.getName());
		}
    	
    }
    
    private void setLongAttribute(Object input,EAttribute attributeRef,String text){
    	Command setAttribCommand = SetCommand.create(editingDomain, input,
    			attributeRef,new Long(text) );
    	if (setAttribCommand.canExecute()) {
    			
    		editingDomain.getCommandStack().execute(setAttribCommand);
			
		} else {
			MessageDialog.openInformation(Display.getCurrent()
					.getActiveShell(),"Problem Occurred!", "Can not modify "+attributeRef.getName());
		}
    }
    
    private void setDoubleAttribute(Object input,EAttribute attributeRef,String text){
    	Command setAttribCommand = SetCommand.create(editingDomain, input,
    			attributeRef,new Double(text) );
    	if (setAttribCommand.canExecute()) {
    			
    		editingDomain.getCommandStack().execute(setAttribCommand);
			
		} else {
			MessageDialog.openInformation(Display.getCurrent()
					.getActiveShell(),"Problem Occurred!", "Can not modify "+attributeRef.getName());
		}
    }
    
    private void setBigIntAttribute(Object input,EAttribute attributeRef,String text){
    	
    	Command setAttribCommand = SetCommand.create(editingDomain, input,
    			attributeRef,new BigInteger(text) );
    	if (setAttribCommand.canExecute()) {
    			
    		editingDomain.getCommandStack().execute(setAttribCommand);
			
		} else {
			MessageDialog.openInformation(Display.getCurrent()
					.getActiveShell(),"Problem Occurred!", "Can not modify "+attributeRef.getName());
		}
    }
    
    private void setBooleanAttribute(Object input,EAttribute attributeRef,boolean isSet){
    	Command setAttribCommand = SetCommand.create(editingDomain, input,
    			attributeRef, new Boolean(isSet));
    	if (setAttribCommand.canExecute()) {
    			
    		editingDomain.getCommandStack().execute(setAttribCommand);
			
		} else {
			MessageDialog.openInformation(Display.getCurrent()
					.getActiveShell(),"Problem Occurred!", "Can not modify "+attributeRef.getName());
		}
    }
    
    
    public static boolean isValidLongString(String text){
		
		try{
			
			new Long(text);
			
		}catch (NumberFormatException  e) {
			
			return false;
		}
		
		return true;
	}
    
   	
	private boolean isValidDoubleString(String text){
		
		try{
			
			new Double(text);
			
		}catch(NumberFormatException e){
			
			return false;
		}
		
		return true;
	}
}
