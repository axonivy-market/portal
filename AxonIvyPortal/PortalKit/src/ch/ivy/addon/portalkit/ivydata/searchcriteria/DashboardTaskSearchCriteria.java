package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class DashboardTaskSearchCriteria {

  private String name;
  private String description;
  private boolean canWorkOn;
  private List<WorkflowPriority> priorities;
  private List<TaskState> states;
  private List<String> responsibles;
  private Date createdFrom;
  private Date createdTo;
  private Date expiryFrom;
  private Date expiryTo;
  private String category;
  private String sortField;
  private boolean sortDescending;
  
  public TaskQuery buildQuery() {
    TaskQuery query = TaskQuery.create();
    queryCanWorkOn(query);
    queryName(query);
    queryDescription(query);
    queryPriorities(query);
    queryStates(query);
    queryResponsibles(query);
    queryCategory(query);
    TaskSortingQueryAppender appender = new TaskSortingQueryAppender(query);
    query = appender.appendSorting(this).toQuery();
    return query;
  }
  
  private void queryCanWorkOn(TaskQuery query) {
    if (canWorkOn) {
      query.where().currentUserCanWorkOn();
    }
  }

  private void queryName(TaskQuery query) {
    if (StringUtils.isNotBlank(name)) {
      query.where().name().isLikeIgnoreCase(String.format("%%%s%%", name));
    }
  }

  private void queryDescription(TaskQuery query) {
    if (StringUtils.isNotBlank(description)) {
      query.where().description().isLikeIgnoreCase(String.format("%%%s%%", description));
    }
  }

  private void queryPriorities(TaskQuery query) {
    if (CollectionUtils.isNotEmpty(priorities)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (WorkflowPriority priority : priorities) {
        filterQuery.or().priority().isEqual(priority);
      }

      query.where().and(subQuery);
    }
  }

  private void queryStates(TaskQuery query) {
    if (CollectionUtils.isNotEmpty(states)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (TaskState state : states) {
        filterQuery.or().state().isEqual(state);
      }

      query.where().and(subQuery);
    }
  }

  private void queryResponsibles(TaskQuery query) {
    if (CollectionUtils.isNotEmpty(responsibles)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String responsible : responsibles) {
        filterQuery.or().activatorName().isEqual(responsible);
      }

      query.where().and(subQuery);
    }
  }

  private void queryCategory(TaskQuery query) {
    if (StringUtils.isNotBlank(category)) {
      query.where().category().isLike(String.format("%s%%", category));
    }
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

  private class TaskSortingQueryAppender {

    private TaskQuery query;

    public TaskSortingQueryAppender(TaskQuery query) {
      this.query = query;
    }

    public TaskQuery toQuery() {
      return query;
    }

    public TaskSortingQueryAppender appendSorting(DashboardTaskSearchCriteria criteria) {
      appendSortByPriorityIfSet(criteria);
      appendSortByNameIfSet(criteria);
      appendSortByActivatorIfSet(criteria);
      appendSortByIdIfSet(criteria);
      appendSortByCreationDateIfSet(criteria);
      appendSortByExpiryDateIfSet(criteria);
      appendSortByStateIfSet(criteria);
      return this;
    }

    private void appendSortByPriorityIfSet(DashboardTaskSearchCriteria criteria) {
      if (TaskSortField.PRIORITY.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().priority().descending();
        } else {
          query.orderBy().priority();
        }
      }
    }

    private void appendSortByNameIfSet(DashboardTaskSearchCriteria criteria) {
      if (TaskSortField.NAME.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().name().descending();
        } else {
          query.orderBy().name();
        }
      }
    }

    private void appendSortByActivatorIfSet(DashboardTaskSearchCriteria criteria) {
      if (TaskSortField.ACTIVATOR.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().activatorDisplayName().descending();
        } else {
          query.orderBy().activatorDisplayName();
        }
      }
    }

    private void appendSortByIdIfSet(DashboardTaskSearchCriteria criteria) {
      if (TaskSortField.ID.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().taskId().descending();
        } else {
          query.orderBy().taskId();
        }
      }
    }

    private void appendSortByCreationDateIfSet(DashboardTaskSearchCriteria criteria) {
      if (TaskSortField.CREATION_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().startTimestamp().descending();
        } else {
          query.orderBy().startTimestamp();
        }
      }
    }

    private void appendSortByExpiryDateIfSet(DashboardTaskSearchCriteria criteria) {
      if (TaskSortField.EXPIRY_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().expiryTimestamp().descendingNullFirst();
        } else {
          query.orderBy().expiryTimestamp().ascendingNullLast();
        }
      }
    }

    private void appendSortByStateIfSet(DashboardTaskSearchCriteria criteria) {
      if (TaskSortField.STATE.toString().equalsIgnoreCase(criteria.getSortField())) {
        if (criteria.isSortDescending()) {
          query.orderBy().state().descending();
        } else {
          query.orderBy().state();
        }
      }
    }
  }
  
  public boolean getCanWorkOn() {
    return canWorkOn;
  }
  
  public void setCanWorkOn(boolean canWorkOn) {
    this.canWorkOn = canWorkOn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<WorkflowPriority> getPriorities() {
    return priorities;
  }

  public void setPriorities(List<WorkflowPriority> priorities) {
    this.priorities = priorities;
  }

  public List<TaskState> getStates() {
    return states;
  }

  public void setStates(List<TaskState> states) {
    this.states = states;
  }

  public List<String> getResponsibles() {
    return responsibles;
  }

  public void setResponsibles(List<String> responsibles) {
    this.responsibles = responsibles;
  }

  public Date getCreatedFrom() {
    return createdFrom;
  }

  public void setCreatedFrom(Date createdFrom) {
    this.createdFrom = createdFrom;
  }

  public Date getCreatedTo() {
    return createdTo;
  }

  public void setCreatedTo(Date createdTo) {
    this.createdTo = createdTo;
  }

  public Date getExpiryFrom() {
    return expiryFrom;
  }

  public void setExpiryFrom(Date expiryFrom) {
    this.expiryFrom = expiryFrom;
  }

  public Date getExpiryTo() {
    return expiryTo;
  }

  public void setExpiryTo(Date expiryTo) {
    this.expiryTo = expiryTo;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

}
