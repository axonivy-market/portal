package com.axonivy.portal.components.dto;

import java.util.Date;

import ch.ivyteam.ivy.workflow.note.Note;

public class AuditTrailNoteDTO {

  private String authorName;
  private String content;
  private Date createdAt;

  public AuditTrailNoteDTO() {}

  public AuditTrailNoteDTO(Note note) {
    this.authorName = note.authorName();
    this.content = note.content();
    this.createdAt = note.createdAt();
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
