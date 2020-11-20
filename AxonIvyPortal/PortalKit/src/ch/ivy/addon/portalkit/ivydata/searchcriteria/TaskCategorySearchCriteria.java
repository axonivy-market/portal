package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.ITaskQueryExecutor;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskCategorySearchCriteria {

  private String involvedUsername;
  private List<String> apps;
  private List<TaskState> includedStates;
  
  private TaskQuery customTaskQuery;

  public TaskQuery createQuery() {
    TaskQuery query = customTaskQuery != null ? createCustomQuery() : TaskQuery.create();
    updateQuery(query);
    return query;
  }

  public TaskQuery createQuery(ITaskQueryExecutor taskExecutor) {
    TaskQuery query = customTaskQuery != null ? createCustomQuery() : TaskQuery.create(taskExecutor);
    updateQuery(query);
    return query;
  }

  @SuppressWarnings("deprecation")
  private TaskQuery createCustomQuery() {
    return TaskQuery.fromJson(customTaskQuery.asJson());// clone to keep the original custom query
  }

  private void updateQuery(TaskQuery query) {
    if (hasIncludedStates()) {
      query.where().and(queryForStates(getIncludedStates()));
    }
  }

  private TaskQuery queryForStates(List<TaskState> states) {
    TaskQuery stateFieldQuery = TaskQuery.create();
    IFilterQuery filterQuery = stateFieldQuery.where();
    for (TaskState state : states) {
      filterQuery.or().state().isEqual(state);
    }
    return stateFieldQuery;
  }

  public List<String> getApps() {
    return apps;
  }

  public void setApps(List<String> apps) {
    this.apps = apps;
  }

  public List<TaskState> getIncludedStates() {
    return includedStates;
  }

  public void setIncludedStates(List<TaskState> includedStates) {
    this.includedStates = includedStates;
  }
  
  public void addIncludedStates(List<TaskState> includedStates) {
    if (CollectionUtils.isEmpty(includedStates)) {
      this.includedStates = new ArrayList<>();
    }
    this.includedStates.addAll(includedStates);
  }

  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
  }

  public TaskQuery getCustomTaskQuery() {
    return customTaskQuery;
  }

  public void setCustomTaskQuery(TaskQuery customTaskQuery) {
    this.customTaskQuery = customTaskQuery;
  }
  
  public boolean hasIncludedStates() {
    return CollectionUtils.isNotEmpty(includedStates);
  }

  public boolean hasApps() {
    return CollectionUtils.isNotEmpty(apps);
  }

  public boolean hasInvolvedUsername() {
    return StringUtils.isNotBlank(involvedUsername);
  }
  
}
