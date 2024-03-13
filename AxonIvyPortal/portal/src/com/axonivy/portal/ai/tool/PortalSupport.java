package com.axonivy.portal.ai.tool;

import java.util.Arrays;

import ch.ivy.addon.portalkit.dto.ai.RetrievalQATool;

public class PortalSupport extends RetrievalQATool {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public void init() {
    setName("portal-support");
    setDescription("Helpful when user ask questions.");
    setCollection("portal-user-guide");
    setPermissions(Arrays.asList("Everybody"));
  }

}
