package com.axonivy.portal.components.dto;

import java.io.Serializable;

import com.axonivy.portal.components.enums.SideStepType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SideStepProcessParam implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = -4988849644631374542L;
  private SideStepProcessDTO sideStepDto;
  private String securityMemberId;
  private SideStepType selectedStepType;
  private String comment;

  public SideStepProcessParam() {
  }

  public SideStepProcessParam(SideStepProcessDTO sideStepDto, String securityMemberId, SideStepType selectedStepType, String comment) {
    this.sideStepDto = sideStepDto;
    this.securityMemberId = securityMemberId;
    this.selectedStepType = selectedStepType;
    this.comment = comment;
  }

  public SideStepProcessDTO getSideStepDto() {
    return sideStepDto;
  }

  public void setSideStepDto(SideStepProcessDTO sideStepDto) {
    this.sideStepDto = sideStepDto;
  }

  public SideStepType getSelectedStepType() {
    return selectedStepType;
  }

  public void setSelectedStepType(SideStepType selectedStepType) {
    this.selectedStepType = selectedStepType;
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


}