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
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivyteam.ivy.environment.Ivy;
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
    List<Application> applications = service.findAllThirdPartyApplication();

    Collections.sort(applications, new ApplicationIndexAscendingComparator());
    buildMenuView(applications);
  }

  public void buildMenuView(List<Application> thirPartyApplications) {
    menuItems = new ArrayList<>();
    
    Button dashboardMenuItem = new Button();
    dashboardMenuItem.setValue(Ivy.cms().co(DASHBOARD));
    dashboardMenuItem.setIcon("icon ivyicon-house-chimney-2");
    dashboardMenuItem.setHref(new PortalNavigator().getPortalStartUrl());
    dashboardMenuItem.setStyleClass(ACTIVE_MENU);
    menuItems.add(dashboardMenuItem);
    
    for (Application application : thirPartyApplications) {
      Button menuItem = new Button();
      menuItem.setValue(ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application));
      menuItem.getAttributes().put(THIRD_PARTY, true);
      menuItem.setHref(application.getLink());
      menuItem.getAttributes().put(APP_NAME, application.getName());
      menuItem.setIcon("fa " + application.getMenuIcon());
      menuItems.add(menuItem);
    }

    buildSubMenuItems();
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
      case TECHNICAL_CASE:
        buildBreadCrumbForTechnicalCaseList(userCase);
        break;
      case RELATED_TASK:
        buildBreadCrumbForRelatedTask(userCase);
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
        break;
      case USER_PROFILE:
        buildBreadCrumbForUserProfile();
        break;
      case EXPRESS_BUSINESS:
        buildBreadCrumbForExpressBusiness(userCase);
        break;
      default:
        break;
    }
  }

  private void buildBreadCrumbForUserProfile() {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements().add(buildUserProfileMenuItem());
  }

  private void buildBreadCrumbForTaskList() {
    setPortalHomeMenuToBreadcrumbModel();
    DefaultMenuItem taskListSubmenuItem = buildTaskListMenuItem();
    taskListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(taskListSubmenuItem);
  }

  private void buildBreadCrumbForCaseList() {
    setPortalHomeMenuToBreadcrumbModel();

    DefaultMenuItem caseListSubmenuItem = buildCaseListMenuItem();
    caseListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(caseListSubmenuItem);
  }

  private void buildBreadCrumbForTechnicalCaseList(ICase userCase) {
    setPortalHomeMenuToBreadcrumbModel();
    DefaultMenuItem caseListSubmenuItem = buildCaseListMenuItem();
    String title = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/headerTitle/technicalCasesOfBusinessCaseTitle", Arrays.asList(Long.toString(userCase.getId()), userCase.getName()));
    caseListSubmenuItem.setValue(title);
    caseListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(caseListSubmenuItem);
  }

  private void buildBreadCrumbForRelatedTask(ICase userCase) {
    setPortalHomeMenuToBreadcrumbModel();
    DefaultMenuItem taskListSubmenuItem = buildTaskListMenuItem();
    String title = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/headerTitle/relatedTasksHeader").concat(" #").concat(Long.toString(userCase.getId()));
    taskListSubmenuItem.setValue(title);
    taskListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(taskListSubmenuItem);
  }

  private void buildBreadCrumbForProcess() {
    setPortalHomeMenuToBreadcrumbModel();

    DefaultMenuItem processListSubmenuItem = buildProcessListMenuItem();
    processListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(processListSubmenuItem);
  }

  private void buildBreadCrumbForStatistic() {
    setPortalHomeMenuToBreadcrumbModel();

    DefaultMenuItem statisticListSubmenuItem = buildStatisticListMenuItem();
    statisticListSubmenuItem.setDisabled(true);
    breadcrumbModel.getElements().add(statisticListSubmenuItem);
  }

  private void buildBreadCrumbForTaskDetails(ITask userTask) {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements().add(buildTaskListMenuItem()); 
    breadcrumbModel.getElements().add(buildTaskDetailsMenuItem(userTask));
  }

  private void buildBreadCrumbForCaseDetails(ICase userCase) {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements().add(buildCaseListMenuItem());
    breadcrumbModel.getElements().add(buildCaseDetailsMenuItem(userCase));
  }

  private void buildBreadCrumbForExpress() {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements().add(buildExpressMenuItem());
  }
  
  private void buildBreadCrumbForExpressBusiness(ICase userCase) {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements().add(buildExpressBusinessMenuItem(Long.toString(userCase.getId())));
  }

  private MenuItem buildPortalHomeMenuItem() {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setIcon("icon ivyicon-house-chimney-2");
    menuItem.setOnclick("navigateToPortalHome();");
    return menuItem;
  }

  private DefaultMenuItem buildMenuItemFromPortalSubMenuItem(SubMenuItem subMenuItem) {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setValue(subMenuItem.getLabel());
    menuItem.setUrl(null);
    return menuItem;
  }

  private DefaultMenuItem buildTaskListMenuItem() {
    TaskSubMenuItem taskSubMenuItem = new TaskSubMenuItem();
    DefaultMenuItem taskMenu = buildMenuItemFromPortalSubMenuItem(taskSubMenuItem);
    taskMenu.setOnclick("navigateToTaskList();");
    return taskMenu;
  }

  private DefaultMenuItem buildCaseListMenuItem() {
    CaseSubMenuItem caseSubMenuItem = new CaseSubMenuItem();
    DefaultMenuItem caseMenuItem = buildMenuItemFromPortalSubMenuItem(caseSubMenuItem);
    caseMenuItem.setOnclick("navigateToCaseList();");
    return caseMenuItem;
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

  private MenuItem buildExpressBusinessMenuItem(String caseId) {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setValue(Ivy.cms().co("/Dialogs/ch/ivy/addon/express/generic/expressWorkflowName", Arrays.asList(caseId)));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private MenuItem buildUserProfileMenuItem() {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/userProfile/myProfileTitle"));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private void setPortalHomeMenuToBreadcrumbModel() {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());
  }

}
