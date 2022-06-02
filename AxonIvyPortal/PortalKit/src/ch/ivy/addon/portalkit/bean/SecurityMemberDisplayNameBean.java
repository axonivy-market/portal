package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.security.ISecurityMember;

@ManagedBean
@ViewScoped
public class SecurityMemberDisplayNameBean implements Serializable {

  private static final long serialVersionUID = 4105979446859094503L;

  private boolean isDisplayUserOfRole;

  public void init() {
    isDisplayUserOfRole = GlobalSettingService.getInstance()
        .findGlobalSettingValueAsBoolean(GlobalVariable.DISPLAY_USERS_OF_ROLE);
  }

  public String getBriefDisplayName(ISecurityMember securityMember, String memberName) {
    return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(securityMember, memberName);
  }

  public String getFullDisplayName(ISecurityMember securityMember, String memberName) {
    return SecurityMemberDisplayNameUtils.generateFullDisplayNameForSecurityMember(securityMember, memberName);
  }

  public String getFullDisplayNameForUserDTO(UserDTO user) {
    return SecurityMemberDisplayNameUtils.generateFullDisplayNameForUserDTO(user);
  }

  public boolean isRenderGroupUsersAsTooltip(ISecurityMember securityMember, String alwaysShowAsText) {
    return BooleanUtils.toBoolean(alwaysShowAsText)
        && isDisplayUserOfRole
        && Objects.nonNull(securityMember) && !securityMember.isUser();
  }

  public boolean isRenderDisplayNameTooltip(ISecurityMember securityMember) {
    return isDisplayUserOfRole
        && Objects.nonNull(securityMember) && securityMember.isUser();
  }

  public boolean isRenderUsersOverlaySearch(ISecurityMember securityMember, String alwaysShowAsText) {
    return isDisplayUserOfRole
        && Objects.nonNull(securityMember) && !securityMember.isUser()
        && !BooleanUtils.toBoolean(alwaysShowAsText);
  }

  public boolean isDisplayUserOfRole() {
    return isDisplayUserOfRole;
  }

}
