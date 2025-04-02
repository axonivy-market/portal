package ch.addon.portal.generic.menu;

import static ch.ivy.addon.portalkit.util.DashboardUtils.DASHBOARD_MENU_JS_CLASS;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DASHBOARD_PAGE_URL;
import static ch.ivy.addon.portalkit.util.DashboardUtils.PARENT_DASHBOARD_MENU_PATTERN;
import static ch.ivy.addon.portalkit.util.DashboardUtils.SUB_DASHBOARD_MENU_PATTERN;
import static java.util.Objects.isNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import com.axonivy.portal.components.publicapi.ApplicationMultiLanguageAPI;

import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;
import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.DashboardDisplayType;
import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.service.MainMenuEntryService;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
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
  private static String defaultPortalStartUrl;
  private static String defaultDashboardUrl;
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
    return new PortalMenuBuilder(subMenuItem.getLabel(), subMenuItem.getMenuKind(), isWorkingOnATask)
        .url(subMenuItem.buildLink())
        .icon(subMenuItem.getIcon())
        .cleanParam(isExternalLink)
        .menuIndex(menuIndex)
        .build();
  }

  private boolean isExternalLink(SubMenuItem subMenuItem) {
    return subMenuItem.getMenuKind() == MenuKind.EXTERNAL_LINK
        || subMenuItem.getMenuKind() == MenuKind.THIRD_PARTY;
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
    String dashboardLink = determineDashboardLink();
    String currentLanguage = UserUtils.getUserLanguage();

    MainMenuEntryService mainMenuEntryService = new MainMenuEntryService();
    String mainMenuDisplayName = mainMenuEntryService.getNameInCurrentLocale();
    String mainMenuIcon = mainMenuEntryService.getMenuIcon();

    List<Dashboard> subItemDashboards = getSubItemDashboards();
    if (subItemDashboards.size() > 1) {
      return buildDashboardGroupMenu(subItemDashboards, dashboardTitle, mainMenuDisplayName, mainMenuIcon,
          currentLanguage, dashboardLink);
    } else if (subItemDashboards.size() == 1) {
      Dashboard dashboard = subItemDashboards.getFirst();
      String localizedTitle = getLocalizedTitle(dashboard, currentLanguage, dashboardTitle);
      return buildSingleDashboardMenu(localizedTitle, dashboardId, dashboardLink, dashboard.getIcon());
    }

    return buildSingleDashboardMenu(dashboardTitle, "", dashboardLink, "");
  }

  private String determineDashboardLink() {
    String defaultHomepageConfig = HomepageUtils.getHomepageName();
    HomepageType configHomepageType = HomepageType.getType(defaultHomepageConfig);

    if (HomepageType.DASHBOARD != configHomepageType) {
      return getDefaultDashboardUrl();
    }

    return getDefaultPortalStartUrl();
  }

  private List<Dashboard> getSubItemDashboards() {
    return DashboardUtils.getDashboardsWithoutMenuItem();
  }

  private MenuElement buildDashboardGroupMenu(List<Dashboard> subItemDashboards, String defaultTitle,
      String mainMenuDisplayName, String mainMenuIcon, String currentLanguage, String dashboardLink) {

    DefaultSubMenu dashboardGroupMenu = createDashboardGroupMenu(defaultTitle, mainMenuDisplayName, mainMenuIcon);

    for (Dashboard board : subItemDashboards) {
      if (DashboardDisplayType.TOP_MENU.equals(board.getDashboardDisplayType())) {
        continue;
      }
      String iconClass = determineIconClass(board);
      var dashboardMenu = createDashboardMenu(board, dashboardLink, iconClass);

      String localizedTitle =
          getLocalizedTitle(board, currentLanguage, (String) ((DefaultMenuItem) dashboardMenu).getValue());
      ((DefaultMenuItem) dashboardMenu).setValue(localizedTitle);

      dashboardGroupMenu.getElements().add(dashboardMenu);
    }

    setMenuExpansion(dashboardGroupMenu);
    return dashboardGroupMenu;
  }

  private DefaultSubMenu createDashboardGroupMenu(String defaultTitle, String mainMenuDisplayName,
      String mainMenuIcon) {
    return DefaultSubMenu.builder().label(StringUtils.isBlank(mainMenuDisplayName) ? defaultTitle : mainMenuDisplayName)
        .icon(StringUtils.isBlank(mainMenuIcon) ? PortalMenuItem.DEFAULT_DASHBOARD_ICON : mainMenuIcon)
        .id(String.format(PARENT_DASHBOARD_MENU_PATTERN, MenuKind.DASHBOARD.name())).styleClass(DASHBOARD_MENU_JS_CLASS)
        .build();
  }

  private String determineIconClass(Dashboard board) {
    if (StringUtils.isBlank(board.getIcon())) {
      board.setIcon(board.getIsPublic() ? "si-network-share" : "si-single-neutral-shield");
    }
    return (board.getIcon().startsWith("fa") ? "fa " : "si ") + board.getIcon();
  }

  private MenuElement createDashboardMenu(Dashboard board, String dashboardLink, String iconClass) {
    var dashboardMenu = new PortalMenuBuilder(board.getTitle(), MenuKind.DASHBOARD, this.isWorkingOnATask)
        .icon(iconClass).url(dashboardLink).workingTaskId(this.workingTaskId).build();
    dashboardMenu.setId(String.format(SUB_DASHBOARD_MENU_PATTERN, board.getId()));
    return dashboardMenu;
  }

  private String getLocalizedTitle(Dashboard board, String currentLanguage, String defaultTitle) {
    return board.getTitles().stream()
        .filter(name -> StringUtils.equalsIgnoreCase(name.getLocale().toString(), currentLanguage)
            && StringUtils.isNotBlank(name.getValue()))
        .map(DisplayName::getValue).findFirst().orElse(defaultTitle);
  }

  private void setMenuExpansion(DefaultSubMenu dashboardGroupMenu) {
    String activeDashboardId = (String) session().getAttribute(SELECTED_MENU_ID);
    boolean isMainDashboardMenu =
        StringUtils.isNotEmpty(activeDashboardId) && activeDashboardId.endsWith(DashboardUtils.MAIN_DASHBOARD_MENU_POSTFIX);

    if (StringUtils.endsWith(Ivy.request().getRootRequest().getRequestPath(), DASHBOARD_PAGE_URL)
        && !isMainDashboardMenu) {
      dashboardGroupMenu.setExpanded(true);
    }
  }

  private MenuElement buildSingleDashboardMenu(String dashboardTitle, String dashboardId, String dashboardLink,
      String dashboardIcon) {
    var dashboardMenu = new PortalMenuBuilder(dashboardTitle, MenuKind.DASHBOARD, this.isWorkingOnATask)
        .icon(StringUtils.isNoneEmpty(dashboardIcon) ? dashboardIcon : PortalMenuItem.DEFAULT_DASHBOARD_ICON)
        .url(dashboardLink).workingTaskId(this.workingTaskId).build();

    if (StringUtils.isBlank(dashboardId)) {
      dashboardId = dashboardMenu.getId();
    }
    dashboardMenu.setId(String.format(PARENT_DASHBOARD_MENU_PATTERN, dashboardId));

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
      case TECHNICAL_CASE -> buildBreadCrumbForTechnicalCaseList(userCase);
      case RELATED_TASK -> buildBreadCrumbForRelatedTask(userCase);
      case PROCESS -> buildBreadCrumbForProcess();
      case TASK_DETAIL -> buildBreadCrumbForTaskDetails(userTask);
      case CASE_DETAIL -> buildBreadCrumbForCaseDetails(userCase);
      case USER_PROFILE -> buildBreadCrumbForUserProfile();
      case ABSENCES_MANAGEMENT -> buildBreadCrumbForAbsences();
      case DASHBOARD_CONFIGURATION -> buildBreadCrumbForDashboardConfiguration();
      case EDIT_DASHBOARD_DETAILS -> buildBreadCrumbForEditDashboardDetail();
      case PROCESS_VIEWER -> buildBreadCrumbForProcessViewer(userTask, userCase);
      case PORTAL_MANAGEMENT -> {
        setPortalHomeMenuToBreadcrumbModel();
        breadcrumbModel.getElements().add(buildGenericMenuItem("/ch.ivy.addon.portalkit.ui.jsf/PortalManagement/AdminSetting"));}
      case NOTIFICATION -> buildBreadCrumbForNotification();
      default -> {}
    }
  }

  private void buildBreadCrumbForProcessViewer(ITask userTask, ICase userCase) {
    setPortalHomeMenuToBreadcrumbModel();
    var menuItem = (DefaultMenuItem) buildGenericMenuItem("/ch.ivy.addon.portalkit.ui.jsf/ProcessViewer/Title");
    if (Objects.isNull(userCase) || StringUtils.isBlank(userCase.getProcessStart().getName())) {
      if (Objects.nonNull(userTask)) {
        userCase = userTask.getCase();
      }
    }

    if (Objects.nonNull(userCase) && StringUtils.isNotEmpty(userCase.getProcessStart().getName())) {
      menuItem.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/ProcessViewer/Breadcrumb", Arrays.asList(userCase.getProcessStart().getName())));
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

  private void buildBreadCrumbForTechnicalCaseList(ICase userCase) {
    setPortalHomeMenuToBreadcrumbModel();
    DefaultMenuItem caseListSubmenuItem = buildCaseListMenuItem();
    String title = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/headerTitle/technicalCasesOfBusinessCaseTitle", Arrays.asList(Long.toString(userCase.getId()), userCase.names().current()));
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
    return DefaultMenuItem.builder()
      .icon("si si-house-chimney-2 portal-icon")
      .onclick("navigateToPortalHome();")
      .build();
  }

  private DefaultMenuItem buildMenuItemFromPortalSubMenuItem(String cmsOfMenuItemLabel) {
    return DefaultMenuItem.builder().value(ApplicationMultiLanguageAPI.getCmsValueByUserLocale(cmsOfMenuItemLabel))
        .url(null).build();
  }

  private DefaultMenuItem buildTaskListMenuItem() {
    DefaultMenuItem taskMenu = buildMenuItemFromPortalSubMenuItem("/ch.ivy.addon.portalkit.ui.jsf/common/tasks");
    taskMenu.setOnclick("navigateToTaskList();");
    return taskMenu;
  }

  private DefaultMenuItem buildCaseListMenuItem() {
    DefaultMenuItem caseMenuItem = buildMenuItemFromPortalSubMenuItem("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases");
    caseMenuItem.setOnclick("navigateToCaseList();");
    return caseMenuItem;
  }

  private DefaultMenuItem buildProcessListMenuItem() {
    return buildMenuItemFromPortalSubMenuItem("/ch.ivy.addon.portalkit.ui.jsf/common/processes");
  }

  private MenuItem buildTaskDetailsMenuItem(ITask userTask) {
    String taskName = StringUtils.isEmpty(userTask.getName()) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable") : userTask.names().current();
    return DefaultMenuItem.builder()
      .value(String.join(": ", Ivy.cms().co("/Labels/Task"), taskName))
      .url("#")
      .disabled(true)
      .build();
  }

  private MenuItem buildCaseDetailsMenuItem(ICase userCase) {
    return DefaultMenuItem.builder()
      .value(String.join(": ", Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/case"), userCase.names().current()))
      .url("#")
      .disabled(true)
      .build();
  }

  private MenuItem buildGenericMenuItem(String cms) {
    return DefaultMenuItem.builder()
      .value(Ivy.cms().co(cms))
      .url("#")
      .disabled(true)
      .build();
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

  private String getDefaultPortalStartUrl() {
    if (StringUtils.isEmpty(defaultPortalStartUrl)) {
      defaultPortalStartUrl = PortalNavigator.getPortalStartUrl();
    }
    return defaultPortalStartUrl;
  }

  private String getDefaultDashboardUrl() {
    if (StringUtils.isEmpty(defaultDashboardUrl)) {
      defaultDashboardUrl = getDashboardLink();
    }
    return defaultDashboardUrl;
  }

  public void resetSelectedMenuItems() {
    session().setAttribute(SELECTED_MENU_ID, null);
    session().setAttribute(PREV_SELECTED_MENU_ID, null);
  }

  private IWorkflowSession session() {
    return Ivy.session();
  }

  private void buildBreadCrumbForNotification() {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements().add(buildGenericMenuItem("/ch.ivy.addon.portalkit.ui.jsf/notifications/notificationTitle"));
  }
}
