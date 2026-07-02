package com.axonivy.portal.components.dto;

import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.business.data.store.context.BusinessCaseData;

@BusinessCaseData
public class AuditTrailBundle {

  private List<AuditTrailDTO> entries = new ArrayList<>();

  public List<AuditTrailDTO> getEntries() {
    return entries;
  }

  public void setEntries(List<AuditTrailDTO> entries) {
    this.entries = entries;
  }
}
