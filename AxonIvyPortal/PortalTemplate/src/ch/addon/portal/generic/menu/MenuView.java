package ch.addon.portal.generic.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.button.Button;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class MenuView {

  public final static String LOAD_SUB_MENU_PROCESS = "loadSubMenuItems()";
  public final static String SUB_MENU = "subMenuItems";
  public final static String THIRD_PARTY = "isThirdPartyApp";
  public final static String ACTIVE_MENU = "active-menuitem";
  public final static String APP_NAME = "appName";
  public final static String DASHBOARD = "/ch.ivy.addon.portalkit.ui.jsf/common/dashboard";

  private List<Button> menuItems;
  private List<SubMenuItem> subMenuItems;
  private MenuModel breadcrumbModel;
  

  @PostConstruct
  public void init() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<Application> applications = service.findApplicationByUser(Ivy.session().getSessionUserName());

    Collections.sort(applications, new ApplicationIndexAscendingComparator());
    buildMenuView(applications);
  }

  public void buildMenuView(List<Application> applications) {
    menuItems = new ArrayList<>();
    removeAppDataInSession();

    RegisteredApplicationService applicationService = new RegisteredApplicationService();
    int numberOfIvyApplications = (int) applicationService.countIvyApplications(applications);

    for (Application application : applications) {
      Button menuItem = new Button();
      menuItem.setValue(ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application));
      boolean isThirdPartyApp = application.getServerId() == null;
      if (isThirdPartyApp) {
        menuItem.getAttributes().put(THIRD_PARTY, true);
      }
      menuItem.setHref(application.getLink());
      menuItem.getAttributes().put(APP_NAME, application.getName());
      menuItem.setIcon("fa " + application.getMenuIcon());

      menuItems.add(menuItem);
      if (application.getName().equals(Ivy.request().getApplication().getName())
          || (!isThirdPartyApp && numberOfIvyApplications == 1)) {
        menuItem.setStyleClass("active-menuitem");
        updateAppDataToSession(application);
      }
    }

    if (numberOfIvyApplications > 1 || numberOfIvyApplications == 0) {
      Button menuItem = new Button();
      menuItem.setValue(Ivy.cms().co(DASHBOARD));
      menuItem.setIcon("fa fa-home");
      if (numberOfIvyApplications == 0) {
        menuItem.setHref(new PortalNavigator().getPortalStartUrlOfCurrentApplication());
        menuItem.setStyleClass(ACTIVE_MENU);
        menuItems.add(0, menuItem);
      } else {
        IApplication portal = ServerFactory.getServer().getApplicationConfigurationManager()
            .findApplication(PortalConstants.PORTAL_APPLICATION_NAME);
        if (portal != null && portal.getActivityState() != ch.ivyteam.ivy.application.ActivityState.INACTIVE
            && portal.getSecurityContext().users().find(Ivy.session().getSessionUserName()) != null) {
          menuItem.setHref(SecurityServiceUtils.getDefaultPortalStartUrl());
          if (PortalConstants.PORTAL_APPLICATION_NAME.equals(Ivy.request().getApplication().getName())) {
            menuItem.setStyleClass(ACTIVE_MENU);
          }
          menuItems.add(0, menuItem);
        }
      }
    }

    buildSubMenuItems();
  }

  private void updateAppDataToSession(Application application) {
    SecurityServiceUtils.setSessionAttribute(SessionAttribute.SELECTED_APP.toString(), application.getName());
    SecurityServiceUtils.setSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString(),
        application.getDisplayName());
  }

  private void removeAppDataInSession() {
    SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_APP.toString());
    SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString());
  }

  @SuppressWarnings("unchecked")
  public void buildSubMenuItems() {
    subMenuItems = new ArrayList<>();
    Map<String, Object> response = IvyAdapterService.startSubProcess(LOAD_SUB_MENU_PROCESS, null,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    try {
      subMenuItems = (List<SubMenuItem>) response.get(SUB_MENU);
    } catch (Exception e) {
      Ivy.log().error("Cannot load SubMenuItems {0}", e.getMessage());
    }
  }

  public List<Button> getMenuItems() {
    return menuItems;
  }

  public List<SubMenuItem> getSubMenuItems() {
    return subMenuItems;
  }

  public MenuModel getBreadcrumbModel() {
    return breadcrumbModel;
  }

  public void setBreadcrumbModel(MenuModel breadcrumbModel) {
    this.breadcrumbModel = breadcrumbModel;
  }

  public void loadBreadcrumb(String viewName, ITask userTask, ICase userCase) {
    breadcrumbModel = new DefaultMenuModel();
    if (StringUtils.isBlank(viewName)) {
      return;
    }
    BreadCrumbKind breadCrumbKind = BreadCrumbKind.valueOf(viewName);
    switch (breadCrumbKind) {
      case TASK:
        buildBreadCrumbForTaskList();
        break;
      case CASE:
        buildBreadCrumbForCaseList();
        break;
      case PROCESS:
        buildBreadCrumbForProcess();
        break;
      case DASHBOARD:
        buildBreadCrumbForStatistic();
        break;
      case TASK_DETAIL:
        buildBreadCrumbForTaskDetails(userTask);
        break;
      case CASE_DETAIL:
        buildBreadCrumbForCaseDetails(userCase);
        break;
      case EXPRESS:
        buildBreadCrumbForExpress();
      default:
        break;
    }
  }

  private void buildBreadCrumbForTaskList() {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());

    DefaultMenuItem taskListSubmenuItem = buildTaskListMenuItem();
    taskListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(taskListSubmenuItem);
  }

  private void buildBreadCrumbForCaseList() {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());

    DefaultMenuItem caseListSubmenuItem = buildCaseListMenuItem();
    caseListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(caseListSubmenuItem);
  }

  private void buildBreadCrumbForProcess() {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());

    DefaultMenuItem processListSubmenuItem = buildProcessListMenuItem();
    processListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(processListSubmenuItem);
  }

  private void buildBreadCrumbForStatistic() {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());

    DefaultMenuItem statisticListSubmenuItem = buildStatisticListMenuItem();
    statisticListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(statisticListSubmenuItem);
  }

  private void buildBreadCrumbForTaskDetails(ITask userTask) {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());
    breadcrumbModel.getElements().add(buildTaskListMenuItem());
    breadcrumbModel.getElements().add(buildTaskDetailsMenuItem(userTask));
  }

  private void buildBreadCrumbForCaseDetails(ICase userCase) {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());
    breadcrumbModel.getElements().add(buildCaseListMenuItem());
    breadcrumbModel.getElements().add(buildCaseDetailsMenuItem(userCase));
  }

  private void buildBreadCrumbForExpress() {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());
    breadcrumbModel.getElements().add(buildExpressMenuItem());
  }

  private MenuItem buildPortalHomeMenuItem() {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    PortalNavigator navigator = new PortalNavigator();

    menuItem.setValue("");
    menuItem.setStyleClass("home-breadcrumb");
    menuItem.setUrl(navigator.getPortalStartUrlOfCurrentApplication());
    return menuItem;
  }

  private DefaultMenuItem buildMenuItemFromPortalSubMenuItem(SubMenuItem subMenuItem) {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setValue(subMenuItem.getLabel());
    menuItem.setUrl(subMenuItem.getLink());
    return menuItem;
  }

  private DefaultMenuItem buildTaskListMenuItem() {
    TaskSubMenuItem taskSubMenuItem = new TaskSubMenuItem();
    return buildMenuItemFromPortalSubMenuItem(taskSubMenuItem);
  }

  private DefaultMenuItem buildCaseListMenuItem() {
    CaseSubMenuItem caseSubMenuItem = new CaseSubMenuItem();
    return buildMenuItemFromPortalSubMenuItem(caseSubMenuItem);
  }

  private DefaultMenuItem buildProcessListMenuItem() {
    ProcessSubMenuItem processSubMenuItem = new ProcessSubMenuItem();
    return buildMenuItemFromPortalSubMenuItem(processSubMenuItem);
  }

  private DefaultMenuItem buildStatisticListMenuItem() {
    DashboardSubMenuItem dashboardSubMenuItem = new DashboardSubMenuItem();
    return buildMenuItemFromPortalSubMenuItem(dashboardSubMenuItem);
  }

  private MenuItem buildTaskDetailsMenuItem(ITask userTask) {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    String taskName = StringUtils.isEmpty(userTask.getName()) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable") : userTask.getName();
    menuItem.setValue(String.join(": ", Ivy.cms().co("/Labels/Task"), taskName));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private MenuItem buildCaseDetailsMenuItem(ICase userCase) {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setValue(String.join(": ", Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/case"), userCase.getName()));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private MenuItem buildExpressMenuItem() {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setValue(Ivy.cms().co("/Categories/ExpressWorkflow/name"));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  public String getHomepageLink() {
    PortalNavigator navigator = new PortalNavigator();
    return navigator.getPortalStartUrlOfCurrentApplication();
  }
}
