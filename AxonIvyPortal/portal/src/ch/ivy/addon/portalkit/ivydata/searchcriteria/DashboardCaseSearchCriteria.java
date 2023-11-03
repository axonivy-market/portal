package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.operator.caze.application.ApplicationInOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.category.CategoryContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.category.CategoryInOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.category.CategoryNoCategoryOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.createddate.CreatedDateAfterOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.createddate.CreatedDateBeforeOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.createddate.CreatedDateBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.createddate.CreatedDateCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.createddate.CreatedDateIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.createddate.CreatedDateNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.createddate.CreatedDateTodayOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.createddate.CreatedDateYesterdayOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.creator.CreatorCurrentUserOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.creator.CreatorInOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.description.DescriptionStartWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateAfterOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateBeforeOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateCurrentPeriodOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateNumberOfPeriodsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateTodayOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.finisheddate.FinishedDateYesterdayOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.name.NameContainsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.name.NameEndWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.name.NameIsEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.name.NameIsOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.name.NameStartWithOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.state.StateInOperatorHandler;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardFilterType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.ICustomFieldFilterQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.OrderByColumnQuery;

public class DashboardCaseSearchCriteria {

  private static final String LIKE_FORMAT = "%%%s%%";
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

  private void queryCreatedDate(CaseQuery query, Date from, Date to) {
    if (from != null || to != null) {
      CaseQuery subQuery = CaseQuery.create();
      if (from != null) {
        subQuery.where().startTimestamp().isGreaterOrEqualThan(from);
      }

      if (to != null) {
        subQuery.where().startTimestamp().isLowerOrEqualThan(DateUtils.addDays(to, 1));
      }
      query.where().and(subQuery);
    }
  }

  private void queryComplexFilter(CaseQuery query, List<DashboardFilter> filterList) {
    if (CollectionUtils.isEmpty(filterList)) {
      return;
    }

    for (DashboardFilter filter : filterList) {
      DashboardStandardCaseColumn fieldEnum  = DashboardStandardCaseColumn.findBy(filter.getField());

      if (Optional.ofNullable(filter).map(DashboardFilter::getOperator).isEmpty()) {
        continue;
      }

      CaseQuery filterQuery = switch (fieldEnum) {
        case NAME -> generateNameFilterQuery(filter);
        case DESCRIPTION -> generateDescriptionFilterQuery(filter);
        case CREATED -> generateCreatedDateFilterQuery(filter);
        case FINISHED -> generateFinishedDateFilterQuery(filter);
        case CREATOR -> generateCreatorFilterQuery(filter);
        case CATEGORY -> generateCategoryFilterQuery(filter);
        case APPLICATION -> generateApplicationFilterQuery(filter);
        case STATE -> generateStateFilterQuery(filter);
        default -> null;
      };

      if (filterQuery != null) {
        query.where().and(filterQuery);
      }
    }
  }

  private CaseQuery generateCreatedDateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case BETWEEN -> CreatedDateBetweenOperatorHandler.getInstance().buildBetweenQuery(filter);
      case NOT_BETWEEN -> CreatedDateBetweenOperatorHandler.getInstance().buildNotBetweenQuery(filter);
      case BEFORE -> CreatedDateBeforeOperatorHandler.getInstance().buildQuery(filter);
      case AFTER -> CreatedDateAfterOperatorHandler.getInstance().buildQuery(filter);
      case TODAY -> CreatedDateTodayOperatorHandler.getInstance().buildQuery();
      case YESTERDAY -> CreatedDateYesterdayOperatorHandler.getInstance().buildQuery();
      case CURRENT -> CreatedDateCurrentPeriodOperatorHandler.getInstance().buildQuery(filter);
      case LAST -> CreatedDateNumberOfPeriodsOperatorHandler.getInstance().buildLastPeriodQuery(filter);
      case NEXT -> CreatedDateNumberOfPeriodsOperatorHandler.getInstance().buildNextPeriodQuery(filter);
      case IS -> CreatedDateIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> CreatedDateIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      default -> null;
    };
  }

  private CaseQuery generateFinishedDateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case BETWEEN -> FinishedDateBetweenOperatorHandler.getInstance().buildBetweenQuery(filter);
      case NOT_BETWEEN -> FinishedDateBetweenOperatorHandler.getInstance().buildNotBetweenQuery(filter);
      case BEFORE -> FinishedDateBeforeOperatorHandler.getInstance().buildQuery(filter);
      case AFTER -> FinishedDateAfterOperatorHandler.getInstance().buildQuery(filter);
      case TODAY -> FinishedDateTodayOperatorHandler.getInstance().buildQuery();
      case YESTERDAY -> FinishedDateYesterdayOperatorHandler.getInstance().buildQuery();
      case CURRENT -> FinishedDateCurrentPeriodOperatorHandler.getInstance().buildQuery(filter);
      case LAST -> FinishedDateNumberOfPeriodsOperatorHandler.getInstance().buildLastPeriodQuery(filter);
      case NEXT -> FinishedDateNumberOfPeriodsOperatorHandler.getInstance().buildNextPeriodQuery(filter);
      case IS -> FinishedDateIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> FinishedDateIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      default -> null;
    };
  }

  private CaseQuery generateNameFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case CONTAINS -> NameContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> NameContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case IS -> NameIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> NameIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case START_WITH -> NameStartWithOperatorHandler.getInstance().buildStartWithQuery(filter);
      case NOT_START_WITH -> NameStartWithOperatorHandler.getInstance().buildNotStartWithQuery(filter);
      case END_WITH -> NameEndWithOperatorHandler.getInstance().buildEndWithQuery(filter);
      case NOT_END_WITH -> NameEndWithOperatorHandler.getInstance().buildNotEndWithQuery(filter);
      case EMPTY -> NameIsEmptyOperatorHandler.getInstance().buildIsEmptyQuery(filter);
      case NOT_EMPTY -> NameIsEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      default -> null;
    };
  }

  private CaseQuery generateDescriptionFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case CONTAINS -> DescriptionContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> DescriptionContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case IS -> DescriptionIsOperatorHandler.getInstance().buildIsQuery(filter);
      case IS_NOT -> DescriptionIsOperatorHandler.getInstance().buildIsNotQuery(filter);
      case START_WITH -> DescriptionStartWithOperatorHandler.getInstance().buildStartWithQuery(filter);
      case NOT_START_WITH -> DescriptionStartWithOperatorHandler.getInstance().buildNotStartWithQuery(filter);
      case END_WITH -> DescriptionEndWithOperatorHandler.getInstance().buildEndWithQuery(filter);
      case NOT_END_WITH -> DescriptionEndWithOperatorHandler.getInstance().buildNotEndWithQuery(filter);
      case EMPTY -> DescriptionIsEmptyOperatorHandler.getInstance().buildIsEmptyQuery(filter);
      case NOT_EMPTY -> DescriptionIsEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      default -> null;
    };
  }

  private CaseQuery generateCreatorFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> CreatorInOperatorHandler.getInstance().buildInQuery(filter);
      case NOT_IN -> CreatorInOperatorHandler.getInstance().buildNotInQuery(filter);
      case CURRENT_USER -> CreatorCurrentUserOperatorHandler.getInstance().buildQuery();
      default -> null;
    };
  }

  private CaseQuery generateCategoryFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> CategoryInOperatorHandler.getInstance().buildInQuery(filter);
      case NOT_IN -> CategoryInOperatorHandler.getInstance().buildNotInQuery(filter);
      case CONTAINS -> CategoryContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      case NOT_CONTAINS -> CategoryContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
      case NO_CATEGORY -> CategoryNoCategoryOperatorHandler.getInstance().buildQuery();
      default -> null;
    };
  }

  private CaseQuery generateApplicationFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> ApplicationInOperatorHandler.getInstance().buildQuery(filter);
      default -> null;
    };
  }

  private CaseQuery generateStateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> StateInOperatorHandler.getInstance().buildStateInQuery(filter);
      default -> null;
    };
  }

  private void queryName(CaseQuery query, String name) {
    if (StringUtils.isNotBlank(name)) {
      query.where().name().isLikeIgnoreCase(String.format(LIKE_FORMAT, name));
    }
  }

  private void queryDescription(CaseQuery query, String description) {
    if (StringUtils.isNotBlank(description)) {
      query.where().description().isLikeIgnoreCase(String.format(LIKE_FORMAT, description));
    }
  }

  private void queryCustomFieldSelection(CaseQuery query, String field, List<String> filterList) {
    if (CollectionUtils.isNotEmpty(filterList)) {
      CaseQuery subQuery = CaseQuery.create();
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
  
  private void queryFilters(CaseQuery query) {
    if (CollectionUtils.isNotEmpty(filters)) {
      queryComplexFilter(query, filters);
    }

    if (CollectionUtils.isNotEmpty(userFilters)) {
      queryComplexFilter(query, userFilters);
    }

    if (CollectionUtils.isNotEmpty(filters) || CollectionUtils.isNotEmpty(filters)) {
      return;
    }

    else {
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
        
        if (equals(DashboardStandardCaseColumn.NAME, column)) {
          queryName(query, configuredFilter);
          if (!isInConfiguration) {
            queryName(query, userFilter);
          }
        } else if (equals(DashboardStandardCaseColumn.DESCRIPTION, column)) {
          queryDescription(query, configuredFilter);
          if (!isInConfiguration) {
            queryDescription(query, userFilter);
          }
        } else if (equals(DashboardStandardCaseColumn.OWNER, column)) {
          queryOwner(query, filterList);
        } else if (equals(DashboardStandardCaseColumn.CREATED, column)) {
          Date from = Dates.parse(filterFrom);
          Date to = Dates.parse(filterTo);
          queryCreatedDate(query, from, to);
        } else if (equals(DashboardStandardCaseColumn.FINISHED, column)) {
          Date from = Dates.parse(filterFrom);
          Date to = Dates.parse(filterTo);
          queryFinishedDate(query, from, to);
        } else if (column.getFilterType() == DashboardFilterType.SELECTION || CollectionUtils.isNotEmpty(filterList)) {
          queryCustomFieldSelection(query, field, filterList);
        } else {
          if (StringUtils.isNotBlank(configuredFilter) || StringUtils.isNotBlank(userFilter) || StringUtils.isNotBlank(filterFrom) || StringUtils.isNotBlank(filterTo)) {
            CaseQuery subQuery = applyFilter(column, field, configuredFilter, userFilter, filterFrom, filterTo);
            query.where().and(subQuery);
          }
        }
      }
    }
  }

  private boolean equals(DashboardStandardCaseColumn caseColumn, ColumnModel column) {
    return StringUtils.equals(caseColumn.getField(), column.getField());
  }

  private CaseQuery applyFilter(ColumnModel column, String field, String configuredFilter, String userFilter,
      String filterFrom, String filterTo) {
    CaseQuery subQuery = CaseQuery.create();
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
  
  private void queryFinishedDate(CaseQuery query, Date from, Date to) {
    if (from != null || to != null) {
      CaseQuery subQuery = CaseQuery.create();
      if (from != null) {
        subQuery.where().endTimestamp().isGreaterOrEqualThan(from);
      }

      if (to != null) {
        subQuery.where().endTimestamp().isLowerOrEqualThan(DateUtils.addDays(to, 1));
      }
      query.where().and(subQuery);
    }
  }

  private void queryOwner(CaseQuery query, List<String> owners) {
    if (CollectionUtils.isNotEmpty(owners)
        && GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
      CaseQuery subQuery = CaseQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String owner : owners) {
        filterQuery.or().ownerName().isEqual(owner);
      }

      query.where().and(subQuery);
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
