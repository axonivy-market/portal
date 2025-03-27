package com.axonivy.portal.components.util;

import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.components.dto.UserDTO;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

public class SecurityMemberDisplayNameUtils {

  private static final String DISABLED_USER_PREFIX_CMS = "/Labels/DisabledUserPrefix";
  private static final String NO_NAME_CMS = "/Labels/NoName";
  private static final String NOT_AVAILABLE_CMS = "/Labels/NotAvailable";
  private static final String FORMAT_DISABLED_USER = "%s %s";


  public static String generateBriefDisplayNameForSecurityMember(ISecurityMember securityMember, String securityMemberName) {
    if(StringUtils.isBlank(securityMemberName)) {
      return Ivy.cms().co(NOT_AVAILABLE_CMS);
    }
    if(securityMember == null) {
      return stripSharpCharacterFromSecurityMemberName(securityMemberName);
    }
    if(securityMember.isUser()) {
      IUser user = (IUser) securityMember;
      return generateBriefDisplayNameForUser(user, user.getName());
    }
    return generateBriefDisplayNameForRole(securityMember.getDisplayName(), securityMember.getName());
  }

  public static String generateBriefDisplayNameForRole(String displayName, String name) {
    if (StringUtils.isBlank(displayName)) {
      return name;
    }
    return displayName;
  }

  public static String generateBriefDisplayNameForUser(IUser user, String username) {
    if (StringUtils.isBlank(username)) {
      return Ivy.cms().co(NOT_AVAILABLE_CMS);
    }

    if (user == null) {
      return stripSharpCharacterFromSecurityMemberName(username);
    }

    if (user.isEnabled()) {
      return user.getDisplayName();
    }
    return Ivy.cms().co(DISABLED_USER_PREFIX_CMS) + " " + user.getDisplayName();
  }

  public static String stripSharpCharacterFromSecurityMemberName(String username) {
    if (username == null) {
      return "";
    }
    return username.startsWith("#") ? username.substring(1) : username;
  }

  public static String generateDisplayNameForUserDTO(UserDTO user) {
    if (user == null) {
      return Ivy.cms().co(NOT_AVAILABLE_CMS);
    }

    String displayName = StringUtils.defaultIfBlank(user.getDisplayName(), Ivy.cms().co(NO_NAME_CMS));
    return user.isEnabled() ? displayName : formatDisabledUser(displayName);
  }

  private static String formatDisabledUser(String displayName) {
    return String.format(FORMAT_DISABLED_USER, Ivy.cms().co(DISABLED_USER_PREFIX_CMS), displayName);
  }

  public static String formatWithTooltip(ISecurityMember securityMember,  String displayName) {
//  Ivy.log().info("====================================");
//  Ivy.log().info("Security member: " + securityMember);
//  // fullName
//  Ivy.log().info("Security member displayName: " + securityMember.getDisplayName());
//  // userName
//  Ivy.log().info("Security member getName: " + securityMember.getName());
//  Ivy.log().info("Display name: " + displayName);
//  Ivy.log().info("");

    if (securityMember == null || StringUtils.isBlank(securityMember.getName())) {
      // N/A
      return StringUtils.EMPTY;
    }

    String formattedUserName = displayName.startsWith("#") ? displayName.substring(1) : displayName;
    if (StringUtils.isBlank(securityMember.getDisplayName())) {
      return "<" + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/noName") + ">" + " (" + formattedUserName + ")";
    }
    return securityMember.getDisplayName() + " (" + formattedUserName + ")";
  }
}
