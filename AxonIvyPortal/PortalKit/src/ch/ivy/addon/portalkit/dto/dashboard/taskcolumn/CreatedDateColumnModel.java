package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;

public class CreatedDateColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CREATION_TIME");
    this.field = DashboardStandardTaskColumn.CREATED.getField();
    this.style = defaultIfEmpty(this.style, SMALL_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__created-date u-text-align-center");
    this.format = DashboardColumnFormat.TIMESTAMP;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getStartTimestamp();
  }

}
