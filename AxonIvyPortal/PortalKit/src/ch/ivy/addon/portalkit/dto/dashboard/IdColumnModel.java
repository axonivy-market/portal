package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;

public class IdColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  public IdColumnModel() {
    this.header = cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ID");
    this.width = "120";
    this.styleClass = "dashboard-tasks__id";
    this.property = DashboardStandardTaskColumn.ID.getProperty();
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getId();
  }
}
