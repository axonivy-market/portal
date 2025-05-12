package com.axonivy.portal.util;

import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CASE;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CLIENT_STATISTIC;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CUSTOM;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.NEWS;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.NOTIFICATION;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.PROCESS;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.PROCESS_VIEWER;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.TASK;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.WELCOME;

import java.io.IOException;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.dto.dashboard.NewsDashboardWidget;
import com.axonivy.portal.dto.dashboard.NotificationDashboardWidget;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ClientStatisticDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CombinedProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.FullProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ImageProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessViewerDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.CustomWidgetUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class DashboardCloneUtils {

  public static DashboardWidget cloneWidget(DashboardWidget cloneFromWidget) {
    DashboardWidget widget = null;
    cloneFromWidget = DashboardWidgetUtils.buildWidgetColumns(cloneFromWidget);
    switch (cloneFromWidget) {
      case TaskDashboardWidget taskWidget -> {
        widget = DashboardWidgetUtils.buildTaskColumns(taskWidget);
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(TASK));
      }
      case CaseDashboardWidget caseWidget -> {
        widget = DashboardWidgetUtils.buildCaseColumns(caseWidget);
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(CASE));
      }
      case FullProcessDashboardWidget fullProcessWidget -> {
        DashboardWidgetUtils.loadProcessesOfWidget(fullProcessWidget);
        widget = fullProcessWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(PROCESS));
      }
      case ImageProcessDashboardWidget imageProcessWidget -> {
        DashboardWidgetUtils.loadProcessesOfWidget(imageProcessWidget);
        widget = imageProcessWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(PROCESS));
      }
      case CompactProcessDashboardWidget compactProcessWidget -> {
        DashboardWidgetUtils.loadProcessesOfWidget(compactProcessWidget);
        widget = compactProcessWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(PROCESS));
      }
      case CombinedProcessDashboardWidget combinedProcessWidget -> {
        DashboardWidgetUtils.loadProcessesOfWidget(combinedProcessWidget);
        widget = combinedProcessWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(PROCESS));
      }
      case CustomDashboardWidget customWidget -> {
        widget = customWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(CUSTOM));
      }
      case ProcessViewerDashboardWidget processViewerWidget -> {
        CustomWidgetUtils.loadDataForProcessViewerWidget(processViewerWidget);
        widget = processViewerWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(PROCESS_VIEWER));
      }
      case WelcomeDashboardWidget welcomeWidget -> {
        widget = welcomeWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(WELCOME));
        try {
          WelcomeWidgetUtils.cloneImage(welcomeWidget.getImageType(),
              welcomeWidget.getImageLocation(), welcomeWidget, false);
          WelcomeWidgetUtils.cloneImage(welcomeWidget.getImageTypeDarkMode(),
              welcomeWidget.getImageLocationDarkMode(), welcomeWidget, true);
        } catch (IOException e) {
          Ivy.log().error(e);
        }
      }
      case NewsDashboardWidget newsWidget -> {
        widget = newsWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(NEWS));
      }
      case ClientStatisticDashboardWidget statisticWidget -> {
        widget = statisticWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(CLIENT_STATISTIC));
      }
      case NotificationDashboardWidget notificationWidget -> {
        widget = notificationWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(NOTIFICATION));
      }
      case NavigationDashboardWidget navigationDashboardWidget -> {
        widget = navigationDashboardWidget;
        widget.setId(DashboardWidgetUtils.generateNewWidgetId(DashboardWidgetType.NAVIGATION_DASHBOARD));
      }
      default -> {}
    }

    if (widget != null) {
      widget.setAutoPosition(true);
    }
    return widget;
  }
}
