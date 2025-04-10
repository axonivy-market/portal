package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.GlobalVariable;
import com.axonivy.portal.components.service.GlobalSettingService;
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
  

  public boolean getPortalShowTechnicalTooltipOrDefault(boolean defaultIfEmpty) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_TOOLTIP_TECHNICAL_NAME, defaultIfEmpty);
  }
  
  /**
   * Tooltip format: Show display name and the "technical name" (memberName) in parentheses.
   */
  public String tooltipTechnicalDisplayName(ISecurityMember member) {
    if (member == null || StringUtils.isBlank(member.getName())) {
      return StringUtils.EMPTY;
    }
    return buildTooltip(member.getDisplayName(), member.getMemberName());
  }

  public String tooltipTechnicalDisplayName(UserDTO member) {
    if (member == null || StringUtils.isBlank(member.getName())) {
      return StringUtils.EMPTY;
    }
    return buildTooltip(member.getDisplayName(), member.getMemberName());
  }

  public String tooltipTechnicalDisplayName(RoleDTO member) {
    if (member == null || StringUtils.isBlank(member.getName())) {
      return StringUtils.EMPTY;
    }
    return buildTooltip(member.getDisplayName(), member.getMemberName());
  }

  /**
   * Build a tooltip string like: "John Doe (jdoe)"
   * If display name is empty, fallback to localized "no name" string.
   */
  private String buildTooltip(String displayName, String memberName) {
    // Remove leading "#" if present
    String formattedUserName = memberName != null && memberName.startsWith("#")
        ? memberName.substring(1)
        : memberName;

    if (StringUtils.isBlank(displayName)) {
      String noNameLabel = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/noName");
      return String.format("<%s> (%s)", noNameLabel, formattedUserName);
    }
    return String.format("%s (%s)", displayName, formattedUserName);
  }}
