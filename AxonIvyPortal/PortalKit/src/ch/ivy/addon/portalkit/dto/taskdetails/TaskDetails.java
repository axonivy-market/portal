package ch.ivy.addon.portalkit.dto.taskdetails;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TaskDetails implements Serializable {

  private static final long serialVersionUID = -8424800742022240884L;

  private String id;
  private boolean isDefault;
  private List<TaskDetailsWidget> widgets;
  private TaskDetailsFilters filters;
  private boolean changed;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  public List<TaskDetailsWidget> getWidgets() {
    return widgets;
  }
  
  public void setWidgets(List<TaskDetailsWidget> widgets) {
    this.widgets = widgets;
  }
  
  public boolean isChanged() {
    return changed;
  }
  
  public void setChanged(boolean changed) {
    this.changed = changed;
  }

  public TaskDetailsFilters getFilters() {
    return filters;
  }

  public void setFilters(TaskDetailsFilters filters) {
    this.filters = filters;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean isDefault) {
    this.isDefault = isDefault;
  }
}