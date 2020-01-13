package ch.ivy.addon.portalkit.ivydata.searchcriteria;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CaseCategorySearchCriteria {

  private String involvedUsername;
  private List<String> apps;
  private List<CaseState> includedStates;
  
  private CaseQuery customCaseQuery;
  
  public CaseQuery createQuery() {
    CaseQuery finalQuery = CaseUtils.createBusinessCaseQuery();
    if (customCaseQuery != null) {
      finalQuery = CaseQuery.fromJson(customCaseQuery.asJson()); // clone to keep the original custom query
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

  public List<String> getApps() {
    return apps;
  }

  public void setApps(List<String> apps) {
    this.apps = apps;
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

  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
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

  public boolean hasInvolvedUsername() {
    return StringUtils.isNotBlank(involvedUsername);
  }
  
}
