package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.ITask;

public class ExpiryDateColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  public ExpiryDateColumnModel() {
    this.field = "expiry";
    this.header = cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/EXPIRY_TIME");
    this.width = "120";
    this.styleClass = "dashboard-tasks__expiry-date";
    this.property = "expiryTimestamp";
    this.type = DashboardColumnType.DATE;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getExpiryTimestamp();
  }
}
