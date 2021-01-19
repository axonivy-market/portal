package ch.ivy.addon.portalkit.dto.taskdetails;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TaskDetails implements Serializable {

  private static final long serialVersionUID = -8424800742022240884L;

  private List<TaskDetailsWidget> widgets;
  private boolean changed;

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
}