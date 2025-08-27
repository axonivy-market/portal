package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;

import ch.addon.portal.generic.menu.PortalMenuItem;
import ch.ivy.addon.portalkit.util.DefaultDashboardUtils;

public final class DefaultMenuUtils {
  public static List<PortalMenuItem> buildDefaultMenus() {
    List<PortalMenuItemDefinition> definitions = buildDefaultMenuDefinitions();
    List<PortalMenuItem> result = new ArrayList<>();

    for (var definition : definitions) {
      result.add(definition.convertToPortalMenuItem());
    }
    return result;
  }

  public static List<PortalMenuItemDefinition> buildDefaultMenuDefinitions() {
    List<PortalMenuItemDefinition> result = new ArrayList<>();
    result.add(new StandardMenuItemDefinition(StandardMenuItemDefinitionType.CASE));
    result.add(new StandardMenuItemDefinition(StandardMenuItemDefinitionType.TASK));
    result.add(new StandardMenuItemDefinition(StandardMenuItemDefinitionType.PROCESS));
    return result;
  }

  public static void addDefaultMenus(List<PortalMenuItemDefinition> definitions) {
    definitions = Optional.ofNullable(definitions).orElseGet(() -> new ArrayList<>());
    addDefaultDashboardMenu(definitions);
    addDefaultProcessMenu(definitions);
    addDefaultCaseMenu(definitions);
    addDefaultTaskMenu(definitions);
  }

  private static void addDefaultDashboardMenu(List<PortalMenuItemDefinition> definitions) {
    PortalMenuItemDefinition dashboardMenu = new StandardMenuItemDefinition(StandardMenuItemDefinitionType.DASHBOARD);
    dashboardMenu.setIndex(0);
    definitions.add(dashboardMenu);
  }

  private static void addDefaultProcessMenu(List<PortalMenuItemDefinition> definitions) {
    PortalMenuItemDefinition processMenu = new StandardMenuItemDefinition(StandardMenuItemDefinitionType.PROCESS);
    processMenu.setIndex(1);
    definitions.add(processMenu);
  }

  private static void addDefaultTaskMenu(List<PortalMenuItemDefinition> definitions) {
    PortalMenuItemDefinition taskMenu = MenuUtils.fromDashboard(DefaultDashboardUtils.getDefaultTaskListDashboard());
    taskMenu.setIndex(2);
    definitions.add(taskMenu);
  }

  private static void addDefaultCaseMenu(List<PortalMenuItemDefinition> definitions) {
    PortalMenuItemDefinition caseMenu = MenuUtils.fromDashboard(DefaultDashboardUtils.getDefaultCaseListDashboard());
    caseMenu.setIndex(3);
    definitions.add(caseMenu);
  }
}