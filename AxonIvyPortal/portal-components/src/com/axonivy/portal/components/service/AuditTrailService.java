package com.axonivy.portal.components.service;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.components.dto.AuditTrailBundle;
import com.axonivy.portal.components.dto.AuditTrailDTO;
import com.axonivy.portal.components.factory.AuditTrailDTOFactory;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public class AuditTrailService {

  private static AuditTrailService instance;

  public static AuditTrailService getInstance() {
    if (instance == null) {
      instance = new AuditTrailService();
    }
    return instance;
  }

  public void save(ICase caze, String input) {
    try {
      if (!caze.getBusinessCase().isPersistent()) {
        return;
      }
      String author = Ivy.session().getSessionUserName();
      AuditTrailDTO newEntry = AuditTrailDTOFactory.build(author, input);
      AuditTrailBundle bundle = Ivy.repo().get(AuditTrailBundle.class);
      List<AuditTrailDTO> merged = new ArrayList<>(bundle.getEntries());
      merged.add(newEntry);
      merged.sort((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()));
      bundle.setEntries(merged);
      Ivy.repo().save(bundle);
    } catch (Exception e) {
      Ivy.log().error("Failed to save audit trail ", e);
    }
  }

  public List<AuditTrailDTO> load(ICase caze) {
    try {
      AuditTrailBundle bundle = Ivy.repo().get(AuditTrailBundle.class);
      return bundle == null ? new ArrayList<>() : new ArrayList<>(bundle.getEntries());
    } catch (Exception e) {
      Ivy.log().error("Failed to load audit trail for case " + caze.getId(), e);
      return new ArrayList<>();
    }
  }

}
