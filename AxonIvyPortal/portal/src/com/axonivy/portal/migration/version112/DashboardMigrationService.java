package com.axonivy.portal.migration.version112;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.JsonVersion;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class DashboardMigrationService {

  private DashboardMigrationService() {}
  private static final JsonVersion VERSION_112 = new JsonVersion("11.2.0");

  public static void migrateAllDashboardsAndRelatedData() {
    // Migrate public dashboard
    List<Dashboard> publicDashboards = DashboardUtils.getPublicDashboards();
    migrateDashboards(publicDashboards);

    // Save public dashboards
    String publicDashboardJson = BusinessEntityConverter.entityToJsonValue(publicDashboards);
    Ivy.var().set(PortalVariable.DASHBOARD.key, publicDashboardJson);

    // Migrate all private dashboards
    Ivy.security().users().paged(100) .forEach(user -> {
      List<Dashboard> userDashboards =
          DashboardUtils.jsonToDashboards(user.getProperty(PortalVariable.DASHBOARD.key));
      migrateDashboards(userDashboards);

      // save private dashboards
      String userDashboardJson = BusinessEntityConverter.entityToJsonValue(publicDashboards);
      user.setProperty(PortalVariable.DASHBOARD.key, userDashboardJson);
    });

    // Migrate dashboard templates
    migrateDashboardTemplates();
    
    // Migrate widget sets
    migrateWidgetFilterSets();
  }

  private static void migrateDashboards(List<Dashboard> dashboards) {
    if (CollectionUtils.isNotEmpty(dashboards)) {
      for (Dashboard dashboard : dashboards) {
        migrateDashboard(dashboard);
      }
    }
  }

  private static void migrateDashboardTemplates() {
    List<DashboardTemplate> templates = DashboardUtils.getDashboardTemplates();
    if (CollectionUtils.isNotEmpty(templates)) {
      for(DashboardTemplate template : templates) {
        Dashboard dashboard = template.getDashboard();
        migrateDashboard(dashboard);
      }
    }

    // Save dashboard templates
    String templatesJson = BusinessEntityConverter.entityToJsonValue(templates);
    Ivy.var().set(PortalVariable.DASHBOARD_TEMPLATES.key, templatesJson);
  }

  private static void migrateWidgetFilterSets() {
    List<WidgetFilterModel> widgetFilters = WidgetFilterService.getInstance().findAll();

    //IVYPORTAL-14663: Introduce CaseBusinessState
    CaseBusinessStateMigrationService.adaptCaseBusinessStateForWidgetFilterSets(widgetFilters);
  }

  private static void migrateDashboard(Dashboard dashboard) {
    if (StringUtils.isBlank(dashboard.getVersion()) || !dashboard.getVersion().contentEquals(VERSION_112.getValue())) {

      //IVYPORTAL-14663: Introduce CaseBusinessState
      CaseBusinessStateMigrationService.adaptCaseBusinessStateForDashboard(dashboard);

      // Increase version after update
      dashboard.setVersion(VERSION_112.getValue());
    }
  }
}
