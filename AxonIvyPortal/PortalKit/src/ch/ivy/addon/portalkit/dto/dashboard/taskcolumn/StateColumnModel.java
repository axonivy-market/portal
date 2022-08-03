package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

public class StateColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.STATE.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.fieldStyleClass = defaultIfEmpty(this.fieldStyleClass, StringUtils.EMPTY);
    this.format = getDefaultFormat();
  }

  @Override
  public String getHeaderText() {
    return translateHeader(defaultIfEmpty(this.header, DashboardConfigurationPrefix.CMS + getDefaultHeaderCMS()));
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
  public String getDefaultStyle() {
    return NORMAL_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__state u-text-align-center";
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

  @JsonIgnore
  public List<TaskState> getUserFilterStateOptions() {
    List<TaskState> states = getStates();
    if (CollectionUtils.isEmpty(states)) {
      states = TaskUtils.getValidStates();
    } else {
      states = TaskUtils.filterStateByPermission(states);
    }
    return states;
  }

}
