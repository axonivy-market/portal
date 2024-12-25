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
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

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
    try {
      collectedDashboards.addAll(getPublicDashboards());
      collectedDashboards.addAll(getPrivateDashboards());
    } catch (PortalException e) {
      // If errors like parsing JSON errors, ignore them
      Ivy.log().error(e);
    }
    return collectedDashboards;
  }

  public static void addDefaultTaskCaseListDashboardsIfMissing(List<Dashboard> dashboards) {
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

  public static List<Dashboard> collectMainDashboards() {
    List<Dashboard> collectedDashboards =
        new ArrayList<>(getPublicDashboards().stream().filter(dashboard -> dashboard.getIsTopMenu()).toList());
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
    storeDashboardInSession(id, isMainDashboard(id, true));
  }

  public static void storeDashboardInSession(String id, boolean isMainDashboard) {
    Ivy.session().setAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString(), id);
    if (!isMainDashboard) {
      Ivy.session().setAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.toString(), id);
    }
  }

  public static List<Dashboard> getDashboardsWithoutMenuItem() {
    return collectDashboards().stream().filter(dashboard -> !dashboard.getIsTopMenu()).toList();
  }

  public static String getSelectedMainDashboardIdFromSession() {
    return (String) Ivy.session().getAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString());
  }

  private static PortalDashboardItemWrapper getPortalDashboardItemWrapper() {
    String sessionUserId = UserUtils.getSessionIdentifierAttribteWithInitIfEmpty();
    return (PortalDashboardItemWrapper) IvyCacheService.getInstance()
        .getSessionCacheValue(IvyCacheIdentifier.PORTAL_DASHBOARDS, sessionUserId).orElse(null);
  }

  public static boolean isMainDashboard(String dashboardId, boolean defaultValue) {
    if (StringUtils.isEmpty(dashboardId)) {
      return false;
    }
    boolean isMainDashboard = Optional.ofNullable(getPortalDashboardItemWrapper()).map(wrapper -> wrapper.dashboards())
        .orElse(new ArrayList<>()).stream().filter(dashboard -> dashboardId.equals(dashboard.getId()))
        .map(dashboard -> dashboard.getIsTopMenu()).findFirst().orElse(defaultValue);
    return isMainDashboard;
  }


  /**
   * Uses this method before saving a dashboard to simplify generated json from the dashboard
   */
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

  public static boolean isDefaultTaskListDashboard(Dashboard dashboard) {
    return Optional.ofNullable(dashboard).map(Dashboard::getId).orElseGet(() -> "")
        .contentEquals(DEFAULT_TASK_LIST_DASHBOARD);
  }

  public static boolean isDefaultCaseListDashboard(Dashboard dashboard) {
    return Optional.ofNullable(dashboard).map(Dashboard::getId).orElseGet(() -> "")
        .contentEquals(DEFAULT_CASE_LIST_DASHBOARD);
  }

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
          .getSessionCacheValue(IvyCacheIdentifier.PORTAL_PUBLIC_DASHBOARDS, sessionUserId).orElse(null);
    } catch (ClassCastException e) {
      cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_PUBLIC_DASHBOARDS, sessionUserId);
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
        cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_PUBLIC_DASHBOARDS, sessionUserId,
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
          .getSessionCacheValue(IvyCacheIdentifier.PORTAL_PRIVATE_DASHBOARDS, sessionUserId).orElse(null);
    } catch (ClassCastException e) {
      cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_PRIVATE_DASHBOARDS, sessionUserId);
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
        cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_PRIVATE_DASHBOARDS, sessionUserId,
            portalPrivateDashboardWrapper);
      }
    }
    return portalPrivateDashboardWrapper.dashboards();
  }

  public static List<Dashboard> collectDashboards() {
    String sessionUserId = getSessionUserId();
    IvyCacheService cacheService = IvyCacheService.getInstance();
    PortalDashboardItemWrapper portalDashboardItemWrapper = null;
    try {
      portalDashboardItemWrapper = (PortalDashboardItemWrapper) cacheService
          .getSessionCacheValue(IvyCacheIdentifier.PORTAL_DASHBOARDS, sessionUserId).orElse(null);
    } catch (ClassCastException e) {
      cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_DASHBOARDS, sessionUserId);
    }

    if (portalDashboardItemWrapper == null) {
      synchronized (sessionUserId.intern()) {
        List<Dashboard> collectedDashboards = new ArrayList<>();
        try {
          List<Dashboard> visibleDashboards = getAllVisibleDashboardsOfSessionUser();
          List<DashboardOrder> dashboardOrders = getDashboardOrdersOfSessionUser();
          Map<String, Dashboard> idToDashboard = createMapIdToDashboard(visibleDashboards);
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
        } catch (Exception e) {
          Ivy.log().error("Cannot collect Dashboards {0}", e.getMessage());
        }
        portalDashboardItemWrapper = new PortalDashboardItemWrapper(collectedDashboards);
        cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_DASHBOARDS, sessionUserId,
            portalDashboardItemWrapper);
      }
    }
    return portalDashboardItemWrapper.dashboards;
  }

  public static void updateDashboardCache() {
    String sessionUserId = getSessionUserId();
    IvyCacheService cacheService = IvyCacheService.getInstance();
    cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_DASHBOARDS, sessionUserId);
    cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_PRIVATE_DASHBOARDS, sessionUserId);
    cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_PRIVATE_DASHBOARDS, sessionUserId);
  }

  private static String getSessionUserId() {
    String sessionIdAttribute = SessionAttribute.SESSION_IDENTIFIER.name();
    if (session().getAttribute(sessionIdAttribute) == null) {
      session().setAttribute(sessionIdAttribute, UUID.randomUUID().toString());
    }
    return (String) session().getAttribute(sessionIdAttribute);
  }

  private static IWorkflowSession session() {
    return Ivy.session();
  }

  private record PortalPrivateDashboardWrapper(List<Dashboard> dashboards) {
  }
  private record PortalPublicDashboardWrapper(List<Dashboard> dashboards) {
  }
  public record PortalDashboardItemWrapper(List<Dashboard> dashboards) {
  }

}
