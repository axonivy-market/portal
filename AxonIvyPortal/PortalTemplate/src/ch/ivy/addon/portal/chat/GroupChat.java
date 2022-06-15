package ch.ivy.addon.portal.chat;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.server.ServerFactory;

@SuppressWarnings("deprecation")
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
      IApplication app =
          ServerFactory.getServer().getApplicationConfigurationManager().findApplication(this.applicationName);
      this.assignees =
          assigneeNames.stream().map(assigneeName -> app.getSecurityContext().findSecurityMember(assigneeName))
              .map(assignee -> new SecurityMemberDTO(assignee)).collect(Collectors.toSet());
    }
    return assignees;
  }

  public void setAssignees(Set<SecurityMemberDTO> assignees) {
    this.assignees = assignees;
    if (CollectionUtils.isNotEmpty(assignees)) {
      this.assigneeNames = assignees.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toSet());
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
