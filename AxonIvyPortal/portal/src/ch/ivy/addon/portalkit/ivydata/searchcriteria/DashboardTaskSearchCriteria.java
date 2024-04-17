package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.axonivy.portal.util.filter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.OrderByColumnQuery;

public class DashboardTaskSearchCriteria {
  private static final String LIKE_FORMAT = "%%%s%%";

  private boolean canWorkOn;
  private List<TaskColumnModel> columns;
  private List<DashboardFilter> filters;
  private List<DashboardFilter> userFilters;
  private String sortField;
  private boolean sortDescending;
  private boolean isInConfiguration;
  private String quickSearchKeyword;

  public TaskQuery buildQuery() {
    TaskQuery query = buildQueryWithoutOrderByClause();
    TaskSortingQueryAppender appender = new TaskSortingQueryAppender(query);
    query = appender.appendSorting(this).toQuery();
    appendQuickSearchQuery(query);
    return query;
  }

  public TaskQuery buildQueryWithoutOrderByClause() {
    TaskQuery query = TaskQuery.create();
    queryFilters(query);
    queryCanWorkOn(query);
    return query;
  }

  private void queryCanWorkOn(TaskQuery query) {
    if (canWorkOn) {
      query.where().currentUserCanWorkOn();
    }
  }

  private void queryComplexFilter(TaskQuery query, List<DashboardFilter> filterList) {
    if (CollectionUtils.isEmpty(filterList)) {
      return;
    }

    for (DashboardFilter filter : filterList) {
      if (Optional.ofNullable(filter).map(DashboardFilter::getOperator).isEmpty()) {
        continue;
      }

      appendTaskQueryByDashboardFilter(query, filter);
    }
  }

  private void appendTaskQueryByDashboardFilter(TaskQuery query, DashboardFilter filter) {
    FilterField filterField = TaskFilterFieldFactory.findBy(filter.getField(), filter.getFilterType());
    if (filterField != null) {
      TaskQuery filterQuery = filterField.generateFilterTaskQuery(filter);
      if (filterQuery != null) {
        query.where().or(filterQuery);
      }
    }
  }
  
  private void appendCaseQueryByDashboardFilter(TaskQuery query, DashboardFilter filter) {
    FilterField filterField = FilterFieldFactory.findBy(filter.getField());
    if (filterField != null) {
      CaseQuery filterQuery = filterField.generateFilterQuery(filter);
      if (filterQuery != null) {
        query.where().or().cases(filterQuery);
      }
    }
  }

  private void queryFilters(TaskQuery query) {
    List<DashboardFilter> allFilters = new ArrayList<>(CollectionUtils.union(filters, userFilters));
    if (CollectionUtils.isNotEmpty(allFilters)) {
      queryComplexFilter(query, allFilters);
    }
  }
  
  private void appendQuickSearchQuery(TaskQuery query) {
    if (StringUtils.isNotBlank(this.quickSearchKeyword)) {
      TaskQuery subQuery = TaskQuery.create();

      List<TaskColumnModel> quickSearchColumns = columns.stream()
          .filter(col -> Optional.ofNullable(col.getQuickSearch()).orElse(false)).collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(quickSearchColumns)) {
        for (ColumnModel column : quickSearchColumns) {
          DashboardStandardTaskColumn columnEnum = DashboardStandardTaskColumn.findBy(column.getField());
          if (columnEnum != null) {
            appendTaskQueryByDashboardFilter(subQuery, selectStandandFieldToQuickSearchQuery(columnEnum));
          } else {
            appendCustomFieldsForQuickSearchQuery(subQuery, column);
          }
        }

        query.where().and(subQuery);
      }
    }
  }

  private void appendCustomFieldsForQuickSearchQuery(TaskQuery subQuery, ColumnModel column) {
    switch (column.getType()) {
    case CUSTOM_CASE -> {
      appendCaseQueryByDashboardFilter(subQuery, selectCustomFieldToQuickSearchQuery(column));
//      CaseQuery subCaseQuery = applyCaseFilter(column, column.getField(), this.quickSearchKeyword, null, null, null);
//      subQuery.where().or().cases(subCaseQuery);
    }
    case CUSTOM -> {
      appendTaskQueryByDashboardFilter(subQuery, selectCustomFieldToQuickSearchQuery(column));
//      TaskQuery taskSubQuery = applyFilter(column, column.getField(), this.quickSearchKeyword, null, null, null);
//      subQuery.where().or(taskSubQuery);
    }
    default -> {}
    }
  }
  
  private DashboardFilter selectStandandFieldToQuickSearchQuery(DashboardStandardTaskColumn columnEnum) {
    return switch (columnEnum) {
      case NAME, DESCRIPTION, ID, CATEGORY, RESPONSIBLE -> buildQuickSearchToDashboardFilter(columnEnum.getField(), FilterOperator.CONTAINS, DashboardColumnType.STANDARD);
      case APPLICATION -> buildQuickSearchToDashboardFilter(columnEnum.getField(), FilterOperator.IN, DashboardColumnType.STANDARD);
      default -> new DashboardFilter();
    };
  }
  
  private DashboardFilter selectCustomFieldToQuickSearchQuery(ColumnModel column) {
    return buildQuickSearchToDashboardFilter(column.getField(), FilterOperator.CONTAINS, DashboardColumnType.CUSTOM);
  }

  private DashboardFilter buildQuickSearchToDashboardFilter(String columnField, FilterOperator operator, DashboardColumnType type) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(columnField);
    filter.setFilterType(type);
    filter.setOperator(operator);
    filter.setValues(List.of(this.quickSearchKeyword));
    return filter;
  }
  
//
//  private void queryApplicationByQuickSearch(TaskQuery query, String app) {
//    final Optional<IApplication> appFindByName = IApplicationRepository.instance().findByName(app);
//    if (appFindByName.isPresent()) {
//      query.where().or().applicationId().isEqual(appFindByName.get().getId());
//    }
//  }
//  
//  
//  private TaskQuery applyFilter(ColumnModel column, String field, String configuredFilter, String userFilter,
//      String filterFrom, String filterTo) {
//    TaskQuery subQuery = TaskQuery.create();
//    ICustomFieldFilterQuery filterQuery = subQuery.where().customField();
//    if (column.isNumber()) {
//      if (StringUtils.isNotBlank(filterFrom)) {
//        Number from = Double.parseDouble(filterFrom.toString());
//        filterQuery.numberField(field).isGreaterOrEqualThan(from);
//      }
//
//      if (StringUtils.isNotBlank(filterTo)) {
//        Number to = Double.parseDouble(filterTo.toString());
//        filterQuery.numberField(field).isLowerOrEqualThan(to);
//      }
//    } else if (column.isDate()) {
//      Date from = Dates.parse(filterFrom);
//      Date to = Dates.parse(filterTo);
//      if (from != null) {
//        filterQuery.timestampField(field).isGreaterOrEqualThan(from);
//      }
//
//      if (to != null) {
//        filterQuery.timestampField(field).isLowerOrEqualThan(DateUtils.addDays(to, 1));
//      }
//    } else if (column.isText()) {
//      queryTextField(filterQuery, field, configuredFilter);
//      if (!isInConfiguration) {
//        queryTextField(filterQuery, field, userFilter);
//      }
//    } else {
//      queryStringField(filterQuery, field, configuredFilter);
//      if (!isInConfiguration) {
//        queryStringField(filterQuery, field, userFilter);
//      }
//    }
//    return subQuery;
//  }
//
//  private CaseQuery applyCaseFilter(ColumnModel column, String field, String configuredFilter, String userFilter,
//      String filterFrom, String filterTo) {
//    CaseQuery subQuery = CaseQuery.create();
//    ch.ivyteam.ivy.workflow.query.CaseQuery.ICustomFieldFilterQuery filterQuery = subQuery.where().customField();
//    if (column.isNumber()) {
//      if (StringUtils.isNotBlank(filterFrom)) {
//        Number from = Double.parseDouble(filterFrom.toString());
//        filterQuery.numberField(field).isGreaterOrEqualThan(from);
//      }
//
//      if (StringUtils.isNotBlank(filterTo)) {
//        Number to = Double.parseDouble(filterTo.toString());
//        filterQuery.numberField(field).isLowerOrEqualThan(to);
//      }
//    } else if (column.isDate()) {
//      Date from = Dates.parse(filterFrom);
//      Date to = Dates.parse(filterTo);
//      if (from != null) {
//        filterQuery.timestampField(field).isGreaterOrEqualThan(from);
//      }
//
//      if (to != null) {
//        filterQuery.timestampField(field).isLowerOrEqualThan(DateUtils.addDays(to, 1));
//      }
//    } else if (column.isText()) {
//      queryCaseTextField(filterQuery, field, configuredFilter);
//      if (!isInConfiguration) {
//        queryCaseTextField(filterQuery, field, userFilter);
//      }
//    } else {
//      queryCaseStringField(filterQuery, field, configuredFilter);
//      if (!isInConfiguration) {
//        queryCaseStringField(filterQuery, field, userFilter);
//      }
//    }
//    return subQuery;
//  }

  
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
    @SuppressWarnings("unused")
    private boolean sortStandardColumn;

    public TaskSortingQueryAppender(TaskQuery query) {
      this.query = query;
    }

    public TaskQuery toQuery() {
      return query;
    }

    public TaskSortingQueryAppender appendSorting(DashboardTaskSearchCriteria criteria) {
      appendSortByNameIfSet(criteria);
      appendSortByResponsibleIfSet(criteria);
      appendSortByIdIfSet(criteria);
      appendSortByExpiryDateIfSet(criteria);
      appendSortByStateIfSet(criteria);
      appendSortByPriorityIfSet(criteria);
      if (criteria.isSortDescending() && order != null) {
        order.descending();
      }
      return this;
    }
    
    private void appendSortByNameIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.NAME.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().name();
        sortStandardColumn = true;
      }
    }
    
    private void appendSortByResponsibleIfSet(DashboardTaskSearchCriteria criteria) {
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
    
    private void appendSortByStateIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.STATE.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().state();
        sortStandardColumn = true;
      }
    }

    private void appendSortByExpiryDateIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.EXPIRY.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().expiryTimestamp();
        sortStandardColumn = true;
      }
    }

    private void appendSortByPriorityIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.PRIORITY.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().priority();
        sortStandardColumn = true;
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
  
  public List<DashboardFilter> getFilters() {
    return filters;
  }

  public void setFilters(List<DashboardFilter> filters) {
    this.filters = filters;
  }

  public List<DashboardFilter> getUserFilters() {
    return userFilters;
  }

  public void setUserFilters(List<DashboardFilter> userFilters) {
    this.userFilters = userFilters;
  }

  public String getQuickSearchKeyword() {
    return quickSearchKeyword;
  }

  public void setQuickSearchKeyword(String quickSearchKeyword) {
    this.quickSearchKeyword = quickSearchKeyword;
  }
}
