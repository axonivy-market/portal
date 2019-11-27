package ch.ivy.addon.portalkit.bo;

import java.util.Date;

public class AdhocHistory {
	
  private long originalTaskId;
  private String content;
  private Date timestamp;
  private String taskName;
  private String authorUsername;
  
  public long getOriginalTaskId() {
    return originalTaskId;
  }

  public void setOriginalTaskId(long originalTaskId) {
    this.originalTaskId = originalTaskId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getAuthorUsername() {
    return authorUsername;
  }

  public void setAuthorUsername(String authorUsername) {
    this.authorUsername = authorUsername;
  }
}
