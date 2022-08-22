package ch.addon.portal.generic.menu;

import static ch.ivy.addon.portalkit.util.DashboardUtils.DASHBOARD_MENU_ITEM_PATTERN;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DASHBOARD_MENU_JS_CLASS;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DASHBOARD_MENU_PATTERN;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DASHBOARD_PAGE_URL;
import static java.util.Objects.isNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;
import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.publicapi.ApplicationMultiLanguageAPI;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

@ManagedBean
@ViewScoped
public class MenuView implements Serializable {
  private static final long serialVersionUID = 3188259472933435953L;

  private final static String DASHBOARD = "/ch.ivy.addon.portalkit.ui.jsf/common/dashboard";
  public final static String SELECTED_MENU_ID = "selectedMenuId";
  public final static String PREV_SELECTED_MENU_ID = "prevSelectedMenuId";
  public final static String IS_WORKING_ON_TASK = "isWorkingOnATask";
  public final static String IS_OPEN_NEW_TAB = "isOpenOnNewTab";
  public final static String CLICK_ON_MENU_ITEM_PATTERN = "fireEventClickOnMenuItem('%s', '%s')";

  private DefaultMenuModel mainMenuModel;
  private MenuModel breadcrumbModel;

  private long workingTaskId;
  private boolean isWorkingOnATask;
  private Map<String, List<String>> params;

  public void onClickMenuItem(ActionEvent event) throws IOException {
    this.params = PortalMenuNavigator.extractMenuParams(event);
    if (PortalMenuNavigator.showWarningDialog(params)) {
      return;
    }
    PortalMenuNavigator.navigateToTargetPage(params);
  }

  public void navigateToTargetPage(boolean isClickOnBreadcrumb, String destinationPage) throws IOException {
    PortalMenuNavigator.navigateToTargetPage(isClickOnBreadcrumb, destinationPage, this.params);
  }

  public void buildPortalLeftMenu(ITask workingTask, boolean isWorkingOnATask) {
    initTaskParams(workingTask, isWorkingOnATask);
    mainMenuModel = new DefaultMenuModel();
    mainMenuModel.getElements().add(buildDashboardItem()); // menuIndex = 0

    List<SubMenuItem> subMenuItems = PortalMenuNavigator.callSubMenuItemsProcess();
    int menuIndex = 1;
    for (SubMenuItem subMenu : subMenuItems) {
      DefaultMenuItem item = buildSubMenuItem(subMenu, menuIndex);
      mainMenuModel.getElements().add(item);
      menuIndex++;
    }
    
    List<Application> thirdPartyApps = PortalMenuNavigator.getThirdPartyApps();
    for (Application app : thirdPartyApps) {
      DefaultMenuItem item = buildThirdPartyItem(app, menuIndex);
      mainMenuModel.getElements().add(item);
      menuIndex++;
    }
    mainMenuModel.generateUniqueIds();
  }

  private void initTaskParams(ITask workingTask, boolean isWorkingOnATask) {
    this.workingTaskId = isNull(workingTask) ? Ivy.wfTask().getId() : workingTask.getId();
    this.isWorkingOnATask = isWorkingOnATask;
  }

  private DefaultMenuItem buildSubMenuItem(SubMenuItem subMenuItem, int menuIndex) {
    boolean isExternalLink = isExternalLink(subMenuItem);
    DefaultMenuItem item = new PortalMenuBuilder(subMenuItem.getLabel(), subMenuItem.getMenuKind(), isWorkingOnATask)
        .url(subMenuItem.buildLink())
        .icon(subMenuItem.getIcon())
        .cleanParam(isExternalLink)
        .menuIndex(menuIndex)
        .build();
    return item;
  }

  private boolean isExternalLink(SubMenuItem subMenuItem) {
    return subMenuItem.getMenuKind() == MenuKind.EXTERNAL_LINK || subMenuItem.getMenuKind() == MenuKind.THIRD_PARTY
        || !UrlUtils.isIvyUrl(subMenuItem.getLink());
  }

  public boolean isShowLegacyUI() {
    return HomepageUtils.isShowLegacyUI();
  }

  private DefaultMenuItem buildThirdPartyItem(Application application, int menuIndex) {
    String menuIcon = StringUtils.defaultString(application.getMenuIcon());
    String iconClass = (menuIcon.startsWith("fa") ? "fa " : "si ") + menuIcon;
    return new PortalMenuBuilder(ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application), MenuKind.THIRD_PARTY, this.isWorkingOnATask)
        .icon(iconClass)
        .url(UrlUtils.buildUrl(application.getLink()))
        .workingTaskId(this.workingTaskId)
        .cleanParam(true)
        .menuIndex(menuIndex)
        .build();
  }

  private MenuElement buildDashboardItem() {
    var dashboardTitle = translate(DASHBOARD);
    var dashboardId = "";
    String dashboardLink = PortalNavigator.getPortalStartUrl();
    String defaultHomepageConfig = HomepageUtils.getHomepageName();
    HomepageType configHomepageType = HomepageType.getType(defaultHomepageConfig);
    if (HomepageType.DASHBOARD != configHomepageType) {
      dashboardLink = getDashboardLink();
    }

    if (!isShowLegacyUI()) {
      var dashboards = DashboardUtils.collectDashboards();
      if (CollectionUtils.isNotEmpty(dashboards)) {
        DefaultSubMenu dashboardGroupMenu = DefaultSubMenu.builder()
                .label(dashboardTitle)
                .icon(PortalMenuItem.DEFAULT_DASHBOARD_ICON)
                .id(String.format(DASHBOARD_MENU_PATTERN, MenuKind.DASHBOARD.name()))
                .styleClass(DASHBOARD_MENU_JS_CLASS).build();
        if (dashboards.size() > 1) {
          for (var board : dashboards) {
            var iconClass = board.getIsPublic() ? "si si-network-share" : "si si-single-neutral-shield";
            var dashboardMenu = new PortalMenuBuilder(board.getTitle(), MenuKind.DASHBOARD, this.isWorkingOnATask)
                  .icon(iconClass)
                  .url(dashboardLink)
                  .workingTaskId(this.workingTaskId).build();
            dashboardMenu.setId(String.format(DASHBOARD_MENU_ITEM_PATTERN, board.getId()));
            dashboardGroupMenu.getElements().add(dashboardMenu);
          }
          if (StringUtils.endsWith(Ivy.request().getRootRequest().getRequestPath(), DASHBOARD_PAGE_URL)) {
            dashboardGroupMenu.setExpanded(true);
          }
          return dashboardGroupMenu;
        } else {
          dashboardTitle = dashboards.get(0).getTitle();
          dashboardId = dashboards.get(0).getId();
        }
      }
    }

    var dashboardMenu = new PortalMenuBuilder(dashboardTitle, MenuKind.DASHBOARD, this.isWorkingOnATask)
            .icon(PortalMenuItem.DEFAULT_DASHBOARD_ICON)
            .url(dashboardLink)
            .workingTaskId(this.workingTaskId).build();
    if (StringUtils.isBlank(dashboardId)) {
      dashboardId = dashboardMenu.getId();
    }
    dashboardMenu.setId(String.format(DASHBOARD_MENU_PATTERN, dashboardId));
    return dashboardMenu;
  }

  public String getDashboardLink() {
    return PortalNavigator.getDashboardLink();
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
      case DASHBOARD_CONFIGURATION:
        buildBreadCrumbForDashboardConfiguration();
        break;
      case EDIT_DASHBOARD_DETAILS:
        buildBreadCrumbForEditDashboardDetail();
        break;
      case PROCESS_VIEWER:
        buildBreadCrumbForProcessViewer(userCase);
        break;
      case PORTAL_MANAGEMENT:
        setPortalHomeMenuToBreadcrumbModel();
        breadcrumbModel.getElements().add(buildGenericMenuItem("/ch.ivy.addon.portalkit.ui.jsf/PortalManagement/AdminSetting"));
        break;
      default:
        break;
    }
  }

  private void buildBreadCrumbForProcessViewer(ICase userCase) {
    setPortalHomeMenuToBreadcrumbModel();
    var menuItem = (DefaultMenuItem) buildGenericMenuItem("/ch.ivy.addon.portalkit.ui.jsf/ProcessViewer/Title");
    if (userCase != null && StringUtils.isNotEmpty(userCase.getProcessStart().getName())) {
      menuItem.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/ProcessViewer/Breadcrumb",
          Arrays.asList(userCase.getProcessStart().getName())));
    }
    breadcrumbModel.getElements().add(menuItem);
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

  private void buildBreadCrumbForDashboardConfiguration() {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements()
        .add(buildGenericMenuItem("/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/Title"));
  }

  private void buildBreadCrumbForEditDashboardDetail() {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements()
        .add(buildGenericMenuItem("/ch.ivy.addon.portalkit.ui.jsf/dashboard/dashboardManagement/editDashboard"));
  }

  private MenuItem buildPortalHomeMenuItem() {
    DefaultMenuItem menuItem = DefaultMenuItem.builder().build();
    menuItem.setIcon("si si-house-chimney-2 portal-icon");
    menuItem.setOnclick("navigateToPortalHome();");
    return menuItem;
  }

  private DefaultMenuItem buildMenuItemFromPortalSubMenuItem(SubMenuItem subMenuItem) {
    DefaultMenuItem menuItem = DefaultMenuItem.builder().build();
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
    DefaultMenuItem menuItem = DefaultMenuItem.builder().build();
    String taskName = StringUtils.isEmpty(userTask.getName()) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable") : userTask.getName();
    menuItem.setValue(String.join(": ", Ivy.cms().co("/Labels/Task"), taskName));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private MenuItem buildCaseDetailsMenuItem(ICase userCase) {
    DefaultMenuItem menuItem = DefaultMenuItem.builder().build();
    menuItem.setValue(String.join(": ", Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/case"), userCase.getName()));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private MenuItem buildExpressBusinessMenuItem(String caseId) {
    DefaultMenuItem menuItem = DefaultMenuItem.builder().build();
    menuItem.setValue(Ivy.cms().co("/Dialogs/ch/ivy/addon/express/generic/expressWorkflowName", Arrays.asList(caseId)));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private MenuItem buildGenericMenuItem(String cms) {
    DefaultMenuItem menuItem = DefaultMenuItem.builder().build();
    menuItem.setValue(Ivy.cms().co(cms));
    menuItem.setUrl("#");
    menuItem.setDisabled(true);
    return menuItem;
  }

  private void setPortalHomeMenuToBreadcrumbModel() {
    breadcrumbModel.getElements().add(buildPortalHomeMenuItem());
  }

  private String translate(String cmsURI) {
    return ApplicationMultiLanguageAPI.getCmsValueByUserLocale(cmsURI);
  }

  public void storeSelectedMenuItems() {
    var requestParamMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    var selectedMenuItemId = Optional.ofNullable(requestParamMap.get(SELECTED_MENU_ID)).orElse("");
    var isWorkingOnATask = Optional.ofNullable(requestParamMap.get(IS_WORKING_ON_TASK)).map(BooleanUtils::toBoolean).orElse(false);
    var isOpenOnNewTab =  Optional.ofNullable(requestParamMap.get(IS_OPEN_NEW_TAB)).map(BooleanUtils::toBoolean).orElse(false);
    session().setAttribute(SELECTED_MENU_ID, selectedMenuItemId);
    DashboardUtils.updateSelectedDashboardToSession(selectedMenuItemId);

    if (!isWorkingOnATask && !isOpenOnNewTab) {
      session().setAttribute(PREV_SELECTED_MENU_ID, selectedMenuItemId);
    }

    if (isOpenOnNewTab) {
      var prevSelectedMenuItemId = session().getAttribute(PREV_SELECTED_MENU_ID);
      if (prevSelectedMenuItemId == null) {
        prevSelectedMenuItemId = "";
      }
      PrimeFaces.current().executeScript(String.format(CLICK_ON_MENU_ITEM_PATTERN,
            prevSelectedMenuItemId, session().getAttribute(SELECTED_MENU_ID)));
    }
  }

  public void resetSelectedMenuItems() {
    session().setAttribute(SELECTED_MENU_ID, null);
    session().setAttribute(PREV_SELECTED_MENU_ID, null);
  }

  private IWorkflowSession session() {
    return Ivy.session();
  }

}
