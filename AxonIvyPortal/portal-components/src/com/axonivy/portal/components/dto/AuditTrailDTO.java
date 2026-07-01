package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.Date;

public class AuditTrailDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Date timestamp;
  private String author;
  private String content;

  public AuditTrailDTO() {}

  public AuditTrailDTO(Date timestamp, String author, String content) {
    this.timestamp = timestamp;
    this.author = author;
    this.content = content;
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
