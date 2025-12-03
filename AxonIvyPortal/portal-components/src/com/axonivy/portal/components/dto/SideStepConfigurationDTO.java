package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.ivydata.bo.JsonVersion;
import com.axonivy.portal.components.util.DisplayNameUtils;
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
 * .customParallelSideStepTitleCmsUri("/Processes/SideStep/CustomParallelTitle")
 * .build();
 * </pre> 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepConfigurationDTO implements Serializable {
  private static final long serialVersionUID = -1404169898838544342L;
  private String version;
  private List<SideStepProcessDTO> processes;
  private Boolean isParallelSideStep;
  private List<DisplayNameDTO> customParallelSideStepTitles;
  private List<DisplayNameDTO> customSwitchSideStepTitles;
  
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
  public List<DisplayNameDTO> getCustomParallelSideStepTitles() {
    return customParallelSideStepTitles;
  }

  /**
   * @return custom title for switch side step.
   */
  public List<DisplayNameDTO> getCustomSwitchSideStepTitles() {
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
    private String customParallelSideStepTitleCmsUri;
    private String customSwitchSideStepTitleCmsUri;
    private String cmsProjectName;
    private List<DisplayNameDTO> customParallelSideStepTitles;
    private List<DisplayNameDTO> customSwitchSideStepTitles;
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
     * Set CMS URI for custom parallel side step title. This is optional.
     * @param customParallelSideStepTitleCmsUri CMS URI (e.g., "/Processes/SideStep/CustomParallelTitle")
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder customParallelSideStepTitleCmsUri(String customParallelSideStepTitleCmsUri) {
      this.customParallelSideStepTitleCmsUri = customParallelSideStepTitleCmsUri;
      return this;
    }

    /**
     * Set CMS URI for custom switch side step title. This is optional.
     * @param customSwitchSideStepTitleCmsUri CMS URI (e.g., "/Processes/SideStep/CustomSwitchTitle")
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder customSwitchSideStepTitleCmsUri(String customSwitchSideStepTitleCmsUri) {
      this.customSwitchSideStepTitleCmsUri = customSwitchSideStepTitleCmsUri;
      return this;
    }

    /**
     * Set the project name to locate CMS URIs. Optional - if not provided, CMS URIs will be resolved from current
     * project context.
     * 
     * @param cmsProjectName project name
     * @return builder for {@link SideStepConfigurationDTO}
     */
    public Builder cmsProjectName(String cmsProjectName) {
      this.cmsProjectName = cmsProjectName;
      return this;
    }

    /**
     * Build a new instance.
     * @return new instance of {@link SideStepConfigurationDTO}
     */
    public SideStepConfigurationDTO build() {
      validate();
      if (StringUtils.isNotBlank(customParallelSideStepTitleCmsUri)) {
        this.customParallelSideStepTitles = DisplayNameUtils.createCmsDisplayName(
            customParallelSideStepTitleCmsUri, cmsProjectName);
      }
      if (StringUtils.isNotBlank(customSwitchSideStepTitleCmsUri)) {
        this.customSwitchSideStepTitles = DisplayNameUtils.createCmsDisplayName(
            customSwitchSideStepTitleCmsUri, cmsProjectName);
      }
      return new SideStepConfigurationDTO(this);
    }
    
    private void validate() {
      if (CollectionUtils.isEmpty(processes)) {
        throw new IllegalArgumentException("List of SideStepProcessDTO must be provided");
      }
    }
  }

}