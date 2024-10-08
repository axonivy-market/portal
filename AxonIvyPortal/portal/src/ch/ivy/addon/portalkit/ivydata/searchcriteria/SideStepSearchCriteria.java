package ch.ivy.addon.portalkit.ivydata.searchcriteria;

public class SideStepSearchCriteria {

  private Long caseId;

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }

  public boolean hasCaseId() {
    return caseId != null && caseId != 0;
  }
}
