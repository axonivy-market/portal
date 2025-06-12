package ch.addon.portal.generic.menu;

import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_CASE_LIST_DASHBOARD;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DEFAULT_TASK_LIST_DASHBOARD;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.service.CustomSubMenuItemService;

import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.MenuKind;
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
      case CUSTOM:
      case EXTERNAL_LINK:
        redirectToSelectedMenuUrl(params);
        break;
      case PROCESS:
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

  public static List<Application> getThirdPartyApps() {
    List<Application> applications = RegisteredApplicationService.getInstance().getPublicConfig();
    applications.removeIf(application -> {
      List<String> permissions = application.getPermissions();
      if (CollectionUtils.isEmpty(applications)) {
        return false;
      }
      return permissions.stream().noneMatch(PortalMenuNavigator::isSessionUserHasPermisson);
    });
    Collections.sort(applications, new ApplicationIndexAscendingComparator());
    return applications;
  }

  private static boolean isSessionUserHasPermisson(String permission) {
    return StringUtils.startsWith(permission, "#")
        ? StringUtils.equals(Ivy.session().getSessionUser().getMemberName(), permission)
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
    List<SubMenuItem> subMenuItems = new ArrayList<>();

    addProcessSubmenuItems(subMenuItems);

    List<Dashboard> mainDashboards = DashboardUtils.collectMainDashboards();
    for (Dashboard dashboard : mainDashboards) {
      if (isDefaultTaskCaseListDashboardButNoAccessPermission(dashboard)) {
        continue;
      }
      subMenuItems.add(convertDashboardToSubMenuItem(dashboard, currentLanguage));
    }

    subMenuItems.addAll(CustomSubMenuItemService.findAll());

    return subMenuItems;
  }

  private static boolean isDefaultTaskCaseListDashboardButNoAccessPermission(Dashboard dashboard) {
    return (DEFAULT_TASK_LIST_DASHBOARD.equals(dashboard.getId())
        && !PermissionUtils.checkAccessFullTaskListPermission())
        || (DEFAULT_CASE_LIST_DASHBOARD.equals(dashboard.getId())
            && !PermissionUtils.checkAccessFullCaseListPermission());
  }

  private static void addProcessSubmenuItems(List<SubMenuItem> subMenuItems) {
    if (PermissionUtils.checkAccessFullProcessListPermission()) {
      subMenuItems.add(new ProcessSubMenuItem());
    }
  }

  private static SubMenuItem convertDashboardToSubMenuItem(Dashboard dashboard, String currentLanguage) {
    SubMenuItem item = new SubMenuItem();
    String defaultTitle = dashboard.getTitle();

    // Set default icon if it's blank
    if (StringUtils.isBlank(dashboard.getIcon())) {
      dashboard.setIcon(dashboard.getIsPublic() ? "si-network-share" : "si-single-neutral-shield");
    }

    // Set icon with the appropriate prefix
    item.icon = (dashboard.getIcon().startsWith("fa") ? "fa " : "si ") + dashboard.getIcon();

    // Set the name of the submenu item based on the current language or use default title
    item.label = dashboard.getTitles().stream()
        .filter(name -> StringUtils.equalsIgnoreCase(name.getLocale().toString(), currentLanguage)
            && StringUtils.isNotBlank(name.getValue()))
        .map(DisplayName::getValue).findFirst().orElse(defaultTitle);

    // Set other properties
    item.menuKind = MenuKind.MAIN_DASHBOARD;
    item.name = HomepageUtils.generateHomepageId(MenuKind.MAIN_DASHBOARD, dashboard.getId());
    item.link = UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(dashboard.getId());

    return item;
  }

}
