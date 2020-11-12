package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.ICustomFieldFilterQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.OrderByColumnQuery;

public class DashboardTaskSearchCriteria {

  private boolean canWorkOn;
  private List<String> categories;
  private List<ColumnModel> columns;
  private String sortField;
  private boolean sortDescending;

  public TaskQuery buildQuery() throws ParseException {
    TaskQuery query = TaskQuery.create();
    queryCanWorkOn(query);
    queryCategory(query);
    queryFilters(query);
    TaskSortingQueryAppender appender = new TaskSortingQueryAppender(query);
    query = appender.appendSorting(this).toQuery();
    return query;
  }

  private void queryCreatedDate(TaskQuery query, Date from, Date to) {
    if (from != null || to != null) {
      TaskQuery subQuery = TaskQuery.create();
      if (from != null) {
        subQuery.where().startTimestamp().isGreaterOrEqualThan(from);
      }

      if (to != null) {
        subQuery.where().startTimestamp().isLowerOrEqualThan(DateUtils.addDays(to, 1));
      }
      query.where().and(subQuery);
    }
  }

  private void queryExpiryDate(TaskQuery query, Date from, Date to) {
    if (from != null || to != null) {
      TaskQuery subQuery = TaskQuery.create();
      if (from != null) {
        subQuery.where().expiryTimestamp().isGreaterOrEqualThan(from);
      }

      if (to != null) {
        subQuery.where().expiryTimestamp().isLowerOrEqualThan(DateUtils.addDays(to, 1));
      }
      query.where().and(subQuery);
    }
  }

  private void queryCanWorkOn(TaskQuery query) {
    if (canWorkOn) {
      query.where().currentUserCanWorkOn();
    }
  }

  private void queryName(TaskQuery query, String name) {
    if (StringUtils.isNotBlank(name)) {
      query.where().name().isLikeIgnoreCase(String.format("%%%s%%", name));
    }
  }

  private void queryDescription(TaskQuery query, String description) {
    if (StringUtils.isNotBlank(description)) {
      query.where().description().isLikeIgnoreCase(String.format("%%%s%%", description));
    }
  }

  private void queryPriorities(TaskQuery query, List<WorkflowPriority> priorities) {
    if (CollectionUtils.isNotEmpty(priorities)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (WorkflowPriority priority : priorities) {
        filterQuery.or().priority().isEqual(priority);
      }

      query.where().and(subQuery);
    }
  }

  private void queryStates(TaskQuery query, List<TaskState> states) {
    if (CollectionUtils.isNotEmpty(states)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (TaskState state : states) {
        filterQuery.or().state().isEqual(state);
      }

      query.where().and(subQuery);
    }
  }

  private void queryResponsibles(TaskQuery query, List<String> responsibles) {
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
    if (CollectionUtils.isNotEmpty(categories)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String category : categories) {
        filterQuery.or().category().isEqual(category);
      }
      
      query.where().and(subQuery);
    }
  }
  
  private void queryFilters(TaskQuery query) throws ParseException {
    for (ColumnModel column : columns) {
      String field = column.getField();
      String filter = column.getFilter();
      List<String> filterList = column.getFilterList();
      String filterFrom = column.getFilterFrom();
      String filterTo = column.getFilterTo();
      
      if (StringUtils.equals(DashboardStandardTaskColumn.PRIORITY.getField(), column.getField())) {
        List<WorkflowPriority> priorities = new ArrayList<>();
        for (String priority : filterList) {
          priorities.add(WorkflowPriority.valueOf(priority.toUpperCase()));
        }
        queryPriorities(query, priorities);
      } else if (StringUtils.equals(DashboardStandardTaskColumn.NAME.getField(), column.getField())) {
        queryName(query, filter);
      } else if (StringUtils.equals(DashboardStandardTaskColumn.DESCRIPTION.getField(), column.getField())) {
        queryDescription(query, filter);
      } else if (StringUtils.equals(DashboardStandardTaskColumn.STATE.getField(), column.getField())) {
        List<TaskState> states = new ArrayList<>();
        for (String state : filterList) {
          states.add(TaskState.valueOf(state.toUpperCase()));
        }
        queryStates(query, states);
      } else if (StringUtils.equals(DashboardStandardTaskColumn.RESPONSIBLE.getField(), column.getField())) {
        queryResponsibles(query, filterList);
      } else if (StringUtils.equals(DashboardStandardTaskColumn.CREATED.getField(), column.getField())) {
        Date from = parse(filterFrom);
        Date to = parse(filterTo);
        queryCreatedDate(query, from, to);
      } else if (StringUtils.equals(DashboardStandardTaskColumn.EXPIRY.getField(), column.getField())) {
        Date from = parse(filterFrom);
        Date to = parse(filterTo);
        queryExpiryDate(query, from, to);
      } else {
        TaskQuery subQuery = TaskQuery.create();
        ICustomFieldFilterQuery filterQuery = subQuery.where().customField();
        if (StringUtils.isNotBlank(filter) || CollectionUtils.isNotEmpty(filterList) || filterFrom != null || filterTo != null) {
          if (column.isNumber()) {
            if (filterFrom != null) {
              Number from = Double.parseDouble(filterFrom.toString());
              filterQuery.numberField(field).isGreaterOrEqualThan(from);
            }
  
            if (filterTo != null) {
              Number to = Double.parseDouble(filterTo.toString());
              filterQuery.numberField(field).isLowerOrEqualThan(to);
            }
          } else if (column.isDate()) {
            Date from = parse(filterFrom);
            Date to = parse(filterTo);
            if (filterFrom != null) {
              filterQuery.timestampField(field).isGreaterOrEqualThan(from);
            }
  
            if (filterTo != null) {
              filterQuery.timestampField(field).isLowerOrEqualThan(DateUtils.addDays(to, 1));
            }
          } else if (column.isText()) {
            filterQuery.textField(field).isLikeIgnoreCase(String.format("%%%s%%", filter));
          } else {
            filterQuery.stringField(field).isLikeIgnoreCase(String.format("%%%s%%", filter));
          }
          query.where().and(subQuery);
        }
      }
    }
  }
  
  private Date parse(String dateInString) throws ParseException {
    if (StringUtils.isBlank(dateInString)) {
      return null;
    }
    
    return DateUtils.parseDate(dateInString, "MM/dd/yyyy", "dd.MM.yyyy");
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
    private boolean sortStandardColumn;

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
      appendSortByCustomFieldIfSet(criteria);
      if (criteria.isSortDescending()) {
        order.descending();
      }
      return this;
    }

    private void appendSortByPriorityIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.PRIORITY.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().priority();
        sortStandardColumn = true;
      }
    }

    private void appendSortByNameIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.NAME.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().name();
        sortStandardColumn = true;
      }
    }

    private void appendSortByActivatorIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.RESPONSIBLE.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().activatorDisplayName();
        sortStandardColumn = true;
      }
    }

    private void appendSortByIdIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.ID.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().taskId();
        sortStandardColumn = true;
      }
    }

    private void appendSortByCreationDateIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.CREATED.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().startTimestamp();
        sortStandardColumn = true;
      }
    }

    private void appendSortByExpiryDateIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.EXPIRY.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().expiryTimestamp();
        sortStandardColumn = true;
      }
    }

    private void appendSortByStateIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.STATE.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().state();
        sortStandardColumn = true;
      }
    }

    private void appendSortByCustomFieldIfSet(DashboardTaskSearchCriteria criteria) {
      if (!sortStandardColumn) {
        String sortField = criteria.getSortField();
        if (StringUtils.isNotBlank(sortField)) {
          if (StringUtils.startsWithIgnoreCase(sortField, DashboardConfigurationPrefix.CUSTOM_FIELD_NUMBER)) {
            order = query.orderBy().customField().numberField(
                StringUtils.removeStartIgnoreCase(sortField, DashboardConfigurationPrefix.CUSTOM_FIELD_NUMBER));
          } else if (StringUtils.startsWithIgnoreCase(sortField, DashboardConfigurationPrefix.CUSTOM_FIELD_TIMESTAMP)) {
            order = query.orderBy().customField().timestampField(
                StringUtils.removeStartIgnoreCase(sortField, DashboardConfigurationPrefix.CUSTOM_FIELD_TIMESTAMP));
          } else {
            order = query.orderBy().customField().stringField(
                StringUtils.removeStartIgnoreCase(sortField, DashboardConfigurationPrefix.CUSTOM_FIELD_STRING));
          }
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

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }
  
  public List<ColumnModel> getColumns() {
    return columns;
  }
  
  public void setColumns(List<ColumnModel> columns) {
    this.columns = columns;
  }
}
