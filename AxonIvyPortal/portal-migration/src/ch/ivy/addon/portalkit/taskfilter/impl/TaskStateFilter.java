package ch.ivy.addon.portalkit.taskfilter.impl;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskStateFilter extends TaskFilter {

  private List<TaskState> selectedFilteredStates;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE");
  }

  @Override
  public String value() {
    return ALL;
  }

  @Override
  public TaskQuery buildQuery() {
    return null;
  }

  @Override
  public void resetValues() {
    selectedFilteredStates = new ArrayList<>();
  }

  @Override
  public void validate() {}


  public List<TaskState> getSelectedFilteredStates() {
    return selectedFilteredStates;
  }

  public void setSelectedFilteredStates(List<TaskState> selectedFilteredStates) {
    this.selectedFilteredStates = selectedFilteredStates;
  }
}
