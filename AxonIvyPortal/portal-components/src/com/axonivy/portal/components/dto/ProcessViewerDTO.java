package com.axonivy.portal.components.dto;

import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessViewerDTO {
  private IWebStartable webStartable;
  private WebLink webLink;
  private boolean isError;
  private String errorMessage;
  private String errorIcon;
  
  public static final class Builder{
    private ProcessViewerDTO processViewerDTO;
    private Builder() {
      processViewerDTO = new ProcessViewerDTO();
    }
    
    public Builder webStartable(IWebStartable webStartable) {
      processViewerDTO.webStartable = webStartable;
      return this;
    }
    
    public Builder webLink(WebLink webLink) {
      processViewerDTO.webLink = webLink;
      return this;
    }
    
    public Builder isError(boolean isError) {
      processViewerDTO.isError = isError;
      return this;
    }
    
    public Builder errorMessage(String errorMessage) {
      processViewerDTO.errorMessage = errorMessage;
      return this;
    }
    
    public Builder errorIcon(String errorIcon) {
      processViewerDTO.errorIcon = errorIcon;
      return this;
    }
    
    public ProcessViewerDTO build() {
      return processViewerDTO;
    }
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  public ProcessViewerDTO() {}
  
  public IWebStartable getWebStartable() {
    return webStartable;
  }

  public WebLink getWebLink() {
    return webLink;
  }

  public boolean isError() {
    return isError;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public String getErrorIcon() {
    return errorIcon;
  }
}