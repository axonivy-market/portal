package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import static ch.ivy.addon.portalkit.statistics.StatisticChartConstants.NO_CATEGORY_CMS;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.OrderByColumnQuery;

public class CaseSearchCriteria {

  private String involvedUsername;
  private List<String> apps;
  private List<CaseState> includedStates;
  private String keyword;
  private Long caseId;
  private String category;
  private String sortField;
  private boolean sortDescending;
  private boolean isBusinessCase;
  private boolean isTechnicalCase;
  private Long businessCaseId;
  private boolean isAdminQuery;
  
  private boolean isNewQueryCreated;
  private boolean isSorted = true;
  private CaseQuery customCaseQuery;
  
  private CaseQuery finalCaseQuery;

  public CaseQuery createQuery() {
    CaseQuery finalQuery = CaseQuery.create();
    if (isBusinessCase) {
      finalQuery = CaseQuery.businessCases();
    } else if (isTechnicalCase) {
      finalQuery = CaseQuery.subCases().where().and().businessCaseId().isEqual(businessCaseId);
    }

    setNewQueryCreated(isNewQueryCreated() || customCaseQuery == null || hasCaseId());
    if (!isNewQueryCreated()) {
      finalQuery.where().andOverall(CaseQuery.fromJson(customCaseQuery.asJson())); // clone to keep the original custom query
    }

    if (hasIncludedStates()) {
      finalQuery.where().and(queryForStates(getIncludedStates()));
    }

    if (hasCaseId()) {
      finalQuery.where().and().caseId().isEqual(getCaseId());
    }

    if (hasKeyword()) {
      finalQuery.where().and(queryForKeyword(getKeyword()));
    }

    if (hasCategory()) {
      finalQuery.where().and(queryForCategory(getCategory()));
    }

    if (isSorted) {
      CaseSortingQueryAppender appender = new CaseSortingQueryAppender(finalQuery);
      finalQuery = appender.appendSorting(this).toQuery();
    }
    return finalQuery;
  }
  
  private CaseQuery queryForStates(List<CaseState> states) {
    CaseQuery stateFieldQuery = CaseQuery.create();
    IFilterQuery filterQuery = stateFieldQuery.where();
    for (CaseState state : states) {
      filterQuery.or().state().isEqual(state);
    }
    return stateFieldQuery;
  }

  private CaseQuery queryForKeyword(String keyword) {
    String containingKeyword = String.format("%%%s%%", keyword);

    CaseQuery filterByKeywordQuery = CaseQuery.create().where().or().name().isLikeIgnoreCase(containingKeyword).or()
        .description().isLikeIgnoreCase(containingKeyword).or().customField().anyStringField().isLikeIgnoreCase(containingKeyword);

    try {
      long idKeyword = Long.parseLong(keyword);
      String containingIdKeyword = String.format("%%%d%%", idKeyword);
      filterByKeywordQuery.where().or().caseId().isLike(containingIdKeyword);
    } catch (NumberFormatException e) {
      // do nothing
    }
    return filterByKeywordQuery;
  }

  private CaseQuery queryForCategory(String keyword) {
    if (keyword.equalsIgnoreCase(Ivy.cms().co(NO_CATEGORY_CMS))) {
      return CaseQuery.create().where().and().category().isEqual("");
    } else {
      String startingWithCategory = String.format("%s%%", keyword);
      return CaseQuery.create().where().category().isLike(startingWithCategory);
    }
  }

  private static final class CaseSortingQueryAppender {

    private CaseQuery query;

    public CaseSortingQueryAppender(CaseQuery query) {
      this.query = query;
    }

    public CaseQuery toQuery() {
      return query;
    }

    public CaseSortingQueryAppender appendSorting(CaseSearchCriteria criteria) {
      appendSortByNameIfSet(criteria);
      appendSortByIdIfSet(criteria);
      appendSortByStartTimeIfSet(criteria);
      appendSortByEndTimeIfSet(criteria);
      appendSortByCreatorIfSet(criteria);
      appendSortByStateIfSet(criteria);
      return this;
    }

    private void appendSortByNameIfSet(CaseSearchCriteria criteria) {
      if (!CaseSortField.NAME.toString().equalsIgnoreCase(criteria.getSortField())) {
        return;
      }
      OrderByColumnQuery orderByName = query.orderBy().name();
      if (criteria.isSortDescending()) {
        orderByName.descending();
      }
    }

    private void appendSortByIdIfSet(CaseSearchCriteria criteria) {
      if (criteria.getSortField() == null) {
        query.orderBy().caseId().descending();
        return;
      }
      if (!CaseSortField.ID.toString().equalsIgnoreCase(criteria.getSortField())) {
        return;
      }
      OrderByColumnQuery orderByName = query.orderBy().caseId();
      if (criteria.isSortDescending()) {
        orderByName.descending();
      }
    }

    private void appendSortByStartTimeIfSet(CaseSearchCriteria criteria) {
      if (!CaseSortField.CREATION_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
        return;
      }
      OrderByColumnQuery orderByName = query.orderBy().startTimestamp();
      if (criteria.isSortDescending()) {
        orderByName.descending();
      }
    }

    private void appendSortByEndTimeIfSet(CaseSearchCriteria criteria) {
      if (!CaseSortField.FINISHED_TIME.toString().equalsIgnoreCase(criteria.getSortField())) {
        return;
      }
      OrderByColumnQuery orderByName = query.orderBy().endTimestamp();
      if (criteria.isSortDescending()) {
        orderByName.descending();
      }
    }

    private void appendSortByCreatorIfSet(CaseSearchCriteria criteria) {
      if (!CaseSortField.CREATOR.toString().equalsIgnoreCase(criteria.getSortField())) {
        return;
      }
      OrderByColumnQuery orderByName = query.orderBy().creatorUserDisplayName();
      if (criteria.isSortDescending()) {
        orderByName.descending();
      }
    }

    private void appendSortByStateIfSet(CaseSearchCriteria criteria) {
      if (!CaseSortField.STATE.toString().equalsIgnoreCase(criteria.getSortField())) {
        return;
      }
      OrderByColumnQuery orderByName = query.orderBy().state();
      if (criteria.isSortDescending()) {
        orderByName.descending();
      }
    }
  }

  public List<String> getApps() {
    return apps;
  }

  public void setApps(List<String> apps) {
    this.apps = apps;
  }

  public List<CaseState> getIncludedStates() {
    return includedStates;
  }
  
  public void addIncludedStates(List<CaseState> includedStates) {
    if (CollectionUtils.isEmpty(includedStates)) {
      this.includedStates = new ArrayList<>();
    }
    this.includedStates.addAll(includedStates);
  }

  public void setIncludedStates(List<CaseState> includedStates) {
    this.includedStates = includedStates;
  }
  
  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
  }

  public boolean isBusinessCase() {
    return isBusinessCase;
  }

  public void setBusinessCase(boolean isBusinessCase) {
    this.isBusinessCase = isBusinessCase;
  }

  public boolean isTechnicalCase() {
    return isTechnicalCase;
  }

  public void setTechnicalCase(boolean isTechnicalCase) {
    this.isTechnicalCase = isTechnicalCase;
  }

  public Long getBusinessCaseId() {
    return businessCaseId;
  }

  public void setBusinessCaseId(Long businessCaseId) {
    this.businessCaseId = businessCaseId;
  }

  public boolean isAdminQuery() {
    return isAdminQuery;
  }

  public void setAdminQuery(boolean isAdminQuery) {
    this.isAdminQuery = isAdminQuery;
  }

  public CaseQuery getCustomCaseQuery() {
    return customCaseQuery;
  }

  public void setCustomCaseQuery(CaseQuery customCaseQuery) {
    this.customCaseQuery = customCaseQuery;
  }
  
  public boolean hasIncludedStates() {
    return CollectionUtils.isNotEmpty(includedStates);
  }

  public boolean hasApps() {
    return CollectionUtils.isNotEmpty(apps);
  }

  public boolean hasKeyword() {
    return StringUtils.isNotEmpty(keyword);
  }

  public boolean hasCaseId() {
    return caseId != null && caseId != 0;
  }

  public boolean hasCategory() {
    return StringUtils.isNotBlank(category);
  }
  
  public boolean isSorted() {
    return isSorted;
  }

  public void setSorted(boolean isSorted) {
    this.isSorted = isSorted;
  }

  public boolean isNewQueryCreated() {
    return isNewQueryCreated;
  }

  public void setNewQueryCreated(boolean isNewQueryCreated) {
    this.isNewQueryCreated = isNewQueryCreated;
  }
  
  public boolean hasInvolvedUsername() {
    return StringUtils.isNotBlank(involvedUsername);
  }

  public CaseQuery getFinalCaseQuery() {
    if (finalCaseQuery == null) {
      finalCaseQuery = createQuery();
    }
    return finalCaseQuery;
  }

  public void setFinalCaseQuery(CaseQuery finalCaseQuery) {
    this.finalCaseQuery = finalCaseQuery;
  }
  
}
