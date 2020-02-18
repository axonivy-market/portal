package ch.ivy.addon.portalkit.taskfilter;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskResponsibleFilter extends TaskFilter {
  @JsonIgnore
  private List<SecurityMemberDTO> responsibles;
  @JsonIgnore
  private SecurityMemberDTO selectedResponsible;
  private String selectedResponsibleMemberName;
  
  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR");
  }

  @Override
  public String value() {
    if (getSelectedResponsibleMemberName() == null) {
      return ALL;
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedResponsible));
  }

  private List<SecurityMemberDTO> getResponsibles() {
    if (CollectionUtils.isEmpty(responsibles)) {
      responsibles = SecurityMemberUtils.findSecurityMembers(StringUtils.EMPTY, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
    }
    return responsibles;
  }

  @Override
  public TaskQuery buildQuery() {
    if (getSelectedResponsible() == null) {
      return null;
    }

    String memberName = selectedResponsible.getMemberName();
    return TaskQuery.create().where().activatorName().isEqual(memberName);
  }

  @Override
  public void resetValues() {
    selectedResponsible = null;
    selectedResponsibleMemberName = null;
  }

  @Override
  public boolean defaultFilter() {
    return true;
  }

  public String formatName(SecurityMemberDTO responsible) {
    if (StringUtils.isBlank(responsible.getDisplayName())) {
      return responsible.getName();
    }
    return responsible.getDisplayName() + " (" + responsible.getName() + ")";
  }

  public SecurityMemberDTO getSelectedResponsible() {
    return selectedResponsible;
  }

  public void setSelectedResponsible(SecurityMemberDTO selectedResponsible) {
    this.selectedResponsible = selectedResponsible;
    this.selectedResponsibleMemberName = Optional.ofNullable(selectedResponsible).map(SecurityMemberDTO::getMemberName).orElse(StringUtils.EMPTY);
  }

  /**
   * Checks selectedResponsibleMemberName which is saved on BusinessData
   * then find correspond SecurityMemberDTO of selectedResponsibleMemberName
   * @return Member name of SecurityMemberDTO
   */
  public String getSelectedResponsibleMemberName() {
    if (StringUtils.isEmpty(selectedResponsibleMemberName)) {
      setSelectedResponsible(null);
      return null;
    } else if (selectedResponsible == null || !StringUtils.equals(selectedResponsibleMemberName, selectedResponsible.getMemberName())) {
      setSelectedResponsible(getResponsibles().stream()
          .filter(securityMember -> StringUtils.equals(securityMember.getMemberName(), selectedResponsibleMemberName))
          .findFirst().orElse(null));
    }
    return selectedResponsibleMemberName;
  }

  public void setSelectedResponsibleMemberName(String selectedResponsibleMemberName) {
    this.selectedResponsibleMemberName = selectedResponsibleMemberName;
  }
  
}
