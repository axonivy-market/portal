package ch.ivy.gawfs;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

public class DynaFormController implements Serializable {

	private static final String CONTROL_SPACER = "Spacer";

	private static final String FORM_ELEMENT_FILE_UPLOAD = "FileUpload";

	private static final long serialVersionUID = -1486400417160014320L;

	private DynaFormModel model;
	private DynaFormRow tempRow;
	private DragAndDropController dragAndDropController;

	public DynaFormController(DragAndDropController dragAndDropController) {
		this.dragAndDropController = dragAndDropController;
		model = new DynaFormModel();

	}

	public void createForm(){
			model = new DynaFormModel();
			dragAndDropController.getFinalUsedFormelements().clear();
			
		//Loop to create all Elements places in the Header Section	
		for (Formelement element : dragAndDropController.getSelectedFormelementsHeader()) {				
			dragAndDropController.addFinalUsedFormelements(element);
			
			tempRow = model.createRegularRow();
			
			if(!element.getType().equals(FORM_ELEMENT_FILE_UPLOAD)){
				
				DynaFormLabel label = tempRow.addLabel(element.getLabel());
				DynaFormControl control = tempRow.addControl(element, element.getType(),3,1);
				label.setForControl(control);
				
			}else{
				DynaFormLabel label = tempRow.addLabel(element.getLabel(),4,1);
				tempRow = model.createRegularRow();
				DynaFormControl control = tempRow.addControl(element, element.getType(),4,1);
				label.setForControl(control);
			}
		}
		
		
		//Loop to create all Elements from the Left and Right Section	
		for (int i = 0; i < getMax(dragAndDropController.getSelectedFormelementsLeftPanel().size(), dragAndDropController.getSelectedFormelementsRightPanel().size()); i++) {
			
			tempRow = model.createRegularRow();
			
			//Elements from the Left Section	
			if (dragAndDropController.getSelectedFormelementsLeftPanel().size()> i) {
				Formelement element = dragAndDropController.getSelectedFormelementsLeftPanel().get(i);
				dragAndDropController.addFinalUsedFormelements(element);
				
				DynaFormLabel label = tempRow.addLabel(element.getLabel());
				DynaFormControl control = tempRow.addControl(element, element.getType());
				label.setForControl(control);				
			} else {
				tempRow.addLabel(StringUtils.EMPTY);
				tempRow.addControl(null, CONTROL_SPACER);
			}
			
			//Elements from the Right Section	
			if (dragAndDropController.getSelectedFormelementsRightPanel().size()> i) {	
				
				Formelement element = dragAndDropController.getSelectedFormelementsRightPanel().get(i);
				dragAndDropController.addFinalUsedFormelements(element);
				
				DynaFormLabel label = tempRow.addLabel(element.getLabel());
				DynaFormControl control = tempRow.addControl(element, element.getType());
				label.setForControl(control);
				
			} else {
				tempRow.addLabel(StringUtils.EMPTY);
				tempRow.addControl(null, CONTROL_SPACER);
				
			}	
	
		}
		
		//Loop to create all Elements places in the Footer Section	
		for (Formelement element : dragAndDropController.getSelectedFormelementsFooter()) {
			
			dragAndDropController.addFinalUsedFormelements(element);
			tempRow = model.createRegularRow();
			
			if(!element.getType().equals(FORM_ELEMENT_FILE_UPLOAD)){
				DynaFormLabel label = tempRow.addLabel(element.getLabel());
				DynaFormControl control = tempRow.addControl(element, element.getType(),3,1);
				label.setForControl(control);
			}else{
				DynaFormLabel label = tempRow.addLabel(element.getLabel(),4,1);
				tempRow = model.createRegularRow();
				DynaFormControl control = tempRow.addControl(element, element.getType(),4,1);
				label.setForControl(control);
			}
		}
	}


	public String submitForm() {
		FacesMessage.Severity sev = FacesContext.getCurrentInstance()
				.getMaximumSeverity();
		boolean hasErrors = (sev != null && (FacesMessage.SEVERITY_ERROR
				.compareTo(sev) >= 0));

		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.addCallbackParam("isValid", !hasErrors);

		return null;
	}

	public DynaFormModel getModel() {
		return model;
	}
	
	
    private int getMax(int a, int b ){
    	if (a > b) {
    		return a;
    	}else{
    		return b;
    	}
    }

}
