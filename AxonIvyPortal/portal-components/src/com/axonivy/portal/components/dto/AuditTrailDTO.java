package com.axonivy.portal.components.dto;

import java.util.Date;

public class AuditTrailDTO {

  private String author;
  private Date timestamp;
  private String content;
  private String taskName;
  private String taskState;

  public AuditTrailDTO(String author, Date timestamp, String content, String taskName, String taskState) {
    this.author = author;
    this.timestamp = timestamp;
    this.content = content;
    this.taskName = taskName;
    this.taskState = taskState;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskState() {
    return taskState;
  }

  public void setTaskState(String taskState) {
    this.taskState = taskState;
  }
}
