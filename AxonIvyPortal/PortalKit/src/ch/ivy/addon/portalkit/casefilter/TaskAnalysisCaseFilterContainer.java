package ch.ivy.addon.portalkit.casefilter;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.CaseFilterComparator;

public class TaskAnalysisCaseFilterContainer extends DefaultCaseFilterContainer {

  private CaseNameFilter caseNameFilter = new CaseNameFilter();
  private CaseCategoryFilter caseCategoryFilter = new CaseCategoryFilter();

  public TaskAnalysisCaseFilterContainer() {
    super();
    filters.add(caseNameFilter);
    filters.add(caseCategoryFilter);
    Collections.sort(filters, new CaseFilterComparator());
  }

  public CaseNameFilter getCaseNameFilter() {
    return caseNameFilter;
  }

  public void setCaseNameFilter(CaseNameFilter caseNameFilter) {
    this.caseNameFilter = caseNameFilter;
  }

  public CaseCategoryFilter getCaseCategoryFilter() {
    return caseCategoryFilter;
  }

  public void setCaseCategoryFilter(CaseCategoryFilter caseCategoryFilter) {
    this.caseCategoryFilter = caseCategoryFilter;
  }
}
