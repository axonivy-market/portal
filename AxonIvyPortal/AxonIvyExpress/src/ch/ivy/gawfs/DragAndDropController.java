package ch.ivy.gawfs;

import java.io.Serializable;
import java.util.ArrayList;
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

	private static final String FORM_ELEMENT_FILE_UPLOAD = "FileUpload";

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
    	selectedFormelementsHeader = new ArrayList<Formelement>();
    	selectedFormelementsLeftPanel = new ArrayList<Formelement>();
    	selectedFormelementsRightPanel = new ArrayList<Formelement>();
    	selectedFormelementsFooter = new ArrayList<Formelement>();
    	finalUsedFormelements = new ArrayList<Formelement>();
	}
    
    
    public void deleteFormelement(Formelement formelement){
    	availableFormelements.remove(formelement);
    	updateForm();
    }

     
	public void onFieldDropHeader(DragDropEvent ddEvent) {
		Formelement droppedFormelement = ((Formelement) ddEvent.getData());

		if (droppedFormelement.getType().equals(FORM_ELEMENT_FILE_UPLOAD)) {
			for (Formelement element : selectedFormelementsHeader) {
				if (element.getType().equals(FORM_ELEMENT_FILE_UPLOAD)) {
					displayFileUploadPositionNotice();
					return;
				}
				
			}
			for (Formelement element : selectedFormelementsFooter) {
				if (element.getType().equals(FORM_ELEMENT_FILE_UPLOAD)) {
					displayFileUploadPositionNotice();
					return;
				}
				
			}
			
		}
		

		selectedFormelementsHeader.add(droppedFormelement);
		availableFormelements.remove(droppedFormelement);

		updateForm();
	}
    
    
    public void resetFormelementHeader(Formelement formelement){
    	availableFormelements.add(formelement);
    	selectedFormelementsHeader.remove(formelement);
    	
    	updateForm();
    }
    
    
    
    public void onFieldDropLeftPanel(DragDropEvent ddEvent) {
        Formelement droppedFormelement = ((Formelement) ddEvent.getData());
        
        if(droppedFormelement.getType().equals(FORM_ELEMENT_FILE_UPLOAD)){
         	 displayFileUploadPositionNotice();           
         	return;
       }
  
        selectedFormelementsLeftPanel.add(droppedFormelement);
        availableFormelements.remove(droppedFormelement);
        
        updateForm();        
    }
    
    public void resetFormelementLeftPanel(Formelement formelement){
    	availableFormelements.add(formelement);
    	selectedFormelementsLeftPanel.remove(formelement);
    	
    	updateForm();
    }
    
    public void onFieldDropRightPanel(DragDropEvent ddEvent) {
        Formelement droppedFormelement = ((Formelement) ddEvent.getData());
        
        if(droppedFormelement.getType().equals(FORM_ELEMENT_FILE_UPLOAD)){
        	 displayFileUploadPositionNotice();                    
          	return;
        }
  
        selectedFormelementsRightPanel.add(droppedFormelement);
        availableFormelements.remove(droppedFormelement);
        
        updateForm();
    }
    
    public void resetFormelementRightPanel(Formelement formelement){
    	availableFormelements.add(formelement);
    	selectedFormelementsRightPanel.remove(formelement);
    	
    	updateForm();
    }
    
    
    public void onFieldDropFooter(DragDropEvent ddEvent) {
        Formelement droppedFormelement = ((Formelement) ddEvent.getData());
        
    	if (droppedFormelement.getType().equals(FORM_ELEMENT_FILE_UPLOAD)) {
			for (Formelement element : selectedFormelementsHeader) {
				if (element.getType().equals(FORM_ELEMENT_FILE_UPLOAD)) {
					displayFileUploadPositionNotice();
					return;
				}
				
			}
			for (Formelement element : selectedFormelementsFooter) {
				if (element.getType().equals(FORM_ELEMENT_FILE_UPLOAD)) {
					 displayFileUploadPositionNotice();          
					return;
				}
				
			}
			
		}
        
       
  
        selectedFormelementsFooter.add(droppedFormelement);
        availableFormelements.remove(droppedFormelement);
        
        updateForm();
        
    }

	private void displayFileUploadPositionNotice() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, Ivy.cms().co("/Dialogs/components/CaseDocument/fileUploadElementPosition"), "");
		  FacesContext.getCurrentInstance().addMessage("availableFormelements", message);
	}
    
    public void resetFormelementFooter(Formelement formelement){
    	availableFormelements.add(formelement);
    	selectedFormelementsFooter.remove(formelement);
    	
    	updateForm();
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