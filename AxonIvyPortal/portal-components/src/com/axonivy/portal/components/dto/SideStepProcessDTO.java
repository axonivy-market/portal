package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

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
 * .processNames(Map.ofEntries(entry("en", "name in English"),entry("de", "name in German")))
 * .customSecurityMemberCallable("getCustomSecurityMemberSubProcessName()")
 * .build();
 * </pre>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepProcessDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private String signal;
  private Map<String, String> processNames;
  private String customSecurityMemberCallable;

  public SideStepProcessDTO() {
  }

  private SideStepProcessDTO(Builder builder) {
    this.signal = builder.signal;
    this.customSecurityMemberCallable = builder.customSecurityMemberCallable;
    this.processNames = builder.processNames;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getSignal() {
    return signal;
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
    private Map<String, String> processNames;

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
     * Set a map of multiple locale - title values to support for multiple languages of process name.
     * This is mandatory
     * @param processNames A Map where keys are String to store locale value (e.g., "de", "en")
     * and values are also String to store title value (e.g., "My process name").
     * This map contains various multiple language values for custom title.
     * @return builder of {@link SideStepProcessDTO}
     */
    public Builder processNames(Map<String, String> processNames) {
      this.processNames = processNames;
      return this;
    }

    /**
     * Build a new instance.
     * @return new instance of {@link SideStepProcessDTO}
     */
    public SideStepProcessDTO build() {
      validate();
      return new SideStepProcessDTO(this);
    }

    private void validate() {
      if (StringUtils.isBlank(signal)) {
        throw new IllegalArgumentException("Signal must be provided");
      }
      if (MapUtils.isEmpty(processNames)) {
        throw new IllegalArgumentException("Process name must be provided");
      }
    }
  }

}
