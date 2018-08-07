package ch.ivy.ws.addon.service;

import java.util.List;
import java.util.Objects;

import ch.ivyteam.ivy.workflow.CaseState;

public class CaseSearchCriteria {

  private String involvedUsername;
  private List<String> involvedApplications;
  private List<CaseState> includedStates;
  private List<CaseState> excludedStates;
  private String keyword;
  private Long taskId;
  private Long caseId;
  private boolean ignoreInvolvedUser;

  public boolean isEmpty() {
    return !hasInvolvedUsername() && !hasInvolvedApplications() && !hasKeyword() && !hasExcludedStates()
        && !hasIncludedStates() && !hasTaskId() && !hasCaseId();
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

  public List<CaseState> getIncludedStates() {
    return includedStates;
  }

  public void setIncludedStates(List<CaseState> includedStates) {
    this.includedStates = includedStates;
  }

  public List<CaseState> getExcludedStates() {
    return excludedStates;
  }

  public void setExcludedStates(List<CaseState> excludedStates) {
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

  public boolean isIgnoreInvolvedUser() {
    return ignoreInvolvedUser;
  }

  public void setIgnoreInvolvedUser(boolean ignoreInvolvedUser) {
    this.ignoreInvolvedUser = ignoreInvolvedUser;
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
}
