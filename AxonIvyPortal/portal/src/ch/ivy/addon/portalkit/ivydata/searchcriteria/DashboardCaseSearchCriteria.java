package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.PortalCustomFieldUtils;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.ICustomFieldOrderBy;
import ch.ivyteam.ivy.workflow.query.CaseQuery.OrderByColumnQuery;

public class DashboardCaseSearchCriteria {

  private List<CaseColumnModel> columns;
  private List<DashboardFilter> filters;
  private List<DashboardFilter> userFilters;
  private String sortField;
  private boolean sortDescending;
  private boolean isInConfiguration;
  private String quickSearchKeyword;
  private boolean showPinnedItem;

  public CaseQuery buildQuery() {
    CaseQuery query = buildQueryWithoutOrderByClause();
    CaseSortingQueryAppender appender = new CaseSortingQueryAppender(query);
    query = appender.appendSorting(this).toQuery();
    return query;
  }

  public CaseQuery buildQueryWithoutOrderByClause() {
    CaseQuery query = CaseQuery.businessCases();
    queryFilters(query);
    appendQuickSearchQuery(query);
    if (showPinnedItem) {
      queryPinnedCase(query);
    }
    return query;
  }

  private void queryComplexFilter(CaseQuery query, List<DashboardFilter> filterList) {
    if (CollectionUtils.isEmpty(filterList)) {
      return;
    }

    for (DashboardFilter filter : filterList) {
      if (Optional.ofNullable(filter).map(DashboardFilter::getOperator).isEmpty()) {
        continue;
      }

      FilterField filterField = FilterFieldFactory.findBy(filter.getField());
      if (filterField != null) {
        CaseQuery filterQuery = filterField.generateFilterQuery(filter);
        if (filterQuery != null) {
          query.where().and(filterQuery);
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  private void queryFilters(CaseQuery query) {
    List<DashboardFilter> allFilters =
        new ArrayList<>(CollectionUtils.union(filters, userFilters));
    if (CollectionUtils.isNotEmpty(allFilters)) {
      queryComplexFilter(query, allFilters);
    }
  }

  private void appendQuickSearchQuery(CaseQuery query) {
    if (StringUtils.isNotBlank(this.quickSearchKeyword)) {
      CaseQuery subQuery = CaseQuery.create();

      List<CaseColumnModel> quickSearchColumns = columns.stream()
          .filter(col -> Optional.ofNullable(col.getQuickSearch()).orElse(false)).collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(quickSearchColumns)) {
        for (ColumnModel column : quickSearchColumns) {
          DashboardStandardTaskColumn columnEnum = DashboardStandardTaskColumn.findBy(column.getField());
          if (columnEnum != null) {
            appendQuickSearchCaseQueryByDashboardFilter(subQuery, selectStandandFieldToQuickSearchQuery(columnEnum));
          } else {
            appendQuickSearchCaseQueryByDashboardFilter(subQuery, selectCustomFieldToQuickSearchQuery(column));
            if (column.getType() == DashboardColumnType.CUSTOM && Boolean.TRUE.equals(column.getHasCmsValues())) {
              appendQuickSearchCaseQueryByDashboardFilter(subQuery, buildQuickSearchForCustomFieldWithCmsValues(column.getField()));
            }
          }
        }

        query.where().and(subQuery);
      }
    }
  }
  
  private void appendQuickSearchCaseQueryByDashboardFilter(CaseQuery query, DashboardFilter filter) {
    FilterField filterField = FilterFieldFactory.findBy(filter.getField());
    if (filterField != null) {
      CaseQuery filterQuery = filterField.generateFilterQuery(filter);
      if (filterQuery != null) {
        query.where().or(filterQuery);
      }
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
  
  private DashboardFilter buildQuickSearchForCustomFieldWithCmsValues(String columnField) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(columnField);
    filter.setFilterType(DashboardColumnType.CUSTOM);
    filter.setOperator(FilterOperator.IN);
    filter.setValues(PortalCustomFieldUtils.getCmsValuesMatchingWithKeywordList(columnField, DashboardColumnType.CUSTOM_CASE, List.of(this.quickSearchKeyword)));
    return filter; 
  }

  
  private DashboardFilter buildQuickSearchToDashboardFilter(String columnField, FilterOperator operator, DashboardColumnType type) {
    DashboardFilter filter = new DashboardFilter();
    filter.setField(columnField);
    filter.setFilterType(type);
    filter.setOperator(operator);
    filter.setValues(List.of(this.quickSearchKeyword));
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

  private class CaseSortingQueryAppender {

    private CaseQuery query;
    private OrderByColumnQuery order;
    private boolean sortStandardColumn;

    public CaseSortingQueryAppender(CaseQuery query) {
      this.query = query;
    }

    public CaseQuery toQuery() {
      return query;
    }

    public CaseSortingQueryAppender appendSorting(DashboardCaseSearchCriteria criteria) {
      appendSortByNameIfSet(criteria);
      appendSortByCreatorIfSet(criteria);
      appendSortByIdIfSet(criteria);
      appendSortByCreationDateIfSet(criteria);
      appendSortByStateIfSet(criteria);
      appendSortByEndDateIfSet(criteria);
      appendSortByCustomFieldIfSet(criteria);
      if (order != null && isSortDescending()) {
        order.descending();
      }
      return this;
    }
    
    private void appendSortByEndDateIfSet(DashboardCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.FINISHED.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().endTimestamp();
        sortStandardColumn = true;
      }
    }

    private void appendSortByNameIfSet(DashboardCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.NAME.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().name();
        sortStandardColumn = true;
      }
    }

    private void appendSortByCreatorIfSet(DashboardCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.CREATOR.getField().equalsIgnoreCase(criteria.getSortField())
          && !GlobalSettingService.getInstance().isHideCaseCreator()) {
        order = query.orderBy().creatorUserDisplayName();
        sortStandardColumn = true;
      }
    }

    private void appendSortByIdIfSet(DashboardCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.ID.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().caseId();
        sortStandardColumn = true;
      }
    }

    private void appendSortByCreationDateIfSet(DashboardCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.CREATED.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().startTimestamp();
        sortStandardColumn = true;
      }
    }

    private void appendSortByStateIfSet(DashboardCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.STATE.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().state();
        sortStandardColumn = true;
      }
    }
  
  private void appendSortByCustomFieldIfSet(DashboardCaseSearchCriteria criteria) {
    String sortField = criteria.getSortField();
    if (sortStandardColumn || StringUtils.isBlank(sortField)) {
        return;
    }
    
    DashboardColumnFormat format = columns.stream()
        .filter(c -> StringUtils.equalsIgnoreCase(sortField, c.getField()))
        .map(ColumnModel::getFormat)
        .findFirst()
        .orElse(DashboardColumnFormat.STRING);
    final ICustomFieldOrderBy customField = query.orderBy().customField();
    
    switch (format) {
      case NUMBER:
        order = customField.numberField(sortField);
        break;
      case TIMESTAMP:
        order = customField.timestampField(sortField);
        break;
      default:
        if (PortalCustomFieldUtils.isSupportMultiLanguageCaseField(sortField)) {
          order = customField.stringField(sortField).values(PortalCustomFieldUtils.getAllLocalizedValueOnCaseField(sortField));
        } else {
          order = customField.stringField(sortField);
        }
        break;
    }
}
}

  public List<CaseColumnModel> getColumns() {
    return columns;
  }

  public void setColumns(List<CaseColumnModel> columns) {
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

  public boolean showPinnedItem() {
    return showPinnedItem;
  }

  public void setShowPinnedItem(boolean showPinnedItem) {
    this.showPinnedItem = showPinnedItem;
  }

  private void queryPinnedCase(CaseQuery query) {
    Set<String> pinnedCaseUuids = Optional.ofNullable(CaseUtils.getPinnedCaseUuids()).orElse(Collections.emptySet());

    if (pinnedCaseUuids.isEmpty()) {
      query.where().uuid().isEqual(StringUtils.EMPTY); // value that can never match
    } else {
      String[] uuidArray = pinnedCaseUuids.toArray(new String[0]);
      query.where().uuid().isIn(uuidArray);
    }
  }

}
