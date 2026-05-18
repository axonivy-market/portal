package com.axonivy.portal.menu.view;

import java.util.Objects;
import java.util.function.Predicate;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.dto.menu.DashboardMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;

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
        && subMenu.getLabel() != null && subMenu.getLabel().equals(standard.getDisplayTitle());
  }

  private static Predicate<PortalMenuItemDefinition> matchCustomMenu(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.CUSTOM
        && (idsMatch(subMenu, menu) || labelMatches(subMenu, menu));
  }

  private static Predicate<PortalMenuItemDefinition> matchDashboardMenu(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.MAIN_DASHBOARD
        && menu instanceof DashboardMenuItemDefinition dashboardMenu
        && dashboardMenu.getDashboardId() != null
        && subMenu.getLink() != null
        && subMenu.getLink().contains(dashboardMenu.getDashboardId());
  }

  private static Predicate<PortalMenuItemDefinition> matchExternalLink(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.EXTERNAL_LINK
        && (idsMatch(subMenu, menu) || urlAndLabelMatch(subMenu, menu));
  }

  private static Predicate<PortalMenuItemDefinition> matchStaticPage(SubMenuItem subMenu) {
    return menu -> menu.getType() == MenuKind.STATIC_PAGE
        && (idsMatch(subMenu, menu) || urlAndLabelMatch(subMenu, menu));
  }

  private static boolean idsMatch(SubMenuItem subMenu, PortalMenuItemDefinition menu) {
    return subMenu.getId() != null && subMenu.getId().equals(menu.getId());
  }

  private static boolean labelMatches(SubMenuItem subMenu, PortalMenuItemDefinition menu) {
    return subMenu.getLabel() != null && Objects.equals(subMenu.getLabel(), menu.getDisplayTitle());
  }

  private static boolean urlAndLabelMatch(SubMenuItem subMenu, PortalMenuItemDefinition menu) {
    return subMenu.getLink() != null && Objects.equals(subMenu.getLink(), menu.getUrl())
        && labelMatches(subMenu, menu);
  }
}
