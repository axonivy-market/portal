package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivyteam.ivy.workflow.ITask;

public class DescriptionColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  public DescriptionColumnModel() {
    this.field = "description";
    this.header = cms("/ch.ivy.addon.portalkit.ui.jsf/common/description");
    this.property = "description";
    this.sortable = false;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getDescription();
  }
}
