package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.security.ISecurityContext;

/**
 * DTO object contains information about side step task process
 * <pre>
 * <b>signal</b>: list of side step task created processes for user to select 
 * <b>processName</b>: display name of the process which start side step task
 * <b>params</b>: parameters need to send to the process
 * <b>customSecurityMemberCallable</b>: name of Ivy callable subprocess to define custom list of users and roles which side step can be assigned to
 * </pre>
 * <p><b>Examples</b></p>
 * <pre>
 * SideStepProcessDTO.builder()
 * .signal("com:axonivy:portal:developerexample:sideStepProcess")
 * .params({@link Map} object which contains parameters)
 * .processName("My side step process display name")
 * .customSecurityMemberCallable("getCustomSecurityMemberSubProcessName()")
 * .build();
 * </pre>
 */
public class SideStepProcessDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private String signal;
  private Map<String, String> processNames;
  private Map<String, Object> params;
  private String customSecurityMemberCallable;

  public SideStepProcessDTO() {
  }

  private SideStepProcessDTO(Builder builder) {
    this.signal = builder.signal;
    this.params = builder.params;
    this.customSecurityMemberCallable = builder.customSecurityMemberCallable;
    this.processNames = builder.processNames;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getSignal() {
    return signal;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public String getCustomSecurityMemberCallable() {
    return customSecurityMemberCallable;
  }

  public Map<String, String> getProcessNames() {
    return processNames;
  }

  public static class Builder {
    private String signal;
    private String customSecurityMemberCallable;
    private Map<String, Object> params;
    private Map<String, String> processNames;

    /**
     * Name of Ivy callable subprocess to define custom list of users or roles which side step can be assigned to.
     * If it's not defined, side step task can assign to all users or roles of {@link ISecurityContext}
     * @param customSecurityMemberCallable
     * @return builder of {@link SideStepProcessDTO}
     */
    public Builder customSecurityMembersCallable(String customSecurityMemberCallable) {
      this.customSecurityMemberCallable = customSecurityMemberCallable;
      return this;
    }

    /**
     * Set signal value to trigger the process which start side step task
     * @param signal 
     * @return builder of {@link SideStepProcessDTO}
     */
    public Builder signal(String signal) {
      this.signal = signal;
      return this;
    }

    /**
     * Set parameters need to send to the process, some information like current task uuid, case uuid
     * @param params
     * @return builder of {@link SideStepProcessDTO}
     */
    public Builder params(Map<String, Object> params) {
      this.params = params;
      return this;
    }
    
    public Builder processNames(Map<String, String> processNames) {
      this.processNames = processNames;
      return this;
    }

    public SideStepProcessDTO build() {
      validate();
      return new SideStepProcessDTO(this);
    }

    private void validate() {
      if (StringUtils.isBlank(signal)) {
        throw new IllegalArgumentException("Signal must be provided");
      }
    }
  }

}
