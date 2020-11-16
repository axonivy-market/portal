package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

public class PriorityColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/abbreviation/PRIORITY"));
    this.field = DashboardStandardTaskColumn.PRIORITY.getField();
    this.style = defaultIfEmpty(this.style, "width: 80px");
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__priority");
    this.format = DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getPriority();
  }
  
  public List<WorkflowPriority> getPriorities() {
    return this.filterList.stream().map(String::toUpperCase).map(WorkflowPriority::valueOf).collect(Collectors.toList());
  }
  
  public void setPriorities(List<WorkflowPriority> priorities) {
    this.filterList = priorities.stream().map(WorkflowPriority::toString).collect(Collectors.toList());
  }
}
