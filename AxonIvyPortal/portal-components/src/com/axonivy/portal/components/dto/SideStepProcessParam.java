package com.axonivy.portal.components.dto;

import com.axonivy.portal.components.enums.SideStepType;

public class SideStepProcessParam {

  private SideStepProcessDTO sideStepDto;
  private UserDTO assignee;
  private SideStepType selectedStepType;
  private String comment;

  public SideStepProcessParam() {
  }

  public SideStepProcessParam(SideStepProcessDTO sideStepDto, UserDTO assignee, SideStepType selectedStepType, String comment) {
    this.sideStepDto = sideStepDto;
    this.assignee = assignee;
    this.selectedStepType = selectedStepType;
    this.comment = comment;
  }

  public SideStepProcessDTO getSideStepDto() {
    return sideStepDto;
  }

  public void setSideStepDto(SideStepProcessDTO sideStepDto) {
    this.sideStepDto = sideStepDto;
  }

  public UserDTO getAssignee() {
    return assignee;
  }

  public void setAssignee(UserDTO assignee) {
    this.assignee = assignee;
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

}
