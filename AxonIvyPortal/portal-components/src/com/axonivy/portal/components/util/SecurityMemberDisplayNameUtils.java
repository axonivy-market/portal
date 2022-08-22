package com.axonivy.portal.components.util;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

public class SecurityMemberDisplayNameUtils {

  private static final String DISABLED_USER_PREFIX_CMS = "/Labels/disabledUserPrefix";
  private static final String NOT_AVAILABLE_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable";


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
}
