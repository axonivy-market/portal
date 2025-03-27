package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.BooleanUtils;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.GlobalVariable;
import com.axonivy.portal.components.service.GlobalSettingService;
import com.axonivy.portal.components.util.SecurityMemberDisplayNameUtils;
import com.axonivy.portal.components.util.SecurityMemberUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
public class PortalComponentAvatarBean implements Serializable {

  private static final long serialVersionUID = 6793376941093725298L;

  public boolean getPortalShowAvatarSettingOrDefault(boolean defaultIfEmpty) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_AVATAR, defaultIfEmpty);
  }

  public String getNameInitials(String displayName) {
    return SecurityMemberUtils.getNameInitials(displayName);
  }

  public String getEmailAddress(ISecurityMember securityMember,
      boolean useLowercaseEmail) {
    if (securityMember == null || !securityMember.isUser()) {
      return "";
    }

    String email = ((IUser) securityMember).getEMailAddress();
    return BooleanUtils.isTrue(useLowercaseEmail)
        ? Optional.ofNullable(email).map(String::toLowerCase).orElse(email)
        : email;
  }

  public String getEmailAddress(SecurityMemberDTO securityMember,
      boolean useLowercaseEmail) {
    if (securityMember == null || !securityMember.isUser()) {
      return "";
    }
    String email = securityMember.getEMailAddress();
    return BooleanUtils.isTrue(useLowercaseEmail)
        ? Optional.ofNullable(email).map(String::toLowerCase).orElse(email)
        : email;
  }

  public String getEmailAddress(UserDTO user, boolean useLowercaseEmail) {
    if (user == null) {
      return "";
    }
    String email = user.getEmail();
    return BooleanUtils.isTrue(useLowercaseEmail)
        ? Optional.ofNullable(email).map(String::toLowerCase).orElse(email)
        : email;
  }

  public String getEmailAddress(RoleDTO role, boolean useLowercaseEmail) {
    return "";
  }

  public String tooltipTechnicalDisplayName(ISecurityMember securityMember,  String displayName) {

    Ivy.log().info("====================================");
    Ivy.log().info("Security member: " + securityMember);
    // fullName
    Ivy.log().info("Security member displayName: " + securityMember.getDisplayName());
    // userName
    Ivy.log().info("Security member getName: " + securityMember.getName());
    Ivy.log().info("Display name: " + displayName);
    Ivy.log().info("");

    return SecurityMemberDisplayNameUtils.formatWithTooltip(securityMember, displayName);
  }
}
