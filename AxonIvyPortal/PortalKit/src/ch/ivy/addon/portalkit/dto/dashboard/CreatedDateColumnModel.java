package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.ITask;

public class CreatedDateColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  public CreatedDateColumnModel() {
    this.field = "created";
    this.header = cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CREATION_TIME");
    this.width = "100";
    this.styleClass = "dashboard-tasks__created-date";
    this.property = "startTimestamp";
    this.type = DashboardColumnType.DATE;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getStartTimestamp();
  }
}
