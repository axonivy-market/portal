package ch.ivy.addon.portalkit.support;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.enums.CaseAssigneeType;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseQueryCriteria {
	
  private String involvedUsername;
  private List<String> involvedApplications;
  private List<CaseState> includedStates;
  private String keyword;
  private Long taskId;
  private Long caseId;
  private String category;
  private String sortField;
  private boolean sortDescending;
  private CaseAssigneeType caseAssigneeType;
  
  private CaseQuery caseQuery;
  private boolean isNewQueryCreated = false;

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

  public void addIncludedStates(List<CaseState> includedStates) {
    if (CollectionUtils.isEmpty(includedStates)) {
      this.includedStates = new ArrayList<>();
    }
    this.includedStates.addAll(includedStates);
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSortField() {
    return sortField;
  }

  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  public boolean isSortDescending() {
    return sortDescending;
  }

  public void setSortDescending(boolean sortDescending) {
    this.sortDescending = sortDescending;
  }

  public CaseQuery getCaseQuery() {
    return caseQuery;
  }

  public void setCaseQuery(CaseQuery caseQuery) {
    this.caseQuery = caseQuery;
  }

  public boolean isNewQueryCreated() {
    return isNewQueryCreated;
  }

  public void setNewQueryCreated(boolean isNewQueryCreated) {
    this.isNewQueryCreated = isNewQueryCreated;
  }
  
  public CaseAssigneeType getCaseAssigneeType() {
    return caseAssigneeType;
  }

  public void setCaseAssigneeType(CaseAssigneeType caseAssigneeType) {
    this.caseAssigneeType = caseAssigneeType;
  }

  public boolean hasIncludedStates() {
    return CollectionUtils.isNotEmpty(includedStates);
  }

  public boolean hasInvolvedApplications() {
    return CollectionUtils.isNotEmpty(involvedApplications);
  }

  public boolean hasKeyword() {
    return StringUtils.isNotEmpty(keyword);
  }

  public boolean hasInvolvedUsername() {
    return StringUtils.isNotEmpty(involvedUsername);
  }

  public boolean hasTaskId() {
    return taskId != null && taskId != 0;
  }

  public boolean hasCaseId() {
    return caseId != null && caseId != 0;
  }

  public boolean hasCategory() {
    return StringUtils.isNotEmpty(category);
  }

}
