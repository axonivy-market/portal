package ch.internalsupport;

import java.util.Collections;

import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.casefilter.impl.CaseDescriptionFilter;
import ch.ivy.addon.portalkit.casefilter.impl.CaseIdFilter;
import ch.ivy.addon.portalkit.casefilter.impl.CaseNameFilter;
import ch.ivy.addon.portalkit.comparator.CaseFilterComparator;

public class CustomizedCaseFilterContainer extends CaseFilterContainer{

  protected CaseIdFilter idFilter = new CaseIdFilter();
  protected CaseNameFilter nameFilter = new CaseNameFilter();
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

  public CaseIdFilter getIdFilter() {
    return idFilter;
  }

  public void setIdFilter(CaseIdFilter idFilter) {
    this.idFilter = idFilter;
  }

  public CaseNameFilter getNameFilter() {
    return nameFilter;
  }

  public void setNameFilter(CaseNameFilter nameFilter) {
    this.nameFilter = nameFilter;
  }

}
