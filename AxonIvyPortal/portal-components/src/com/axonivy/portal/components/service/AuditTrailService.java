package com.axonivy.portal.components.service;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.components.dto.AuditTrailDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public class AuditTrailService {

  private static final String FIELD_NAME = "PORTAL_AUDIT_TRAIL";
  private static final String ENTRIES_KEY = "entries";
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static AuditTrailService instance;

  public static AuditTrailService getInstance() {
    if (instance == null) {
      instance = new AuditTrailService();
    }
    return instance;
  }

  public void save(ICase caze, List<AuditTrailDTO> entries) {
    try {
      entries.sort((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()));
      ObjectNode root = MAPPER.createObjectNode();
      root.set(ENTRIES_KEY, MAPPER.valueToTree(entries));
      caze.customFields().stringField(FIELD_NAME).set(MAPPER.writeValueAsString(root));
    } catch (Exception e) {
      Ivy.log().error("Failed to save audit trail for case " + caze.getId(), e);
    }
  }

  public List<AuditTrailDTO> load(ICase caze) {
    try {
      String json = caze.customFields().stringField(FIELD_NAME).get().orElse(null);
      if (json == null || json.isEmpty()) {
        return new ArrayList<>();
      }
      JsonNode entriesNode = MAPPER.readTree(json).path(ENTRIES_KEY);
      if (entriesNode.isMissingNode() || entriesNode.isNull()) {
        return new ArrayList<>();
      }
      CollectionType type = MAPPER.getTypeFactory().constructCollectionType(List.class, AuditTrailDTO.class);
      return MAPPER.convertValue(entriesNode, type);
    } catch (Exception e) {
      Ivy.log().error("Failed to load audit trail for case " + caze.getId(), e);
      return new ArrayList<>();
    }
  }
}
