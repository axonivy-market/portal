package ch.internalsupport;

import java.util.Collections;

import ch.ivy.addon.portalkit.casefilter.CaseDescriptionFilter;
import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.comparator.CaseFilterComparator;

public class CustomizedCaseFilterContainer extends CaseFilterContainer{

  private CaseDescriptionFilter caseDescriptionFilter = new CaseDescriptionFilter();
  
  public CustomizedCaseFilterContainer() {
    super();
    filters.add(caseDescriptionFilter);
    Collections.sort(filters, new CaseFilterComparator());
  }

  public CaseDescriptionFilter getCaseDescriptionFilter() {
    return caseDescriptionFilter;
  }

  public void setCaseDescriptionFilter(CaseDescriptionFilter caseDescriptionFilter) {
    this.caseDescriptionFilter = caseDescriptionFilter;
  }

}
