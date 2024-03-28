package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.OrderByColumnQuery;

public class DashboardCaseSearchCriteria {

  private List<CaseColumnModel> columns;
  private List<DashboardFilter> filters;
  private List<DashboardFilter> userFilters;
  private String sortField;
  private boolean sortDescending;
  private boolean isInConfiguration;

  public CaseQuery buildQuery() {
    CaseQuery query = buildQueryWithoutOrderByClause();
    CaseSortingQueryAppender appender = new CaseSortingQueryAppender(query);
    query = appender.appendSorting(this).toQuery();
    return query;
  }

  public CaseQuery buildQueryWithoutOrderByClause() {
    CaseQuery query = CaseQuery.businessCases();
    queryFilters(query);
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
      appendSortByOwnerIfSet(criteria);
      appendSortByEndDateIfSet(criteria);
      appendSortByCustomFieldIfSet(criteria);
      if (criteria.isSortDescending()) {
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

    private void appendSortByOwnerIfSet(DashboardCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.OWNER.getField().equalsIgnoreCase(criteria.getSortField())
          && GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
        order = query.orderBy().ownerDisplayName();
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
      if (DashboardStandardCaseColumn.CREATOR.getField().equalsIgnoreCase(criteria.getSortField())) {
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
}
