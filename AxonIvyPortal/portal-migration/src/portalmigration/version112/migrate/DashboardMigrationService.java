package portalmigration.version112.migrate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import portalmigration.constant.Version;
import portalmigration.version112.converter.BusinessEntityConverter;
import portalmigration.version112.dto.CaseDashboardWidget;
import portalmigration.version112.dto.Dashboard;
import portalmigration.version112.dto.DashboardTemplate;
import portalmigration.version112.dto.WidgetFilterModel;
import portalmigration.version112.dto.casecolumn.CaseColumnModel;
import portalmigration.version112.enums.DashboardStandardCaseColumn;
import portalmigration.version112.enums.DashboardWidgetType;
import portalmigration.version112.enums.PortalVariable;
import portalmigration.version112.service.WidgetFilterService;
import portalmigration.version112.util.DashboardUtils;

public class DashboardMigrationService {
  public static void migrateDashboardsTo112() {
    // Migrate public dashboard
    List<Dashboard> publicDashboards = DashboardUtils.getPublicDashboards();
    migrateDashboardsTo112(publicDashboards);

    // Save public dashboards
    String publicDashboardJson = BusinessEntityConverter.entityToJsonValue(publicDashboards);
    Ivy.var().set(PortalVariable.DASHBOARD.key, publicDashboardJson);

    // Migrate all private dashboards
    Ivy.security().users().paged(100) .forEach(user -> {
      List<Dashboard> userDashboards =
          DashboardUtils.jsonToDashboards(user.getProperty(PortalVariable.DASHBOARD.key));
      migrateDashboardsTo112(userDashboards);

      // save private dashboards
      String userDashboardJson = BusinessEntityConverter.entityToJsonValue(publicDashboards);
      user.setProperty(PortalVariable.DASHBOARD.key, userDashboardJson);
    });

  }

  private static void migrateDashboardsTo112(List<Dashboard> dashboards) {
    if (CollectionUtils.isNotEmpty(dashboards)) {
      for (Dashboard dashboard : dashboards) {
        migrateDashboardTo112(dashboard);
      }
    }
  }

  public static void migrateDashboardTemplatesTo112() {
    List<DashboardTemplate> templates = DashboardUtils.getDashboardTemplates();
    if (CollectionUtils.isNotEmpty(templates)) {
      for(DashboardTemplate template : templates) {
        Dashboard dashboard = template.getDashboard();
        migrateDashboardTo112(dashboard);
      }
    }

    // Save dashboard templates
    String templatesJson = BusinessEntityConverter.entityToJsonValue(templates);
    Ivy.var().set(PortalVariable.DASHBOARD_TEMPLATES.key, templatesJson);
  }

  public static void migrateWidgetFilterSetsTo112() {
    List<WidgetFilterModel> widgetFilters = WidgetFilterService.getInstance().findAll();
    adaptCaseBusinessStateForWidgetFilterSets(widgetFilters);
  }

  private static void migrateDashboardTo112(Dashboard dashboard) {
    if (StringUtils.isBlank(dashboard.getVersion()) || dashboard.getVersion().contentEquals(Version.VERSION_11_1_0)) {
      adaptCaseBusinessStateForDashboard(dashboard);

      // Increase version after update
      dashboard.setVersion(Version.VERSION_11_2_0);
    }
  }

  /**
   * Adapt case business state for dashboards
   * IVYPORTAL-14663: Introduce CaseBusinessState
   * 
   * @param dashboard
   */
  private static void adaptCaseBusinessStateForDashboard(Dashboard dashboard) {
    dashboard.getWidgets().forEach(widget -> {
      if (widget instanceof CaseDashboardWidget) {
        CaseDashboardWidget caseWidget = (CaseDashboardWidget) widget;
        CaseColumnModel stateColumn = caseWidget.getColumns().stream()
            .filter(col -> col.getField().contentEquals(DashboardStandardCaseColumn.STATE.getField())).findFirst().orElseGet(null);

        if (stateColumn != null) {
          stateColumn.setFilterList(updateFilters(stateColumn.getFilterList()));
        }
      }
    });
  }

  /**
   * Adapt case business state for widget filter sets
   * IVYPORTAL-14663: Introduce CaseBusinessState
   * 
   * @param dashboard
   */
  private static void adaptCaseBusinessStateForWidgetFilterSets(List<WidgetFilterModel> widgetFilters) {
    widgetFilters.stream().filter(f -> f.getWidgetType() == DashboardWidgetType.CASE).forEach(filter -> {
      filter.getFilterableColumns().stream()
        .filter(col -> col.getField().contentEquals("state"))
        .forEach(col -> {
          col.setFilterList(updateFilters(col.getUserFilterList()));
      });
      WidgetFilterService.getInstance().save(filter);
    });
  }

  private static List<String> updateFilters(List<String> oldFilters) {
    List<String> newFilters = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(oldFilters)) {
      if (oldFilters.contains(CaseState.RUNNING.name()) || oldFilters.contains(CaseState.CREATED.name())) {
        newFilters.add(CaseBusinessState.OPEN.name());
      }
      if (oldFilters.contains(CaseState.DONE.name())) {
        newFilters.add(CaseBusinessState.DONE.name());
      }
      if (oldFilters.contains(CaseState.DESTROYED.name())) {
        newFilters.add(CaseBusinessState.DESTROYED.name());
      }
    }
    return newFilters;
  }
}
