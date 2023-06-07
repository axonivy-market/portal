package com.axonivy.portal.components.dto.builder;

import com.axonivy.portal.components.dto.BusinessDetailsDTO;

import ch.ivyteam.ivy.workflow.ICase;

public class BusinessDetailsBuilder {

  private ICase iCase;
  private String URL;
  private boolean isFullPath;

  public BusinessDetailsBuilder iCase(ICase iCase) {
    this.iCase = iCase;
    return this;
  }

  public BusinessDetailsBuilder URL(String URL) {
    this.URL = URL;
    return this;
  }

  public BusinessDetailsBuilder isFullPath(boolean isFullPath) {
    this.isFullPath = isFullPath;
    return this;
  }

  public BusinessDetailsDTO build() {
    return new BusinessDetailsDTO(iCase, URL, isFullPath);
  }
}
