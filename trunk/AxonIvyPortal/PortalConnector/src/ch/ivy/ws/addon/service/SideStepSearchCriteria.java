package ch.ivy.ws.addon.service;

import java.util.Objects;

public class SideStepSearchCriteria {

  private String involvedUsername;
  private Long caseId;
  private boolean isAdhocExcluded;

  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
  }

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }

  public boolean hasCaseId() {
    return caseId != null && caseId != 0;
  }

  public boolean hasInvolvedUsername() {
    return !(Objects.toString(involvedUsername, "").isEmpty());
  }

  public boolean isAdhocExcluded() {
    return isAdhocExcluded;
  }

  public void setAdhocExcluded(boolean isAdhocExcluded) {
    this.isAdhocExcluded = isAdhocExcluded;
  }


}
