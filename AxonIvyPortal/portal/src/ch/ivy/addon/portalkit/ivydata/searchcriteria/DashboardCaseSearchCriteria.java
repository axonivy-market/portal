package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.casecolumn.CaseColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardFilterType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.ICustomFieldFilterQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.OrderByColumnQuery;

public class DashboardCaseSearchCriteria {

  private static final String LIKE_FORMAT = "%%%s%%";
  private List<CaseColumnModel> columns;
  private String sortField;
  private boolean sortDescending;
  private boolean isInConfiguration;
  private String quickSearchKeyword;

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

  private void queryStates(CaseQuery query, List<CaseState> states) {
    if (CollectionUtils.isNotEmpty(states)) {
      states = CaseUtils.filterStateByPermission(states);
    } else {
      states = CaseUtils.getValidStates();
    }
    CaseQuery subQuery = CaseQuery.create();
    IFilterQuery filterQuery = subQuery.where();
    for (CaseState state : states) {
      filterQuery.or().state().isEqual(state);
    }
    query.where().and(subQuery);
  }

  private void queryCreator(CaseQuery query, List<String> creators) {
    if (CollectionUtils.isNotEmpty(creators)) {
      CaseQuery subQuery = CaseQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String creator : creators) {
        filterQuery.or().creatorUserName().isEqual(creator.replace("#", ""));
      }

      query.where().and(subQuery);
    }
  }

  private void queryCategory(CaseQuery query, List<String> categories) {
    if (CollectionUtils.isNotEmpty(categories)) {
      CaseQuery subQuery = CaseQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String category : categories) {
        filterQuery.or().category().isEqual(category);
      }

      query.where().and(subQuery);
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
    var states = new ArrayList<CaseState>();
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

      List<String> filterList = CollectionUtils.isNotEmpty(userFilterList) && !isInConfiguration ? userFilterList
          : configuredFilterList;
      String filterFrom = StringUtils.isNotBlank(userFilterFrom) && !isInConfiguration ? userFilterFrom
          : configuredFilterFrom;
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
      } else if (equals(DashboardStandardCaseColumn.STATE, column)) {
        for (String state : filterList) {
          states.add(CaseState.valueOf(state.toUpperCase()));
        }
      } else if (equals(DashboardStandardCaseColumn.CREATOR, column)) {
        queryCreator(query, filterList);
      } else if (equals(DashboardStandardCaseColumn.OWNER, column)) {
        queryOwner(query, filterList);
      } else if (equals(DashboardStandardCaseColumn.CATEGORY, column)) {
        queryCategory(query, filterList);
      } else if (equals(DashboardStandardCaseColumn.CREATED, column)) {
        Date from = Dates.parse(filterFrom);
        Date to = Dates.parse(filterTo);
        queryCreatedDate(query, from, to);
      } else if (equals(DashboardStandardCaseColumn.FINISHED, column)) {
        Date from = Dates.parse(filterFrom);
        Date to = Dates.parse(filterTo);
        queryFinishedDate(query, from, to);
      } else if (equals(DashboardStandardCaseColumn.APPLICATION, column)) {
        queryApplications(query, filterList);
      } else if (column.getFilterType() == DashboardFilterType.SELECTION || CollectionUtils.isNotEmpty(filterList)) {
        queryCustomFieldSelection(query, field, filterList);
      } else {
        if (StringUtils.isNotBlank(configuredFilter) || StringUtils.isNotBlank(userFilter)
            || StringUtils.isNotBlank(filterFrom) || StringUtils.isNotBlank(filterTo)) {
          CaseQuery subQuery = applyFilter(column, field, configuredFilter, userFilter, filterFrom, filterTo);
          query.where().and(subQuery);
        }
      }
    }
    queryStates(query, states);
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
    if (CollectionUtils.isNotEmpty(owners) && GlobalSettingService.getInstance().isCaseOwnerEnabled()) {
      CaseQuery subQuery = CaseQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String owner : owners) {
        filterQuery.or().ownerName().isEqual(owner);
      }

      query.where().and(subQuery);
    }
  }

  private void queryApplications(CaseQuery query, List<String> applications) {
    if (CollectionUtils.isNotEmpty(applications)) {
      CaseQuery subQuery = CaseQuery.create();
      IFilterQuery filterQuery = subQuery.where();
      for (String app : applications) {
        final Optional<IApplication> appFindByName = IApplicationRepository.instance().findByName(app);
        if (appFindByName.isPresent()) {
          filterQuery.or().applicationId().isEqual(appFindByName.get().getId());
        }
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

  private void appendQuickSearchQuery(CaseQuery query) {
    if (StringUtils.isNotBlank(this.quickSearchKeyword)) {
      CaseQuery subQuery = CaseQuery.create();

      List<CaseColumnModel> quickSearchColumns = columns.stream()
          .filter(col -> Optional.ofNullable(col.getQuickSearch()).orElse(false)).collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(quickSearchColumns)) {
        for (ColumnModel column : quickSearchColumns) {
          DashboardStandardCaseColumn columnEnum = DashboardStandardCaseColumn.findBy(column.getField());
          if (columnEnum != null) {
            appendStandardFieldsToQuickSearchQuery(subQuery, columnEnum);
          } else {
            appendCustomFieldsForQuickSearchQuery(subQuery, column);
          }
        }
        query.where().and(subQuery);
      }
    }
  }

  private void appendStandardFieldsToQuickSearchQuery(CaseQuery subQuery, DashboardStandardCaseColumn columnEnum) {
    String formattedKeyword = String.format(LIKE_FORMAT, this.getQuickSearchKeyword());
    switch (columnEnum) {
    case NAME -> subQuery.where().or().name().isLikeIgnoreCase(formattedKeyword);
    case DESCRIPTION -> subQuery.where().or().description().isLikeIgnoreCase(formattedKeyword);
    case CATEGORY -> subQuery.where().or().category().isLikeIgnoreCase(formattedKeyword);
    case ID -> subQuery.where().or().caseId().isLikeIgnoreCase(formattedKeyword);
    case CREATOR -> subQuery.where().or().creatorUserDisplayName().isLikeIgnoreCase(formattedKeyword);
    case APPLICATION -> queryApplicationByQuickSearch(subQuery, this.quickSearchKeyword);
    default -> {
    }
    }
  }

  private void appendCustomFieldsForQuickSearchQuery(CaseQuery subQuery, ColumnModel column) {
    switch (column.getType()) {
    case CUSTOM -> {
      CaseQuery caseQuery = applyFilter(column, column.getField(), this.quickSearchKeyword, null, null, null);
      subQuery.where().or(caseQuery);
    }
    default -> {
    }
    }
  }

  private void queryApplicationByQuickSearch(CaseQuery query, String app) {
    final Optional<IApplication> appFindByName = IApplicationRepository.instance().findByName(app);
    if (appFindByName.isPresent()) {
      query.where().or().applicationId().isEqual(appFindByName.get().getId());
    }
  }

  public List<CaseColumnModel> getColumns() {
    return columns;
  }

  public void setColumns(List<CaseColumnModel> columns) {
    this.columns = columns;
  }

  public String getQuickSearchKeyword() {
    return quickSearchKeyword;
  }

  public void setQuickSearchKeyword(String quickSearchKeyword) {
    this.quickSearchKeyword = quickSearchKeyword;
  }
}
