package ch.ivy.addon.portalkit.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.migration.dashboard.migrator.JsonDashboardMigrator;
import com.axonivy.portal.migration.dashboardtemplate.migrator.JsonDashboardTemplateMigrator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.addon.portal.generic.menu.MenuView.PortalDashboardItemWrapper;
import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardOrder;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;

public class DashboardUtils {

  public final static String DASHBOARD_MENU_PREFIX = "_js__";
  public final static String PARENT_DASHBOARD_MENU_POSTFIX = "-parent-dashboard";
  public final static String MAIN_DASHBOARD_MENU_POSTFIX = "-main-dashboard";
  public final static String SUB_DASHBOARD_MENU_POSTFIX = "-sub-dashboard";
  public final static String PARENT_DASHBOARD_MENU_PATTERN = DASHBOARD_MENU_PREFIX + "%s" + PARENT_DASHBOARD_MENU_POSTFIX;
  public final static String MAIN_DASHBOARD_MENU_PATTERN = DASHBOARD_MENU_PREFIX + "%s" + MAIN_DASHBOARD_MENU_POSTFIX;
  public final static String SUB_DASHBOARD_MENU_PATTERN = DASHBOARD_MENU_PREFIX + "%s" + SUB_DASHBOARD_MENU_POSTFIX;
  public final static String DASHBOARD_PAGE_URL =
      "/ch.ivy.addon.portal.generic.dashboard.PortalDashboard/PortalDashboard.xhtml";
  public final static String DASHBOARD_MENU_JS_CLASS = "js-dashboard-group";
  public final static String HIGHLIGHT_DASHBOARD_ITEM_METHOD_PATTERN = "highlightDashboardItem('%s')";
  public final static String DASHBOARD_TASK_TEMPLATE_ID = "default-task-list-dashboard";
  private static final String DASHBOARD_TASK_TEMPLATE_JSON = """
      {
        "id": "default-task-list-dashboard",
        "version": "11.4.0",
        "templateId": "create-from-scratch",
        "titles": [
          { "locale": "en", "value": "Tasks" },
          { "locale": "fr", "value": "Tâches" },
          { "locale": "de", "value": "Aufgaben" },
          { "locale": "es", "value": "Tareas" }
        ],
        "icon": "si-task-list-edit",
        "description": "Default Task List Dashboard",
        "widgets": [
          {
            "type": "task",
            "id": "task_e16cbee90a244f4a857d0a8c875b7b34",
            "names": [
              { "locale": "en", "value": "Your Tasks" },
              { "locale": "fr", "value": "Vos Tâches" },
              { "locale": "de", "value": "Ihre Aufgaben" },
              { "locale": "es", "value": "Tus Tareas" }
            ],
            "layout": { "w": 12, "h": 8, "x": 0, "y": 0 },
            "rowsPerPage": 5,
            "enableQuickSearch": true,
            "showWidgetInfo": true,
            "showFullscreenMode": true,
            "columns": [
              { "field": "start", "width": "50" },
              { "field": "priority", "width": "60" },
              { "field": "id", "width": "80" },
              { "field": "name", "quickSearch": true, "width": "600" },
              { "field": "description", "quickSearch": true, "width": "250" },
              { "field": "activator", "width": "125" },
              { "field": "state", "width": "70" },
              { "field": "startTimestamp", "width": "95" },
              { "field": "expiryTimestamp", "width": "105" },
              { "field": "category", "width": "110" },
              { "field": "actions", "width": "85" }
            ],
            "canWorkOn": false,
            "sortField": "id",
            "sortDescending": true
          }
        ],
        "permissions": [ "Everybody" ],
        "isTopMenu": true
      }
      """;

  public static List<Dashboard> getVisibleDashboards(String dashboardJson) {
    List<Dashboard> dashboards = jsonToDashboards(dashboardJson);
    dashboards.removeIf(dashboard -> {
      List<String> permissions = dashboard.getPermissions();
      if (permissions == null) {
        return false;
      }
      return permissions.stream().noneMatch(DashboardUtils::isSessionUserHasPermisson);
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

  public static Dashboard jsonToDashboard(String dashboardJson) {
    if (StringUtils.isBlank(dashboardJson)) {
      return null;
    }

    ObjectMapper objectMapper = new ObjectMapper();
    Dashboard dashboard = null;

    try {
      dashboard = objectMapper.readValue(dashboardJson, Dashboard.class);
      initDefaultPermission();
    } catch (IOException e) {
      Ivy.log().error("Failed to read dashboard from JSON {0}", e, dashboardJson);
    }

    return dashboard;
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
      collectedDashboards.addAll(getVisiblePublicDashboards());
      collectedDashboards.addAll(jsonToDashboards(dashboardInUserProperty));
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
    Dashboard taskTemplateDashboard = DashboardUtils.getDefaultTaskListDashboard();
    String dashboardJson = Ivy.var().get(PortalVariable.DASHBOARD.key);
    List<Dashboard> visibleDashboards = jsonToDashboards(dashboardJson);
    if (!visibleDashboards.contains(taskTemplateDashboard)) {
      visibleDashboards.add(0, taskTemplateDashboard);
    }
    setDashboardAsPublic(visibleDashboards);
    return visibleDashboards;
  }

  public static List<DashboardTemplate> getDashboardTemplates() {
    String dashboardTemplatesJson = Ivy.var().get(PortalVariable.DASHBOARD_TEMPLATES.key);
    return convertDashboardTemplatesToLatestVersion(dashboardTemplatesJson);
  }

  public static void setDashboardAsPublic(List<Dashboard> visibleDashboards) {
    visibleDashboards.forEach(dashboard -> dashboard.setIsPublic(true));
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
    Dashboard defaultTaskListDashboard = getDefaultTaskListDashboard();
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
    if (!collectedDashboards.contains(defaultTaskListDashboard)) {
      collectedDashboards.add(0, defaultTaskListDashboard);
    }
    return collectedDashboards;
  }

  public static List<Dashboard> collectMainDashboards() {
    List<Dashboard> collectedDashboards =
        new ArrayList<>(collectDashboards().stream().filter(dashboard -> dashboard.getIsTopMenu()).toList());
    return collectedDashboards;
  }


  public static void highlightDashboardMenuItem(String selectedDashboardId) {
    PrimeFaces.current().executeScript(String.format(HIGHLIGHT_DASHBOARD_ITEM_METHOD_PATTERN, selectedDashboardId));
  }

  public static void updateSelectedDashboardToSession(String selectedMenuItemId) {
    if (selectedMenuItemId != null && (selectedMenuItemId.contains(PARENT_DASHBOARD_MENU_POSTFIX)
        || selectedMenuItemId.contains(SUB_DASHBOARD_MENU_POSTFIX)
        || selectedMenuItemId.contains(MAIN_DASHBOARD_MENU_POSTFIX))) {

      String[] menuIds = selectedMenuItemId.split(":");

      String[] dashboardIds = menuIds[menuIds.length - 1].split(DASHBOARD_MENU_PREFIX);

      String dashboardId = dashboardIds[dashboardIds.length - 1].replace(PARENT_DASHBOARD_MENU_POSTFIX, "")
          .replace(SUB_DASHBOARD_MENU_POSTFIX, "").replace(MAIN_DASHBOARD_MENU_POSTFIX, "");

      DashboardUtils.storeDashboardInSession(dashboardId);
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

  public static List<Dashboard> convertDashboardsFromUploadFileToLatestVersion(InputStream inputStream)
      throws IOException {
    try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
      ObjectMapper mapper = new ObjectMapper();
      JsonDashboardMigrator migrator = new JsonDashboardMigrator(mapper.readTree(reader));
      return BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), Dashboard.class);
    } catch (JsonProcessingException e) {
      Ivy.log().error("Failed to read dashboard from JSON {0}", e);
    }
    return null;
  }

  public static Dashboard convertDashboardToLatestVersion(InputStream inputStream) throws IOException {
    try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
      ObjectMapper mapper = new ObjectMapper();
      JsonDashboardMigrator migrator = new JsonDashboardMigrator(mapper.readTree(reader));
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
    storeDashboardInSession(id, isMainDashboard(id));
  }

  public static void storeDashboardInSession(String id, boolean isMainDashboard) {
    Ivy.session().setAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString(), id);
    if (!isMainDashboard) {
      Ivy.session().setAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.toString(), id);
    }
  }

  public static Dashboard getDefaultTaskListDashboard() {
    Dashboard defautTaskListDashboard = new Dashboard();
    defautTaskListDashboard.setId(DASHBOARD_TASK_TEMPLATE_ID);
    return jsonToDashboard(DASHBOARD_TASK_TEMPLATE_JSON);
  }

  public static List<Dashboard> getDashboardsWithoutMenuItem() {
    var dashboards = collectDashboards();
    return dashboards.stream().filter(dashboard -> !dashboard.getIsTopMenu()).toList();
  }

  public static String getSelectedMainDashboardIdFromSession() {
    return (String) Ivy.session().getAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString());
  }

  private static PortalDashboardItemWrapper getPortalDashboardItemWrapper() {
    String sessionUserId = UserUtils.getSessionIdentifierAttribteWithInitIfEmpty();
    return (PortalDashboardItemWrapper) IvyCacheService.getInstance()
        .getSessionCacheValue(IvyCacheIdentifier.PORTAL_DASHBOARDS, sessionUserId).orElse(null);
  }

  public static boolean isMainDashboard(String dashboardId) {
    if (StringUtils.isEmpty(dashboardId)) {
      return false;
    }
    boolean isMainDashboard = Optional.ofNullable(getPortalDashboardItemWrapper())
        .map(wrapper -> wrapper.dashboards()).orElse(new ArrayList<>()).stream()
        .filter(dashboard -> dashboardId.equals(dashboard.getId())).map(dashboard -> dashboard.getIsTopMenu())
        .findFirst().orElse(true);
    // default is true because we need extra handling for not main menu like updating selected dashboard in
    // session attribute
    return isMainDashboard;
   }
}
