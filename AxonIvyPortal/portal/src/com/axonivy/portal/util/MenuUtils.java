package com.axonivy.portal.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.Locales;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;

import ch.ivy.addon.portalkit.dto.DisplayName;

public class MenuUtils {

  public static String getDisplayTitle(PortalMenuItemDefinition menu) {
    if (menu == null || CollectionUtils.isEmpty(menu.getTitles())) {
      return StringUtils.EMPTY;
    }

    String currentLocaleLang = Locales.getCurrentLocale().toLanguageTag();
    return menu.getTitles().stream().filter(title -> currentLocaleLang.equals(title.getLocale().toLanguageTag()))
        .map(DisplayName::getValue).findFirst().orElse(StringUtils.EMPTY);
  }
}
