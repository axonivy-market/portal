package com.axonivy.portal.menu;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;

import ch.addon.portal.generic.menu.SubMenuItem;

/**
 * Single source of truth for deterministic menu IDs. Both the sidebar
 * ({@code CustomSubMenuItemService.convertToSubmenuItem}) and the admin loader
 * ({@code MenuLoader.buildCallableMenus} / {@code buildConfigurationMenus}) must
 * compute the same ID for the same source item; routing both callers through
 * here prevents silent drift.
 */
public final class MenuId {

  private MenuId() {}

  /**
   * Compute the deterministic ID for a {@link CustomSubMenuItem}, applying the
   * same kind/link normalization the sidebar uses. Used when the source item
   * has no explicit ID.
   */
  public static String compute(CustomSubMenuItem menu) {
    return SubMenuItem.generateId(resolveKind(menu), resolveLink(menu));
  }

  private static MenuKind resolveKind(CustomSubMenuItem menu) {
    if (menu.getMenuKind() != null) {
      return menu.getMenuKind();
    }
    return Boolean.TRUE.equals(menu.getIsExternalLink()) ? MenuKind.EXTERNAL_LINK : MenuKind.PROCESS;
  }

  private static String resolveLink(CustomSubMenuItem menu) {
    String link = StringUtils.defaultString(menu.getLink());
    if (resolveKind(menu) == MenuKind.STATIC_PAGE) {
      return PortalNavigatorAPI.buildPortalStaticPageUrl(link);
    }
    return link;
  }
}
