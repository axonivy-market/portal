package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseCategorySearchCriteria {

  private boolean isAdminQuery;
  private List<CaseState> includedStates;
  
  private CaseQuery customCaseQuery;
  
  public CaseQuery createQuery() {
    CaseQuery finalQuery = CaseUtils.createBusinessCaseQuery();
    if (customCaseQuery != null) {
      finalQuery.where().andOverall(customCaseQuery);
    }

    if (hasIncludedStates()) {
      finalQuery.where().and(queryForStates(getIncludedStates()));
    }

    return finalQuery;
  }
  
  private CaseQuery queryForStates(List<CaseState> states) {
    CaseQuery stateFieldQuery = CaseUtils.createBusinessCaseQuery();
    IFilterQuery filterQuery = stateFieldQuery.where();
    for (CaseState state : states) {
      filterQuery.or().state().isEqual(state);
    }
    return stateFieldQuery;
  }

  public List<CaseState> getIncludedStates() {
    return includedStates;
  }

  public void setIncludedStates(List<CaseState> includedStates) {
    this.includedStates = includedStates;
  }
  
  public void addIncludedStates(List<CaseState> includedStates) {
    if (CollectionUtils.isEmpty(includedStates)) {
      this.includedStates = new ArrayList<>();
    }
    this.includedStates.addAll(includedStates);
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

  public boolean isAdminQuery() {
    return isAdminQuery;
  }

  public void setAdminQuery(boolean isAdminQuery) {
    this.isAdminQuery = isAdminQuery;
  }

}
