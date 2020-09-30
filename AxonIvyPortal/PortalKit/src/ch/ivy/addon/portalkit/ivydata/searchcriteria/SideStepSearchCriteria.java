package ch.ivy.addon.portalkit.ivydata.searchcriteria;

public class SideStepSearchCriteria {

  private Long caseId;
  private boolean isAdhocExcluded;

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }

  public boolean hasCaseId() {
    return caseId != null && caseId != 0;
  }

  public boolean isAdhocExcluded() {
    return isAdhocExcluded;
  }

  public void setAdhocExcluded(boolean isAdhocExcluded) {
    this.isAdhocExcluded = isAdhocExcluded;
  }
}
