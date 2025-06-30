package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO for configuring Side Step process
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepProcessDTO implements Serializable {
  /**
  * 
  */
  private static final long serialVersionUID = 1L;
  private String signal;
  private String processName;
  private String caseUuid;
  private String taskUuid;
  private String originalTaskUuid;
  private Map<String, Object> params;
  private String securityMembersCallable;

  public SideStepProcessDTO() {
  }

  private SideStepProcessDTO(Builder builder) {
    this.signal = builder.signal;
    this.caseUuid = builder.caseUuid;
    this.taskUuid = builder.taskUuid;
    this.processName = builder.processName;
    this.originalTaskUuid = builder.originalTaskUuid;
    this.params = builder.params;
    this.securityMembersCallable = builder.securityMembersCallable;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getSignal() {
    return signal;
  }

  public String getCaseUuid() {
    return caseUuid;
  }

  public String getTaskUuid() {
    return taskUuid;
  }

  public String getOriginalTaskUuid() {
    return originalTaskUuid;
  }

  public void setOriginalTaskUuid(String originalTaskUuid) {
    this.originalTaskUuid = originalTaskUuid;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public String getProcessName() {
    return processName;
  }

  public String getSecurityMembersCallable() {
    return securityMembersCallable;
  }

  public static class Builder {
    private String signal;
    private String caseUuid;
    private String taskUuid;
    private String processName;
    private String originalTaskUuid;
    private String securityMembersCallable;
    private Map<String, Object> params;

    public Builder securityMembersCallable(String securityMembersCallable) {
      this.securityMembersCallable = securityMembersCallable;
      return this;
    }

    public Builder processName(String processName) {
      this.processName = processName;
      return this;
    }

    public Builder signal(String signal) {
      this.signal = signal;
      return this;
    }

    public Builder caseUuid(String caseUuid) {
      this.caseUuid = caseUuid;
      return this;
    }

    public Builder taskUuid(String taskUuid) {
      this.taskUuid = taskUuid;
      return this;
    }

    public Builder originalTaskUuid(String originalTaskUuid) {
      this.originalTaskUuid = originalTaskUuid;
      return this;
    }

    public Builder params(Map<String, Object> params) {
      this.params = params;
      return this;
    }

    public SideStepProcessDTO build() {
      validate();
      return new SideStepProcessDTO(this);
    }

    private void validate() {
      if (signal == null || signal.trim().isEmpty()) {
        throw new IllegalArgumentException("Signal must be provided");
      }
      if (taskUuid == null && caseUuid == null) {
        throw new IllegalArgumentException("Task uuid or Case uuid must be provided");
      }
    }
  }

}
