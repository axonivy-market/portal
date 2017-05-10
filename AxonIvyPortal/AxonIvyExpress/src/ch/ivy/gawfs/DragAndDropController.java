package ch.ivy.gawfs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean(name = "dragAndDropController")
@ViewScoped
public class DragAndDropController implements Serializable{

	private static final long serialVersionUID = 2896675125359151717L;

	private List<Formelement> availableFormelements;    
	private List<Formelement> selectedFormelementsHeader;
	private List<Formelement> selectedFormelementsLeftPanel;
	private List<Formelement> selectedFormelementsRightPanel;
	private List<Formelement> selectedFormelementsFooter;
    
	private List<Formelement> finalUsedFormelements;
	
    private Formelement selectedFormelement;
    
    private DynaFormController dynaFormController;
      
    
    public DragAndDropController() {
    	
    	availableFormelements = new ArrayList<Formelement>();    	
   
    	//for Testing only
//    	List<String> exampleData = Arrays.asList("Value 1", "Value 2", "Value 3");   	
//    	availableFormelements.add(new Formelement("10", "InputFieldText", "InputFieldTextName1", "InputFieldTextLabel1", true, 0, exampleData));
//    	availableFormelements.add(new Formelement("11", "InputFieldText", "InputFieldTextName2", "InputFieldTextLabel2", true, null, exampleData));
//    	availableFormelements.add(new Formelement("4", "InputTextArea", "InputTextAreaName1", "InputTextAreaLabel1", false, 3, exampleData));
//    	availableFormelements.add(new Formelement("4", "InputTextArea", "InputTextAreaName2", "InputTextAreaLabel2", false, 3, exampleData));
//    	availableFormelements.add(new Formelement("2", "InputFieldNumber", "InputFieldNumberName1", "InputFieldNumberLabel1", false, null, exampleData));
//    	availableFormelements.add(new Formelement("2", "InputFieldNumber", "InputFieldNumberName2", "InputFieldNumberLabel2", false, null, exampleData)); 
//    	availableFormelements.add(new Formelement("3", "InputFieldDate", "InputFieldDateName1", "InputFieldDateLabel1", false, null, exampleData));  
//    	availableFormelements.add(new Formelement("3", "InputFieldDate", "InputFieldDateName2", "InputFieldDateLabel2", false, null, exampleData));  	
//    	availableFormelements.add(new Formelement("5", "ManyCheckbox", "ManyCheckboxName1", "ManyCheckboxLabel1", false, null, exampleData));
//    	availableFormelements.add(new Formelement("5", "ManyCheckbox", "ManyCheckboxName2", "ManyCheckboxLabel2", false, null, exampleData));    	
//    	availableFormelements.add(new Formelement("6", "OneRadio", "OneRadioName1", "OneRadioLabel1", false, null, exampleData));
//    	availableFormelements.add(new Formelement("6", "OneRadio", "OneRadioName2", "OneRadioLabel2", false, null, exampleData));   	
//    	availableFormelements.add(new Formelement("7", "FileUpload", "FileUploadName", "FileUploadLabel", false, 2, exampleData));

    	
    			
    			
    			
    	selectedFormelementsHeader = new ArrayList<Formelement>();
    	selectedFormelementsLeftPanel = new ArrayList<Formelement>();
    	selectedFormelementsRightPanel = new ArrayList<Formelement>();
    	selectedFormelementsFooter = new ArrayList<Formelement>();
    	finalUsedFormelements = new ArrayList<Formelement>();
        
        Ivy.log().info("Formelements initiated");
	}
    
    
    public void deleteFormelement(Formelement formelement){
    	availableFormelements.remove(formelement);
    	updateForm();
    }

     
	public void onFieldDropHeader(DragDropEvent ddEvent) {
		Formelement droppedFormelement = ((Formelement) ddEvent.getData());

		if (droppedFormelement.getType().equals("FileUpload")) {
			for (Formelement element : selectedFormelementsHeader) {
				if (element.getType().equals("FileUpload")) {
					displayFileUploadPositionNotice();
					return;
				}
				
			}
			for (Formelement element : selectedFormelementsFooter) {
				if (element.getType().equals("FileUpload")) {
					displayFileUploadPositionNotice();
					return;
				}
				
			}
			
		}
		

		selectedFormelementsHeader.add(droppedFormelement);
		availableFormelements.remove(droppedFormelement);

		updateForm();
		Ivy.log().info(
				"Formselement (" + droppedFormelement.getType()
						+ ") droppped in Header");
	}
    
    
    public void resetFormelementHeader(Formelement formelement){
    	availableFormelements.add(formelement);
    	selectedFormelementsHeader.remove(formelement);
    	
    	updateForm();
    	Ivy.log().info("Formelement resetted from Header");
    }
    
    
    
    public void onFieldDropLeftPanel(DragDropEvent ddEvent) {
        Formelement droppedFormelement = ((Formelement) ddEvent.getData());
        
        if(droppedFormelement.getType().equals("FileUpload")){
         	 displayFileUploadPositionNotice();           
         	return;
       }
  
        selectedFormelementsLeftPanel.add(droppedFormelement);
        availableFormelements.remove(droppedFormelement);
        
        updateForm();        
        Ivy.log().info("Formselement(" + droppedFormelement.getType()+ ") droppped in LeftPanel");
    }
    
    public void resetFormelementLeftPanel(Formelement formelement){
    	availableFormelements.add(formelement);
    	selectedFormelementsLeftPanel.remove(formelement);
    	
    	updateForm();
    	Ivy.log().info("Formelement resetted from Left Panel");
    }
    
    public void onFieldDropRightPanel(DragDropEvent ddEvent) {
        Formelement droppedFormelement = ((Formelement) ddEvent.getData());
        
        if(droppedFormelement.getType().equals("FileUpload")){
        	 displayFileUploadPositionNotice();                    
          	return;
        }
  
        selectedFormelementsRightPanel.add(droppedFormelement);
        availableFormelements.remove(droppedFormelement);
        
        updateForm();
        Ivy.log().info("Formselement(" + droppedFormelement.getType()+ ") droppped in RightPanel");
    }
    
    public void resetFormelementRightPanel(Formelement formelement){
    	availableFormelements.add(formelement);
    	selectedFormelementsRightPanel.remove(formelement);
    	
    	updateForm();
    	Ivy.log().info("Formelement resetted from Right Panel");
    }
    
    
    public void onFieldDropFooter(DragDropEvent ddEvent) {
        Formelement droppedFormelement = ((Formelement) ddEvent.getData());
        
    	if (droppedFormelement.getType().equals("FileUpload")) {
			for (Formelement element : selectedFormelementsHeader) {
				if (element.getType().equals("FileUpload")) {
					displayFileUploadPositionNotice();
					return;
				}
				
			}
			for (Formelement element : selectedFormelementsFooter) {
				if (element.getType().equals("FileUpload")) {
					 displayFileUploadPositionNotice();          
					return;
				}
				
			}
			
		}
        
       
  
        selectedFormelementsFooter.add(droppedFormelement);
        availableFormelements.remove(droppedFormelement);
        
        updateForm();
        Ivy.log().info("Formselement (" + droppedFormelement.getType()+ ") droppped in Footer");
        
    }

	private void displayFileUploadPositionNotice() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, Ivy.cms().co("/Dialogs/components/CaseDocument/fileUploadElementPosition"), "");
		  FacesContext.getCurrentInstance().addMessage("DnDmessages", message);
	}
    
    public void resetFormelementFooter(Formelement formelement){
    	availableFormelements.add(formelement);
    	selectedFormelementsFooter.remove(formelement);
    	
    	updateForm();
    	Ivy.log().info("Formelement resetted from Footer");
    }


	public List<Formelement> getAvailableFormelements() {
		return availableFormelements;
	}


	public void setAvailableFormelements(List<Formelement> availableFormelements) {
		this.availableFormelements = availableFormelements;
	}


	public List<Formelement> getSelectedFormelementsHeader() {
		return selectedFormelementsHeader;
	}


	public void setSelectedFormelementsHeader(
			List<Formelement> selectedFormelementsHeader) {
		this.selectedFormelementsHeader = selectedFormelementsHeader;
	}


	public List<Formelement> getSelectedFormelementsLeftPanel() {
		return selectedFormelementsLeftPanel;
	}


	public void setSelectedFormelementsLeftPanel(
			List<Formelement> selectedFormelementsLeftPanel) {
		this.selectedFormelementsLeftPanel = selectedFormelementsLeftPanel;
	}


	public List<Formelement> getSelectedFormelementsRightPanel() {
		return selectedFormelementsRightPanel;
	}


	public void setSelectedFormelementsRightPanel(
			List<Formelement> selectedFormelementsRightPanel) {
		this.selectedFormelementsRightPanel = selectedFormelementsRightPanel;
	}


	public List<Formelement> getSelectedFormelementsFooter() {
		return selectedFormelementsFooter;
	}


	public void setSelectedFormelementsFooter(
			List<Formelement> selectedFormelementsFooter) {
		this.selectedFormelementsFooter = selectedFormelementsFooter;
	}


	public Formelement getSelectedFormelement() {
		return selectedFormelement;
	}


	public void setSelectedFormelement(Formelement selectedFormelement) {
		this.selectedFormelement = selectedFormelement;
	}


	public DynaFormController getDynaFormController() {
		return dynaFormController;
	}


	public void setDynaFormController(DynaFormController dynaFormController) {
		this.dynaFormController = dynaFormController;
	}
	
	private void updateForm(){
		dynaFormController.createForm();
	}


	public List<Formelement> getFinalUsedFormelements() {
		return finalUsedFormelements;
	}


	public void setFinalUsedFormelements(List<Formelement> finalUsedFormelements) {
		this.finalUsedFormelements = finalUsedFormelements;
	}
	
	public void addFinalUsedFormelements(Formelement formelement) {
		this.finalUsedFormelements.add(formelement);
	}
    
    
	
    
    

     
 

}