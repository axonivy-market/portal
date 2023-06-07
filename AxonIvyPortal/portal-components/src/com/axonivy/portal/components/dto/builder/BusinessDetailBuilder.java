package com.axonivy.portal.components.dto.builder;

import com.axonivy.portal.components.dto.BusinessDetailDTO;

import ch.ivyteam.ivy.workflow.ICase;

public class BusinessDetailBuilder {

  private ICase iCase;
  private String URL;
  private boolean isFullPath;

  public BusinessDetailBuilder iCase(ICase iCase) {
    this.iCase = iCase;
    return this;
  }

  public BusinessDetailBuilder URL(String URL) {
    this.URL = URL;
    return this;
  }

  public BusinessDetailBuilder isFullPath(boolean isFullPath) {
    this.isFullPath = isFullPath;
    return this;
  }

  public BusinessDetailDTO build() {
    return new BusinessDetailDTO(iCase, URL, isFullPath);
  }
}
