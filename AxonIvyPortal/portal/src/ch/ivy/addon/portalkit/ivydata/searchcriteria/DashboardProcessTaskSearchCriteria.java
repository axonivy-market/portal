package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.OrderByColumnQuery;

public class DashboardProcessTaskSearchCriteria {

  private Long processStartId;
  private String sortField;
  private boolean sortDescending;
  
  public DashboardProcessTaskSearchCriteria(Long processStartId) {
    this.processStartId = processStartId;
  }

  public TaskQuery buildQuery() {
    TaskQuery query = buildQueryWithoutOrderByClause();
    TaskSortingQueryAppender appender = new TaskSortingQueryAppender(query);
    query = appender.appendSorting(this).toQuery();
    return query;
  }

  public TaskQuery buildQueryWithoutOrderByClause() {
    TaskQuery query = TaskQuery.create();
    CaseQuery caseQuery = CaseQuery.businessCases();
    if (this.processStartId != null) {
      queryProcessStartId();
    }
    query.where().cases(caseQuery);
    queryCanWorkOn(query);
    return query;
  }

  private CaseQuery queryProcessStartId() {
    return CaseQuery.businessCases().where().taskStartId().isEqual(processStartId);
  }

  private void queryCanWorkOn(TaskQuery query) {
    query.where().currentUserCanWorkOn();
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
    private OrderByColumnQuery order;

    public TaskSortingQueryAppender(TaskQuery query) {
      this.query = query;
    }

    public TaskQuery toQuery() {
      return query;
    }

    public TaskSortingQueryAppender appendSorting(DashboardProcessTaskSearchCriteria criteria) {
      appendSortByPriorityIfSet(criteria);
      appendSortByNameIfSet(criteria);
      appendSortByActivatorIfSet(criteria);
      appendSortByIdIfSet(criteria);
      appendSortByCreationDateIfSet(criteria);
      appendSortByCompletionDateIfSet(criteria);
      appendSortByExpiryDateIfSet(criteria);
      appendSortByStateIfSet(criteria);
      if (criteria.isSortDescending()) {
        order.descending();
      }
      return this;
    }

    private void appendSortByPriorityIfSet(DashboardProcessTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.PRIORITY.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().priority();
      }
    }

    private void appendSortByNameIfSet(DashboardProcessTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.NAME.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().name();
      }
    }

    private void appendSortByActivatorIfSet(DashboardProcessTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.RESPONSIBLE.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().activatorDisplayName();
      }
    }

    private void appendSortByIdIfSet(DashboardProcessTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.ID.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().taskId();
      }
    }

    private void appendSortByCreationDateIfSet(DashboardProcessTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.CREATED.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().startTimestamp();
      }
    }

    private void appendSortByCompletionDateIfSet(DashboardProcessTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.COMPLETED.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().endTimestamp();
      }
    }

    private void appendSortByExpiryDateIfSet(DashboardProcessTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.EXPIRY.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().expiryTimestamp();
      }
    }

    private void appendSortByStateIfSet(DashboardProcessTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.STATE.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().state();
      }
    }
  }
}