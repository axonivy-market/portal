package ch.ivy.addon.portalkit.casefilter.impl;

import java.util.List;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseStateFilter extends CaseFilter {

  private List<CaseState> selectedFilteredStates;


  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/STATE");
  }

  @Override
  public String value() {
    return ALL;
  }

  @Override
  public CaseQuery buildQuery() {
    return null;
  }

  @Override
  public void validate() {

  }

  @Override
  public void resetValues() {}

  @Override
  public boolean defaultFilter() {
    return true;
  }

  @Override
  public boolean reloadView() {
    return true;
  }

  public String userFriendlyState(CaseState state) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/" + state);
  }

  public List<CaseState> getSelectedFilteredStates() {
    return selectedFilteredStates;
  }

  public void setSelectedFilteredStates(List<CaseState> selectedFilteredStates) {
    this.selectedFilteredStates = selectedFilteredStates;
  }
}
