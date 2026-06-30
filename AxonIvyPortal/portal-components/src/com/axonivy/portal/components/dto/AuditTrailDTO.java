package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.Date;

public class AuditTrailDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long taskId;
  private Date timestamp;
  private String author;
  private String action;
  private String content;

  public AuditTrailDTO() {}

  public AuditTrailDTO(Long taskId, Date timestamp, String author, String action, String content) {
    this.taskId = taskId;
    this.timestamp = timestamp;
    this.author = author;
    this.action = action;
    this.content = content;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
