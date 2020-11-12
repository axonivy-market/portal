package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;

public class CreatedDateColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CREATION_TIME"));
    this.field = DashboardStandardTaskColumn.CREATED.getField();
    this.style = defaultIfEmpty(this.style, "width: 100px");
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__created-date");
    this.format = DashboardColumnFormat.DATE;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getStartTimestamp();
  }
}
