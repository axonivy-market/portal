package com.axonivy.portal.components.examples.dto;

import com.axonivy.portal.components.dto.UserDTO;

public class SideStepExampleDTO {
  private String assignee;
  private String question;
  private String answer;
  private boolean isCompleted;
  private String comment1;
  private String comment2;
  private String comment3;

  public String getComment1() {
    return comment1;
  }

  public void setComment1(String comment1) {
    this.comment1 = comment1;
  }

  public String getComment2() {
    return comment2;
  }

  public void setComment2(String comment2) {
    this.comment2 = comment2;
  }

  public String getComment3() {
    return comment3;
  }

  public void setComment3(String comment3) {
    this.comment3 = comment3;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

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

}
