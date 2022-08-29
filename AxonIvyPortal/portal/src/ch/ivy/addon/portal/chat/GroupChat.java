package ch.ivy.addon.portal.chat;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityMember;

public class GroupChat implements Serializable {

  private static final long serialVersionUID = -3091897713010721574L;

  private Long caseId;
  private Long version = 0L;
  private String caseName;
  private String applicationName;
  private String creator;
  private Set<String> assigneeNames;
  private Map<String, String> params;
  @JsonIgnore
  private Set<SecurityMemberDTO> assignees;

  public Long getCaseId() {
    return caseId;
  }

  public void setCaseId(Long caseId) {
    this.caseId = caseId;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getCaseName() {
    return caseName;
  }

  public void setCaseName(String caseName) {
    this.caseName = caseName;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Set<String> getAssigneeNames() {
    return assigneeNames;
  }

  public void setAssigneeNames(Set<String> assigneeNames) {
    this.assigneeNames = assigneeNames;
  }

  public Set<SecurityMemberDTO> getAssignees() {
    if (CollectionUtils.isEmpty(this.assignees) && CollectionUtils.isNotEmpty(this.assigneeNames)
        && StringUtils.isNotBlank(this.applicationName)) {
      var app = IApplicationRepository.instance().findByName(applicationName).orElse(null);
      this.assignees = new HashSet<>();
      for (String assigneeName : assigneeNames) {
        ISecurityMember assignee = assigneeName.startsWith("#")
            ? app.getSecurityContext().users().find(Long.parseLong(assigneeName.substring(1)))
            : app.getSecurityContext().members().find(assigneeName);
        if (assignee != null) {
          this.assignees.add(new SecurityMemberDTO(assignee));
        }
      }
    }
    return this.assignees;
  }

  public void setAssignees(Set<SecurityMemberDTO> assignees) {
    this.assignees = assignees;
    if (CollectionUtils.isNotEmpty(assignees)) {
      this.assigneeNames = new HashSet<>();
      assignees.forEach(assignee -> {
        if (assignee.isUser()) {
          this.assigneeNames.add("#".concat(Long.toString(assignee.getId())));
        } else {
          this.assigneeNames.add(assignee.getMemberName());
        }
      });
    }
  }

  public Map<String, String> getParams() {
    return params;
  }

  public void setParams(Map<String, String> params) {
    this.params = params;
  }

  @Override
  public String toString() {
    return "{caseId: " + caseId + ", version: " + version + ", applicationName: " + applicationName + ", creator: "
        + creator + ", assignees: " + assignees + "}";
  }
}
