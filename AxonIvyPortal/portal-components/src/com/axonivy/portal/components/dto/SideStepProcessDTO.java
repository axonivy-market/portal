package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.DisplayNameUtils;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.security.ISecurityContext;

/**
 * DTO object contains information about side step task process.
 * <pre>
 * <b>signal</b>: Ivy signal to trigger when start side step (mandatory). 
 * <b>processNames</b>: display name of the process which start side step task (mandatory).
 * <b>customSecurityMemberCallable</b>: name of Ivy callable subprocess to define custom list of users and roles which side step can be assigned to (optional).
 * </pre>
 * <p><b>Examples</b></p>
 * <pre>
 * SideStepProcessDTO.builder()
 * .signal("com:axonivy:portal:developerexample:sideStepProcess")
 * .processNameCmsPath("/Processes/SideStep/AskForDetails")
 * .customSecurityMemberCallable("getCustomSecurityMemberSubProcessName()")
 * .build();
 * </pre>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepProcessDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private String signal;
  private List<DisplayNameDTO> processNames;
  private String customSecurityMemberCallable;
  
  public SideStepProcessDTO() {}

  private SideStepProcessDTO(Builder builder) {
    this.signal = builder.signal;
    this.processNames = builder.processNames;
    this.customSecurityMemberCallable = builder.customSecurityMemberCallable;
  }

  /**
   * Builder to build an instance of {@link SideStepProcessDTO}
   * @return {@link Builder}
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * @return Ivy signal.
   */
  public String getSignal() {
    return signal;
  }

  /**
   * @return list of display names with locale support for the process.
   */
  public List<DisplayNameDTO> getProcessNames() {
    return processNames;
  }

  /**
   * @return name of Ivy callable subprocess to define custom list of users and roles.
   */
  public String getCustomSecurityMemberCallable() {
    return customSecurityMemberCallable;
  }

  public static class Builder {
    private String signal;
    private String processNameCmsPath;
    private String cmsProjectName;
    private List<DisplayNameDTO> processNames;
    private String customSecurityMemberCallable;

    /**
     * Set name of Ivy callable subprocess to define custom list of users or roles which side step can be assigned to.
     * This is optional. If it's not defined, side step task can assign to all users or roles of {@link ISecurityContext}
     * @param customSecurityMemberCallable
     * @return builder of {@link SideStepProcessDTO}
     */
    public Builder customSecurityMembersCallable(String customSecurityMemberCallable) {
      this.customSecurityMemberCallable = customSecurityMemberCallable;
      return this;
    }

    /**
     * Set signal value to trigger the process which start side step task.
     * This is mandatory.
     * @param signal 
     * @return builder of {@link SideStepProcessDTO}
     */
    public Builder signal(String signal) {
      this.signal = signal;
      return this;
    }

    /**
     * Set CMS path for process name. This is mandatory.
     * @param processNameCmsPath CMS path (e.g., "/Processes/SideStep/ProcessName")
     * @return builder of {@link SideStepProcessDTO}
     */
    public Builder processNameCmsPath(String processNameCmsPath) {
      this.processNameCmsPath = processNameCmsPath;
      return this;
    }


    /**
     * Set the project name to locate CMS paths. Optional - if not provided, CMS paths will be resolved from current
     * project context.
     * 
     * @param cmsProjectName project name
     * @return builder of {@link SideStepProcessDTO}
     */
    public Builder cmsProjectName(String cmsProjectName) {
      this.cmsProjectName = cmsProjectName;
      return this;
    }

    /**
     * Build a new instance.
     * @return new instance of {@link SideStepProcessDTO}
     */
    public SideStepProcessDTO build() {
      validate();
      this.processNames = DisplayNameUtils.createCmsDisplayName(processNameCmsPath, cmsProjectName);
      return new SideStepProcessDTO(this);
    }

    private void validate() {
      if (StringUtils.isBlank(signal)) {
        throw new IllegalArgumentException("Signal must be provided");
      }
      if (StringUtils.isBlank(processNameCmsPath)) {
        throw new IllegalArgumentException("Process name CMS path must be provided");
      }
    }
  }

}
