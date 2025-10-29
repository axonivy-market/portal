package com.axonivy.portal.components.dto;

import java.io.Serializable;

import com.axonivy.portal.components.ivydata.bo.JsonVersion;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * This class stores information about side step task.
 * When side step task is created, Portal stores information input by user to this class, parse to JSON and send to the selected process.
 * <pre>
 * <b>sideStepProcessDto</b>: object which has information about selected process 
 * <b>securityMemberId</b>: id of user who created side step task
 * <b>comment</b>: comment of user who created side step task (optional)
 * <b>isParallelSideStep</b>: whether side step task runs parallel with original task
 * </pre>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepProcessParamDTO implements Serializable {
  private static final long serialVersionUID = -4988849644631374542L;
  private String version = JsonVersion.LATEST.getValue();
  private SideStepProcessDTO sideStepProcessDto;
  private String securityMemberId;
  private String comment;
  private Boolean isParallelSideStep;

  public SideStepProcessParamDTO() {}

  public SideStepProcessParamDTO(SideStepProcessDTO sideStepProcessDto, String securityMemberId, Boolean isParallelSideStep, String comment) {
    this.sideStepProcessDto = sideStepProcessDto;
    this.securityMemberId = securityMemberId;
    this.isParallelSideStep = isParallelSideStep;
    this.comment = comment;
  }
  
  public SideStepProcessDTO getSideStepProcessDto() {
    return sideStepProcessDto;
  }

  public void setSideStepProcessDto(SideStepProcessDTO sideStepProcessDto) {
    this.sideStepProcessDto = sideStepProcessDto;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getSecurityMemberId() {
    return securityMemberId;
  }

  public void setSecurityMemberId(String securityMemberId) {
    this.securityMemberId = securityMemberId;
  }

  public Boolean getIsParallelSideStep() {
    return isParallelSideStep;
  }

  public void setIsParallelSideStep(Boolean isParallelSideStep) {
    this.isParallelSideStep = isParallelSideStep;
  }

  public String getVersion() {
    return version;
  }
}
