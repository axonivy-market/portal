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
  private String memberName;
  private SideStepType selectedStepType;
  private String comment;

  public SideStepProcessParam() {
  }

  public SideStepProcessParam(SideStepProcessDTO sideStepDto, String memberName, SideStepType selectedStepType, String comment) {
    this.sideStepDto = sideStepDto;
    this.setMemberName(memberName);
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

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

}
