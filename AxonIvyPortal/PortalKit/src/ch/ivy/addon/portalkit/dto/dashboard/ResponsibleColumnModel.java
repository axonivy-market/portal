package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ITask;

public class ResponsibleColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, cms("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR"));
    this.field = DashboardStandardTaskColumn.RESPONSIBLE.getField();
    this.style = defaultIfEmpty(this.style, "width: 150px");
    this.format = DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null || task.getActivator() == null) {
      return StringUtils.EMPTY;
    }
    ISecurityMember member = task.getActivator();
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, member.getMemberName());
  }
  
  @JsonIgnore
  public List<SecurityMemberDTO> getResponsibles() {
    return this.filterList.stream().map(this::findSecurityMember).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setResponsibles(List<SecurityMemberDTO> responsibles) {
    if (responsibles != null) {
      this.filterList = responsibles.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    } else {
      this.filterList = new ArrayList<>();
    }
  }
  
  @JsonIgnore
  public List<SecurityMemberDTO> getUserFilterResponsibles() {
    return this.userFilterList.stream().map(this::findSecurityMember).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterResponsibles(List<SecurityMemberDTO> responsibles) {
    if (responsibles != null) {
      this.userFilterList = responsibles.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    } else {
      this.userFilterList = new ArrayList<>();
    }
  }
  
  public List<SecurityMemberDTO> completeUserFilterResponsibles(String query) {
    List<SecurityMemberDTO> options = toSecurityMemberDTOs(getFilterList(), query);
    if (CollectionUtils.isEmpty(options)) {
      options = SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
    }
    return options;
  }
  
  private List<SecurityMemberDTO> toSecurityMemberDTOs(List<String> filters, String query) {
    if (CollectionUtils.isEmpty(filters)) {
      return new ArrayList<>();
    }
    return filters.stream()
        .map(this::findSecurityMember)
        .filter(m -> StringUtils.containsIgnoreCase(m.getDisplayName(), query) || StringUtils.containsIgnoreCase(m.getName(), query))
        .collect(Collectors.toList());
  }
  
  private SecurityMemberDTO findSecurityMember(String memberName) {
    ISecurityMember securityMember = Ivy.wf().getApplication().getSecurityContext().findSecurityMember(memberName);
    return securityMember != null ? new SecurityMemberDTO(securityMember) : null;
  }
}
