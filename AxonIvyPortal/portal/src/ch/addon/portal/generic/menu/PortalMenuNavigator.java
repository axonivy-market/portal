package ch.addon.portal.generic.menu;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.publicapi.PortalNavigatorAPI;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
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
    RegisteredApplicationService service = RegisteredApplicationService.getInstance();
    List<Application> applications = service.getPublicConfig();
    Collections.sort(applications, new ApplicationIndexAscendingComparator());
    return applications;
  }

  @SuppressWarnings("unchecked")
  public static List<SubMenuItem> callSubMenuItemsProcess() {
    List<SubMenuItem> subMenuItems = new ArrayList<>();
    Map<String, Object> response = IvyAdapterService.startSubProcess(LOAD_SUB_MENU_PROCESS, null,
        Arrays.asList(PortalLibrary.PORTAL.getValue()));
    try {
      subMenuItems = (List<SubMenuItem>) response.get(SUB_MENU);
    } catch (Exception e) {
      Ivy.log().error("Cannot load SubMenuItems {0}", e.getMessage());
    }
    return subMenuItems;
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

}
