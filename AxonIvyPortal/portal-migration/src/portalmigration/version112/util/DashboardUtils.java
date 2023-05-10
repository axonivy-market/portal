package portalmigration.version112.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import portalmigration.version112.converter.BusinessEntityConverter;
import portalmigration.version112.dto.Dashboard;
import portalmigration.version112.dto.DashboardTemplate;
import portalmigration.version112.enums.PortalVariable;

public class DashboardUtils {

  public final static String DASHBOARD_MENU_PREFIX = "_js__";
  public final static String DASHBOARD_MENU_POSTFIX  = "-main-dashboard";
  public final static String DASHBOARD_MENU_ITEM_POSTFIX = "-sub-dashboard";
  public final static String DASHBOARD_MENU_PATTERN = DASHBOARD_MENU_PREFIX + "%s" + DASHBOARD_MENU_POSTFIX;
  public final static String DASHBOARD_MENU_ITEM_PATTERN = DASHBOARD_MENU_PREFIX + "%s" + DASHBOARD_MENU_ITEM_POSTFIX;
  public final static String DASHBOARD_PAGE_URL = "/ch.ivy.addon.portal.generic.dashboard.PortalDashboard/PortalDashboard.xhtml";
  public final static String DASHBOARD_MENU_JS_CLASS = "js-dashboard-group";
  public final static String HIGHLIGHT_DASHBOARD_ITEM_METHOD_PATTERN = "highlightDashboardItem('%s')";

  public static List<Dashboard> jsonToDashboards(String dashboardJSON) {
    List<Dashboard> mappingDashboards = BusinessEntityConverter.jsonValueToEntities(dashboardJSON, Dashboard.class);
    for (Dashboard dashboard : mappingDashboards) {
      if (CollectionUtils.isEmpty(dashboard.getPermissions())) {
        ArrayList<String> defaultPermissions = new ArrayList<>();
        defaultPermissions.add(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
        dashboard.setPermissions(defaultPermissions);
      }
    }
    return mappingDashboards;
  }

  public static List<Dashboard> getPublicDashboards() {
    String dashboardJson = Ivy.var().get(PortalVariable.DASHBOARD.key);
    List<Dashboard> visibleDashboards = jsonToDashboards(dashboardJson);
    setDashboardAsPublic(visibleDashboards);
    return visibleDashboards;
  }

  public static void setDashboardAsPublic(List<Dashboard> visibleDashboards) {
    visibleDashboards.stream().forEach(dashboard -> dashboard.setIsPublic(true));
  }

  public static List<DashboardTemplate> getDashboardTemplates() {
    String dashboardTemplatesJson = Ivy.var().get(PortalVariable.DASHBOARD_TEMPLATES.key);
    return BusinessEntityConverter.jsonValueToEntities(dashboardTemplatesJson, DashboardTemplate.class);
  }
}
