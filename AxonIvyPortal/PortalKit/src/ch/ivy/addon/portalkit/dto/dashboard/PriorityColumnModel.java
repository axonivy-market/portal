package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.ITask;

public class PriorityColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  public PriorityColumnModel() {
    this.field = "priority";
    this.header = cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/abbreviation/PRIORITY");
    this.width = "80";
    this.styleClass = "dashboard-tasks__priority";
    this.property = "priority";
    this.type = DashboardColumnType.PRIORITY;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getPriority();
  }
}
