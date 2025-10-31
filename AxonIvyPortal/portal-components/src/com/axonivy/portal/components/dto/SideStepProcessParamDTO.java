package com.axonivy.portal.components.dto;

import java.io.Serializable;

import com.axonivy.portal.components.ivydata.bo.JsonVersion;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * This class stores information about side step task.
 * When side step task is created, Portal stores information input by user to this class, parse to JSON and send to the selected process.
 * <pre>
 * <b>sideStepProcessDto</b>: {@link SideStepProcessDTO} object which has information about selected process (mandatory). 
 * <b>securityMemberId</b>: id of user who created side step task (mandatory).
 * <b>comment</b>: comment of user who created side step task (optional).
 * <b>isParallelSideStep</b>: whether side step task runs parallel with original task (mandatory).
 * </pre>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepProcessParamDTO implements Serializable {
  private static final long serialVersionUID = -4988849644631374542L;
  private String version;
  private SideStepProcessDTO sideStepProcessDto;
  private String taskUuid;
  private String securityMemberId;
  private String comment;
  private Boolean isParallelSideStep;

  public SideStepProcessParamDTO() {}

  private SideStepProcessParamDTO(Builder builder) {
    this.version = builder.version;
    this.sideStepProcessDto = builder.sideStepProcessDto;
    this.taskUuid = builder.taskUuid;
    this.securityMemberId = builder.securityMemberId;
    this.comment = builder.comment;
    this.isParallelSideStep = builder.isParallelSideStep;
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  /**
   * Get object which has information about selected process.
   * @return {@link SideStepProcessDTO}
   */
  public SideStepProcessDTO getSideStepProcessDto() {
    return sideStepProcessDto;
  }

  /**
   * Get comment of user who trigger this side step.
   * @return comment
   */
  public String getComment() {
    return comment;
  }

  /**
   * Get security member id of user who trigger this side step. 
   * @return security member id
   */
  public String getSecurityMemberId() {
    return securityMemberId;
  }

  /**
   * Get is parallel or switch side step.
   * @return true : is parallel side step, false : is switch side step
   */
  public Boolean getIsParallelSideStep() {
    return isParallelSideStep;
  }

  /**
   * Get uuid of task which user trigger this side step from.
   * @return task uuid
   */
  public String getTaskUuid() {
    return taskUuid;
  }

  public String getVersion() {
    return version;
  }

  public static class Builder{
    private String version = JsonVersion.LATEST.getValue();
    private SideStepProcessDTO sideStepProcessDto;
    private String taskUuid;
    private String securityMemberId;
    private String comment;
    private Boolean isParallelSideStep;
    
    /**
     * Set {@link SideStepProcessDTO} object which contains information about selected process.
     * @param sideStepProcessDTO
     * @return builder of {@link SideStepProcessParamDTO}
     */
    public Builder sideStepProcessDto(SideStepProcessDTO sideStepProcessDTO) {
      this.sideStepProcessDto = sideStepProcessDTO;
      return this;
    }
    
    /**
     * Set task uuid, this is the task which user trigger side step from.
     * @param taskUuid
     * @return builder of {@link SideStepProcessParamDTO}
     */
    public Builder taskUuid(String taskUuid) {
      this.taskUuid = taskUuid;
      return this;
    }
    
    /**
     * Set security member id of user who trigger this side step.
     * @param securityMemberId
     * @return builder of {@link SideStepProcessParamDTO}
     */
    public Builder securityMemberId(String securityMemberId) {
      this.securityMemberId = securityMemberId;
      return this;
    }
    
    /**
     * Set comment of user who trigger this side step.
     * @param comment
     * @return builder of {@link SideStepProcessParamDTO}
     */
    public Builder comment(String comment) {
      this.comment = comment;
      return this;
    }
    
    /**
     * Set is parallel side step or switch side step.
     * @param isParallelSideStep
     * @return builder of {@link SideStepProcessParamDTO} 
     */
    public Builder isParallelSideStep(Boolean isParallelSideStep) {
      this.isParallelSideStep = isParallelSideStep;
      return this;
    }
    
    /**
     * Build a new instance.
     * @return new instance of {@link SideStepProcessParamDTO}
     */
    public SideStepProcessParamDTO build() {
      return new SideStepProcessParamDTO(this);
    }
  }
  
  
}
