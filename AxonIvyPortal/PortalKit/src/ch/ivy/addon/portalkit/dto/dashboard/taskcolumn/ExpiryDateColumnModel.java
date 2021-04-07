package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;

public class ExpiryDateColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/EXPIRY_TIME");
    this.field = DashboardStandardTaskColumn.EXPIRY.getField();
    this.style = defaultIfEmpty(this.style, NORMAL_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__expiry-date u-text-align-center u-padding-0");
    this.format = DashboardColumnFormat.TIMESTAMP;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getExpiryTimestamp();
  }
}
