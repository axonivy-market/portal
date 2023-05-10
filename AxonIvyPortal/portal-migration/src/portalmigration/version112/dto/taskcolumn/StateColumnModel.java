package portalmigration.version112.dto.taskcolumn;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import portalmigration.version112.enums.DashboardColumnFormat;
import portalmigration.version112.enums.DashboardStandardTaskColumn;

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

}
