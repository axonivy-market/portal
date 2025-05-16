package com.axonivy.portal.components.dto;

import com.axonivy.portal.components.enums.SideStepType;

public class SideStepProcessParam {

  private SideStepDTO sideStepDto;
  private UserDTO assignee;
  private SideStepType selectedStepType;
  private String comment;

  public SideStepProcessParam() {
  }

  public SideStepProcessParam(SideStepDTO sideStepDto, UserDTO assignee, SideStepType selectedStepType, String comment) {
    this.sideStepDto = sideStepDto;
    this.assignee = assignee;
    this.selectedStepType = selectedStepType;
    this.comment = comment;
  }

  public SideStepDTO getSideStepDto() {
    return sideStepDto;
  }

  public void setSideStepDto(SideStepDTO sideStepDto) {
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
