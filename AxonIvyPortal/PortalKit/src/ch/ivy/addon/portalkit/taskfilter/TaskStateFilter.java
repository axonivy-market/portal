package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskStateFilter extends TaskFilter {

  private List<TaskState> filteredStates;
  private List<TaskState> selectedFilteredStates;

  /**
   * Initialize the values of filteredStates: SUSPENDED, RESUMED, PARKED.
   */
  public TaskStateFilter() {
    this.filteredStates = Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED);
    this.selectedFilteredStates = new ArrayList<>();
  }
  
  public TaskStateFilter(List<TaskState> filteredStates, List<TaskState> selectedFilteredStates) {
    this.filteredStates = distinct(filteredStates);
    this.selectedFilteredStates = distinct(selectedFilteredStates);
  }
  
  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskView/state");
  }
  
  @Override
  public String value() {
    if (CollectionUtils.isEmpty(selectedFilteredStates) || filteredStates.equals(selectedFilteredStates)) {
      return ALL;
    }
    String value = userFriendlyState(selectedFilteredStates.get(0));
    for (int i = 1; i < selectedFilteredStates.size(); i++) {
      value += COMMA + userFriendlyState(selectedFilteredStates.get(i));
    }
    return value;
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
    selectedFilteredStates = filteredStates;
  }

  public String userFriendlyState(TaskState state) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/userFriendlyTaskState/" + state);
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

  public void setFilteredStates(List<TaskState> filteredStates) {
    this.filteredStates = filteredStates;
  }
  
  private List<TaskState> distinct(List<TaskState> filteredStates) {
    if (filteredStates != null) {
      return filteredStates.stream().collect(Collectors.toList());
    }
    return new ArrayList<>();
  }
}
