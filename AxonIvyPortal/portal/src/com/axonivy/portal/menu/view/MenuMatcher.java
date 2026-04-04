package com.axonivy.portal.menu.view;

import java.util.function.Predicate;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.CustomMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;
import com.axonivy.portal.util.MenuUtils;

import ch.addon.portal.generic.menu.SubMenuItem;

public class MenuMatcher {

  public static Predicate<PortalMenuItemDefinition> matcherFor(SubMenuItem subMenu) {
    return switch (subMenu.getMenuKind()) {
      case PROCESS -> matchProcessMenu(subMenu);
      case STANDARD -> matchStandardMenu(subMenu);
      case CUSTOM -> matchCustomMenu(subMenu);
      case MAIN_DASHBOARD -> matchDashboardMenu(subMenu);
      case EXTERNAL_LINK -> matchExternalLink(subMenu);
      case STATIC_PAGE -> matchStaticPage(subMenu);
      default -> null;
    };
  }

  private static Predicate<PortalMenuItemDefinition> matchProcessMenu(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.STANDARD && menu instanceof StandardMenuItemDefinition standard
        && standard.getStandardType() == StandardMenuItemDefinitionType.PROCESS;
  }

  private static Predicate<PortalMenuItemDefinition> matchStandardMenu(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.STANDARD && menu instanceof StandardMenuItemDefinition standard
        && subMenu.getLabel().equals(MenuUtils.getDisplayTitle(standard));
  }

  private static Predicate<PortalMenuItemDefinition> matchCustomMenu(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.CUSTOM && menu instanceof CustomMenuItemDefinition custom
        && subMenu.getLabel().equals(MenuUtils.getDisplayTitle(custom));
  }

  private static Predicate<PortalMenuItemDefinition> matchDashboardMenu(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.MAIN_DASHBOARD && menu.getId().equals(subMenu.getId());
  }

  private static Predicate<PortalMenuItemDefinition> matchExternalLink(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.EXTERNAL_LINK && menu.getUrl().equals(subMenu.getLink())
        && MenuUtils.getDisplayTitle(menu).equals(subMenu.getLabel());
  }

  private static Predicate<PortalMenuItemDefinition> matchStaticPage(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.STATIC_PAGE && menu.getUrl().equals(subMenu.getLink())
        && MenuUtils.getDisplayTitle(menu).equals(subMenu.getLabel());
  }
}
