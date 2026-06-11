package com.axonivy.portal.util;

import java.net.URI;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.Locales;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;

import ch.addon.portal.generic.menu.PortalMenuItem;
import ch.ivy.addon.portalkit.dto.DisplayName;

public class MenuUtils {

  public static final Set<String> SAFE_URL_SCHEMES = Set.of("http", "https");

  /**
   * Render-time guard for menu URLs: items can originate from raw variable edits or
   * callable processes that bypass the admin form validator, so unsafe schemes
   * (javascript:, data:, ...) must not reach an href rendered for every user.
   * Relative links (no scheme) are allowed; unparsable URLs are treated as unsafe.
   */
  public static String safeExternalUrl(String url) {
    if (StringUtils.isBlank(url)) {
      return null;
    }
    String trimmed = url.trim();
    try {
      String scheme = URI.create(trimmed).getScheme();
      if (scheme == null || SAFE_URL_SCHEMES.contains(scheme.toLowerCase())) {
        return trimmed;
      }
    } catch (IllegalArgumentException ex) {
      // fall through - treat unparsable URLs as unsafe
    }
    return null;
  }

  /**
   * Static pages render inside the portal frame, so the path must stay within the
   * application: absolute URLs, protocol-relative ({@code //}) and backslash variants
   * are rejected to prevent framing external content or open redirects.
   */
  public static boolean isSafeRelativePath(String path) {
    if (StringUtils.isBlank(path)) {
      return false;
    }
    String trimmed = path.trim();
    if (trimmed.contains("\\") || trimmed.startsWith("//")) {
      return false;
    }
    try {
      return URI.create(trimmed).getScheme() == null;
    } catch (IllegalArgumentException ex) {
      return false;
    }
  }

  public static String buildIconClass(String icon) {
    if (StringUtils.isBlank(icon)) {
      return PortalMenuItem.DEFAULT_DASHBOARD_ICON;
    }
    // Icon already prefixed with family — caller passed full class string.
    if (icon.contains(" ")) {
      return icon;
    }
    if (icon.startsWith("fa-")) {
      return "fa " + icon;
    }
    if (icon.startsWith("tif-")) {
      return "tif " + icon;
    }
    if (icon.startsWith("si-")) {
      return "si " + icon;
    }
    return "ti " + icon;
  }

  public static String getDisplayTitle(PortalMenuItemDefinition menu) {
    if (menu == null || CollectionUtils.isEmpty(menu.getTitles())) {
      return StringUtils.EMPTY;
    }

    String currentLocaleLang = Locales.getCurrentLocale().toLanguageTag();
    return menu.getTitles().stream().filter(title -> currentLocaleLang.equals(title.getLocale().toLanguageTag()))
        .map(DisplayName::getValue).filter(StringUtils::isNotBlank).findFirst()
        // Items may carry no translation for the current locale (e.g. dashboards or
        // third-party apps configured in one language only) — fall back to any
        // non-blank title instead of rendering a blank menu label.
        .orElseGet(() -> menu.getTitles().stream()
            .map(DisplayName::getValue).filter(StringUtils::isNotBlank).findFirst()
            .orElse(StringUtils.EMPTY));
  }
}
