package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
  private Map<String, Object> params;
  private String securityMembersCallable;

  public SideStepProcessDTO() {
  }

  private SideStepProcessDTO(Builder builder) {
    this.signal = builder.signal;
    this.processName = builder.processName;
    this.params = builder.params;
    this.securityMembersCallable = builder.securityMembersCallable;
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

  public String getProcessName() {
    return processName;
  }

  public String getSecurityMembersCallable() {
    return securityMembersCallable;
  }

  public static class Builder {
    private String signal;
    private String processName;
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

    public Builder params(Map<String, Object> params) {
      this.params = params;
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
