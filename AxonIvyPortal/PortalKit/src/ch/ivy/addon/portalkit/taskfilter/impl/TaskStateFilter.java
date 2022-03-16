package ch.ivy.addon.portalkit.taskfilter.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskStateFilter extends TaskFilter {

  @JsonIgnore
  private List<TaskState> filteredStates;
  private List<TaskState> selectedFilteredStates;
  @JsonIgnore
  private List<TaskState> selectedFilteredStatesAtBeginning;
  @JsonIgnore
  private List<TaskState> submittedFilteredStates;
  @JsonIgnore
  private boolean isSelectedAll;

  /**
   * Initialize the values of filteredStates: CREATED, SUSPENDED, RESUMED, PARKED, READY_FOR_JOIN, DONE
   * Advance note: if current user is Administrator, will consider to add system states
   */
  public TaskStateFilter() {
    this.filteredStates = TaskUtils.getValidStates();
    setSelectedFilteredStatesAtBeginning(new ArrayList<>(filteredStates));
    setSubmittedFilteredStates(new ArrayList<>(filteredStates));
    this.selectedFilteredStates = new ArrayList<>();
  }

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE");
  }

  @Override
  public String value() {
    if (CollectionUtils.isNotEmpty(submittedFilteredStates)) {
      selectedFilteredStates = new ArrayList<>(submittedFilteredStates);
    }
    if (CollectionUtils.isEmpty(selectedFilteredStates)) {
      return noSelectionLabel();
    } else if (isAllStatesSelected()) {
      isSelectedAll = true;
      return ALL;
    }
    isSelectedAll = false;
    String value = userFriendlyState(selectedFilteredStates.get(0));
    for (int i = 1; i < selectedFilteredStates.size(); i++) {
      TaskState selectedFilteredState = selectedFilteredStates.get(i);
      if (filteredStates.contains(selectedFilteredState)) {
        value += COMMA + userFriendlyState(selectedFilteredState);
      }
    }
    return value;
  }

  private boolean isAllStatesSelected() {
    return CollectionUtils.isEqualCollection(filteredStates, selectedFilteredStates)
        //In case only one state is selected and the current user doesn't have that state (happen with e.g DELAYED, DESTROY state)
        || (selectedFilteredStates.size() == 1 && !filteredStates.contains(selectedFilteredStates.get(0)))
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
    submittedFilteredStates = new ArrayList<>();
  }

  @Override
  public void validate() {
    submittedFilteredStates = new ArrayList<>(selectedFilteredStates);
  }

  public void onSelectedAllStates() {
    if (isSelectedAll) {
      selectedFilteredStates = new ArrayList<>(filteredStates);
    } else {
      selectedFilteredStates = new ArrayList<>();
    }
  }
  
  public void onSelectState() {
    isSelectedAll = isAllStatesSelected();
  }

  @Override
  public boolean defaultFilter() {
    return true;
  }

  @Override
  public boolean reloadView() {
    return true;
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
  
  public void addFilteredState(TaskState filteredState) {
    if (filteredStates != null) {
      this.filteredStates.add(filteredState);
    }
  }

  public List<TaskState> getSelectedFilteredStatesAtBeginning() {
    return selectedFilteredStatesAtBeginning;
  }

  public void setSelectedFilteredStatesAtBeginning(List<TaskState> selectedFilteredStatesAtBeginning) {
    this.selectedFilteredStatesAtBeginning = new ArrayList<>(selectedFilteredStatesAtBeginning);
  }

  public boolean isSelectedAll() {
    return isSelectedAll;
  }

  public void setSelectedAll(boolean isSelectedAll) {
    this.isSelectedAll = isSelectedAll;
  }

  public void setSubmittedFilteredStates(List<TaskState> submittedFilteredStates) {
    this.submittedFilteredStates = submittedFilteredStates;
  }

  public List<TaskState> getSubmittedFilteredStates() {
    return submittedFilteredStates;
  }
}
