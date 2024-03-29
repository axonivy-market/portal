package com.axonivy.portal.components.ivydata.dto;

import java.util.List;

import ch.ivyteam.ivy.workflow.ICase;

public class IvyCaseResultDTO {

  private List<ICase> cases;
  private long totalCases;
  private List<String> customFields;

  public List<ICase> getCases() {
    return cases;
  }

  public void setCases(List<ICase> cases) {
    this.cases = cases;
  }

  public long getTotalCases() {
    return totalCases;
  }

  public void setTotalCases(long totalCases) {
    this.totalCases = totalCases;
  }

  public List<String> getCustomFields() {
    return customFields;
  }

  public void setCustomFields(List<String> customFields) {
    this.customFields = customFields;
  }
}
