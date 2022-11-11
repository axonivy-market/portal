package com.axonivy.portal.components.dto;

import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessViewerDTO {
  private IWebStartable webStartable;
  private WebLink webLink;
  private boolean isError;
  private String errorMessage;

  public ProcessViewerDTO() {}

  public ProcessViewerDTO(IWebStartable webStartable, WebLink webLink, boolean isError, String errorMessage) {
    super();
    this.webStartable = webStartable;
    this.webLink = webLink;
    this.isError = isError;
    this.errorMessage = errorMessage;
  }

  public IWebStartable getWebStartable() {
    return webStartable;
  }

  public void setWebStartable(IWebStartable webStartable) {
    this.webStartable = webStartable;
  }

  public WebLink getWebLink() {
    return webLink;
  }

  public void setWebLink(WebLink webLink) {
    this.webLink = webLink;
  }

  public boolean isError() {
    return isError;
  }

  public void setError(boolean isError) {
    this.isError = isError;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
