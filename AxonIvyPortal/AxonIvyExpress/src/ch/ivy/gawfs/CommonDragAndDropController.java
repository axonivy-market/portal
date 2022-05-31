package ch.ivy.gawfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.primefaces.event.DragDropEvent;

public abstract class CommonDragAndDropController {

  protected static final int NUMBER_DROP_EVENT_INFO = 2;
  protected static final int NUMBER_DRAG_EVENT_INFO = 4;
  protected static final String SEPARATOR_CHAR = ":";

  protected List<Formelement> availableFormelements;

  protected List<Formelement> finalUsedFormelements;

  protected Formelement selectedFormelement;

  protected DynaFormController dynaFormController;

  public CommonDragAndDropController() {
    availableFormelements = new ArrayList<>();
    finalUsedFormelements = new ArrayList<>();
  }

  public void onFieldDrop(DragDropEvent<Object> ddEvent) {
    throw new NotImplementedException(ddEvent.getDragId() + SEPARATOR_CHAR + ddEvent.getDropId());
  }


  public void deleteFormelement(Formelement formelement) {
    availableFormelements.remove(formelement);
    updateForm();
  }

  public void resetFormelement(Formelement formelement, List<Formelement> selectedFormelements) {
    availableFormelements.add(formelement);
    selectedFormelements.remove(formelement);
    updateForm();
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

  public Formelement getSelectedFormelement() {
    return selectedFormelement;
  }

  public void setSelectedFormelement(Formelement selectedFormelement) {
    this.selectedFormelement = selectedFormelement;
  }

  public List<Formelement> getAvailableFormelements() {
    return availableFormelements;
  }

  public void setAvailableFormelements(List<Formelement> availableFormelements) {
    this.availableFormelements = availableFormelements;
  }

  public DynaFormController getDynaFormController() {
    return dynaFormController;
  }


  public void setDynaFormController(DynaFormController dynaFormController) {
    this.dynaFormController = dynaFormController;
  }

  protected void updateForm() {
    dynaFormController.createForm();
  }
}
