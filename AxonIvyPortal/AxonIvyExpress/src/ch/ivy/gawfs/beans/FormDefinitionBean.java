package ch.ivy.gawfs.beans;

import gawfs.TaskDef;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.gawfs.enums.FormElementType;
import ch.ivy.gawfs.enums.TaskType;

@ManagedBean
@RequestScoped
public class FormDefinitionBean implements Serializable {

  private static final long serialVersionUID = 8119703742579630358L;

  /**
   * Get form element types for input text field
   * 
   * @return formElementType array
   */
  public FormElementType[] getInputFormElementTypes() {
    FormElementType[] elementTypes = {FormElementType.INPUT_TEXT, FormElementType.INPUT_NUMBER, FormElementType.INPUT_DATE};
    return elementTypes;
  }

  /**
   * Check whether form definition step can be finished or not
   * 
   * @param definedTasks
   * @return check result
   */
  public boolean canFinishFormDefinition(List<TaskDef> definedTasks) {
    boolean isAnyCreateFormNotDefined
      = definedTasks.stream().filter(taskDef -> (taskDef.getTaskType() !=  TaskType.EMAIL  && !taskDef.getDragAndDropController().isNotDefined())).findFirst().isPresent();

    boolean isAnyEmailEmpty
      = definedTasks.stream().filter(taskDef -> taskDef.getTaskType() ==  TaskType.EMAIL  && !taskDef.getEmail().isEmpty()).findFirst().isPresent();

    return isAnyCreateFormNotDefined && isAnyEmailEmpty;
  }
}