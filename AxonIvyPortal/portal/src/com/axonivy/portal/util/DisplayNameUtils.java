package com.axonivy.portal.util;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.UserUtils;

public class DisplayNameUtils {

  public static String findDisplayNameOfUserLanguage(List<DisplayName> names) {
    String userLanguage = StringUtils.upperCase(UserUtils.getUserLanguage());
    return CollectionUtils.emptyIfNull(names).stream()
        .filter(name -> userLanguage.equalsIgnoreCase(name.getLocale().toString())).findFirst()
        .map(DisplayName::getValue).orElse("");
  }

}
