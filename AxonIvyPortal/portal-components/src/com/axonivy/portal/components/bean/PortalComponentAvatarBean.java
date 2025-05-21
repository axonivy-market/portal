package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.Arrays;
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
    if (securityMember == null || !securityMember.available() || !securityMember.isUser()) {
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
   * Supports different member types: ISecurityMember, UserDTO, RoleDTO.
   * @param member 
   * @return string
   */
  public String tooltipTechnicalDisplayName(Object member) {
    // Return empty string if input is null
    if (member == null) {
      return StringUtils.EMPTY;
    }

    // Handle ISecurityMember objects
    if (member instanceof ISecurityMember securityMember) {
      return formatTooltip(securityMember.getDisplayName(), securityMember.getMemberName(), securityMember.getName());
    }

    // Handle UserDTO objects
    if (member instanceof UserDTO user) {
      return formatTooltip(user.getDisplayName(), user.getMemberName(), user.getName());
    }

    // Handle RoleDTO objects
    if (member instanceof RoleDTO role) {
      return formatTooltip(role.getDisplayName(), role.getMemberName(), role.getName());
    }

    // Fallback for unsupported types
    return StringUtils.EMPTY;
  }

  /**
   * Helper method to validate and format tooltip for a member.
   *
   * @param displayName the display name shown to users
   * @param memberName  the technical name (e.g., login or ID)
   * @param nameCheck   used to verify that the member has a valid identity
   * @return formatted tooltip string or empty if invalid
   */
  private String formatTooltip(String displayName, String memberName, String nameCheck) {
    // Skip tooltip if identity (nameCheck) is blank
    if (StringUtils.isBlank(nameCheck)) {
      return StringUtils.EMPTY;
    }
    return buildTooltip(displayName, memberName);
  }

  /**
   * Build a tooltip string like: "John Doe (jdoe)"
   * - If display name is blank, fallback to a localized "no name" label.
   * - Removes leading '#' from member name if present.
   *
   * @param displayName the visible name of the user/member
   * @param memberName the technical/internal identifier
   * @return formatted tooltip string
   */
  private String buildTooltip(String displayName, String memberName) {
    // Clean up member name by removing leading "#" if present
    String formattedUserName = (memberName != null && memberName.startsWith("#"))
        ? memberName.substring(1)
        : memberName;

    // Use a placeholder if display name is blank
    if (StringUtils.isBlank(displayName)) {
      String noNameLabel = Ivy.cms().co("/Labels/NoName");
      return Ivy.cms().co("/Labels/TooltipNoNameFormatted", Arrays.asList(noNameLabel, formattedUserName));
    }

    // Format: "Display Name (memberName)"
    return Ivy.cms().co("/Labels/TooltipUserNameFormatted", Arrays.asList(displayName, formattedUserName));
  }

}
