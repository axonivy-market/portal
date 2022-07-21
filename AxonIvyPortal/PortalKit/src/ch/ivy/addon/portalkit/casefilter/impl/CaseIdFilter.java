package ch.ivy.addon.portalkit.casefilter.impl;

import java.util.Objects;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseIdFilter extends CaseFilter {

  private Long caseId;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/ID");
  }

  @Override
  public String value() {
    if (caseId == null || caseId.equals(0l)) {
      caseId = null;
      return getAllLabel();
    }
    return String.valueOf(caseId);
  }

  @Override
  public CaseQuery buildQuery() {
    if (Objects.isNull(caseId)) {
      return null;
    }

    String containingKeyword = String.format("%%%d%%", caseId);
    return CaseQuery.create().where().caseId().isLike(containingKeyword);
  }

  @Override
  public void resetValues() {
    caseId = null;
  }

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }
}
