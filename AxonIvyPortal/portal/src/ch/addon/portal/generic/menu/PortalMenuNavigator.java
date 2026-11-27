package ch.addon.portal.generic.menu;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.dto.menu.DashboardMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.menu.management.MenuLoader;
import com.axonivy.portal.service.CustomSubMenuItemService;
import com.axonivy.portal.util.MenuUtils;

import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PrimeFacesUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

public class PortalMenuNavigator {
  public final static String LOAD_SUB_MENU_PROCESS = "loadSubMenuItems()";
  public final static String SUB_MENU = "subMenuItems";

  public static void navigateToTargetPage(Map<String, List<String>> params) throws IOException {
    MenuKind selectedMenuKind = MenuKind.getKind(getMenuParam(params, PortalMenuItem.MENU_KIND));
    if (isNull(selectedMenuKind)) {
      return;
    }

    switch (selectedMenuKind) {
      case DASHBOARD:
      case MAIN_DASHBOARD:
      case PROCESS:
      case EXTERNAL_LINK:
      case STATIC_PAGE:
        redirectToSelectedMenuUrl(params);
        break;
      case PROCESS_LIST:
        PortalNavigator.navigateToPortalProcess();
        break;
      default:
        break;
    }
  }

  private static void redirectToSelectedMenuUrl(Map<String, List<String>> params) throws IOException {
    String menuUrl = getMenuParam(params, PortalMenuItem.MENU_URL);
    FacesContext.getCurrentInstance().getExternalContext().redirect(menuUrl);
  }

  public static boolean showWarningDialog(Map<String, List<String>> params) {
    boolean isWorkingOnATask = Boolean.valueOf(getMenuParam(params, PortalMenuItem.WORKING_ON_TASK));
    TaskState workingTaskState = getWorkingTaskState(params);
    if (isWorkingOnATask && workingTaskState != TaskState.DONE) {
      PrimeFacesUtils.executeScript("PF('task-losing-confirmation-dialog').show()");
      return true;
    }
    return false;
  }

  private static TaskState getWorkingTaskState(Map<String, List<String>> params) {
    ITask workingTask = TaskUtils.findTaskById(Long.valueOf(getMenuParam(params, PortalMenuItem.TASK_ID)));
    return isNull(workingTask) ? Ivy.wfTask().getState() : workingTask.getState();
  }

  private static String getMenuParam(Map<String, List<String>> params, String key) {
    if (!isNull(params)) {
      List<String> values = params.get(key);
      return CollectionUtils.isEmpty(values) ? null : values.get(0);
    }
    return EMPTY;
  }

  public static Map<String, List<String>> extractMenuParams(ActionEvent event) {
    MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
    return menuItem.getParams();
  }

  public static String extractMenuItemId(ActionEvent event) {
    return ((MenuActionEvent) event).getMenuItem().getId();
  }
  public static List<Application> getThirdPartyApps() {
    List<Application> applications = RegisteredApplicationService.getInstance().getPublicConfig();
    applications.removeIf(application -> {
      List<String> permissions = application.getPermissions();
      if (CollectionUtils.isEmpty(permissions)) {
        return false;
      }
      return permissions.stream().noneMatch(PortalMenuNavigator::isSessionUserHasPermission);
    });
    Collections.sort(applications, new ApplicationIndexAscendingComparator());
    return applications;
  }

  private static boolean isSessionUserHasPermission(String permission) {
    return Strings.CS.startsWith(permission, "#")
        ? Strings.CS.equals(Ivy.session().getSessionUser().getMemberName(), permission)
        : PermissionUtils.doesSessionUserHaveRole(permission);
  }

  public static List<SubMenuItem> callSubMenuItemsProcess() {
    return getSubmenuList();
  }

  public static void navigateToTargetPage(boolean isClickOnBreadcrumb, String destinationPage,
      Map<String, List<String>> params) throws IOException {
    if (isClickOnBreadcrumb) {
      if (BreadCrumbKind.TASK.name().equals(destinationPage)) {
        PortalNavigator.navigateToPortalTask();
      } else if (BreadCrumbKind.HOME.name().equals(destinationPage)) {
        PortalNavigatorAPI.navigateToPortalHome();
      } else {
        redirectToSelectedMenuUrl(params);
      }
    }
    navigateToTargetPage(params);
  }

  private static List<SubMenuItem> getSubmenuList() {
    String currentLanguage = UserUtils.getUserLanguage();
    Map<String, SubMenuItem> customItems = new HashMap<>();
    for (SubMenuItem item : CustomSubMenuItemService.findAll()) {
      customItems.putIfAbsent(item.getId(), item);
    }

    List<SubMenuItem> subMenuItems = new ArrayList<>();
    for (PortalMenuItemDefinition definition : MenuLoader.loadMenuDefinitions()) {
      if (!definition.isVisible()) {
        continue;
      }
      SubMenuItem item = toHomepageSubMenuItem(definition, customItems, currentLanguage);
      if (item != null) {
        subMenuItems.add(item);
      }
    }
    return subMenuItems;
  }

  /**
   * Maps a visible sidebar definition to its homepage candidate in sidebar order. Returns null when the
   * definition is not a homepage candidate (hidden access, third-party app, unsupported built-in).
   */
  private static SubMenuItem toHomepageSubMenuItem(PortalMenuItemDefinition definition,
      Map<String, SubMenuItem> customItems, String currentLanguage) {
    return switch (definition.getSource()) {
      case STANDARD -> toStandardSubMenuItem((StandardMenuItemDefinition) definition);
      case DASHBOARD -> toMainDashboardSubMenuItem((DashboardMenuItemDefinition) definition, currentLanguage);
      case CALLABLE, CUSTOM_MENU_CONFIGURATION ->
        customItems.get(definition.getId());
      default -> null;
    };
  }

  private static SubMenuItem toStandardSubMenuItem(StandardMenuItemDefinition definition) {
    return switch (definition.getStandardType()) {
      case PROCESS -> PermissionUtils.checkAccessFullProcessListPermission() ? new ProcessSubMenuItem() : null;
      case DASHBOARD -> defaultDashboardSubMenuItem();
      default -> null;
    };
  }

  private static SubMenuItem defaultDashboardSubMenuItem() {
    SubMenuItem item = new SubMenuItem();
    item.setId(HomepageType.DASHBOARD.name());
    item.menuKind = MenuKind.DASHBOARD;
    item.name = HomepageType.DASHBOARD.name();
    item.label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dashboard");
    item.link = PortalNavigator.getPortalStartUrl();
    return item;
  }

  private static SubMenuItem toMainDashboardSubMenuItem(DashboardMenuItemDefinition definition,
      String currentLanguage) {
    Dashboard dashboard = definition.getDashboard();
    return dashboard != null && DashboardUtils.canSessionUserAccessDashboard(dashboard)
        ? convertDashboardToSubMenuItem(dashboard, currentLanguage)
        : null;
  }

  private static SubMenuItem convertDashboardToSubMenuItem(Dashboard dashboard, String currentLanguage) {
    SubMenuItem item = new SubMenuItem();
    String defaultTitle = dashboard.getTitle();

    // Set default icon if it's blank
    if (StringUtils.isBlank(dashboard.getIcon())) {
      dashboard.setIcon(dashboard.getIsPublic() ? "ti-world-share" : "ti-lock-square-rounded");
    }

    // Set id
    item.setId(dashboard.getId());

    // Set icon with the appropriate prefix
    item.icon = MenuUtils.buildIconClass(dashboard.getIcon());

    // Set the name of the submenu item based on the current language or use default title
    item.label = dashboard.getTitles().stream()
        .filter(name -> Strings.CI.equals(name.getLocale().toString(), currentLanguage)
            && StringUtils.isNotBlank(name.getValue()))
        .map(DisplayName::getValue).findFirst().orElse(defaultTitle);

    // Set other properties
    item.menuKind = MenuKind.MAIN_DASHBOARD;
    item.name = HomepageUtils.generateHomepageId(MenuKind.MAIN_DASHBOARD, dashboard.getId());
    item.link = UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(dashboard.getId());
    item.id = SubMenuItem.generateId(item.menuKind, item.link);

    return item;
  }

}
