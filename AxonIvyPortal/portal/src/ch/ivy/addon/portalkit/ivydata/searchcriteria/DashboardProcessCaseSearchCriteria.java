package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.category.CategoryPath;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.OrderByColumnQuery;

public class DashboardProcessCaseSearchCriteria {

  private Long processStartId;
  private String processName;
  private String sortField;
  private boolean sortDescending;
  
  public DashboardProcessCaseSearchCriteria(Long processStartId, String processName) {
    this.processStartId = processStartId;
    this.processName = processName;
  }

  public CaseQuery buildQuery() {
    CaseQuery query = buildQueryWithoutOrderByClause();
    CaseSortingQueryAppender appender = new CaseSortingQueryAppender(query);
    query = appender.appendSorting(this).toQuery();
    return query;
  }

  public CaseQuery buildQueryWithoutOrderByClause() {
    CaseQuery query = CaseQuery.businessCases();
    if (this.processStartId != null) {
      queryProcessStartId(query);
    } else {
      queryExpressProcessCasesByCategoryPath(query);
    }
    queryStates(query);
    queryIsInvolved(query);
    return query;
  }

  private void queryStates(CaseQuery query) {
    var states = CaseUtils.getValidStates();
    CaseQuery subQuery = CaseQuery.create();
    IFilterQuery filterQuery = subQuery.where();
    for (CaseState state : states) {
      filterQuery.or().state().isEqual(state);
    }
    query.where().and(subQuery);
  }

  private void queryProcessStartId(CaseQuery query) {
    query.where().taskStartId().isEqual(processStartId);
  }

  private void queryExpressProcessCasesByCategoryPath(CaseQuery query) {
    String categoryPath = new CategoryPath("ExpressWorkflow/" + this.processName).getValidRawPath();
    query.where().customField().stringField(CustomFields.IS_EXPRESS_PROCESS).isEqual("true");
    query.where().category().isEqual(categoryPath);
  }

  private void queryIsInvolved(CaseQuery query) {
    query.where().currentUserIsInvolved();
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
  
  private class CaseSortingQueryAppender {

    private CaseQuery query;
    private OrderByColumnQuery order;

    public CaseSortingQueryAppender(CaseQuery query) {
      this.query = query;
    }

    public CaseQuery toQuery() {
      return query;
    }
    
    public CaseSortingQueryAppender appendSorting(DashboardProcessCaseSearchCriteria criteria) {
      appendSortByNameIfSet(criteria);
      appendSortByCreatorIfSet(criteria);
      appendSortByIdIfSet(criteria);
      appendSortByCreationDateIfSet(criteria);
      appendSortByStateIfSet(criteria);
      appendSortByOwnerIfSet(criteria);
      appendSortByEndDateIfSet(criteria);
      if (criteria.isSortDescending()) {
        order.descending();
      }
      return this;
    }

    private void appendSortByEndDateIfSet(DashboardProcessCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.FINISHED.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().endTimestamp();
      }
    }

    private void appendSortByOwnerIfSet(DashboardProcessCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.OWNER.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().ownerDisplayName();
      }
    }

    private void appendSortByNameIfSet(DashboardProcessCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.NAME.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().name();
      }
    }

    private void appendSortByCreatorIfSet(DashboardProcessCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.CREATOR.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().creatorUserDisplayName();
      }
    }

    private void appendSortByIdIfSet(DashboardProcessCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.ID.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().caseId();
      }
    }

    private void appendSortByCreationDateIfSet(DashboardProcessCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.CREATED.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().startTimestamp();
      }
    }

    private void appendSortByStateIfSet(DashboardProcessCaseSearchCriteria criteria) {
      if (DashboardStandardCaseColumn.STATE.getField().equalsIgnoreCase(criteria.getSortField())) {
        order = query.orderBy().state();
      }
    }
  }
}