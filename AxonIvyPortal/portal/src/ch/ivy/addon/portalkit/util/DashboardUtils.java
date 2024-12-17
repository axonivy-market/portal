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
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.migration.dashboard.migrator.JsonDashboardMigrator;
import com.axonivy.portal.migration.dashboardtemplate.migrator.JsonDashboardTemplateMigrator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardOrder;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;

public class DashboardUtils {

  public final static String DASHBOARD_MENU_PREFIX = "_js__";
  public final static String PARENT_DASHBOARD_MENU_POSTFIX = "-parent-dashboard";
  public final static String MAIN_DASHBOARD_MENU_POSTFIX = "-main-dashboard";
  public final static String SUB_DASHBOARD_MENU_POSTFIX = "-sub-dashboard";
  public final static String PARENT_DASHBOARD_MENU_PATTERN =
      DASHBOARD_MENU_PREFIX + "%s" + PARENT_DASHBOARD_MENU_POSTFIX;
  public final static String MAIN_DASHBOARD_MENU_PATTERN = DASHBOARD_MENU_PREFIX + "%s" + MAIN_DASHBOARD_MENU_POSTFIX;
  public final static String SUB_DASHBOARD_MENU_PATTERN = DASHBOARD_MENU_PREFIX + "%s" + SUB_DASHBOARD_MENU_POSTFIX;
  public final static String DASHBOARD_PAGE_URL =
      "/ch.ivy.addon.portal.generic.dashboard.PortalDashboard/PortalDashboard.xhtml";
  public final static String DASHBOARD_MENU_JS_CLASS = "js-dashboard-group";
  public final static String HIGHLIGHT_DASHBOARD_ITEM_METHOD_PATTERN = "highlightDashboardItem('%s')";
  public final static String DEFAULT_TASK_LIST_DASHBOARD = "default-task-list-dashboard";
  public final static String DEFAULT_CASE_LIST_DASHBOARD = "default-case-list-dashboard";

  public static List<Dashboard> getPublicDashboards() {
    String sessionIdAttribute = SessionAttribute.SESSION_IDENTIFIER.toString();
    if (Ivy.session().getAttribute(sessionIdAttribute) == null) {
      Ivy.session().setAttribute(sessionIdAttribute, UUID.randomUUID().toString());
    }
    String sessionUserId = (String) Ivy.session().getAttribute(sessionIdAttribute);
    IvyCacheService cacheService = IvyCacheService.getInstance();
    PortalPublicDashboardWrapper portalPublicDashboardWrapper = null;
    try {
      portalPublicDashboardWrapper = (PortalPublicDashboardWrapper) cacheService
          .getSessionCacheValue(IvyCacheIdentifier.PORTAL_PUBLIC_DASHBOARD, sessionUserId).orElse(null);
    } catch (ClassCastException e) {
      cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_PUBLIC_DASHBOARD, sessionUserId);
    }

    if (portalPublicDashboardWrapper == null) {
      synchronized (sessionUserId.intern()) {
        List<Dashboard> dashboards = new ArrayList<>();
        try {
          String dashboardJson = Ivy.var().get(PortalVariable.DASHBOARD.key);
          dashboards = jsonToDashboards(dashboardJson);
          addDefaultTaskCaseListDashboardsIfMissing(dashboards);
          setDashboardAsPublic(dashboards);
        } catch (Exception e) {
          Ivy.log().error("Cannot load Public Dashboards {0}", e.getMessage());
        }
        portalPublicDashboardWrapper = new PortalPublicDashboardWrapper(dashboards);
        cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_PUBLIC_DASHBOARD, sessionUserId,
            portalPublicDashboardWrapper);
      }
    }
    return portalPublicDashboardWrapper.dashboards();
  }

  public static List<Dashboard> getPrivateDashboards() {
    String sessionIdAttribute = SessionAttribute.SESSION_IDENTIFIER.toString();
    if (Ivy.session().getAttribute(sessionIdAttribute) == null) {
      Ivy.session().setAttribute(sessionIdAttribute, UUID.randomUUID().toString());
    }
    String sessionUserId = (String) Ivy.session().getAttribute(sessionIdAttribute);
    IvyCacheService cacheService = IvyCacheService.getInstance();
    PortalPrivateDashboardWrapper portalPrivateDashboardWrapper = null;
    try {
      portalPrivateDashboardWrapper = (PortalPrivateDashboardWrapper) cacheService
          .getSessionCacheValue(IvyCacheIdentifier.PORTAL_PRIVATE_DASHBOARD, sessionUserId).orElse(null);
    } catch (ClassCastException e) {
      cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_PRIVATE_DASHBOARD, sessionUserId);
    }

    if (portalPrivateDashboardWrapper == null) {
      synchronized (sessionUserId.intern()) {
        List<Dashboard> dashboards = new ArrayList<>();
        try {
          String dashboardInUserProperty = readDashboardBySessionUser();
          dashboards = jsonToDashboards(dashboardInUserProperty);
        } catch (Exception e) {
          Ivy.log().error("Cannot load Public Dashboards {0}", e.getMessage());
        }
        portalPrivateDashboardWrapper = new PortalPrivateDashboardWrapper(dashboards);
        cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_PRIVATE_DASHBOARD, sessionUserId,
            portalPrivateDashboardWrapper);
      }
    }
    return portalPrivateDashboardWrapper.dashboards();
  }

  public static List<Dashboard> getVisibleDashboards(boolean isPublic) {
    return isPublic ? getPublicDashboards() : getPrivateDashboards();
  }

  public static List<Dashboard> collectDashboards() {
    List<Dashboard> dashboards = new ArrayList<>();
    dashboards.addAll(getVisibleDashboards(true)); // Public Dashboards
    dashboards.addAll(getVisibleDashboards(false)); // Private Dashboards

    List<DashboardOrder> dashboardOrders = getDashboardOrdersOfSessionUser();
    Map<String, Dashboard> idToDashboard = createMapIdToDashboard(dashboards);
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
    addDefaultTaskCaseListDashboardsIfMissing(collectedDashboards);
    return collectedDashboards;
  }

  public static List<Dashboard> collectMainDashboards() {
    return getPublicDashboards().stream().filter(Dashboard::getIsTopMenu).toList();
  }

  public static List<Dashboard> getDashboardsWithoutMenuItem() {
    return collectDashboards().stream().filter(dashboard -> !dashboard.getIsTopMenu()).toList();
  }

  public static void highlightDashboardMenuItem(String selectedDashboardId) {
    PrimeFaces.current().executeScript(String.format(HIGHLIGHT_DASHBOARD_ITEM_METHOD_PATTERN, selectedDashboardId));
  }

  private static List<Dashboard> jsonToDashboards(String dashboardJson) {
    if (StringUtils.isBlank(dashboardJson)) {
      return new ArrayList<>();
    }
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonDashboardMigrator migrator = new JsonDashboardMigrator(mapper.readTree(dashboardJson));
      List<Dashboard> dashboards = BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), Dashboard.class);
      dashboards.forEach(initDefaultPermission());

      return dashboards;
    } catch (JsonProcessingException e) {
      Ivy.log().error("Failed to parse dashboards from JSON: {0}", e);
    }
    return new ArrayList<>();
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

  private static void addDefaultTaskCaseListDashboardsIfMissing(List<Dashboard> dashboards) {
    if (!hasDashboardWithId(dashboards, DEFAULT_CASE_LIST_DASHBOARD)) {
      dashboards.add(0, DefaultDashboardUtils.getDefaultCaseListDashboard());
    }
    if (!hasDashboardWithId(dashboards, DEFAULT_TASK_LIST_DASHBOARD)) {
      dashboards.add(0, DefaultDashboardUtils.getDefaultTaskListDashboard());
    }
  }

  private static boolean hasDashboardWithId(List<Dashboard> dashboards, String id) {
    return dashboards.stream().map(Dashboard::getId).anyMatch(dashboardId -> id.equals(dashboardId));
  }

  private static void setDashboardAsPublic(List<Dashboard> dashboards) {
    dashboards.forEach(dashboard -> dashboard.setIsPublic(true));
  }

  private static String readDashboardBySessionUser() {
    return Ivy.session().isSessionUserUnknown() ? "" : currentUser().getProperty(PortalVariable.DASHBOARD.key);
  }

  private static IUser currentUser() {
    return Ivy.session().getSessionUser();
  }

  public static Map<String, Dashboard> createMapIdToDashboard(List<Dashboard> dashboards) {
    Map<String, Dashboard> idToDashboard = new LinkedHashMap<>();
    dashboards.forEach(dashboard -> idToDashboard.put(dashboard.getId(), dashboard));
    return idToDashboard;
  }

  public static List<DashboardOrder> getDashboardOrdersOfSessionUser() {
    var dashboardOrderJson = currentUser().getProperty(PortalVariable.DASHBOARD_ORDER.key);
    return BusinessEntityConverter.jsonValueToEntities(dashboardOrderJson, DashboardOrder.class);
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

  public static void storeDashboardInSession(String dashboardId) {
    boolean isMain = isMainDashboard(dashboardId, false);
    storeDashboardInSession(dashboardId, isMain);
  }

  public static void storeDashboardInSession(String dashboardId, boolean isMainDashboard) {
    Ivy.session().setAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString(), dashboardId);
    if (!isMainDashboard) {
      Ivy.session().setAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.toString(), dashboardId);
    }
  }

  public static boolean isMainDashboard(String dashboardId, boolean defaultValue) {
    if (StringUtils.isEmpty(dashboardId)) {
      return false;
    }
    return collectMainDashboards().stream().filter(dashboard -> dashboardId.equals(dashboard.getId()))
        .map(Dashboard::getIsTopMenu).findFirst().orElse(defaultValue);
  }

  public static List<DashboardTemplate> getDashboardTemplates() {
    String dashboardTemplatesJson = Ivy.var().get(PortalVariable.DASHBOARD_TEMPLATES.key);
    return convertDashboardTemplatesToLatestVersion(dashboardTemplatesJson);
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

  public static String generateId() {
    return UUID.randomUUID().toString().replace("-", "");
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

  public static void updatePropertiesToNullIfCurrentValueIsDefaultValue(List<Dashboard> dashboards) {
    if (CollectionUtils.isEmpty(dashboards)) {
      return;
    }
    for (Dashboard dashboard : dashboards) {
      if (BooleanUtils.isFalse(dashboard.getIsTopMenu())) {
        dashboard.setIsTopMenu(null);
      }
    }
  }

  private record PortalPrivateDashboardWrapper(List<Dashboard> dashboards) {
  }
  private record PortalPublicDashboardWrapper(List<Dashboard> dashboards) {
  }

}
