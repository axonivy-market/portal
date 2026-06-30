package com.axonivy.portal.components.factory;

import java.util.Date;

import com.axonivy.portal.components.dto.AuditTrailDTO;

public class AuditTrailDTOFactory {

  private AuditTrailDTOFactory() {}

  public static AuditTrailDTO build(Long taskId, String author, String action, String content) {
    return new AuditTrailDTO(taskId, new Date(), author, action, content);
  }

  public static AuditTrailDTO build(Long taskId, String author, String action) {
    return build(taskId, author, action, null);
  }
}
