package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

public class PriorityColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.PRIORITY.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.format = getDefaultFormat();
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/abbreviation/PRIORITY";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  protected int getDefaultColumnWidth() {
    return TINY_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__priority text-center widget-column";
  }

  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getPriority();
  }
  
  @JsonIgnore
  public List<WorkflowPriority> getPriorities() {
    return this.filterList.stream().map(String::toUpperCase).map(WorkflowPriority::valueOf).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setPriorities(List<WorkflowPriority> priorities) {
    this.filterList = priorities.stream().map(WorkflowPriority::toString).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public List<WorkflowPriority> getUserFilterPriorityOptions() {
    List<WorkflowPriority> options = getPriorities();
    if (CollectionUtils.isEmpty(options)) {
      options = Arrays.asList(WorkflowPriority.values());
    }
    return options;
  }
  
  @JsonIgnore
  public List<WorkflowPriority> getUserFilterPriorities() {
    return this.userFilterList.stream().map(String::toUpperCase).map(WorkflowPriority::valueOf).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterPriorities(List<WorkflowPriority> priorities) {
    this.userFilterList = priorities.stream().map(WorkflowPriority::toString).collect(Collectors.toList());
  }
}
