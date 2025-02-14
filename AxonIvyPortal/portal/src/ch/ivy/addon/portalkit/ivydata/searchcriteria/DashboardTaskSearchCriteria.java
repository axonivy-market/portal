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
import com.axonivy.portal.util.filter.operator.task.customfield.CustomStringIsOperatorHandler;
import com.google.common.collect.Iterables;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomField;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.ICustomFieldOrderBy;
import ch.ivyteam.ivy.workflow.query.TaskQuery.OrderByColumnQuery;

public class DashboardTaskSearchCriteria {

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

      FilterField filterField = TaskFilterFieldFactory.findBy(filter.getField(), filter.getFilterType());
      if (filterField != null) {
        TaskQuery filterQuery = filterField.generateFilterTaskQuery(filter);
        if (filterQuery != null) {
          query.where().and(filterQuery);
        }
      }
    }
  }

  private void appendQuickSearchTaskQueryByDashboardFilter(TaskQuery query, DashboardFilter filter) {
    FilterField filterField = TaskFilterFieldFactory.findBy(filter.getField(), filter.getFilterType());
    if (filterField != null) {
      TaskQuery filterQuery = filterField.generateFilterTaskQuery(filter);
      if (filterQuery != null) {
        query.where().or(filterQuery);
      }
    }
  }
  
  private void appendQuickSearchCaseQueryByDashboardFilter(TaskQuery query, DashboardFilter filter) {
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
            appendQuickSearchTaskQueryByDashboardFilter(subQuery, selectStandandFieldToQuickSearchQuery(columnEnum));
          } else if (isSupport(column.getField()) && !getQuickSearchKeywordListMatchingWithCustomField(column.getField()).isEmpty()) {
              DashboardFilter filter = generateFilterForCustomFieldWithMultiLanguageSupport(column.getField(), getQuickSearchKeywordListMatchingWithCustomField(column.getField()));
              TaskQuery customFieldQuery = generateQueryForCustomFieldWithMultiLanguage(filter);
              subQuery.where().or(customFieldQuery);
            }
            else {
              appendCustomFieldsForQuickSearchQuery(subQuery, column);
            }
            }
        }
        
        query.where().and(subQuery);
        Ivy.log().info(query);
      }
    }
  
  private boolean isSupportMultiLanguage(String columnField) {
    return !getQuickSearchKeywordListMatchingWithCustomField(columnField).isEmpty();
  }
  
  private TaskQuery generateQueryForCustomFieldWithMultiLanguage(DashboardFilter filter) {
    return CustomStringIsOperatorHandler.getInstance().buildIsQuery(filter);
  }
  
  private DashboardFilter generateFilterForCustomFieldWithMultiLanguageSupport(String field, List<String> keywordList) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(field);
    filter.setFilterType(DashboardColumnType.CUSTOM);
    filter.setOperator(FilterOperator.IS);
    filter.setValues(keywordList);
    return filter;
  }
  
  private void appendCustomFieldsForQuickSearchQuery(TaskQuery subQuery, ColumnModel column) {
    switch (column.getType()) {
    case CUSTOM_CASE -> appendQuickSearchCaseQueryByDashboardFilter(subQuery, selectCustomFieldToQuickSearchQuery(column));
    case CUSTOM -> appendQuickSearchTaskQueryByDashboardFilter(subQuery, selectCustomFieldToQuickSearchQuery(column));
    default -> {}
    }
  }
  
  private DashboardFilter selectStandandFieldToQuickSearchQuery(DashboardStandardTaskColumn columnEnum) {
    return switch (columnEnum) {
      case APPLICATION -> buildQuickSearchToDashboardFilter(columnEnum.getField(), FilterOperator.IN, DashboardColumnType.STANDARD);
      default -> buildQuickSearchToDashboardFilter(columnEnum.getField(), FilterOperator.CONTAINS, DashboardColumnType.STANDARD);
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
    filter.setValues(List.of(quickSearchKeyword));
    return filter;
  }

//  private DashboardFilter buildQuickSearchForCustomColumn(String columnField, FilterOperator operator, DashboardColumnType type) {
//    
//  }
  
  
  
  private List<String> getQuickSearchKeywordListMatchingWithCustomField(String columnField) {
    List<String> keywordList = new ArrayList<>();
    ICustomFieldMeta.tasks().stream().filter(task -> task.type().equals(CustomFieldType.STRING) && task.name().equals(columnField)).forEach(field -> {
      Iterable<Object> list =  field.values().matching(this.quickSearchKeyword);
      if (!Iterables.isEmpty(list) && field.name().equals(columnField)) {
        Ivy.log().info(field.name());
        for (Object obj : list) {
            keywordList.add(obj.toString());
        }
    }
    });
    return keywordList;
  }
  
  private boolean isSupport(String column) {
    List<ICustomFieldMeta> list = ICustomFieldMeta.tasks().stream().filter(field -> field.name().equals(column) && field.attribute("CmsPath")!= null).collect(Collectors.toList());
    return !list.isEmpty();
  }
  
  private DashboardFilter buildQuickSearchToDashboardFilterWithCustomColumn(DashboardFilter filter, List<String> keywordList, String columnField) {
    keywordList.add(this.quickSearchKeyword);
    filter.setField(columnField);
    filter.setFilterType(DashboardColumnType.CUSTOM);
    filter.setOperator(FilterOperator.CONTAINS);
    filter.setValues(keywordList);
    return filter;
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
      appendSortByCreatedDateIfSet(criteria);
      appendSortByExpiryDateIfSet(criteria);
      appendSortByStateIfSet(criteria);
      appendSortByPriorityIfSet(criteria);
      appendSortByCustomFieldIfSet(criteria);
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

    private void appendSortByCreatedDateIfSet(DashboardTaskSearchCriteria criteria) {
      if (DashboardStandardTaskColumn.CREATED.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().startTimestamp();
        sortStandardColumn = true;
      }
    }

    private void appendSortByCustomFieldIfSet(DashboardTaskSearchCriteria criteria) {
      if (!sortStandardColumn) {
        String sortField = criteria.getSortField();
        if (StringUtils.isNotBlank(sortField)) {
          DashboardColumnFormat format =
              columns.stream().filter(c -> StringUtils.equalsIgnoreCase(sortField, c.getField()))
                  .map(ColumnModel::getFormat).findFirst().orElse(DashboardColumnFormat.STRING);
          final ICustomFieldOrderBy customField = query.orderBy().customField();
          if (format == DashboardColumnFormat.NUMBER) {
            order = customField.numberField(sortField);
          } else if (format == DashboardColumnFormat.TIMESTAMP) {
            order = customField.timestampField(sortField);
          } else {
            order = customField.stringField(sortField);
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
