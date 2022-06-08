package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

public class SecurityMemberDisplayNameUtils {

  private static final String DISABLED_USER_PREFIX_CMS = "/Labels/disabledUserPrefix";
  private static final String NO_NAME_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/noName";
  private static final String NOT_AVAILABLE_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable";
  private static final String FORMAT_WITH_DISPLAY_NAME = "%s (%s)";
  private static final String FORMAT_WITHOUT_DISPLAY_NAME = "<%s> (%s)";
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

  public static String generateFullDisplayNameForSecurityMember(ISecurityMember securityMember, String securityMemberName) {
    if(StringUtils.isBlank(securityMemberName)) {
      return Ivy.cms().co(NOT_AVAILABLE_CMS);
    }
    if(securityMember == null) {
      return stripSharpCharacterFromSecurityMemberName(securityMemberName);
    }
    if(securityMember.isUser()) {
      IUser user = (IUser) securityMember;
      return generateFullDisplayNameForUser(user, user.getName());
    }
    return generateFullDisplayNameForRole(securityMember.getDisplayName(), securityMember.getName());
  }
  
  public static String generateBriefDisplayNameForRole(String displayName, String name) {
    if (StringUtils.isBlank(displayName)) {
      return name;
    }
    return displayName;
  }
  
  public static String generateFullDisplayNameForRole(String displayName, String name) {
    if (StringUtils.isBlank(displayName)) {
      return String.format(FORMAT_WITHOUT_DISPLAY_NAME, Ivy.cms().co(NO_NAME_CMS), name);
    }
    return String.format(FORMAT_WITH_DISPLAY_NAME, displayName, name);
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

  public static String generateFullDisplayNameForUser(IUser user, String username) {
    if (StringUtils.isBlank(username)) {
      return Ivy.cms().co(NOT_AVAILABLE_CMS);
    }

    String formattedUsername = stripSharpCharacterFromSecurityMemberName(username);
    String displayName = null;
    if (user == null || StringUtils.isBlank(user.getDisplayName())) {
      displayName = String.format(FORMAT_WITHOUT_DISPLAY_NAME, Ivy.cms().co(NO_NAME_CMS), formattedUsername);
    }
    else {
      displayName = String.format(FORMAT_WITH_DISPLAY_NAME, user.getDisplayName(), formattedUsername);
      if(!user.isEnabled()) {
        displayName = formatDisabledUser(displayName);
      }
    }
    return displayName;
  }
  
  public static String generateFullDisplayNameForUserDTO(UserDTO user) {
    if (user == null) {
      return Ivy.cms().co(NOT_AVAILABLE_CMS);
    }

    String formattedUsername = stripSharpCharacterFromSecurityMemberName(user.getName());
    String displayName = null;
    if (StringUtils.isBlank(user.getDisplayName())) {
      displayName = String.format(FORMAT_WITHOUT_DISPLAY_NAME, Ivy.cms().co(NO_NAME_CMS), formattedUsername);
    }
    else {
      displayName = String.format(FORMAT_WITH_DISPLAY_NAME, user.getDisplayName(), formattedUsername);
      if(!user.isEnabled()) {
        displayName = formatDisabledUser(displayName);
      }
    }
    return displayName;
  }

  private static String formatDisabledUser(String displayName) {
    return String.format(FORMAT_DISABLED_USER, Ivy.cms().co(DISABLED_USER_PREFIX_CMS), displayName);
  }

  public static String stripSharpCharacterFromSecurityMemberName(String username) {
    if (username == null) {
      return "";
    }
    return username.startsWith("#") ? username.substring(1) : username;
  }

  public static String joinSecurityMemberNames(List<ISecurityMember> securityMembers) {
    if (CollectionUtils.isNotEmpty(securityMembers)) {
      List<String> responsibleNames = new ArrayList<>();
      for (ISecurityMember securityMember : securityMembers) {
        responsibleNames.add(SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(securityMember,
            securityMember.getName()));
      }
      return String.join(", ", responsibleNames);
    }
    return Strings.EMPTY;
  }
}
