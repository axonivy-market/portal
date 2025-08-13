package com.axonivy.portal.developerexamples.dto;

public class SideStepExampleDTO {
  private String originalTaskUuid;
  private String assignee;
  private boolean isCompleted;
  
  private String askMoreDetails;
  private String ceoApprovalComment;
  private String customerToInform;

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

  public String getCeoApprovalComment() {
    return ceoApprovalComment;
  }

  public String getCustomerToInform() {
    return customerToInform;
  }

  public void setCeoApprovalComment(String ceoApprovalComment) {
    this.ceoApprovalComment = ceoApprovalComment;
  }

  public void setCustomerToInform(String customerToInform) {
    this.customerToInform = customerToInform;
  }

  public String getAskMoreDetails() {
    return askMoreDetails;
  }

  public void setAskMoreDetails(String askMoreDetails) {
    this.askMoreDetails = askMoreDetails;
  }

  public String getOriginalTaskUuid() {
    return originalTaskUuid;
  }

  public void setOriginalTaskUuid(String originalTaskUuid) {
    this.originalTaskUuid = originalTaskUuid;
  }

}
