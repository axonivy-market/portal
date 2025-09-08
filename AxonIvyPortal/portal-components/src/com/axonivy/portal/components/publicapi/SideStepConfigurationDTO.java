package com.axonivy.portal.components.publicapi;

import java.io.Serializable;
import java.util.List;

import com.axonivy.portal.components.ivydata.bo.JsonVersion;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * DTO object contains information about side step configuration
 * Use this class to configure side step then parse it to JSON and save to custom text field of {@link ITask} or {@link ICase}
 * <pre>
 * <b>processes</b>: list of side step task created processes for user to select 
 * <b>isParallelSideStep</b>: whether side step task runs parallel with original task
 * <b>customParallelSideStepTitle</b>: custom parallel side step title (optional)
 * <b>customSwitchSideStepTitle</b>: custom switch side step title (optional)
 * </pre>
 * <p><b>Examples:</b></p>
 * <pre>
 * SideStepConfigurationDTO.builder()
 * .processes({@link List} of {@link SideStepProcessDTO})
 * .customSwitchSideStepTitle("Custom switch text")
 * .customParallelSideStepTitle("Custom parallel text")
 * .isParallelSideStep(true)
 * .build();
 * </pre> 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepConfigurationDTO implements Serializable {
  private static final long serialVersionUID = -1404169898838544342L;
  private String version;
  private List<SideStepProcessDTO> processes;
  private Boolean isParallelSideStep;
  private String customParallelSideStepTitle;
  private String customSwitchSideStepTitle;

  public SideStepConfigurationDTO() {}
  
  private SideStepConfigurationDTO(Builder builder) {
    this.processes = builder.processes;
    this.isParallelSideStep = builder.isParallelSideStep;
    this.customParallelSideStepTitle = builder.customParallelSideStepTitle;
    this.customSwitchSideStepTitle = builder.customSwitchSideStepTitle;
    this.version = builder.version;
  }

  public static Builder builder() {
    return new Builder();
  }

  public List<SideStepProcessDTO> getProcesses() {
    return processes;
  }

  public String getCustomParallelSideStepTitle() {
    return customParallelSideStepTitle;
  }

  public String getCustomSwitchSideStepTitle() {
    return customSwitchSideStepTitle;
  }

  public String getVersion() {
    return version;
  }

  public Boolean getIsParallelSideStep() {
    return isParallelSideStep;
  }

  public static class Builder {
    private List<SideStepProcessDTO> processes;
    private Boolean isParallelSideStep;
    private String customParallelSideStepTitle;
    private String customSwitchSideStepTitle;
    private String version = JsonVersion.LATEST.getValue();

    /**
     * Set list of processes for side step
     * @param processes 
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder processes(List<SideStepProcessDTO> processes) {
      this.processes = processes;
      return this;
    }

    /**
     * Set true if this side step runs parallel with current task
     * @param isParallelSideStep
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder isParallelSideStep(Boolean isParallelSideStep) {
      this.isParallelSideStep = isParallelSideStep;
      return this;
    }

    /**
     * Optional function to set custom parallel side step title
     * @param customParallelSideStepTitle 
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder customParallelSideStepTitle(String customParallelSideStepTitle) {
      this.customParallelSideStepTitle = customParallelSideStepTitle;
      return this;
    }

    /**
     * Optional function to set custom switch side step title
     * @param customSwitchSideStepTitle
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder customSwitchSideStepTitle(String customSwitchSideStepTitle) {
      this.customSwitchSideStepTitle = customSwitchSideStepTitle;
      return this;
    }

    public SideStepConfigurationDTO build() {
      return new SideStepConfigurationDTO(this);
    }
  }

}