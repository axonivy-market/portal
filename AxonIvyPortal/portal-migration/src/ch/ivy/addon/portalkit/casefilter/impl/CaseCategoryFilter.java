package ch.ivy.addon.portalkit.casefilter.impl;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseCategoryFilter extends CaseFilter {


  private List<String> categoryPaths = new ArrayList<>();

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/caseCategory");
  }

  @Override
  public String value() {
    return null;
  }

  @Override
  public void resetValues() {}
  
  @Override
  public CaseQuery buildQuery() {
    return null;
  }
  
  public List<String> getCategoryPaths() {
    return this.categoryPaths;
  }

  public void setCategoryPaths(List<String> categoryPaths) {
    this.categoryPaths = categoryPaths;
  }
}
