package com.axonivy.portal.components.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axonivy.portal.components.dto.AuditTrailBundle;
import com.axonivy.portal.components.dto.AuditTrailDTO;
import com.axonivy.portal.components.enums.CustomSignature;

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

  public void save(ICase caze, List<AuditTrailDTO> entries) {
    try {
      // IBusinessCase businessCase = caze.getBusinessCase();
      AuditTrailBundle bundle = Ivy.repo().get(AuditTrailBundle.class);
      if (bundle == null) {
        bundle = new AuditTrailBundle();
      }
      List<AuditTrailDTO> merged = new ArrayList<>(bundle.getEntries());
      merged.addAll(entries);
      merged.sort((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()));
      bundle.setEntries(merged);
      Ivy.repo().save(bundle);
    } catch (Exception e) {
      Ivy.log().error("Failed to save audit trail for case " + caze.getId(), e);
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

  @SuppressWarnings("unchecked")
  public List<AuditTrailDTO> getAuditTrailList(ICase businessCase) {
    Map<String, Object> params = new HashMap<>();
    params.put("businessCase", businessCase);
    Map<String, Object> result = IvyAdapterService
        .startSubProcessInSecurityContext(CustomSignature.GET_AUDIT_TRAIL_DATA.getSignature(), params);
    if (result == null || result.isEmpty()) {
      return new ArrayList<>();
    }
    List<AuditTrailDTO> list = (List<AuditTrailDTO>) result.get("auditTrailDTOs");
    return list != null ? list : new ArrayList<>();
  }

  @SuppressWarnings("unchecked")
  public List<AuditTrailDTO> saveAdditionalInformation(ICase businessCase, List<AuditTrailDTO> auditTrailDTOs) {
    Map<String, Object> params = new HashMap<>();
    params.put("businessCase", businessCase);
    params.put("auditTrailDTOs", auditTrailDTOs);
    Map<String, Object> result = IvyAdapterService
        .startSubProcessInSecurityContext(CustomSignature.SAVE_ADDITIONAL_AUDIT_TRAIL_DATA.getSignature(), params);
    if (result == null || result.isEmpty()) {
      return new ArrayList<>();
    }
    List<AuditTrailDTO> list = (List<AuditTrailDTO>) result.get("auditTrailDTOs");
    return list != null ? list : new ArrayList<>();
  }
}
