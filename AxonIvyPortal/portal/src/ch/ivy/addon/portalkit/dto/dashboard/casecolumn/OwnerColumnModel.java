package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.workflow.ICase;

public class OwnerColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -2458647281596281704L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.OWNER.getField();
    this.styleToDisplay = initDefaultStyle();
    this.format = getDefaultFormat();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/OWNER";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  protected int getDefaultColumnWidth() {
    return EXTRA_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__owner";
  }

  @Override
  public Object display(ICase caze) {
    if (caze == null || CollectionUtils.isEmpty(caze.owners().all())) {
      return StringUtils.EMPTY;
    }
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForCaseOwners(caze.owners());
  }
  
  @JsonIgnore
  public List<SecurityMemberDTO> getOwners() {
    return this.filterList.stream().map(this::findSecurityMember).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setOwners(List<SecurityMemberDTO> owner) {
    if (owner != null) {
      this.filterList = owner.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
      return;
    } 
    this.filterList = new ArrayList<>();
  }
  
  @JsonIgnore
  public List<SecurityMemberDTO> getUserFilterOwners() {
    return this.userFilterList.stream().map(this::findSecurityMember).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterOwners(List<SecurityMemberDTO> owners) {
    if (owners != null) {
      this.userFilterList = owners.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
      return;
    } 
    this.userFilterList = new ArrayList<>();
  }
  
  public List<SecurityMemberDTO> completeUserFilterOwners(String query) {
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
    return ServiceUtilities.findSecurityMemberByName(memberName);
  }
  
  @Override
  public Boolean getDefaultSortable() {
    return false;
  }

}
