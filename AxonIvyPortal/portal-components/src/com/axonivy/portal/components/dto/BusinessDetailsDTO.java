package com.axonivy.portal.components.dto;

import java.util.Map;

import com.axonivy.portal.components.service.exception.PortalException;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Holds Business Detail Information.
 */
public class BusinessDetailsDTO {
  private ICase iCase;
  private String path;
  private boolean isEmbedInFrame;
  private Map<String, String> parameters;

  private BusinessDetailsDTO(ICase iCase, String path, boolean isEmbedInFrame, Map<String, String> parameters) {
    this.iCase = iCase;
    this.path = path;
    this.isEmbedInFrame = isEmbedInFrame;
    this.parameters = parameters;
  }

  /**
   * Gets the ICase
   * @return Icase
   */
  public ICase getCase() {
    return iCase;
  }

  /**
   * Gets the business details path
   * @return string path
   */
  public String getPath() {
    return path;
  }

  /**
   * Gets the embed in frame boolean
   * @return boolean isEmbedInFrame
   */
  public boolean isEmbedInFrame() {
    return isEmbedInFrame;
  }
  
  public Map<String, String> getParameters() {
    return parameters;
  }

  /**
   * Create new builder for BusinessDetailsDTO
   * @return Builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * {@code BusinessDetailsDTO.Builder} is used for creating a {@code BusinessDetailsDTO} from
   * ICase and path.
   */
  public static class Builder {
    private ICase iCase;
    private String path;
    private boolean isEmbedInFrame = Boolean.TRUE;
    private Map<String, String> parameters;

    /**
     * Set {@code ICase}
     * @param iCase
     *
     * @return Builder
     */
    public Builder iCase(ICase iCase) {
      this.iCase = iCase;
      return this;
    }

    /**
     * Set path
     * @param path
     *
     * @return Builder
     */
    public Builder path(String path) {
      this.path = path;
      return this;
    }
    
    /**
     * Set isEmbedInFrame
     * @param isEmbedInFrame
     *
     * @return Builder
     */
    public Builder isEmbedInFrame(boolean isEmbedInFrame) {
      this.isEmbedInFrame = isEmbedInFrame;
      return this;
    }
    
    /**
     * Set parameters
     * @param parameters
     *
     * @return Builder
     */
    public Builder parameters(Map<String, String> parameters) {  
      this.parameters = parameters;  
      return this;
    }
    
    /**
     * Build BusinessDetailsDTO
     *
     * @return BusinessDetailsDTO
     */
    public BusinessDetailsDTO build() {
      if (path == null) {
        throw new PortalException("Path cannot be null.");
      }
      if (iCase == null) {
        iCase = Ivy.wfCase();
      }
      
      if (parameters == null) {
        parameters = Map.of();
      }
      return new BusinessDetailsDTO(iCase, path, isEmbedInFrame, parameters);
    }

  }

}