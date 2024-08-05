package ch.addon.portal.generic.menu;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.service.CustomSubMenuItemService;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.PrimeFacesUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
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
      case CUSTOM:
      case EXTERNAL_LINK:
        redirectToSelectedMenuUrl(params);
        break;
      case PROCESS:
        PortalNavigator.navigateToPortalProcess();
        break;
      case TASK:
        PortalNavigator.navigateToPortalTask();
        break;
      case CASE:
        PortalNavigator.navigateToPortalCase();
        break;
      case STATISTICS:
        PortalNavigator.navigateToPortalStatistic();
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
    Collections.sort(applications, new ApplicationIndexAscendingComparator());
    return applications;
  }

  public static List<SubMenuItem> callSubMenuItemsProcess() {
    Locale requestLocale = Ivy.session().getContentLocale();
    String sessionIdAttribute = SessionAttribute.SESSION_IDENTIFIER.toString();
    if (Ivy.session().getAttribute(sessionIdAttribute) == null) {
      Ivy.session().setAttribute(sessionIdAttribute, UUID.randomUUID().toString());
    }
    String sessionUserId = (String) Ivy.session().getAttribute(sessionIdAttribute);
    IvyCacheService cacheService = IvyCacheService.getInstance();
    PortalSubMenuItemWrapper portalSubMenuItemWrapper = null;
    try {
      portalSubMenuItemWrapper = (PortalSubMenuItemWrapper) cacheService
          .getSessionCacheValue(IvyCacheIdentifier.PORTAL_MENU, sessionUserId).orElse(null);
    } catch (ClassCastException e) {
      cacheService.invalidateSessionEntry(IvyCacheIdentifier.PORTAL_MENU, sessionUserId);
    }

    if (portalSubMenuItemWrapper == null
        || !requestLocale.equals(portalSubMenuItemWrapper.loadedLocale)) {
      synchronized(PortalSubMenuItemWrapper.class) {
        List<SubMenuItem> subMenuItems = new ArrayList<>();
        try {
          subMenuItems = getSubmenuList();
        } catch (Exception e) {
          Ivy.log().error("Cannot load SubMenuItems {0}", e.getMessage());
        }

        portalSubMenuItemWrapper = new PortalSubMenuItemWrapper(requestLocale, subMenuItems);
        cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_MENU, sessionUserId, portalSubMenuItemWrapper);
      }
    }
    return portalSubMenuItemWrapper.portalSubMenuItems;
  }

  public static void navigateToTargetPage(boolean isClickOnBreadcrumb, String destinationPage, Map<String, List<String>> params) throws IOException {
    if (isClickOnBreadcrumb) {
      if (BreadCrumbKind.TASK.name().equals(destinationPage)) {
        PortalNavigator.navigateToPortalTask();
      }
      else if (BreadCrumbKind.HOME.name().equals(destinationPage)) {
        PortalNavigatorAPI.navigateToPortalHome();
      }
      else {
        redirectToSelectedMenuUrl(params);
      }
    }
    navigateToTargetPage(params);
  }
  private record PortalSubMenuItemWrapper(Locale loadedLocale, List<SubMenuItem> portalSubMenuItems) {}

  private static List<SubMenuItem> getSubmenuList() {
    List<SubMenuItem> subMenuItems = new ArrayList<>();
    GlobalSettingService globalSettingService = GlobalSettingService.getInstance();

    if(PermissionUtils.checkAccessFullProcessListPermission()) {
      subMenuItems.add(new ProcessSubMenuItem());
    }

    if(PermissionUtils.checkAccessFullTaskListPermission()) {
      subMenuItems.add(new TaskSubMenuItem());
    }

    if(PermissionUtils.checkAccessFullCaseListPermission()) {
      subMenuItems.add(new CaseSubMenuItem());
    }

    if(PermissionUtils.checkAccessFullStatisticsListPermission()
        && !globalSettingService.findBooleanGlobalSettingValue(GlobalVariable.HIDE_STATISTIC_WIDGET)) {
      subMenuItems.add(new StatisticSubMenuItem());
    }

    subMenuItems.addAll(CustomSubMenuItemService.findAll());
    return subMenuItems;
  }
}