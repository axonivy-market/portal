package ch.ivy.addon.portalkit.bo;

import java.util.Date;

public class AdhocHistory {
	
  private long originalTaskID;
  private String content;
  private Date timestamp;
  private String taskName;
  
  public long getOriginalTaskID() {
    return originalTaskID;
  }

  public void setOriginalTaskID(long originalTaskId) {
    this.originalTaskID = originalTaskId;
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

}
