package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.List;

import com.axonivy.portal.components.ivydata.bo.JsonVersion;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepDTO implements Serializable {
  private static final long serialVersionUID = -1404169898838544342L;
  private String version;
  private List<SideStepProcessDTO> processes;
  private Boolean parallel;
  private String stepTypeParallelTitle;
  private String stepTypeSwitchTitle;

  public SideStepDTO() {}
  private SideStepDTO(Builder builder) {
    this.processes = builder.processes;
    this.parallel = builder.parallel;
    this.stepTypeParallelTitle = builder.stepTypeParallelTitle;
    this.stepTypeSwitchTitle = builder.stepTypeSwitchTitle;
    this.version = builder.version;
  }

  public static Builder builder() {
    return new Builder();
  }

  public List<SideStepProcessDTO> getProcesses() {
    return processes;
  }

  public Boolean getParallel() {
    return parallel;
  }

  public String getStepTypeParallelTitle() {
    return stepTypeParallelTitle;
  }

  public String getStepTypeSwitchTitle() {
    return stepTypeSwitchTitle;
  }

  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }

  public static class Builder {
    private List<SideStepProcessDTO> processes;
    private Boolean parallel;
    private String stepTypeParallelTitle;
    private String stepTypeSwitchTitle;
    private String version = JsonVersion.LATEST.getValue();

    public Builder processes(List<SideStepProcessDTO> processes) {
      this.processes = processes;
      return this;
    }

    public Builder parallel(Boolean parallel) {
      this.parallel = parallel;
      return this;
    }

    public Builder stepTypeParallelTitle(String stepTypeParallelTitle) {
      this.stepTypeParallelTitle = stepTypeParallelTitle;
      return this;
    }

    public Builder stepTypeSwitchTitle(String stepTypeSwitchTitle) {
      this.stepTypeSwitchTitle = stepTypeSwitchTitle;
      return this;
    }

    public SideStepDTO build() {
      return new SideStepDTO(this);
    }
  }

}