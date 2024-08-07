package ch.ivy.addon.portalkit.casefilter.impl;

import java.util.Collections;

import ch.ivy.addon.portalkit.casefilter.CaseFilterContainer;
import ch.ivy.addon.portalkit.comparator.CaseFilterComparator;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

public class DefaultCaseFilterContainer extends CaseFilterContainer {

  protected CaseIdFilter idFilter = new CaseIdFilter();
  protected CaseNameFilter nameFilter = new CaseNameFilter();
  protected CaseCreationDateFilter caseCreationDateFilter = new CaseCreationDateFilter();
  protected CaseCreatorFilter caseCreatorFilter = new CaseCreatorFilter();
  protected CaseOwnerFilter caseOwnerFilter = new CaseOwnerFilter();
  protected CaseDescriptionFilter caseDescriptionFilter = new CaseDescriptionFilter();
  protected CaseFinishedDateFilter caseFinishedDateFilter = new CaseFinishedDateFilter();
  protected CaseCategoryFilter caseCategoryFilter = new CaseCategoryFilter();
  protected CaseApplicationFilter applicationFilter = new CaseApplicationFilter();


  public DefaultCaseFilterContainer() {
    super();
    filters.add(idFilter);
    filters.add(nameFilter);
    filters.add(caseCreationDateFilter);
    filters.add(caseCreatorFilter);
    if (new GlobalSettingService().isCaseOwnerEnabled()) {
      filters.add(caseOwnerFilter);
    }
    filters.add(caseDescriptionFilter);
    filters.add(caseFinishedDateFilter);
    filters.add(caseCategoryFilter);
    filters.add(applicationFilter);
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
  
  public CaseOwnerFilter getCaseOwnerFilter() {
    return caseOwnerFilter;
  }
  
  public void setCaseOwnerFilter(CaseOwnerFilter caseOwnerFilter) {
    this.caseOwnerFilter = caseOwnerFilter;
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

  public CaseCategoryFilter getCaseCategoryFilter() {
    return caseCategoryFilter;
  }

  public void setCaseCategoryFilter(CaseCategoryFilter caseCategoryFilter) {
    this.caseCategoryFilter = caseCategoryFilter;
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
  
  public CaseApplicationFilter getApplicationFilter() {
    return applicationFilter;
  }
  
  public void setApplicationFilter(CaseApplicationFilter applicationFilter) {
    this.applicationFilter = applicationFilter;
  }
}
