package ch.ivy.addon.portalkit.casefilter.impl;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseOwnerFilter extends CaseFilter {
  private String selectedOwnerMemberName;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/OWNER");
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
  public void resetValues() {}

  public String getSelectedOwnerMemberName() {
    return selectedOwnerMemberName;
  }

  public void setSelectedOwnerMemberName(String selectedOwnerMemberName) {
    this.selectedOwnerMemberName = selectedOwnerMemberName;
  }

}
