package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class StateColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;
  private static final String TASK_BUSINESS_STATE_CMS_PATH = "/ch.ivy.addon.portalkit.ui.jsf/taskBusinessState/";

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.STATE.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.fieldStyleClass = defaultIfEmpty(this.fieldStyleClass, StringUtils.EMPTY);
    this.format = getDefaultFormat();
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  protected int getDefaultColumnWidth() {
    return NORMAL_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__state text-center widget-column";
  }

  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return getUserFriendlyTaskState(task.getBusinessState());
  }

  private String getUserFriendlyTaskState(TaskBusinessState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    return cms(TASK_BUSINESS_STATE_CMS_PATH + state);
  }
  
  @JsonIgnore
  public List<TaskBusinessState> getStates() {
    List<TaskBusinessState> states = this.filterList.stream().map(String::toUpperCase)
        .map(TaskBusinessState::valueOf)
        .collect(Collectors.toList());
    return TaskUtils.filterStateByPermission(states);
  }
  
  @JsonIgnore
  public void setStates(List<TaskBusinessState > states) {
    this.filterList = states.stream().map(TaskBusinessState ::toString).collect(Collectors.toList());
  }
  
  @JsonIgnore
  private void initUserFilterStateOptions() {

  }
  
  @JsonIgnore
  public List<TaskBusinessState> getUserFilterStates() {
    return this.userFilterList.stream().map(String::toUpperCase).map(TaskBusinessState ::valueOf).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterStates(List<TaskBusinessState> states) {
    this.userFilterList = states.stream().map(TaskBusinessState::toString).collect(Collectors.toList());
  }

  @JsonIgnore
  public List<TaskBusinessState> getUserFilterStateOptions() {
    List<TaskBusinessState> states = getStates();
    if (CollectionUtils.isEmpty(states)) {
      states = TaskUtils.getValidStates();
    } else {
      states = TaskUtils.filterStateByPermission(states);
    }
    return states;
  }

}
