package ch.ivy.addon.portalkit.bo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.TaskState;

public class History {

  private long id;
  private HistoryType type;
  private String content;
  private Date timestamp;
  private TaskState taskState;
  private String involvedUsername;
  private IUser involvedUser;
  private String displayName;
  private Long caseId;
  private String displayCaseName;
  private boolean isDisabledCaseName;

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

  public IUser getInvolvedUser() {
    return involvedUser;
  }

  public void setInvolvedUser(IUser involvedUser) {
    this.involvedUser = involvedUser;
  }

  public String getDisplayName() {
    if(StringUtils.isBlank(this.displayName)) {
      this.displayName = SecurityMemberDisplayNameUtils.generateBriefDisplayNameForUser(this.involvedUser, this.involvedUsername);
    }
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }

  public String getDisplayCaseName() {
    return displayCaseName;
  }

  public void setDisplayCaseName(String displayCaseName) {
    this.displayCaseName = displayCaseName;
  }

  public boolean isDisabledCaseName() {
    return isDisabledCaseName;
  }

  public void setDisabledCaseName(boolean isDisabledCaseName) {
    this.isDisabledCaseName = isDisabledCaseName;
  }
}
