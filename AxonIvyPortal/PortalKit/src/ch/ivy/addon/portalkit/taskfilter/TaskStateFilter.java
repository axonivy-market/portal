package ch.ivy.addon.portalkit.taskfilter;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskStateFilter extends TaskFilter {

  private List<TaskState> filteredStates = Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED);
  private List<TaskState> selectedFilteredStates;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskView/state");
  }
  
  @Override
  public String value() {
    if (CollectionUtils.isEmpty(selectedFilteredStates) || filteredStates.equals(selectedFilteredStates)) {
      return ALL;
    }
    return StringUtils.join(selectedFilteredStates, COMMA);
  }

  @Override
  public TaskQuery buildQuery() {
    if (CollectionUtils.isEmpty(selectedFilteredStates)) {
      selectedFilteredStates = filteredStates;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    selectedFilteredStates.forEach(state -> filterQuery.or().state().isEqual(state));
    return query;
  }

  @Override
  public void resetValues() {
    selectedFilteredStates.clear();
  }

  public List<TaskState> getSelectedFilteredStates() {
    return selectedFilteredStates;
  }

  public void setSelectedFilteredStates(List<TaskState> selectedFilteredStates) {
    this.selectedFilteredStates = selectedFilteredStates;
  }

  public List<TaskState> getFilteredStates() {
    return filteredStates;
  }
}
