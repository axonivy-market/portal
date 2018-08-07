package ch.ivy.addon.portalkit.bo;

import java.util.Date;

import ch.ivyteam.ivy.workflow.TaskState;

public class History {

  private HistoryType type;
  private String content;
  private Date timestamp;
  private TaskState taskState;
  private String involvedUser;

  public static enum HistoryType {
    TASK, NOTE;
  }

  public HistoryType getType() {
    return type;
  }

  public void setType(HistoryType type) {
    this.type = type;
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

  public TaskState getTaskState() {
    return taskState;
  }

  public void setTaskState(TaskState taskState) {
    this.taskState = taskState;
  }

  public String getInvolvedUser() {
    return involvedUser;
  }

  public void setInvolvedUser(String involvedUser) {
    this.involvedUser = involvedUser;
  }


}
