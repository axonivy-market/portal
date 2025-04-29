package com.axonivy.portal.components.examples.dto;

import com.axonivy.portal.components.dto.UserDTO;

public class SideStepExampleDTO {
  private UserDTO assignee;
  private String question;
  private String answer;

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

  public UserDTO getAssignee() {
    return assignee;
  }

  public void setAssignee(UserDTO assignee) {
    this.assignee = assignee;
  }

}
