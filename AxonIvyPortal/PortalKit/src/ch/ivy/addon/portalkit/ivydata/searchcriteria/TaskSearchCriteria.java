package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_TIMESTAMP_FIELD5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class TaskSearchCriteria {

  private String involvedUsername;
  private List<String> apps;
  private List<TaskState> includedStates;
  private boolean isAdminQuery;
  private String keyword;
  private Long taskId;
  private Long caseId;
  private String category;
  private boolean isTaskStartedByAnotherDisplayed;
  private TaskAssigneeType taskAssigneeType;
  private boolean isNewQueryCreated;
  private boolean isQueryByTaskId;
  private boolean isQueryByBusinessCaseId;
  private boolean isQueryForUnassignedTask;
  private String sortField;
  private boolean sortDescending;
  private boolean isSorted = true;
  private TaskQuery customTaskQuery;

  private TaskQuery finalTaskQuery;
  
  public TaskQuery createQueryToFindLatestTasks(TaskQuery taskQuery, Date timeStamp) {
    if (isAdminQuery) {
      taskQuery.where().and(TaskQuery.create().where().startTimestamp().isGreaterThan(timeStamp));
    } else {
      // task when delegate or reset, delegate time will write to customTimestampField5
      taskQuery.where().and(TaskQuery.create().where().startTimestamp().isGreaterThan(timeStamp).or().customField().timestampField(CUSTOM_TIMESTAMP_FIELD5)
          .isGreaterOrEqualThan(timeStamp));
    }
    return taskQuery;
  }

  public TaskQuery createQuery() {
    TaskQuery finalQuery = TaskQuery.create();
    setNewQueryCreated(isNewQueryCreated() || customTaskQuery == null || hasTaskId() || hasCaseId());

    if (!isNewQueryCreated()) {
      finalQuery = TaskQuery.fromJson(customTaskQuery.asJson()); // clone to keep the original custom query
    }

    addTaskStateQuery(finalQuery);

    if (hasTaskId()) {
      finalQuery.where().and(queryForTaskId(getTaskId()));
      return finalQuery;
    }

    addCaseIdQuery(finalQuery);
    addKeywordQuery(finalQuery);
    addAssigneeTypeQuery(finalQuery);
    addCategoryQuery(finalQuery);

    if (isSorted) {
      TaskSortingQueryAppender appender = new TaskSortingQueryAppender(finalQuery);
      finalQuery = appender.appendSorting(this).toQuery();
    }
    return finalQuery;
  }
  
  private void addCategoryQuery(TaskQuery finalQuery) {
    if (hasCategory()) {
      finalQuery.where().and(queryForCategory(getCategory()));
    }
  }

  private void addAssigneeTypeQuery(TaskQuery finalQuery) {
    if (isQueryForUnassignedTask()) {
      finalQuery.where().and().activatorUserId().isNull().and().activatorRoleId().isNull();
    } else if (getTaskAssigneeType() == TaskAssigneeType.ROLE) {
      finalQuery.where().and().activatorRoleId().isNotNull();
    } else if (getTaskAssigneeType() == TaskAssigneeType.USER) {
      TaskQuery personalTaskQuery = TaskQuery.create().where().activatorUserId().isNotNull();
      if (getIncludedStates().contains(TaskState.PARKED)) {
        TaskQuery reservedTaskQuery =
            TaskQuery.create().where().activatorRoleId().isNotNull().and().state().isEqual(TaskState.PARKED);
        personalTaskQuery.where().or(reservedTaskQuery);
      }
      finalQuery.where().and(personalTaskQuery);
    }
  }

  private void addCaseIdQuery(TaskQuery finalQuery) {
    if (hasCaseId()) {
      if (isQueryByBusinessCaseId()) {
        finalQuery.where().and(
            TaskQuery.create().where().businessCaseId().isEqual(getCaseId()).or().caseId()
                .isEqual(getCaseId()));
      } else {
        finalQuery.where().and().caseId().isEqual(getCaseId());
      }
    }
  }

  private void addTaskStateQuery(TaskQuery finalQuery) {
    if (hasIncludedStates() && !isQueryForUnassignedTask()) {
      finalQuery.where().and(queryForStates(getIncludedStates()));
    }
  }

  private void addKeywordQuery(TaskQuery finalQuery) {
    if (hasKeyword()) {
      finalQuery.where().and(queryForKeyword(getKeyword()));
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

  private TaskQuery queryForCategory(String keyword) {
    String startingWithCategory = String.format("%s%%", keyword);
    return TaskQuery.create().where().category().isLike(startingWithCategory);
  }
  
  private TaskQuery queryForKeyword(String keyword) {
    String containingKeyword = String.format("%%%s%%", keyword);
    TaskQuery filterByKeywordQuery =
        TaskQuery.create().where().or().name().isLikeIgnoreCase(containingKeyword).or().description()
            .isLikeIgnoreCase(containingKeyword);

    try {
        long idKeyword = Long.parseLong(keyword);
        String containingIdKeyword = String.format("%%%d%%", idKeyword);
        filterByKeywordQuery.where().or().taskId().isLike(containingIdKeyword);
      } catch (NumberFormatException e) {
        // do nothing
    }
    return filterByKeywordQuery;
  }

  private TaskQuery queryForTaskId(Long taskId) {
    TaskQuery query = TaskQuery.create();
    query.where().taskId().isEqual(taskId);
    return query;
  }

  private class TaskSortingQueryAppender {

    private TaskQuery query;

    public TaskSortingQueryAppender(TaskQuery query) {
      this.query = query;
    }

    public TaskQuery toQuery() {
      return query;
    }

    public TaskSortingQueryAppender appendSorting(TaskSearchCriteria criteria) {
      appendSortByPriorityIfSet(criteria);
      appendSortByNameIfSet(criteria);
      appendSortByActivatorIfSet(criteria);
      appendSortByIdIfSet(criteria);
      appendSortByCreationDateIfSet(criteria);
      appendSortByExpiryDateIfSet(criteria);
      appendSortByStateIfSet(criteria);
      return this;
    }

    private void appendSortByPriorityIfSet(TaskSearchCriteria criteria) {
      if (TaskSortField.PRIORITY.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().priority().descending();
        } else {
          query.orderBy().priority();
        }
      }
    }

    private void appendSortByNameIfSet(TaskSearchCriteria criteria) {
      if (TaskSortField.NAME.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().name().descending();
        } else {
          query.orderBy().name();
        }
      }
    }

    private void appendSortByActivatorIfSet(TaskSearchCriteria criteria) {
      if (TaskSortField.ACTIVATOR.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().activatorDisplayName().descending();
        } else {
          query.orderBy().activatorDisplayName();
        }
      }
    }

    private void appendSortByIdIfSet(TaskSearchCriteria criteria) {
      if (TaskSortField.ID.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().taskId().descending();
        } else {
          query.orderBy().taskId();
        }
      }
    }

    private void appendSortByCreationDateIfSet(TaskSearchCriteria criteria) {
      if (TaskSortField.CREATION_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().startTimestamp().descending();
        } else {
          query.orderBy().startTimestamp();
        }
      }
    }

    private void appendSortByExpiryDateIfSet(TaskSearchCriteria criteria) {
      if (TaskSortField.EXPIRY_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().expiryTimestamp().descendingNullFirst();
        } else {
          query.orderBy().expiryTimestamp().ascendingNullLast();
        }
      }
    }

    private void appendSortByStateIfSet(TaskSearchCriteria criteria) {
      if (TaskSortField.STATE.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().state().descending();
        } else {
          query.orderBy().state();
        }
      }
    }
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
  
  public List<String> getApps() {
    return apps;
  }

  public void setApps(List<String> apps) {
    this.apps = apps;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSortField() {
    return sortField;
  }

  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  public boolean isSortDescending() {
    return sortDescending;
  }

  public void setSortDescending(boolean sortDescending) {
    this.sortDescending = sortDescending;
  }

  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
  }

  public boolean isAdminQuery() {
    return isAdminQuery;
  }

  public void setAdminQuery(boolean isAdminQuery) {
    this.isAdminQuery = isAdminQuery;
  }

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }

  public boolean isTaskStartedByAnotherDisplayed() {
    return isTaskStartedByAnotherDisplayed;
  }

  public void setTaskStartedByAnotherDisplayed(boolean isTaskStartedByAnotherDisplayed) {
    this.isTaskStartedByAnotherDisplayed = isTaskStartedByAnotherDisplayed;
  }

  public TaskAssigneeType getTaskAssigneeType() {
    return taskAssigneeType;
  }

  public void setTaskAssigneeType(TaskAssigneeType taskAssigneeType) {
    this.taskAssigneeType = taskAssigneeType;
  }
  
  public boolean isQueryByTaskId() {
    return isQueryByTaskId;
  }

  public void setQueryByTaskId(boolean isQueryByTaskId) {
    this.isQueryByTaskId = isQueryByTaskId;
  }

  public boolean isQueryByBusinessCaseId() {
    return isQueryByBusinessCaseId;
  }

  public void setQueryByBusinessCaseId(boolean isQueryByBusinessCaseId) {
    this.isQueryByBusinessCaseId = isQueryByBusinessCaseId;
  }

  public boolean isQueryForUnassignedTask() {
    return isQueryForUnassignedTask;
  }

  public void setQueryForUnassignedTask(boolean isQueryForUnassignedTask) {
    this.isQueryForUnassignedTask = isQueryForUnassignedTask;
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

  public boolean hasKeyword() {
    return StringUtils.isNotEmpty(keyword);
  }

  public boolean hasTaskId() {
    return taskId != null && taskId != 0;
  }
  
  public boolean hasCaseId() {
    return caseId != null && caseId != 0;
  }

  public boolean hasCategory() {
    return StringUtils.isNotBlank(category);
  }

  public boolean hasInvolvedUsername() {
    return StringUtils.isNotBlank(involvedUsername);
  }
  
  public boolean isSorted() {
    return isSorted;
  }

  public void setSorted(boolean isSorted) {
    this.isSorted = isSorted;
  }

  public boolean isNewQueryCreated() {
    return isNewQueryCreated;
  }

  public void setNewQueryCreated(boolean isNewQueryCreated) {
    this.isNewQueryCreated = isNewQueryCreated;
  }

  public TaskQuery getFinalTaskQuery() {
    if (finalTaskQuery == null) {
      finalTaskQuery = createQuery();
    }
    return finalTaskQuery;
  }

  public void setFinalTaskQuery(TaskQuery finalTaskQuery) {
    this.finalTaskQuery = finalTaskQuery;
  }

}
