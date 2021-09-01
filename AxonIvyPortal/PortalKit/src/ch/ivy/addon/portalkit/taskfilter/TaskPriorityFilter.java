package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

/**
 * @deprecated use TaskPriorityFilter in package ch.ivy.addon.portalkit.casefilter.impl
 *
 */
@Deprecated(since="8.0.18")
public class TaskPriorityFilter extends TaskFilter {

  @JsonIgnore
  private List<WorkflowPriority> priorities;
  private List<WorkflowPriority> selectedPriorities;

  public TaskPriorityFilter() {
    this.priorities = Arrays.asList(WorkflowPriority.EXCEPTION, WorkflowPriority.HIGH, WorkflowPriority.NORMAL, WorkflowPriority.LOW);
    this.selectedPriorities = new ArrayList<>(this.priorities);
  }

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/PRIORITY");
  }

  @Override
  public String value() {
    if (CollectionUtils.isEmpty(selectedPriorities) || priorities.equals(selectedPriorities)) {
      return ALL;
    }
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

}
