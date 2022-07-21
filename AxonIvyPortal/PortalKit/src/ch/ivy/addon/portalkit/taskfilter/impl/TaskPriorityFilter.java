package ch.ivy.addon.portalkit.taskfilter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskPriorityFilter extends TaskFilter {

  @JsonIgnore
  private List<WorkflowPriority> priorities;
  private List<WorkflowPriority> selectedPriorities;
  @JsonIgnore
  private List<WorkflowPriority> submittedFilteredPriorities;
  @JsonIgnore
  private boolean isSelectedAll;

  public TaskPriorityFilter() {
    this.priorities = Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW);
    this.selectedPriorities = new ArrayList<>(this.priorities);
    this.submittedFilteredPriorities = new ArrayList<>(this.priorities);
  }

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/PRIORITY");
  }

  @Override
  public String value() {
    if (CollectionUtils.isNotEmpty(submittedFilteredPriorities)) {
      selectedPriorities = new ArrayList<>(submittedFilteredPriorities);
    }
    if (CollectionUtils.isEmpty(selectedPriorities)) {
      return noSelectionLabel();
    }
    else if (priorities.equals(selectedPriorities)) {
      isSelectedAll = true;
      return getAllLabel();
    }
    isSelectedAll = false;
    String value = userFriendlyPriority(selectedPriorities.get(0));
    for (int i = 1; i < selectedPriorities.size(); i++) {
      value += COMMA + userFriendlyPriority(selectedPriorities.get(i));
    }
    return value;
  }

  @Override
  public TaskQuery buildQuery() {
    if (CollectionUtils.isEmpty(selectedPriorities)) {
      selectedPriorities = new ArrayList<>(priorities);
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    selectedPriorities.forEach(priority -> filterQuery.or().priority().isEqual(priority));
    return query;
  }

  @Override
  public void resetValues() {
    selectedPriorities = new ArrayList<>(this.priorities);
    submittedFilteredPriorities = new ArrayList<>(this.priorities);
  }

  @Override
  public void validate() {
    submittedFilteredPriorities = new ArrayList<>(selectedPriorities);
  }

  public void onSelectedAllPriorities() {
    if (isSelectedAll) {
      selectedPriorities = new ArrayList<>(priorities);
    } else {
      selectedPriorities = new ArrayList<>();
    }
  }

  public void onSelectPriority() {
    isSelectedAll = selectedPriorities.size() == priorities.size();
  }

  public String userFriendlyPriority(WorkflowPriority priority) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + priority);
  }

  public List<WorkflowPriority> getSelectedPriorities() {
    return selectedPriorities;
  }

  public void setSelectedPriorities(List<WorkflowPriority> selectedPriorities) {
    this.selectedPriorities = selectedPriorities;
  }

  public List<WorkflowPriority> getPriorities() {
    return priorities;
  }

  public void setPriorities(List<WorkflowPriority> priorities) {
    this.priorities = priorities;
  }

  public boolean isSelectedAll() {
    return isSelectedAll;
  }

  public void setSelectedAll(boolean isSelectedAll) {
    this.isSelectedAll = isSelectedAll;
  }

  public List<WorkflowPriority> getSubmittedFilteredPriorities() {
    return submittedFilteredPriorities;
  }

  public void setSubmittedFilteredPriorities(List<WorkflowPriority> submittedFilteredPriorities) {
    this.submittedFilteredPriorities = submittedFilteredPriorities;
  }

}
