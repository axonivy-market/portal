package com.axonivy.portal.util;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

public class DisplayNameUtils {

  public static String findDisplayNameOfUserLanguage(List<DisplayName> names) {
    String userLanguage = UserUtils.getUserLanguage();
    return CollectionUtils.emptyIfNull(names).stream()
        .filter(name -> userLanguage.equalsIgnoreCase(name.getLocale().toString())).findFirst()
        .map(DisplayName::getValue).orElse("");
  }
  
  public static String findLocalizedName(List<DisplayName> names) {
  return LanguageUtils.getLocalizedName(names);
  }

}
