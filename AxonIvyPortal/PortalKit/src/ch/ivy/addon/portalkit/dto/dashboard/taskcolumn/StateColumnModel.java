package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

public class StateColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE");
    this.field = DashboardStandardTaskColumn.STATE.getField();
    this.style = defaultIfEmpty(this.style, NORMAL_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__state u-text-align-center");
    this.fieldStyleClass = defaultIfEmpty(this.fieldStyleClass, StringUtils.EMPTY);
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
    return cms("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + state.toString());
  }
  
  @JsonIgnore
  public List<TaskState> getStates() {
    return this.filterList.stream().map(String::toUpperCase).map(TaskState::valueOf).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setStates(List<TaskState> states) {
    this.filterList = states.stream().map(TaskState::toString).collect(Collectors.toList());
  }
  
  @JsonIgnore
  private void initUserFilterStateOptions() {

  }
  
  @JsonIgnore
  public List<TaskState> getUserFilterStates() {
    return this.userFilterList.stream().map(String::toUpperCase).map(TaskState::valueOf).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterStates(List<TaskState> states) {
    this.userFilterList = states.stream().map(TaskState::toString).collect(Collectors.toList());
  }

  @SuppressWarnings("removal")
  @JsonIgnore
  public List<TaskState> getUserFilterStateOptions() {
    List<TaskState> states = getStates();
    if (CollectionUtils.isEmpty(states)) {
      states = Arrays.asList(TaskState.values()).stream().filter(s -> !s.equals(TaskState.UNASSIGNED)).sorted((s1, s2) -> StringUtils.compare(s1.toString(), s2.toString())).collect(Collectors.toList());
    }
    return states;
  }

}
