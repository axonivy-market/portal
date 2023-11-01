package ch.ivy.addon.portalkit.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.migration.dashboard.migrator.JsonDashboardMigrator;
import com.axonivy.portal.migration.dashboardtemplate.migrator.JsonDashboardTemplateMigrator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    if (StringUtils.isBlank(dashboardJSON)) {
      return new ArrayList<>();
    }

    List<Dashboard> mappingDashboards = convertDashboardsToLatestVersion(dashboardJSON);
    mappingDashboards.forEach(dashboard -> initDefaultPermission());
    return mappingDashboards;
  }

  private static Consumer<Dashboard> initDefaultPermission() {
    return dashboard -> {
      if (CollectionUtils.isEmpty(dashboard.getPermissions())) {
        ArrayList<String> defaultPermissions = new ArrayList<>();
        defaultPermissions.add(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
        dashboard.setPermissions(defaultPermissions);
      }
    };
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
    return convertDashboardTemplatesToLatestVersion(dashboardTemplatesJson);
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
    dashboards.forEach(dashboard -> idToDashboard.put(dashboard.getId(), dashboard));
    return idToDashboard;
  }

  public static String generateId() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  private static String readDashboardBySessionUser() {
    if (Ivy.session().isSessionUserUnknown()) {
      return "";
    }
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

  public static List<Dashboard> convertDashboardsToLatestVersion(String json) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonDashboardMigrator migrator = new JsonDashboardMigrator(mapper.readTree(json));
      return BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), Dashboard.class);
    } catch (JsonProcessingException ex) {
      Ivy.log().error("Failed to read dashboard from JSON {0}", ex, json);
    }
    return null;
  }

  public static List<Dashboard> convertDashboardsFromUploadFileToLastestVersion(InputStream inputStream)
      throws IOException {
    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonDashboardMigrator migrator = new JsonDashboardMigrator(mapper.readTree(inputStream));
      return BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), Dashboard.class);
    } catch (JsonProcessingException e) {
      Ivy.log().error("Failed to read dashboard from JSON {0}", e);
    }
    return null;
  }

  public static Dashboard convertDashboardToLatestVersion(InputStream inputStream) throws IOException {
    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonDashboardMigrator migrator = new JsonDashboardMigrator(mapper.readTree(inputStream));
      return BusinessEntityConverter.convertJsonNodeToEntity(migrator.migrate(), Dashboard.class);
    } catch (JsonProcessingException ex) {
      Ivy.log().error("Failed to read dashboard from JSON {0}", ex);
    }
    return null;
  }

  private static List<DashboardTemplate> convertDashboardTemplatesToLatestVersion(String json) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonDashboardTemplateMigrator migrator = new JsonDashboardTemplateMigrator(mapper.readTree(json));
      return BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), DashboardTemplate.class);
    } catch (JsonProcessingException ex) {
      Ivy.log().error("Failed to read dashboard template from JSON {0}", ex, json);
    }
    return null;
  }
  
  
  public static void storeDashboardInSession(String id) {
    Ivy.session().setAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString(), id);
  }
}
