package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskStateFilter extends TaskFilter {

  @JsonIgnore
  private List<TaskState> filteredStates;
  private List<TaskState> selectedFilteredStates;
  @JsonIgnore
  private List<TaskState> selectedFilteredStatesAtBeginning;

  /**
   * Initialize the values of filteredStates: SUSPENDED, RESUMED, PARKED.
   */
  public TaskStateFilter() {
    this.filteredStates = new ArrayList<>();
    this.filteredStates.addAll(Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE));
    if(PermissionUtils.checkReadAllTasksPermission()) {
      this.filteredStates.add(TaskState.UNASSIGNED);
    }
    this.selectedFilteredStatesAtBeginning = new ArrayList<>(filteredStates);
    this.selectedFilteredStates = new ArrayList<>();
  }

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE");
  }

  @Override
  public String value() {
    if (CollectionUtils.isEmpty(selectedFilteredStates) || isAllStatesSelected()) {
      return ALL;
    }
    String value = userFriendlyState(selectedFilteredStates.get(0));

    for (int i = 1; i < selectedFilteredStates.size(); i++) {
      if (filteredStates.contains(selectedFilteredStates.get(i))) {
        value += COMMA + userFriendlyState(selectedFilteredStates.get(i));
      }
    }
    return value;
  }

  private boolean isAllStatesSelected() {
    List<TaskState> readAllTaskStates = Arrays.asList(TaskState.DONE, TaskState.UNASSIGNED);
    boolean hasReadAllTasksPermission = PermissionUtils.checkReadAllTasksPermission();
    return filteredStates.equals(selectedFilteredStates)
        //In case user doesn't have permission to see DONE & UNASSIGNED task
        || (!hasReadAllTasksPermission && selectedFilteredStates.size() == 1 && readAllTaskStates.contains(selectedFilteredStates.get(0)))
        || (!hasReadAllTasksPermission && selectedFilteredStates.size() == 2 && readAllTaskStates.containsAll(selectedFilteredStates))
    // In case the filter is a saved filter from a user who can filter more task state
        || (filteredStates.size() < selectedFilteredStates.size() && selectedFilteredStates.containsAll(filteredStates));
  }

  @Override
  public TaskQuery buildQuery() {
    if (CollectionUtils.isEmpty(selectedFilteredStates) && filteredStates.size() == 1) {
      return null;
    } else if (CollectionUtils.isEmpty(selectedFilteredStates)) {
      selectedFilteredStates = new ArrayList<>(filteredStates);
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    selectedFilteredStates.forEach(state -> filterQuery.or().state().isEqual(state));
    return query;
  }

  @Override
  public void resetValues() {
    selectedFilteredStates = new ArrayList<>(selectedFilteredStatesAtBeginning);
  }

  public String userFriendlyState(TaskState state) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + state);
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

  public List<TaskState> getSelectedFilteredStatesAtBeginning() {
    return selectedFilteredStatesAtBeginning;
  }

  public void setSelectedFilteredStatesAtBeginning(List<TaskState> selectedFilteredStatesAtBeginning) {
    this.selectedFilteredStatesAtBeginning = selectedFilteredStatesAtBeginning;
  }
}
