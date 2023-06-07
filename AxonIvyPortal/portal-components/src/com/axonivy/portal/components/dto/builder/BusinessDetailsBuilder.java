package com.axonivy.portal.components.dto.builder;

import com.axonivy.portal.components.dto.BusinessDetailsDTO;

import ch.ivyteam.ivy.workflow.ICase;

/**
 * Builder for Business Case Detail
 *
 */
public class BusinessDetailsBuilder {

  private ICase iCase;
  private String URL;
  private boolean isFullPath;

  /**
   * Set ICase
   * @param iCase 
   *
   * @return BusinessDetailsBuilder
   */
  public BusinessDetailsBuilder iCase(ICase iCase) {
    this.iCase = iCase;
    return this;
  }

  /**
   * Set URL
   * @param URL 
   *
   * @return BusinessDetailsBuilder
   */
  public BusinessDetailsBuilder URL(String URL) {
    this.URL = URL;
    return this;
  }

  /**
   * Set isFullPath
   * @param isFullPath 
   *
   * @return BusinessDetailsBuilder
   */
  public BusinessDetailsBuilder isFullPath(boolean isFullPath) {
    this.isFullPath = isFullPath;
    return this;
  }

  /**
   * Build BusinessDetailsDTO
   *
   * @return BusinessDetailsDTO
   */
  public BusinessDetailsDTO build() {
    return new BusinessDetailsDTO(iCase, URL, isFullPath);
  }
}
