package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepDTO implements Serializable {
  private static final long serialVersionUID = -1404169898838544342L;

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
  
  public static class Builder {
    private List<SideStepProcessDTO> processes;
    private Boolean parallel;
    private String stepTypeParallelTitle;
    private String stepTypeSwitchTitle;
    
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
