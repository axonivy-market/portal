package ch.ivy.addon.portalkit.casefilter;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.CaseFilterComparator;

/**
 * @deprecated use TaskAnalysisCaseFilterContainer in package ch.ivy.addon.portalkit.casefilter.impl
 *
 */
@Deprecated(since="8.0.18")
public class TaskAnalysisCaseFilterContainer extends DefaultCaseFilterContainer {

  private CaseNameFilter caseNameFilter = new CaseNameFilter();

  public TaskAnalysisCaseFilterContainer() {
    super();
    filters.add(caseNameFilter);
    Collections.sort(filters, new CaseFilterComparator());
  }

  public CaseNameFilter getCaseNameFilter() {
    return caseNameFilter;
  }

  public void setCaseNameFilter(CaseNameFilter caseNameFilter) {
    this.caseNameFilter = caseNameFilter;
  }
}
