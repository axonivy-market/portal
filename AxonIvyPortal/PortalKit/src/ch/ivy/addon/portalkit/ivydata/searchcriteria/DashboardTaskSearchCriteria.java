package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardFilterType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.ICustomFieldFilterQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.OrderByColumnQuery;

public class DashboardTaskSearchCriteria {

  private static final String LIKE_FORMAT = "%%%s%%";
  private boolean canWorkOn;
  private List<TaskColumnModel> columns;
  private String sortField;
  private boolean sortDescending;
  private boolean isInConfiguration;

  public TaskQuery buildQuery() {
    TaskQuery query = buildQueryWithoutOrderByClause();
    TaskSortingQueryAppender appender = new TaskSortingQueryAppender(query);
    query = appender.appendSorting(this).toQuery();
    return query;
  }

  public TaskQuery buildQueryWithoutOrderByClause() {
    TaskQuery query = TaskQuery.create();
    queryFilters(query);
    queryCanWorkOn(query);
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
      query.where().name().isLikeIgnoreCase(String.format(LIKE_FORMAT, name));
    }
  }

  private void queryDescription(TaskQuery query, String description) {
    if (StringUtils.isNotBlank(description)) {
      query.where().description().isLikeIgnoreCase(String.format(LIKE_FORMAT, description));
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
      states = TaskUtils.filterStateByPermission(states);
    } else {
      states = TaskUtils.getValidStates();
    }
    TaskQuery subQuery = TaskQuery.create();
    IFilterQuery filterQuery = subQuery.where();
    for (TaskState state : states) {
      filterQuery.or().state().isEqual(state);
    }
    query.where().and(subQuery);
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

  private void queryCategory(TaskQuery query, List<String> categories) {
    if (CollectionUtils.isNotEmpty(categories)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String category : categories) {
        filterQuery.or().category().isEqual(category);
      }
      
      query.where().and(subQuery);
    }
  }
  
  private void queryCustomFieldSelection(TaskQuery query, String field, List<String> filterList) {
    if (CollectionUtils.isNotEmpty(filterList)) {
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String filter : filterList) {
        filterQuery.or().customField().stringField(field).isEqual(filter);
      }
      
      query.where().and(subQuery);
    }
  }
  
  private void queryTextField(ICustomFieldFilterQuery filterQuery, String field, String filter) {
    if (StringUtils.isNotBlank(filter)) {
      filterQuery.textField(field).isLikeIgnoreCase(String.format(LIKE_FORMAT, filter));
    }
  }
  
  private void queryStringField(ICustomFieldFilterQuery filterQuery, String field, String filter) {
    if (StringUtils.isNotBlank(filter)) {
      filterQuery.stringField(field).isLikeIgnoreCase(String.format(LIKE_FORMAT, filter));
    }
  }
  
  private void queryFilters(TaskQuery query) {
    var states = new ArrayList<TaskState>();
    Ivy.log().error(columns.stream().map(TaskColumnModel::getField).collect(Collectors.joining(", ")));
    for (ColumnModel column : columns) {
      String field = column.getField();
      String configuredFilter = column.getFilter();
      List<String> configuredFilterList = column.getFilterList();
      String configuredFilterFrom = column.getFilterFrom();
      String configuredFilterTo = column.getFilterTo();
      
      String userFilter = column.getUserFilter();
      List<String> userFilterList = column.getUserFilterList();
      String userFilterFrom = column.getUserFilterFrom();
      String userFilterTo = column.getUserFilterTo();
      
      List<String> filterList = CollectionUtils.isNotEmpty(userFilterList) && !isInConfiguration ? userFilterList : configuredFilterList;
      String filterFrom = StringUtils.isNotBlank(userFilterFrom) && !isInConfiguration ? userFilterFrom : configuredFilterFrom;
      String filterTo = StringUtils.isNotBlank(userFilterTo) && !isInConfiguration ? userFilterTo : configuredFilterTo;
      
      if (StringUtils.equals(DashboardStandardTaskColumn.PRIORITY.getField(), column.getField())) {
        List<WorkflowPriority> priorities = new ArrayList<>();
        CollectionUtils.collect(filterList, WorkflowPriority::valueOf, priorities);
        queryPriorities(query, priorities);
      } else if (StringUtils.equals(DashboardStandardTaskColumn.NAME.getField(), column.getField())) {
        queryName(query, configuredFilter);
        if (!isInConfiguration) {
          queryName(query, userFilter);
        }
      } else if (StringUtils.equals(DashboardStandardTaskColumn.DESCRIPTION.getField(), column.getField())) {
        queryDescription(query, configuredFilter);
        if (!isInConfiguration) {
          queryDescription(query, userFilter);
        }
      } else if (StringUtils.equals(DashboardStandardTaskColumn.STATE.getField(), column.getField())) {
        for (String state : filterList) {
          states.add(TaskState.valueOf(state.toUpperCase()));
        }
      } else if (StringUtils.equals(DashboardStandardTaskColumn.CATEGORY.getField(), column.getField())) {
        queryCategory(query, filterList);
      } else if (StringUtils.equals(DashboardStandardTaskColumn.RESPONSIBLE.getField(), column.getField())) {
        queryResponsibles(query, filterList);
      } else if (StringUtils.equals(DashboardStandardTaskColumn.CREATED.getField(), column.getField())) {
        queryCreatedDate(query, Dates.parse(filterFrom), Dates.parse(filterTo));
      } else if (StringUtils.equals(DashboardStandardTaskColumn.EXPIRY.getField(), column.getField())) {
        queryExpiryDate(query, Dates.parse(filterFrom), Dates.parse(filterTo));
      } else if (StringUtils.equals(DashboardStandardTaskColumn.APPLICATION.getField(), column.getField())){
        queryApplications(query, filterList);
      } else if (column.getFilterType() == DashboardFilterType.SELECTION || CollectionUtils.isNotEmpty(filterList)) {
        queryCustomFieldSelection(query, field, filterList);
      } else {
        if (StringUtils.isNotBlank(configuredFilter) || StringUtils.isNotBlank(userFilter) || StringUtils.isNotBlank(filterFrom) || StringUtils.isNotBlank(filterTo)) {
          TaskQuery subQuery = applyFilter(column, field, configuredFilter, userFilter, filterFrom, filterTo);
          query.where().and(subQuery);
        }
      }
    }
    queryStates(query, states);
  }
  
  private TaskQuery applyFilter(ColumnModel column, String field, String configuredFilter, String userFilter,
      String filterFrom, String filterTo) {
    TaskQuery subQuery = TaskQuery.create();
    ICustomFieldFilterQuery filterQuery = subQuery.where().customField();
    if (column.isNumber()) {
      if (StringUtils.isNotBlank(filterFrom)) {
        Number from = Double.parseDouble(filterFrom.toString());
        filterQuery.numberField(field).isGreaterOrEqualThan(from);
      }
 
      if (StringUtils.isNotBlank(filterTo)) {
        Number to = Double.parseDouble(filterTo.toString());
        filterQuery.numberField(field).isLowerOrEqualThan(to);
      }
    } else if (column.isDate()) {
      Date from = Dates.parse(filterFrom);
      Date to = Dates.parse(filterTo);
      if (from != null) {
        filterQuery.timestampField(field).isGreaterOrEqualThan(from);
      }
 
      if (to != null) {
        filterQuery.timestampField(field).isLowerOrEqualThan(DateUtils.addDays(to, 1));
      }
    } else if (column.isText()) {
      queryTextField(filterQuery, field, configuredFilter);
      if (!isInConfiguration) {
        queryTextField(filterQuery, field, userFilter);
      }
    } else {
      queryStringField(filterQuery, field, configuredFilter);
      if (!isInConfiguration) {
        queryStringField(filterQuery, field, userFilter);
      }
    }
    return subQuery;
  }
  
  private void queryApplications(TaskQuery query, List<String> applications) {
    if (CollectionUtils.isNotEmpty(applications)) {
      applications.forEach(x ->Ivy.log().error("queryApplications with app {0}", x));
      TaskQuery subQuery = TaskQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String app : applications) {
        final Optional<IApplication> appFindByName = IApplicationRepository.instance().findByName(app);
        if (appFindByName.isPresent()) {
          Ivy.log().error("found app with name {0}", appFindByName.get().getId());
          filterQuery.or().applicationId().isEqual(appFindByName.get().getId());
        }
      }
      query.where().and(subQuery);
    } 
    else {
      Ivy.log().error("applications is empty");
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
  
  public boolean isInConfiguration() {
    return isInConfiguration;
  }
  
  public void setInConfiguration(boolean isInConfiguration) {
    this.isInConfiguration = isInConfiguration;
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
          DashboardColumnFormat format = columns.stream()
              .filter(c -> StringUtils.equalsIgnoreCase(sortField, c.getField())).map(ColumnModel::getFormat)
              .findFirst().orElse(DashboardColumnFormat.STRING);
          if (format == DashboardColumnFormat.NUMBER) {
            order = query.orderBy().customField().numberField(sortField);
          } else if (format == DashboardColumnFormat.TIMESTAMP) {
            order = query.orderBy().customField().timestampField(sortField);
          } else {
            order = query.orderBy().customField().stringField(sortField);
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

  public List<TaskColumnModel> getColumns() {
    return columns;
  }
  
  public void setColumns(List<TaskColumnModel> columns) {
    this.columns = columns;
  }
}
