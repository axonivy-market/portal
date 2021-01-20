package ch.ivy.addon.portalkit.dto.casedetails;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CaseDetails implements Serializable {

  private static final long serialVersionUID = -8424800742022240884L;

  private List<CaseDetailsWidget> widgets;
  private boolean changed;

  public List<CaseDetailsWidget> getWidgets() {
    return widgets;
  }
  
  public void setWidgets(List<CaseDetailsWidget> widgets) {
    this.widgets = widgets;
  }
  
  public boolean isChanged() {
    return changed;
  }
  
  public void setChanged(boolean changed) {
    this.changed = changed;
  }
}