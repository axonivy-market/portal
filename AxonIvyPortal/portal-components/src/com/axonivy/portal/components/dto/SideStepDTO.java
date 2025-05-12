package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * DTO for configuring Side Step process
 */
public class SideStepDTO implements Serializable {
  /**
  * 
  */
  private static final long serialVersionUID = 1L;
  private String signal;
  private String processName;
  private String caseId;
  private String taskId;
  private String originalTaskId;
  private Map<String, Object> params;

  public SideStepDTO() {
  }

  private SideStepDTO(Builder builder) {
    this.signal = builder.signal;
    this.caseId = builder.caseId;
    this.taskId = builder.taskId;
    this.processName = builder.processName;
    this.originalTaskId = builder.originalTaskId;
    this.params = builder.params;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getSignal() {
    return signal;
  }

  public String getCaseId() {
    return caseId;
  }

  public String getTaskId() {
    return taskId;
  }

  public String getOriginalTaskId() {
    return originalTaskId;
  }
  
  public void setOriginalTaskId(String originalTaskId) {
    this.originalTaskId = originalTaskId;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public String getProcessName() {
    return processName;
  }

  public static class Builder {
    private String signal;
    private String caseId;
    private String taskId;
    private String processName;
    private String originalTaskId;
    private Map<String, Object> params;

    public Builder processName(String processName) {
      this.processName = processName;
      return this;
    }

    public Builder signal(String signal) {
      this.signal = signal;
      return this;
    }

    public Builder caze(String caseId) {
      this.caseId = caseId;
      return this;
    }

    public Builder taskId(String taskId) {
      this.taskId = taskId;
      return this;
    }

    public Builder originalTaskId(String originalTaskId) {
      this.originalTaskId = originalTaskId;
      return this;
    }

    public Builder params(Map<String, Object> params) {
      this.params = params;
      return this;
    }

    public SideStepDTO build() {
      validate();
      return new SideStepDTO(this);
    }

    private void validate() {
      if (signal == null || signal.trim().isEmpty()) {
        throw new IllegalArgumentException("Signal must be provided");
      }
      if (taskId == null && caseId == null) {
        throw new IllegalArgumentException("TaskId or CaseId must be provided");
      }
    }
  }

}