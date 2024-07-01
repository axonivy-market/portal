package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import com.axonivy.portal.components.util.HtmlUtils;

import ch.addon.portal.generic.menu.MenuView;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.CaseEmptyMessage;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.enums.TaskEmptyMessage;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;

  protected List<Dashboard> dashboards;
  protected Dashboard selectedDashboard;
  private String selectedDashboardId;
  protected DashboardWidget widget;
  protected boolean isReadOnlyMode = true;
  private int currentDashboardIndex;
  private List<WidgetFilterModel> widgetFilters;
  private List<WidgetFilterModel> deleteFilters;
  private ITask selectedTask;
  private boolean isRunningTaskWhenClickingOnTaskInList;
  private CaseEmptyMessage noCasesMessage;
  private TaskEmptyMessage noTasksMessage;
  private List<DashboardTemplate> dashboardTemplates;
  protected String dashboardUrl;
  protected List<Dashboard> importedDashboards;
  public String selectedDashboardName;
  private String searchScope;


  @PostConstruct
  public void init() {
    currentDashboardIndex = 0;
    dashboards = collectDashboards();

    if (isReadOnlyMode) {
      MenuView menuView = (MenuView) ManagedBeans.get("menuView");
      menuView.updateDashboardCache(dashboards);
    }

    if (CollectionUtils.isNotEmpty(dashboards)) {
      selectedDashboardId = readDashboardFromSession();
      currentDashboardIndex = findIndexOfDashboardById(selectedDashboardId);
      selectedDashboard = dashboards.get(currentDashboardIndex);
      String selectedDashboardName = selectedDashboard.getTitles().stream()
          .filter(displayName -> displayName.getLocale().equals(Ivy.session().getContentLocale())).findFirst()
          .orElseGet(() -> selectedDashboard.getTitles().get(0))
          .getValue();
      setSelectedDashboardName(selectedDashboardName);
      initShareDashboardLink(selectedDashboard);
      // can not find dashboard by dashboard id session in view mode
      if (StringUtils.isBlank(selectedDashboardId)
          || (!selectedDashboardId.equalsIgnoreCase(selectedDashboard.getId()) && dashboards.size() > 1)) {
        storeDashboardInSession(selectedDashboard.getId());
      }
      if (isReadOnlyMode) {
        DashboardUtils.highlightDashboardMenuItem(selectedDashboard.getId());
      }
    }
    buildWidgetModels(selectedDashboard);
    isRunningTaskWhenClickingOnTaskInList = GlobalSettingService.getInstance()
        .findGlobalSettingValue(GlobalVariable.DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST)
        .equals(BehaviourWhenClickingOnLineInTaskList.RUN_TASK.name());
  }

  public String getSelectedDashboardName() {
    if (selectedDashboardName.isBlank()) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dashboard");
    }
    return selectedDashboardName;
  }

  public void setSelectedDashboardName(String name) {
    this.selectedDashboardName = name;
  }

  protected List<Dashboard> collectDashboards() {
    return DashboardUtils.collectDashboards();
  }

  public void loadDashboardTemplate() {
    this.dashboardTemplates = DashboardUtils.getDashboardTemplates();
  }

  public void mergeUserDashboard() {
    var userDashboardList = DashboardService.getInstance().getDashboardBySessionUser();
    if (CollectionUtils.isEmpty(userDashboardList)) {
      return;
    }
    for (Dashboard userDashboard : userDashboardList) {
      if (dashboards.contains(userDashboard)) {
        dashboards.set(dashboards.indexOf(userDashboard), userDashboard);
      }
    }
  }

  protected List<Dashboard> jsonToDashboards(String dashboardJSON) {
    List<Dashboard> mappingDashboards = BusinessEntityConverter.jsonValueToEntities(dashboardJSON, Dashboard.class);
    for (Dashboard dashboard : mappingDashboards) {
      if (CollectionUtils.isEmpty(dashboard.getPermissions())) {
        ArrayList<String> defaultPermissions = new ArrayList<>();
        defaultPermissions.add(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
        dashboard.setPermissions(defaultPermissions);
      }
    }
    return mappingDashboards;
  }

  protected String readDashboardBySessionUser() {
    return currentUser().getProperty(PortalVariable.DASHBOARD.key);
  }

  protected void buildWidgetModels(Dashboard dashboard) {
    if (dashboard == null || CollectionUtils.isEmpty(dashboard.getWidgets())) {
      return;
    }
    for (var widget : dashboard.getWidgets()) {
      DashboardWidgetUtils.buildWidgetColumns(widget);
      if (!(widget instanceof SingleProcessDashboardWidget)) {
        WidgetFilterService.getInstance().applyUserFilterFromSession(widget);
      }
      DashboardWidgetUtils.removeStyleNewWidget(widget);
    }
  }

  protected List<Dashboard> getVisiblePublicDashboards() {
    String dashboardJson = Ivy.var().get(PortalVariable.DASHBOARD.key);
    List<Dashboard> visibleDashboards = DashboardUtils.getVisibleDashboards(dashboardJson);
    setDashboardAsPublic(visibleDashboards);
    return visibleDashboards;
  }

  private void setDashboardAsPublic(List<Dashboard> visibleDashboards) {
    visibleDashboards.stream().forEach(dashboard -> dashboard.setIsPublic(true));
  }

  public List<Dashboard> getDashboards() {
    return dashboards;
  }

  public void navigateToSelectedTaskDetails(SelectEvent<Object> event) {
    Long taskId = ((ITask) event.getObject()).getId();
    PortalNavigator.navigateToPortalTaskDetails(taskId);
  }

  public void handleRowSelectEventOnTaskWidget(SelectEvent<Object> event) throws IOException {
    ITask task = ((ITask) event.getObject());
    handleSelectedTask(task);
  }

  private void handleSelectedTask(ITask task) throws IOException {
    if (isRunningTaskWhenClickingOnTaskInList) {
      handleStartTask(task);
    } else {
      navigateToSelectedTaskDetails(task);
    }
  }

  public void handleStartTask(ITask task) throws IOException {
    selectedTask = task;
    TaskUtils.handleStartTask(task, PortalPage.HOME_PAGE, PortalConstants.RESET_TASK_CONFIRMATION_DIALOG);
  }

  public void navigateToSelectedTaskDetails(ITask task) {
    PortalNavigator.navigateToPortalTaskDetails(task.getId());
  }

  public void navigateToSelectedCaseDetails(SelectEvent<Object> event) {
    Long caseId = ((ICase) event.getObject()).getId();
    PortalNavigator.navigateToPortalCaseDetails(caseId);
  }

  public void resetAndOpenTask() throws IOException {
    TaskUtils.resetTask(selectedTask);
    FacesContext.getCurrentInstance().getExternalContext().redirect(selectedTask.getStartLinkEmbedded().getRelative());
  }

  protected IUser currentUser() {
    return Ivy.session().getSessionUser();
  }

  public void onDashboardChange(int index) {
    currentDashboardIndex = index;
    selectedDashboard = dashboards.get(index);
    buildWidgetModels(selectedDashboard);
  }

  public String createExtractedTextFromHtml(String text) {
    return HtmlParser.extractTextFromHtml(text);
  }

  public String createParseTextFromHtml (String text) {
    return HtmlUtils.parseTextFromHtml(text);
  }

  public int getCurrentTabIndex() {
    return dashboards.indexOf(getSelectedDashboard());
  }

  public DashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(DashboardWidget widget) {
    this.widget = widget;
  }

  public boolean getIsReadOnlyMode() {
    return isReadOnlyMode;
  }

  public void setIsReadOnlyMode(boolean isReadOnlyMode) {
    this.isReadOnlyMode = isReadOnlyMode;
  }

  public int getCurrentDashboardIndex() {
    return currentDashboardIndex;
  }

  public void setCurrentDashboardIndex(int currentDashboardIndex) {
    this.currentDashboardIndex = currentDashboardIndex;
  }

  public boolean getIsEditMode() {
    return false;
  }

  protected String translate(String cms) {
    return Ivy.cms().co(cms);
  }

  protected String translate(String cms, List<Object> params) {
    return Ivy.cms().co(cms, params);
  }

  public Dashboard getSelectedDashboard() {
    return selectedDashboard;
  }

  public String getSelectedDashboardId() {
    return selectedDashboardId;
  }

  public void setSelectedDashboardId(String selectedDashboardId) {
    this.selectedDashboardId = selectedDashboardId;
  }

  public void loadAllWidgetSavedFilters() {
    widgetFilters = new ArrayList<>();
    deleteFilters = new ArrayList<>();
    widgetFilters.addAll(WidgetFilterService.getInstance().findAll());

    // Update latest widget name
    widgetFilters.forEach(filter -> {
      var selectedWidget = selectedDashboard.getWidgets().stream()
          .filter(widget -> widget.getId().equals(filter.getWidgetId()))
          .findFirst().orElse(null);
      if (selectedWidget != null) {
        filter.setWidgetName(selectedWidget.getName());
      }
    });
  }

  public void onClickSavedFilterItem(WidgetFilterModel filter, DashboardWidget widget) {
    if (filter == null || widget == null) {
      return;
    }

    if (widget.isSavedFilterSelected(filter)) {
      widget.getUserFilterCollection().getSelectedWidgetFilters().removeIf(WidgetFilterModel.isEqualFilter(filter));
    } else {
      widget.getUserFilterCollection().getSelectedWidgetFilters().add(filter);
    }

    var filterableColumns = new ArrayList<ColumnModel>();
    if (DashboardWidgetType.TASK == widget.getType()) {
      filterableColumns.addAll(((TaskDashboardWidget) widget).getFilterableColumns());
    }
    if (DashboardWidgetType.CASE == widget.getType()) {
      filterableColumns.addAll(((CaseDashboardWidget) widget).getFilterableColumns());
    }
    if (DashboardWidgetType.PROCESS == widget.getType()) {
      filterableColumns.addAll(((CompactProcessDashboardWidget) widget).getFilterableColumns());
    }
    WidgetFilterService.getInstance().buildFilterOptions(widget, filterableColumns);
    WidgetFilterService.getInstance().updateUserFilterOptionMap(widget);
  }

  public void deleteSavedFilter() {
    CollectionUtils.emptyIfNull(deleteFilters).forEach(filter -> {
      WidgetFilterService.getInstance().delete(filter.getId());
    });
    loadAllWidgetSavedFilters();
  }

  public List<WidgetFilterModel> getWidgetFilters() {
    return widgetFilters;
  }

  public void setWidgetFilters(List<WidgetFilterModel> widgetFilters) {
    this.widgetFilters = widgetFilters;
  }

  public List<WidgetFilterModel> getDeleteFilters() {
    return deleteFilters;
  }

  public void setDeleteFilters(List<WidgetFilterModel> deleteFilters) {
    this.deleteFilters = deleteFilters;
  }

  public CaseEmptyMessage getNoCasesMessage() {
    if (noCasesMessage == null) {
      List<CaseEmptyMessage> messages = Stream.of(CaseEmptyMessage.values()).collect(Collectors.toList());
      Random random = new Random();
      int index = random.ints(0, messages.size()).findFirst().getAsInt();
      noCasesMessage = messages.get(index);
    }
    return noCasesMessage;
  }

  public TaskEmptyMessage getNoTasksMessage() {
    if (noTasksMessage == null) {
      List<TaskEmptyMessage> messages = Stream.of(TaskEmptyMessage.values()).collect(Collectors.toList());
      Random random = new Random();
      int index = random.ints(0, messages.size()).findFirst().getAsInt();
      noTasksMessage = messages.get(index);
    }
    return noTasksMessage;
  }

  public List<DashboardTemplate> getDashboardTemplates() {
    if (CollectionUtils.isEmpty(dashboardTemplates)) {
      loadDashboardTemplate();
    }
    return dashboardTemplates;
  }

  public void setDashboardTemplates(List<DashboardTemplate> dashboardTemplates) {
    this.dashboardTemplates = dashboardTemplates;
  }

  private String readDashboardFromSession() {
    return (String) Ivy.session().getAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString());
  }

  private void storeDashboardInSession(String id) {
    Ivy.session().setAttribute(SessionAttribute.SELECTED_DASHBOARD_ID.toString(), id);
  }

  private int findIndexOfDashboardById(String selectedDashboardId) {
    int currentDashboardIndex = 0;
    if(StringUtils.isNotBlank(selectedDashboardId)) {
      currentDashboardIndex = dashboards.indexOf(dashboards.stream()
          .filter(dashboard -> dashboard.getId().contentEquals(selectedDashboardId)).findFirst().orElse(null));
      if(currentDashboardIndex == -1) {
        currentDashboardIndex = 0;
      }
    }
    return currentDashboardIndex;
  }

  public int getMaxRowNumberInExcel() {
    return Exporter.MAX_ROW_NUMBER_IN_EXCEL;
  }

  protected List<String> getSupportedLanguages() {
    IvyLanguage ivyLanguage = LanguageService.newInstance().findUserLanguages().getIvyLanguage();
    return ivyLanguage.getSupportedLanguages();
  }

  public String getDashboardUrl() {
    return dashboardUrl;
  }

  public void setDashboardUrl(String dashboardUrl) {
    this.dashboardUrl = dashboardUrl;
  }

  public void initShareDashboardLink(Dashboard dashboard) {
    setDashboardUrl(UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(dashboard.getId()));
  }

  public boolean isShowShareButtonOnDashboard() {
    return PermissionUtils.hasShareDashboardPermission() && selectedDashboard != null && !getIsEditMode() && selectedDashboard.getIsPublic();
  }

  public List<Dashboard> getImportedDashboards() {
    return importedDashboards;
  }

  public void setImportedDashboards(List<Dashboard> importedDashboards) {
    this.importedDashboards = importedDashboards;
  }

  public boolean canEnableQuickSearch(DashboardWidget widget) {
    return widget.getType().canEnableQuickSearch();
  }

  public boolean canShowWidgetInfoIcon(DashboardWidget widget) {
    return widget.getType().canShowWidgetInfoOption();
  }

  public void setSearchScope(DashboardWidget widget) {
    if (widget instanceof TaskDashboardWidget taskWidget) {
      this.searchScope = getSearchScopeFromWidget(taskWidget.getFilterableColumns());
    }
    
    if (widget instanceof CaseDashboardWidget caseWidget) {
      this.searchScope = getSearchScopeFromWidget(caseWidget.getFilterableColumns());
    }
  }

  private String getSearchScopeFromWidget(List<ColumnModel> filterableColumns) {
    List<String> fieldList = filterableColumns.stream().filter(col -> Boolean.TRUE.equals(col.getQuickSearch()))
        .map(ColumnModel::getHeaderText).collect(Collectors.toList());
    StringBuilder fieldNameList = appendFieldNameList(fieldList);
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/QuickSearchScope",
        Arrays.asList(fieldNameList.toString()));
  }

  private StringBuilder appendFieldNameList(List<String> fieldList) {
    StringBuilder fieldNameList = new StringBuilder();
    if (!fieldList.isEmpty()) {
      fieldNameList.append(String.join(", ", fieldList));
    } else {
      fieldNameList.append("none");
    }
    return fieldNameList;
  }

  public String getSearchScope() {
    return this.searchScope;
  }
}
