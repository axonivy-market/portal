package com.axonivy.portal.component.util;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public final class UserFormatUtils {
  private UserFormatUtils() {
  }
 
  public static String format(String fullName, String username) {
    if (StringUtils.isBlank(username)) {
      return Ivy.cms().co("/Dialogs/com/axonivy/portal/component/ProcessHistory/NotAvailable");
    }

    if (StringUtils.isBlank(fullName)) {
      return username.startsWith("#") ? username.substring(1) : username;
    }

    return fullName;
  }

  public static String formatWithTip(String fullName, String username) {
    if (StringUtils.isBlank(username)) {
      return StringUtils.EMPTY;
    }

    String formattedUsername = username.startsWith("#") ? username.substring(1) : username;
    if (StringUtils.isBlank(fullName)) {
      return "<" + Ivy.cms().co("/Dialogs/com/axonivy/portal/component/ProcessHistory/NoName") + ">" + " (" + formattedUsername + ")";
    }

    return fullName + " (" + formattedUsername + ")"; 
  }
}
