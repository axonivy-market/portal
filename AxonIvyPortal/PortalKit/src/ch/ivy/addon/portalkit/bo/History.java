package ch.ivy.addon.portalkit.bo;

import java.util.Date;

import ch.ivyteam.ivy.workflow.TaskState;

public class History {

  private long id;
  private HistoryType type;
  private String content;
  private Date timestamp;
  private TaskState taskState;
  private String involvedUsername;
  private String involvedFullname;

  public enum HistoryType {
    TASK, NOTE, EVENT;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
  }

  public String getInvolvedFullname() {
    return involvedFullname;
  }

  public void setInvolvedFullname(String involvedFullname) {
    this.involvedFullname = involvedFullname;
  }

}
