package com.axonivy.portal.components.dto;

import ch.ivyteam.ivy.workflow.ICase;

public class BusinessDetailsDTO {
  private ICase iCase;
  private String URL;
  private boolean isFullPath;

  public BusinessDetailsDTO(ICase iCase, String uRL, boolean isFullPath) {
    this.iCase = iCase;
    this.URL = uRL;
    this.isFullPath = isFullPath;
  }

  public ICase getiCase() {
    return iCase;
  }

  public String getURL() {
    return URL;
  }

  public boolean isFullPath() {
    return isFullPath;
  }
}