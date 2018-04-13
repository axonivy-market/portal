package ch.ivy.gawfs.beans;

import gawfs.TaskDef;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.gawfs.enums.FormElementType;

@ManagedBean
@RequestScoped
public class FormDefinitionBean implements Serializable {

  private static final long serialVersionUID = 8119703742579630358L;

  public FormElementType[] getFormElementTypes() {
    FormElementType[] elementTypes = {FormElementType.INPUT_TEXT, FormElementType.INPUT_NUMBER, FormElementType.INPUT_DATE};
    return elementTypes;
  }

  public boolean canFinishFormDefinition(List<TaskDef> definedTasks) {
    for (TaskDef taskDef : definedTasks) {
      if (!Optional.ofNullable(taskDef.getDynaFormController()).isPresent()) {
        return false;
      }
    }

    return true;
  }
}
