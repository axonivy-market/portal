package com.axonivy.portal.util;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivyteam.ivy.environment.Ivy;

public class DisplayNameUtils {

  public static String findDisplayNameOfUserLanguage(List<DisplayName> names) {
    String userLanguage = LanguageService.getInstance().getSupportedLanguageInPortal();
    Ivy.log().info(userLanguage);
    return CollectionUtils.emptyIfNull(names).stream()
        .filter(name -> userLanguage.equalsIgnoreCase(name.getLocale().toString())).findFirst()
        .map(DisplayName::getValue).orElse("");
  }

}
