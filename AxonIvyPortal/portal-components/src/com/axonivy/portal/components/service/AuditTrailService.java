package com.axonivy.portal.components.service;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.components.dto.AuditTrailBundle;
import com.axonivy.portal.components.dto.AuditTrailDTO;
import com.axonivy.portal.components.factory.AuditTrailDTOFactory;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;

public class AuditTrailService {

  private static AuditTrailService instance;

  public static AuditTrailService getInstance() {
    if (instance == null) {
      instance = new AuditTrailService();
    }
    return instance;
  }

  public void save(String input) {
    try {
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

  public List<AuditTrailDTO> load() {
    try {
      AuditTrailBundle bundle = Ivy.repo().get(AuditTrailBundle.class);
      return bundle.getEntries();
    } catch (Exception e) {
      Ivy.log().error("Failed to load audit trail for case ", e);
      return new ArrayList<>();
    }
  }

  public List<AuditTrailDTO> load(ICase caze) {
    try {
      IBusinessCase businessCase = caze.getBusinessCase();
      if (businessCase.isPersistent()) {
        AuditTrailBundle bundle = Ivy.repo().find(businessCase, AuditTrailBundle.class);
        return bundle.getEntries();
      }
      return new ArrayList<>();
    } catch (Exception e) {
      Ivy.log().error("Failed to save audit trail ", e);
      return new ArrayList<>();
    }
  }

}
