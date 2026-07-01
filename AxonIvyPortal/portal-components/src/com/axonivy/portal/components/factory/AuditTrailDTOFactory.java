package com.axonivy.portal.components.factory;

import java.util.Date;

import com.axonivy.portal.components.dto.AuditTrailDTO;

public class AuditTrailDTOFactory {

  private AuditTrailDTOFactory() {}

  public static AuditTrailDTO build(String author, String content) {
    return new AuditTrailDTO(new Date(), author, content);
  }

  public static AuditTrailDTO build(String author) {
    return build(author, null);
  }
  
}
