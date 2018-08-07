package ch.ivy.addon.portalkit.casefilter;

import java.util.Collections;

import ch.ivy.addon.portalkit.comparator.CaseFilterComparator;

public class DefaultCaseFilterContainer extends CaseFilterContainer {

  private CaseCreationDateFilter caseCreationDateFilter = new CaseCreationDateFilter();
  private CaseCreatorFilter caseCreatorFilter = new CaseCreatorFilter();
  private CaseDescriptionFilter caseDescriptionFilter = new CaseDescriptionFilter();
  private CaseFinishedDateFilter caseFinishedDateFilter = new CaseFinishedDateFilter();

  public DefaultCaseFilterContainer() {
    super();
    filters.add(caseCreationDateFilter);
    filters.add(caseCreatorFilter);
    filters.add(caseDescriptionFilter);
    filters.add(caseFinishedDateFilter);
    Collections.sort(filters, new CaseFilterComparator());
  }

  public CaseCreationDateFilter getCaseCreationDateFilter() {
    return caseCreationDateFilter;
  }

  public void setCaseCreationDateFilter(CaseCreationDateFilter caseCreationDateFilter) {
    this.caseCreationDateFilter = caseCreationDateFilter;
  }

  public CaseCreatorFilter getCaseCreatorFilter() {
    return caseCreatorFilter;
  }

  public void setCaseCreatorFilter(CaseCreatorFilter caseCreatorFilter) {
    this.caseCreatorFilter = caseCreatorFilter;
  }

  public CaseDescriptionFilter getCaseDescriptionFilter() {
    return caseDescriptionFilter;
  }

  public void setCaseDescriptionFilter(CaseDescriptionFilter caseDescriptionFilter) {
    this.caseDescriptionFilter = caseDescriptionFilter;
  }

  public CaseFinishedDateFilter getCaseFinishedDateFilter() {
    return caseFinishedDateFilter;
  }

  public void setCaseFinishedDateFilter(CaseFinishedDateFilter caseFinishedDateFilter) {
    this.caseFinishedDateFilter = caseFinishedDateFilter;
  }


}
