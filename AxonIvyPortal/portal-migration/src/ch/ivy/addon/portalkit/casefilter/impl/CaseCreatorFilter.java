package ch.ivy.addon.portalkit.casefilter.impl;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseCreatorFilter extends CaseFilter {
  private String selectedCreatorMemberName;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATOR");
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
  public void resetValues() {
   
    selectedCreatorMemberName = null;
  }

  public String getSelectedCreatorMemberName() {
    return selectedCreatorMemberName;
  }

}
