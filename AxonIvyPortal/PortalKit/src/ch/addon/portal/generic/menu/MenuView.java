package ch.addon.portal.generic.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bean.ApplicationSelectionMenuBean;
import ch.ivy.addon.portalkit.comparator.ApplicationIndexAscendingComparator;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@ManagedBean
@ViewScoped
public class MenuView implements Serializable {

  private static final long serialVersionUID = 3188259472933435953L;
  public final static String LOAD_SUB_MENU_PROCESS = "loadSubMenuItems()";
  public final static String SUB_MENU = "subMenuItems";
  /* Menu Parameters */
  public final static String MENU_KIND = "menuKind";
  public final static String MENU_URL = "menuUrl";
  public final static String TASK_ID = "taskId";
  public final static String IS_WORKING_ON_TASK = "isWorkingOnATask";
  /* Menu id format */
  public final static String MENU_ID_FORMAT = "menu-item-%d";
  public final static String SUB_MENU_ID_FORMAT = "sub-menu-item-%d";
  public final static String THIRD_PARTY_MENU_ID_FORMAT = "thirdparty-menu-item-%d";
  public final static String EXTERNAL_MENU_ID_FORMAT = "external-menu-item-%d";
  
  public final static String ICON_POSITION = "right";
  public final static String DEFAULT_MENU_PROCESS = "@this";
  public final static String EXTERNAL_MENU_TARGET = "_blank";
  
  public final static String DASHBOARD_PARAM = "isShowDashboard";
  public final static String DASHBOARD_ICON = "si si-layout-dashboard";
  public final static String DASHBOARD = "/ch.ivy.addon.portalkit.ui.jsf/common/dashboard";

  private DefaultMenuModel mainMenuModel;
  private MenuModel breadcrumbModel;
  private long workingTaskId;
  private boolean isWorkingOnATask;

  @PostConstruct
  public void init() {}

  public void buildPortalLeftMenu(ITask workingTask, boolean isWorkingOnATask) {
    initTaskParams(workingTask, isWorkingOnATask);
    
    mainMenuModel = new DefaultMenuModel();
    mainMenuModel.addElement(buildDashboardItem());
    
    List<SubMenuItem> subMenuItems = callSubMenuItemsProcess();
    for (SubMenuItem subMenu : subMenuItems) {
      DefaultMenuItem item = buildSubMenuItem(subMenuItems.indexOf(subMenu), subMenu);
      mainMenuModel.addElement(item);
    }

    List<Application> thirdPartyApps = getThirdPartyApps();
    for (Application app : thirdPartyApps) {
      DefaultMenuItem item = buildThirdPartyItem(thirdPartyApps.indexOf(app), app);
      mainMenuModel.addElement(item);
    }
  }

  private void initTaskParams(ITask workingTask, boolean isWorkingOnATask) {
    this.workingTaskId = Objects.isNull(workingTask) ? Ivy.wfTask().getId() : workingTask.getId();
    this.isWorkingOnATask = isWorkingOnATask;
  }

  private DefaultMenuItem buildRegularMenuItem(String id, String name, MenuKind menuKind, String url, String iconClass) {
    DefaultMenuItem item = new DefaultMenuItem(name);
    item.setId(id);
    item.setIcon(iconClass);
    item.setIconPos(ICON_POSITION);
    item.setStyleClass(menuKind.name());
    item.setContainerStyleClass(id);
    item.setUrl(url);
    item.setUpdate(DEFAULT_MENU_PROCESS);
    item.setProcess(DEFAULT_MENU_PROCESS);
    item.setPartialSubmit(true);
    item.setImmediate(true);
    item.setGlobal(true);
    item.setCommand(ApplicationSelectionMenuBean.MENU_COMMAND_METHOD);

    item.setParam(TASK_ID, this.workingTaskId);
    item.setParam(IS_WORKING_ON_TASK, this.isWorkingOnATask);
    item.setParam(MENU_KIND, menuKind);
    item.setParam(MENU_URL, StringUtils.defaultIfEmpty(url, EMPTY));
    return item;
  }

  private DefaultMenuItem buildSubMenuItem(int index, SubMenuItem subMenuItem) {
    DefaultMenuItem item = buildRegularMenuItem(String.format(SUB_MENU_ID_FORMAT, index),
        subMenuItem.getLabel(),
        subMenuItem.getMenuKind(),
        subMenuItem.buildLink(),
        subMenuItem.getIcon());

    if (subMenuItem.getMenuKind() == MenuKind.EXTERNAL_LINK
        || subMenuItem.getMenuKind() == MenuKind.THIRD_PARTY
        || !UrlUtils.isIvyUrl(subMenuItem.getLink())) {
      item.getParams().clear();
      item.setTarget(EXTERNAL_MENU_TARGET);
      item.setId(String.format(EXTERNAL_MENU_ID_FORMAT, index));
    } else {
      item.setUrl(null);
    }

    return item;
  }

  private DefaultMenuItem buildThirdPartyItem(int menuIndex, Application application) {
    String iconClass = String.format("fa %s", application.getMenuIcon());
    DefaultMenuItem item = buildRegularMenuItem(String.format(THIRD_PARTY_MENU_ID_FORMAT, menuIndex),
        ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application),
        MenuKind.THIRD_PARTY,
        UrlUtils.buildUrl(application.getLink()),
        iconClass);

    item.setTarget(EXTERNAL_MENU_TARGET);
    item.getParams().clear();
    return item;
  }

  private DefaultMenuItem buildDashboardItem() {
    String dashboardLink = PortalNavigator.getPortalStartUrl();
    String defaultHomepageConfig = HomepageUtils.getHomepageName();
    HomepageType configHomepageType = HomepageType.getType(defaultHomepageConfig);
    if (HomepageType.DASHBOARD != configHomepageType) {
      Map<String, String> params = new HashMap<>();
      params.put(DASHBOARD_PARAM, Boolean.TRUE.toString());
      dashboardLink = PortalNavigator.getPortalDashboardPageUrl(params);
    }
    DefaultMenuItem dashboardItem = buildRegularMenuItem(String.format(MENU_ID_FORMAT, 0),
        Ivy.cms().co(DASHBOARD),
        MenuKind.DASHBOARD,
        dashboardLink,
        DASHBOARD_ICON);

    dashboardItem.setUrl(null);
    return dashboardItem;
  }

  private List<Application> getThirdPartyApps() {
    RegisteredApplicationService service = new RegisteredApplicationService();
    List<Application> applications = service.findAllThirdPartyApplication();
    Collections.sort(applications, new ApplicationIndexAscendingComparator());
    return applications;
  }

  @SuppressWarnings("unchecked")
  private List<SubMenuItem> callSubMenuItemsProcess() {
    List<SubMenuItem> subMenuItems = new ArrayList<>();
    Map<String, Object> response = IvyAdapterService.startSubProcess(LOAD_SUB_MENU_PROCESS, null,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    try {
      subMenuItems = (List<SubMenuItem>) response.get(SUB_MENU);
    } catch (Exception e) {
      Ivy.log().error("Cannot load SubMenuItems {0}", e.getMessage());
    }
    return subMenuItems;
  }

  public MenuModel getMainMenuModel() {
    return mainMenuModel;
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
      case STATISTICS:
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
      case ABSENCES_MANAGEMENT:
        buildBreadCrumbForAbsences();
        break;
      default:
        break;
    }
  }

  private void buildBreadCrumbForUserProfile() {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements().add(buildGenericMenuItem("/ch.ivy.addon.portalkit.ui.jsf/userProfile/myProfileTitle"));
  }

  private void buildBreadCrumbForAbsences() {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements().add(buildGenericMenuItem("/ch.ivy.addon.portalkit.ui.jsf/AbsenceManagement/absenceAndDeputy"));
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
    breadcrumbModel.getElements().add(buildGenericMenuItem("/Categories/ExpressWorkflow/name"));
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
    StatisticSubMenuItem statisticSubMenuItem = new StatisticSubMenuItem();
    return buildMenuItemFromPortalSubMenuItem(statisticSubMenuItem);
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

  private MenuItem buildExpressBusinessMenuItem(String caseId) {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setValue(Ivy.cms().co("/Dialogs/ch/ivy/addon/express/generic/expressWorkflowName", Arrays.asList(caseId)));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private MenuItem buildGenericMenuItem(String cms) {
    DefaultMenuItem menuItem = new DefaultMenuItem();
    menuItem.setValue(Ivy.cms().co(cms));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private void setPortalHomeMenuToBreadcrumbModel() {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());
  }

}
