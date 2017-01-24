package ch.ivy.ws.addon.service;

import java.util.List;
import java.util.Objects;

import ch.ivy.ws.addon.enums.SortType;
import ch.ivy.ws.addon.enums.TaskAssigneeType;
import ch.ivyteam.ivy.workflow.TaskState;

public class TaskSearchCriteria {

  private String involvedUsername;
  private List<String> involvedApplications;
  private List<TaskState> includedStates;
  private List<TaskState> excludedStates;
  private String keyword;
  private Long taskId;
  private Long caseId;
  private String category;
  private SortType sortType;
  private boolean sortDescending;
  private boolean ignoreInvolvedUser;
  private TaskAssigneeType taskAssigneeType;

  public boolean isEmpty() {
    return !hasInvolvedUsername() && !hasInvolvedApplications() && !hasKeyword() && !hasExcludedStates()
        && !hasIncludedStates() && !hasTaskId() && !hasCaseId() && !hasCategory();
  }

  public String getInvolvedUsername() {
    return involvedUsername;
  }

  public void setInvolvedUsername(String involvedUsername) {
    this.involvedUsername = involvedUsername;
  }

  public List<String> getInvolvedApplications() {
    return involvedApplications;
  }

  public void setInvolvedApplications(List<String> involvedApplications) {
    this.involvedApplications = involvedApplications;
  }

  public List<TaskState> getIncludedStates() {
    return includedStates;
  }

  public void setIncludedStates(List<TaskState> includedStates) {
    this.includedStates = includedStates;
  }

  public List<TaskState> getExcludedStates() {
    return excludedStates;
  }

  public void setExcludedStates(List<TaskState> excludedStates) {
    this.excludedStates = excludedStates;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public boolean hasIncludedStates() {
    return includedStates != null && !includedStates.isEmpty();
  }

  public boolean hasExcludedStates() {
    return excludedStates != null && !excludedStates.isEmpty();
  }

  public boolean hasInvolvedApplications() {
    return involvedApplications != null && !involvedApplications.isEmpty();
  }

  public boolean hasKeyword() {
    return !(Objects.toString(keyword, "").isEmpty());
  }

  public boolean hasInvolvedUsername() {
    return !(Objects.toString(involvedUsername, "").isEmpty());
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public boolean hasTaskId() {
    return taskId != null && taskId != 0;
  }

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }

  public boolean hasCaseId() {
    return caseId != null && caseId != 0;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public boolean hasCategory() {
    return !(Objects.toString(category, "").isEmpty());
  }

  public boolean isSortDescending() {
    return sortDescending;
  }

  public void setSortDescending(boolean sortDescending) {
    this.sortDescending = sortDescending;
  }

  public boolean isIgnoreInvolvedUser() {
    return ignoreInvolvedUser;
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    this.ignoreInvolvedUser = ignoreInvolvedUser;
  }

  public SortType getSortType() {
    return sortType;
  }

  public void setSortType(SortType sortType) {
    this.sortType = sortType;
  }

  public TaskAssigneeType getTaskAssigneeType() {
    return taskAssigneeType;
  }

  public void setTaskAssigneeType(TaskAssigneeType taskAssigneeType) {
    this.taskAssigneeType = taskAssigneeType;
  }
}
