package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

public class StateColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;
  
  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE"));
    this.field = DashboardStandardTaskColumn.STATE.getField();
    this.style = defaultIfEmpty(this.style, "width: 100px");
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__state");
    this.fieldStyleClass = defaultIfEmpty(this.fieldStyleClass, "dashboard-tasks__state-text");
    this.format = DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return getUserFriendlyTaskState(task.getState());
  }
  
  private String getUserFriendlyTaskState(TaskState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    switch (state) {
      case SUSPENDED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/SUSPENDED");
      case CREATED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/CREATED");
      case RESUMED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/RESUMED");
      case DONE:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/DONE");
      case PARKED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/PARKED");
      case DESTROYED:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/DESTROYED");
      case ZOMBIE:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/ZOMBIE");
      default:
        return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/SYSTEM");
    }
  }
}
