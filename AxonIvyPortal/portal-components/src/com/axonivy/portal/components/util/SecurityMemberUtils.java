package com.axonivy.portal.components.util;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

public class SecurityMemberUtils {

  private static final int MAX_CHARACTER_NUMBER_OF_NAME_INITIALS = 2;

  public static String getNameInitials(String displayName) {
    String fullInitials = WordUtils.initials(displayName);
    return StringUtils.substring(fullInitials, 0, MAX_CHARACTER_NUMBER_OF_NAME_INITIALS);
  }

}
