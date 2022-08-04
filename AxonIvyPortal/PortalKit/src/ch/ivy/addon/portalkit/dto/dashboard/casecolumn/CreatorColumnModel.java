package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ICase;

public class CreatorColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -7953754221117810034L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.CREATOR.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
    this.format = getDefaultFormat();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
  }

  @Override
  public String getHeaderText() {
    return translateHeader(defaultIfEmpty(this.header, DashboardConfigurationPrefix.CMS + getDefaultHeaderCMS()));
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATOR";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  public String getDefaultStyle() {
    return EXTRA_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__creator";
  }

  @Override
  public Object display(ICase caze) {
    if (caze == null || caze.getCreatorUser() == null) {
      return StringUtils.EMPTY;
    }
    ISecurityMember member = caze.getCreatorUser();
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(member, member.getMemberName());
  }
  
  @JsonIgnore
  public List<SecurityMemberDTO> getCreators() {
    return this.filterList.stream().map(this::findSecurityMember).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setCreators(List<SecurityMemberDTO> creator) {
    if (creator != null) {
      this.filterList = creator.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    } else {
      this.filterList = new ArrayList<>();
    }
  }
  
  @JsonIgnore
  public List<SecurityMemberDTO> getUserFilterCreators() {
    return this.userFilterList.stream().map(this::findSecurityMember).collect(Collectors.toList());
  }
  
  @JsonIgnore
  public void setUserFilterCreators(List<SecurityMemberDTO> creators) {
    if (creators != null) {
      this.userFilterList = creators.stream().map(SecurityMemberDTO::getMemberName).collect(Collectors.toList());
    } else {
      this.userFilterList = new ArrayList<>();
    }
  }
  
  public List<SecurityMemberDTO> completeUserFilterCreators(String query) {
    List<SecurityMemberDTO> options = toSecurityMemberDTOs(getFilterList(), query);
    if (CollectionUtils.isEmpty(options)) {
      options = SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
    }
    return options.stream().filter(SecurityMemberDTO::isUser).collect(Collectors.toList());
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

}
