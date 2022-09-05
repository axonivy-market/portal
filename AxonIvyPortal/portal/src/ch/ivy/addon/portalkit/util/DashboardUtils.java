package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardOrder;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;

public class DashboardUtils {

  public final static String DASHBOARD_MENU_PREFIX = "_js__";
  public final static String DASHBOARD_MENU_POSTFIX  = "-main-dashboard";
  public final static String DASHBOARD_MENU_ITEM_POSTFIX = "-sub-dashboard";
  public final static String DASHBOARD_MENU_PATTERN = DASHBOARD_MENU_PREFIX + "%s" + DASHBOARD_MENU_POSTFIX;
  public final static String DASHBOARD_MENU_ITEM_PATTERN = DASHBOARD_MENU_PREFIX + "%s" + DASHBOARD_MENU_ITEM_POSTFIX;
  public final static String DASHBOARD_PAGE_URL = "/ch.ivy.addon.portal.generic.dashboard.PortalDashboard/PortalDashboard.xhtml";
  public final static String DASHBOARD_MENU_JS_CLASS = "js-dashboard-group";
  public final static String HIGHLIGHT_DASHBOARD_ITEM_METHOD_PATTERN = "highlightDashboardItem('%s')";

  public static List<Dashboard> getVisibleDashboards(String dashboardJson) {
    List<Dashboard> dashboards = jsonToDashboards(dashboardJson);
    dashboards.removeIf(dashboard -> {
      List<String> permissions = dashboard.getPermissions();
      if (permissions == null) {
        return false;
      } else {
        for (String permission : permissions) {
          if (isSessionUserHasPermisson(permission)) {
            return false;
          }
        }
      }
      return true;
    });
    return dashboards;
  }

  private static boolean isSessionUserHasPermisson(String permission) {
    return StringUtils.startsWith(permission, "#") ? StringUtils.equals(currentUser().getMemberName(), permission)
        : PermissionUtils.doesSessionUserHaveRole(permission);
  }

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

  private static IUser currentUser() {
    return Ivy.session().getSessionUser();
  }

  public static List<Dashboard> getAllVisibleDashboardsOfSessionUser() {
    List<Dashboard> collectedDashboards = new ArrayList<>();
    String dashboardInUserProperty = readDashboardBySessionUser();
    try {
      collectedDashboards = getVisiblePublicDashboards();
      List<Dashboard> myDashboards = jsonToDashboards(dashboardInUserProperty);
      collectedDashboards.addAll(myDashboards);
    } catch (PortalException e) {
      // If errors like parsing JSON errors, ignore them
      Ivy.log().error(e);
    }
    return collectedDashboards;
  }

  public static List<Dashboard> getVisiblePublicDashboards() {
    String dashboardJson = Ivy.var().get(PortalVariable.DASHBOARD.key);
    List<Dashboard> visibleDashboards = getVisibleDashboards(dashboardJson);
    setDashboardAsPublic(visibleDashboards);
    return visibleDashboards;
  }

  public static List<Dashboard> getPublicDashboards() {
    String dashboardJson = Ivy.var().get(PortalVariable.DASHBOARD.key);
    List<Dashboard> visibleDashboards = jsonToDashboards(dashboardJson);
    setDashboardAsPublic(visibleDashboards);
    return visibleDashboards;
  }

  public static List<DashboardTemplate> getDashboardTemplates() {
    String dashboardTemplatesJson = Ivy.var().get(PortalVariable.DASHBOARD_TEMPLATES.key);
    return BusinessEntityConverter.jsonValueToEntities(dashboardTemplatesJson, DashboardTemplate.class);
  }

  public static void setDashboardAsPublic(List<Dashboard> visibleDashboards) {
    visibleDashboards.stream().forEach(dashboard -> dashboard.setIsPublic(true));
  }

  public static List<DashboardOrder> getDashboardOrdersOfSessionUser() {
    var dashboardOrderJson = currentUser().getProperty(PortalVariable.DASHBOARD_ORDER.key);
    return BusinessEntityConverter.jsonValueToEntities(dashboardOrderJson, DashboardOrder.class);
  }

  public static Map<String, Dashboard> createMapIdToDashboard(List<Dashboard> dashboards) {
    Map<String, Dashboard> idToDashboard = new LinkedHashMap<>();
    for (Dashboard dashboard : dashboards) {
      idToDashboard.put(dashboard.getId(), dashboard);
    }
    return idToDashboard;
  }

  public static String generateId() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  private static String readDashboardBySessionUser() {
    return currentUser().getProperty(PortalVariable.DASHBOARD.key);
  }

  public static List<Dashboard> collectDashboards() {
    List<Dashboard> visibleDashboards = getAllVisibleDashboardsOfSessionUser();
    List<DashboardOrder> dashboardOrders = getDashboardOrdersOfSessionUser();
    Map<String, Dashboard> idToDashboard = createMapIdToDashboard(visibleDashboards);
    List<Dashboard> collectedDashboards = new ArrayList<>();
    for (DashboardOrder dashboardOrder : dashboardOrders) {
      if (dashboardOrder.getDashboardId() == null) {
        continue;
      }
      Dashboard currentDashboard = idToDashboard.remove(dashboardOrder.getDashboardId());
      if (dashboardOrder.isVisible() && currentDashboard != null) {
        collectedDashboards.add(currentDashboard);
      }
    }
    collectedDashboards.addAll(idToDashboard.values());

    return collectedDashboards;
  }

  public static void highlightDashboardMenuItem(String selectedDashboardId) {
    PrimeFaces.current().executeScript(String.format(HIGHLIGHT_DASHBOARD_ITEM_METHOD_PATTERN, selectedDashboardId));
  }

  public static void updateSelectedDashboardToSession(String selectedMenuItemId) {
    if (StringUtils.endsWith(selectedMenuItemId, DASHBOARD_MENU_POSTFIX)
        || StringUtils.endsWith(selectedMenuItemId, DASHBOARD_MENU_ITEM_POSTFIX)) {
      var menuIds = selectedMenuItemId.split(":");
      var dashboardIds = menuIds[menuIds.length - 1].split(DASHBOARD_MENU_PREFIX);
      var dashboardId = dashboardIds[dashboardIds.length - 1];
      dashboardId = dashboardId.replace(DASHBOARD_MENU_POSTFIX, "");
      dashboardId = dashboardId.replace(DASHBOARD_MENU_ITEM_POSTFIX, "");
      Ivy.session().setAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString(), dashboardId);
    }
  }
}
