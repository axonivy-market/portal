package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.components.ivydata.bo.JsonVersion;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * DTO object contains information about side step configuration.
 * Use this class to configure side step then parse it to JSON and save to custom text field of {@link ITask} or {@link ICase}
 * <pre>
 * <b>processes</b>: list of side step processes for user to select (mandatory). 
 * <b>isParallelSideStep</b>: whether side step task runs parallel with original task (optional).
 * <b>customParallelSideStepTitles</b>: custom parallel side step title (optional)
 * <b>customSwitchSideStepTitles</b>: custom switch side step title (optional)
 * </pre>
 * <p><b>Examples:</b></p>
 * <pre>
 * SideStepConfigurationDTO.builder()
 * .processes({@link List} of {@link SideStepProcessDTO})
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
  private Map<String, String> customParallelSideStepTitles;
  private Map<String, String> customSwitchSideStepTitles;
  
  public SideStepConfigurationDTO() {}

  private SideStepConfigurationDTO(Builder builder) {
    this.version = builder.version;
    this.processes = builder.processes;
    this.isParallelSideStep = builder.isParallelSideStep;
    this.customParallelSideStepTitles = builder.customParallelSideStepTitles;
    this.customSwitchSideStepTitles = builder.customSwitchSideStepTitles;
  }

  /**
   * Builder to build an instance of {@link SideStepConfigurationDTO}
   * @return {@link Builder}
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * @return list of side step processes for user to select.
   */
  public List<SideStepProcessDTO> getProcesses() {
    return processes;
  }

  /**
   * @return true: parallel side step, false: switch side step. 
   */
  public Boolean getIsParallelSideStep() {
    return isParallelSideStep;
  }

  /**
   * @return custom title for parallel side step.
   */
  public Map<String, String> getCustomParallelSideStepTitles() {
    return customParallelSideStepTitles;
  }

  /**
   * @return custom title for switch side step.
   */
  public Map<String, String> getCustomSwitchSideStepTitles() {
    return customSwitchSideStepTitles;
  }

  /**
   * @return version of JSON file.
   */
  public String getVersion() {
    return version;
  }

  public static class Builder {
    private List<SideStepProcessDTO> processes;
    private Boolean isParallelSideStep;
    private Map<String, String> customParallelSideStepTitles;
    private Map<String, String> customSwitchSideStepTitles;
    private String version = JsonVersion.LATEST.getValue();

    /**
     * Set list of processes for side step. This is mandatory.
     * @param processes 
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder processes(List<SideStepProcessDTO> processes) {
      this.processes = processes;
      return this;
    }

    /**
     * Set true if this side step runs parallel with current task. This is optional.
     * @param isParallelSideStep
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder isParallelSideStep(Boolean isParallelSideStep) {
      this.isParallelSideStep = isParallelSideStep;
      return this;
    }

    /**
     * This set a map of multiple locale - title values to support for multiple languages of parallel side step title.
     * This is optional.
     * @param customParallelSideStepTitles A Map where keys are String to store locale value (e.g., "de", "en")
     * and values are also String to store title value (e.g., "My custom title").
     * This map contains various multiple language values for custom title.
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder customParallelSideStepTitles(Map<String, String> customParallelSideStepTitles) {
      this.customParallelSideStepTitles = customParallelSideStepTitles;
      return this;
    }

    /**
     * This set a map of multiple locale - title values to support for multiple languages of switch side step title.
     * This is optional.
     * @param customSwitchSideStepTitles A Map where keys are String to store locale value (e.g., "de", "en")
     * and values are also String to store title value (e.g., "My custom title").
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder customSwitchSideStepTitles(Map<String, String> customSwitchSideStepTitles) {
      this.customSwitchSideStepTitles = customSwitchSideStepTitles;
      return this;
    }
    
    /**
     * Build a new instance.
     * @return new instance of {@link SideStepConfigurationDTO}
     */
    public SideStepConfigurationDTO build() {
      validate();
      return new SideStepConfigurationDTO(this);
    }
    
    private void validate() {
      if (CollectionUtils.isEmpty(processes)) {
        throw new IllegalArgumentException("List of SideStepProcessDTO must be provided");
      }
    }
  }

}