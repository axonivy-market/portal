package ch.addon.portal.generic.menu;

import static ch.ivy.addon.portalkit.util.DashboardUtils.DASHBOARD_MENU_JS_CLASS;
import static ch.ivy.addon.portalkit.util.DashboardUtils.DASHBOARD_PAGE_URL;
import static ch.ivy.addon.portalkit.util.DashboardUtils.PARENT_DASHBOARD_MENU_PATTERN;
import static ch.ivy.addon.portalkit.util.DashboardUtils.SUB_DASHBOARD_MENU_PATTERN;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.components.publicapi.ApplicationMultiLanguageAPI;
import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;
import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.dto.menu.CustomMenuItemDefinition;
import com.axonivy.portal.dto.menu.DashboardMenuItemDefinition;
import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.dto.menu.StaticPageMenuItemDefinition;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;
import com.axonivy.portal.menu.management.MenuLoader;
import com.axonivy.portal.util.MenuUtils;
import com.axonivy.portal.menu.management.enums.MenuSource;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.IvyCacheService;

import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;
import ch.addon.portal.generic.userprofile.homepage.HomepageType;
import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.BreadCrumbKind;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.service.MainMenuEntryService;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

@Named
@ViewScoped
public class MenuView implements Serializable {
  private static final long serialVersionUID = 3188259472933435953L;

  private static final String DASHBOARD = "/ch.ivy.addon.portalkit.ui.jsf/common/dashboard";
  public static final String SELECTED_MENU_ID = "selectedMenuId";
  public static final String PREV_SELECTED_MENU_ID = "prevSelectedMenuId";
  public static final String IS_WORKING_ON_TASK = "isWorkingOnATask";
  public static final String IS_OPEN_NEW_TAB = "isOpenOnNewTab";
  public static final String CLICK_ON_MENU_ITEM_PATTERN = "fireEventClickOnMenuItem('%s', '%s')";

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

    List<PortalMenuItemDefinition> definitions = MenuLoader.loadMenuDefinitions();
    Set<String> startableProcessLinks =
        definitions.stream().anyMatch(CustomMenuItemDefinition.class::isInstance)
            ? collectStartableProcessLinks()
            : Set.of();

    for (PortalMenuItemDefinition def : definitions) {
      MenuElement element = buildMenuElement(def, startableProcessLinks);
      if (element != null) {
        mainMenuModel.getElements().add(element);
      }
    }

    for (PortalMenuItemDefinition def : MenuLoader.loadPrivateThirdPartyDefinitions()) {
      MenuElement element = buildMenuElement(def, startableProcessLinks);
      if (element != null) {
        mainMenuModel.getElements().add(element);
      }
    }

    mainMenuModel.generateUniqueIds();
  }

  private MenuElement buildMenuElement(PortalMenuItemDefinition def, Set<String> startableProcessLinks) {
    if (def instanceof StandardMenuItemDefinition std) {
      return std.getStandardType() == StandardMenuItemDefinitionType.DASHBOARD
          ? buildDashboardItem()
          : buildStandardSimpleItem(std);
    }
    List<String> permissions = def.getPermissions();
    if (permissions != null && !permissions.isEmpty() && !hasAnyPermission(permissions)) {
      return null;
    }
    if (def instanceof DashboardMenuItemDefinition dashboardDef) {
      return buildTopMenuDashboardItem(dashboardDef);
    }
    if (def instanceof CustomMenuItemDefinition customDef) {
      return canStartCustomProcess(customDef, startableProcessLinks)
          ? buildCustomMenuItem(customDef)
          : null;
    }
    if (def instanceof ExternalLinkMenuItemDefinition externalDef) {
      return externalDef.getSource() == MenuSource.THIRD_PARTY_APP_CONFIGURATION
          ? buildThirdPartyItem(externalDef)
          : buildExternalLinkItem(externalDef);
    }
    if (def instanceof StaticPageMenuItemDefinition staticDef) {
      return buildStaticPageItem(staticDef);
    }
    return null;
  }

  private boolean hasAnyPermission(List<String> permissions) {
    return permissions.stream().anyMatch(p ->
        Strings.CS.startsWith(p, "#")
            ? Strings.CS.equals(Ivy.session().getSessionUser().getMemberName(), p)
            : PermissionUtils.doesSessionUserHaveRole(p));
  }

  private Set<String> collectStartableProcessLinks() {
    IvyCacheService cacheService = IvyCacheService.getInstance();
    String sessionUserId = getSessionUserId();
    Optional<Object> cached =
        cacheService.getSessionCacheValue(IvyCacheIdentifier.PORTAL_STARTABLE_PROCESS_LINKS, sessionUserId);
    if (cached.isPresent() && cached.get() instanceof Set<?> cachedLinks) {
      return cachedLinks.stream().map(String.class::cast).collect(Collectors.toSet());
    }
    Set<String> startableLinks = Optional.ofNullable(ProcessService.getInstance().findProcesses()).orElse(List.of())
        .stream()
        .map(startable -> stripQuery(startable.getLink().getRelative()))
        .filter(StringUtils::isNotBlank)
        .collect(Collectors.toSet());
    cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_STARTABLE_PROCESS_LINKS, sessionUserId, startableLinks);
    return startableLinks;
  }

  private static String getSessionUserId() {
    String sessionIdAttribute = SessionAttribute.SESSION_IDENTIFIER.name();
    if (Ivy.session().getAttribute(sessionIdAttribute) == null) {
      Ivy.session().setAttribute(sessionIdAttribute, UUID.randomUUID().toString());
    }
    return (String) Ivy.session().getAttribute(sessionIdAttribute);
  }

  private boolean canStartCustomProcess(CustomMenuItemDefinition def, Set<String> startableProcessLinks) {
    String startLink = stripQuery(def.getProcessStartPath());
    if (StringUtils.isBlank(startLink)) {
      return false;
    }
    return startableProcessLinks.contains(startLink);
  }

  private static String stripQuery(String link) {
    if (StringUtils.isBlank(link)) {
      return link;
    }
    int queryIndex = link.indexOf('?');
    return queryIndex >= 0 ? link.substring(0, queryIndex) : link;
  }

  private MenuItem buildStandardSimpleItem(StandardMenuItemDefinition std) {
    StandardMenuItemDefinitionType type = std.getStandardType();
    MenuKind kind = type == StandardMenuItemDefinitionType.PROCESS ? MenuKind.PROCESS_LIST : MenuKind.STANDARD;
    return new PortalMenuBuilder(Ivy.cms().co(type.getCmsUri()), kind, isWorkingOnATask)
        .icon(buildIconClass(std.getIcon()))
        .onClick(type.getOnClick())
        .url("#")
        .workingTaskId(workingTaskId)
        .build();
  }

  private MenuItem buildTopMenuDashboardItem(DashboardMenuItemDefinition def) {
    String url = DEFAULT_LINK;
    if (def.getDashboard() != null && def.getDashboard().getId() != null) {
      url = UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(def.getDashboard().getId());
    }
    return new PortalMenuBuilder(def.getDisplayTitle(), MenuKind.MAIN_DASHBOARD, isWorkingOnATask)
        .icon(buildIconClass(def.getIcon()))
        .url(url)
        .workingTaskId(workingTaskId)
        .build();
  }

  private MenuItem buildCustomMenuItem(CustomMenuItemDefinition def) {
    String url = Optional.ofNullable(def.getProcessStart())
        .map(p -> p.getStartLink())
        .orElse(def.getProcessStartPath());
    return new PortalMenuBuilder(def.getDisplayTitle(), MenuKind.PROCESS, isWorkingOnATask)
        .icon(buildIconClass(def.getIcon()))
        .url(StringUtils.defaultIfBlank(url, DEFAULT_LINK))
        .workingTaskId(workingTaskId)
        .build();
  }

  private MenuItem buildExternalLinkItem(ExternalLinkMenuItemDefinition def) {
    String target = BooleanUtils.isTrue(def.getOpenInNewTab()) ? "_blank" : "_self";
    String safeUrl = MenuUtils.safeExternalUrl(def.getUrl());
    String url = StringUtils.defaultIfBlank(UrlUtils.buildUrl(safeUrl), DEFAULT_LINK);
    PortalMenuItem item = new PortalMenuBuilder(def.getDisplayTitle(), MenuKind.EXTERNAL_LINK, isWorkingOnATask)
        .icon(buildIconClass(def.getIcon()))
        .url(url)
        .target(target)
        .cleanParam(true)
        .workingTaskId(workingTaskId)
        .build();
    item.setRel(EXTERNAL_LINK_REL);
    return item;
  }

  private MenuItem buildStaticPageItem(StaticPageMenuItemDefinition def) {
    String url = MenuUtils.isSafeRelativePath(def.getUrl())
        ? PortalNavigatorAPI.buildPortalStaticPageUrl(def.getUrl())
        : DEFAULT_LINK;
    return new PortalMenuBuilder(def.getDisplayTitle(), MenuKind.STATIC_PAGE, isWorkingOnATask)
        .icon(buildIconClass(def.getIcon()))
        .url(url)
        .cleanParam(true)
        .workingTaskId(workingTaskId)
        .build();
  }

  private MenuItem buildThirdPartyItem(ExternalLinkMenuItemDefinition def) {
    PortalMenuItem item = new PortalMenuBuilder(def.getDisplayTitle(), MenuKind.THIRD_PARTY, isWorkingOnATask)
        .icon(buildIconClass(def.getIcon()))
        .url(UrlUtils.buildUrl(def.getUrl()))
        .workingTaskId(workingTaskId)
        .cleanParam(true)
        .build();
    item.setRel(EXTERNAL_LINK_REL);
    return item;
  }

  private static final String DEFAULT_LINK = "#";
  // Reverse-tabnabbing protection for links opened in a new tab
  private static final String EXTERNAL_LINK_REL = "noopener noreferrer";

  private void initTaskParams(ITask workingTask, boolean isWorkingOnATask) {
    this.workingTaskId = isNull(workingTask) ? Ivy.wfTask().getId() : workingTask.getId();
    this.isWorkingOnATask = isWorkingOnATask;
  }

  private static String buildIconClass(String icon) {
    return MenuUtils.buildIconClass(icon);
  }

  // --- Default-dashboard dropdown -------------------------------------------------

  private MenuElement buildDashboardItem() {
    var dashboardTitle = translate(DASHBOARD);
    String dashboardLink = determineDashboardLink();
    String currentLanguage = UserUtils.getUserLanguage();

    MainMenuEntryService mainMenuEntryService = new MainMenuEntryService();
    String mainMenuDisplayName = mainMenuEntryService.getNameInCurrentLocale();
    String mainMenuIcon = mainMenuEntryService.getMenuIcon();

    List<Dashboard> subItemDashboards = DashboardUtils.getDashboardsWithoutMenuItem();
    if (subItemDashboards.size() > 1) {
      return buildDashboardGroupMenu(subItemDashboards, dashboardTitle, mainMenuDisplayName, mainMenuIcon,
          currentLanguage, dashboardLink);
    } else if (subItemDashboards.size() == 1) {
      Dashboard dashboard = subItemDashboards.getFirst();
      String localizedTitle = getLocalizedTitle(dashboard, currentLanguage, dashboardTitle);
      return buildSingleDashboardMenu(localizedTitle, "", dashboardLink, dashboard.getIconClass());
    }

    return buildSingleDashboardMenu(dashboardTitle, "", dashboardLink, "");
  }

  private String determineDashboardLink() {
    String defaultHomepageConfig = HomepageUtils.getHomepageName();
    return HomepageType.DASHBOARD == HomepageType.getType(defaultHomepageConfig)
        ? getDefaultPortalStartUrl()
        : getDefaultDashboardUrl();
  }

  private MenuElement buildDashboardGroupMenu(List<Dashboard> subItemDashboards, String defaultTitle,
      String mainMenuDisplayName, String mainMenuIcon, String currentLanguage, String dashboardLink) {

    DefaultSubMenu dashboardGroupMenu = createDashboardGroupMenu(defaultTitle, mainMenuDisplayName, mainMenuIcon, dashboardLink);

    for (Dashboard board : subItemDashboards) {
      if (DashboardDisplayType.TOP_MENU.equals(board.getDashboardDisplayType())) {
        continue;
      }
      String iconClass = determineIconClass(board);
      var dashboardMenu = createDashboardMenu(board, iconClass);

      String localizedTitle =
          getLocalizedTitle(board, currentLanguage, (String) ((DefaultMenuItem) dashboardMenu).getValue());
      ((DefaultMenuItem) dashboardMenu).setValue(localizedTitle);

      dashboardGroupMenu.getElements().add(dashboardMenu);
    }

    setMenuExpansion(dashboardGroupMenu);
    return dashboardGroupMenu;
  }

  private DefaultSubMenu createDashboardGroupMenu(String defaultTitle, String mainMenuDisplayName, String mainMenuIcon, String dashboardLink) {
    // Encode URL as base64 in CSS class for JavaScript href patching (IVYPORTAL-19031)
    String urlClass = String.format("js-parent-dashboard-url-%s",
        Base64.getUrlEncoder().encodeToString(StringUtils.defaultIfEmpty(dashboardLink, EMPTY).getBytes()));

    return DefaultSubMenu.builder()
        .label(StringUtils.isBlank(mainMenuDisplayName) ? defaultTitle : mainMenuDisplayName)
        .icon(StringUtils.isBlank(mainMenuIcon) ? PortalMenuItem.DEFAULT_DASHBOARD_ICON : mainMenuIcon)
        .id(String.format(PARENT_DASHBOARD_MENU_PATTERN, MenuKind.DASHBOARD.name()))
        .styleClass(String.format("%s %s", DASHBOARD_MENU_JS_CLASS, urlClass)).build();
  }

  private String determineIconClass(Dashboard board) {
    if (StringUtils.isBlank(board.getIcon())) {
      board.setIcon(board.getIsPublic() ? "ti-world-share" : "ti-lock-square-rounded");
    }
    return buildIconClass(board.getIcon());
  }

  private MenuElement createDashboardMenu(Dashboard board, String iconClass) {
    var dashboardMenu = new PortalMenuBuilder(board.getTitle(), MenuKind.DASHBOARD, this.isWorkingOnATask)
        .icon(iconClass)
        .url(DashboardUtils.buildDashboardLink(board))
        .workingTaskId(this.workingTaskId)
        .build();
    dashboardMenu.setId(String.format(SUB_DASHBOARD_MENU_PATTERN, board.getId()));
    return dashboardMenu;
  }

  private String getLocalizedTitle(Dashboard board, String currentLanguage, String defaultTitle) {
    return board.getTitles().stream()
        .filter(name -> Strings.CI.equals(name.getLocale().toString(), currentLanguage)
            && StringUtils.isNotBlank(name.getValue()))
        .map(DisplayName::getValue).findFirst().orElse(defaultTitle);
  }

  private void setMenuExpansion(DefaultSubMenu dashboardGroupMenu) {
    String activeDashboardId = (String) session().getAttribute(SELECTED_MENU_ID);
    boolean isMainDashboardMenu = StringUtils.isNotEmpty(activeDashboardId)
        && activeDashboardId.endsWith(DashboardUtils.MAIN_DASHBOARD_MENU_POSTFIX);

    if (Strings.CS.endsWith(Ivy.request().getRootRequest().getRequestPath(), DASHBOARD_PAGE_URL)
        && !isMainDashboardMenu) {
      dashboardGroupMenu.setExpanded(true);
    }
  }

  private MenuElement buildSingleDashboardMenu(String dashboardTitle, String dashboardId, String dashboardLink,
      String dashboardIcon) {
    var dashboardMenu = new PortalMenuBuilder(dashboardTitle, MenuKind.DASHBOARD, this.isWorkingOnATask)
        .icon(StringUtils.isNoneEmpty(dashboardIcon) ? buildIconClass(dashboardIcon) : PortalMenuItem.DEFAULT_DASHBOARD_ICON)
        .url(dashboardLink).workingTaskId(this.workingTaskId).build();

    if (StringUtils.isBlank(dashboardId)) {
      dashboardId = dashboardMenu.getId();
    }
    dashboardMenu.setId(String.format(PARENT_DASHBOARD_MENU_PATTERN, dashboardId));

    return dashboardMenu;
  }

  // --- Breadcrumb -----------------------------------------------------

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

  private static final Map<BreadCrumbKind, String> SIMPLE_BREADCRUMB_CMS_KEYS = Map.of(
      BreadCrumbKind.USER_PROFILE,            "/ch.ivy.addon.portalkit.ui.jsf/userProfile/myProfileTitle",
      BreadCrumbKind.ABSENCES_MANAGEMENT,     "/ch.ivy.addon.portalkit.ui.jsf/AbsenceManagement/absenceAndDeputy",
      BreadCrumbKind.DASHBOARD_CONFIGURATION, "/ch.ivy.addon.portalkit.ui.jsf/dashboard/DashboardConfiguration/Title",
      BreadCrumbKind.EDIT_DASHBOARD_DETAILS,  "/ch.ivy.addon.portalkit.ui.jsf/dashboard/dashboardManagement/editDashboard",
      BreadCrumbKind.PORTAL_MANAGEMENT,       "/ch.ivy.addon.portalkit.ui.jsf/PortalManagement/AdminSetting",
      BreadCrumbKind.NOTIFICATION,            "/ch.ivy.addon.portalkit.ui.jsf/notifications/notificationTitle",
      BreadCrumbKind.STATISTIC_CONFIGURATION, "/Dialogs/com/axonivy/portal/page/StatisticConfiguration/StatisticConfiguration");

  public void loadBreadcrumb(String viewName, ITask userTask, ICase userCase) {
    breadcrumbModel = new DefaultMenuModel();
    if (StringUtils.isBlank(viewName)) {
      return;
    }
    BreadCrumbKind kind = BreadCrumbKind.valueOf(viewName);
    String simpleCmsKey = SIMPLE_BREADCRUMB_CMS_KEYS.get(kind);
    if (simpleCmsKey != null) {
      buildSimpleBreadcrumb(simpleCmsKey);
      return;
    }
    switch (kind) {
      case TECHNICAL_CASE -> buildBreadCrumbForTechnicalCaseList(userCase);
      case RELATED_TASK -> buildBreadCrumbForRelatedTask(userCase);
      case PROCESS -> buildBreadCrumbForProcess();
      case TASK_DETAIL -> buildBreadCrumbForTaskDetails(userTask);
      case CASE_DETAIL -> buildBreadCrumbForCaseDetails(userCase);
      case PROCESS_VIEWER -> buildBreadCrumbForProcessViewer(userTask, userCase);
      default -> {}
    }
  }

  private void buildSimpleBreadcrumb(String cmsKey) {
    setPortalHomeMenuToBreadcrumbModel();
    breadcrumbModel.getElements().add(buildGenericMenuItem(cmsKey));
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

  private void buildBreadCrumbForTechnicalCaseList(ICase userCase) {
    setPortalHomeMenuToBreadcrumbModel();
    DefaultMenuItem caseListSubmenuItem = buildCaseListMenuItem();
    String title = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/headerTitle/technicalCasesOfBusinessCaseTitle", Arrays.asList(Long.toString(userCase.getId()), userCase.names().current()));
    caseListSubmenuItem.setValue(title);
    caseListSubmenuItem.setUrl("#");
    caseListSubmenuItem.setStyleClass("breadcrumb-current-item");
    breadcrumbModel.getElements().add(caseListSubmenuItem);
  }

  private void buildBreadCrumbForRelatedTask(ICase userCase) {
    setPortalHomeMenuToBreadcrumbModel();
    DefaultMenuItem taskListSubmenuItem = buildTaskListMenuItem();
    String title = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/headerTitle/relatedTasksHeader").concat(" #").concat(Long.toString(userCase.getId()));
    taskListSubmenuItem.setValue(title);
    taskListSubmenuItem.setUrl("#");
    taskListSubmenuItem.setStyleClass("breadcrumb-current-item");
    breadcrumbModel.getElements().add(taskListSubmenuItem);
  }

  private void buildBreadCrumbForProcess() {
    setPortalHomeMenuToBreadcrumbModel();

    DefaultMenuItem processListSubmenuItem = buildProcessListMenuItem();
    processListSubmenuItem.setUrl("#");
    processListSubmenuItem.setStyleClass("breadcrumb-current-item");
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

  private MenuItem buildPortalHomeMenuItem() {
    return DefaultMenuItem.builder()
      .icon("ti ti-home portal-icon")
      .ariaLabel(Ivy.cm().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/Homepage"))
      .title(Ivy.cm().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/Homepage"))
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
      .styleClass("breadcrumb-current-item")
      .build();
  }

  private MenuItem buildCaseDetailsMenuItem(ICase userCase) {
    return DefaultMenuItem.builder()
      .value(String.join(": ", Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/case"), userCase.names().current()))
      .url("#")
      .styleClass("breadcrumb-current-item")
      .build();
  }

  private MenuItem buildGenericMenuItem(String cms) {
    return DefaultMenuItem.builder()
      .value(Ivy.cms().co(cms))
      .url("#")
      .styleClass("breadcrumb-current-item")
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
}
