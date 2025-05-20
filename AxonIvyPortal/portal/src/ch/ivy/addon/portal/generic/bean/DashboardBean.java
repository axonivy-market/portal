package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.enums.SessionAttribute.SELECTED_DASHBOARD_ID;

import static ch.ivy.addon.portalkit.enums.SessionAttribute.SELECTED_SUB_DASHBOARD_ID;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import com.axonivy.portal.components.util.HtmlUtils;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.service.DeepLTranslationService;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.CaseEmptyMessage;
import ch.ivy.addon.portalkit.enums.DashboardDisplayType;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.enums.TaskEmptyMessage;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;
  private static final String ACCESSIBILITY_DASHBOARD_TEMPLATE_ID = "accessibility-dashboard-template";

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
  private List<DashboardTemplate> dashboardTemplates;
  protected String translatedText;
  protected String warningText;
  protected String dashboardUrl;
  protected List<Dashboard> importedDashboards;
  private String statisticApiUri;
  private String selectedDashboardName;
  private String searchScope;

  @PostConstruct
  public void init() {
    currentDashboardIndex = 0;
    
    if (isReadOnlyMode) {
      DashboardUtils.updateDashboardCache();
    }

    dashboards = collectDashboards();

    if (CollectionUtils.isNotEmpty(DashboardUtils.getDashboardsWithoutMenuItem())
        || isRequestPathForMainOrDetailModification() || isNavigateToDashboard()) {
      updateSelectedDashboardIdFromSessionAttribute();
      updateSelectedDashboard();
      storeAndHighlightDashboardIfRequired();
    }
    buildWidgetModels(selectedDashboard);
    isRunningTaskWhenClickingOnTaskInList = GlobalSettingService.getInstance()
        .findGlobalSettingValue(GlobalVariable.DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST)
        .equals(BehaviourWhenClickingOnLineInTaskList.RUN_TASK.name());

    // Set responsive option for default task list and case list
    if (DashboardUtils.isDefaultCaseListDashboard(selectedDashboard)
        || DashboardUtils.isDefaultTaskListDashboard(selectedDashboard)) {
      selectedDashboard.setIsResponsive(true);
    }

    buildStatisticApiUri();
  }
  
  private boolean isNavigateToDashboard() {
    Object attr = Ivy.session().getAttribute(SessionAttribute.NAVIGATE_TO_DASHBOARD.name());
    return attr != null && (boolean) attr;
}

  private void buildStatisticApiUri() {
    this.statisticApiUri = FacesContext.getCurrentInstance()
        .getExternalContext().getRequestContextPath() + "/api/statistics/data";
  }

  private boolean isRequestPathForMainOrDetailModification() {
    String requestPath = Ivy.request().getRequestPath();
    return requestPath.endsWith("/PortalMainDashboard.xhtml")
        || requestPath.endsWith("/PortalDashboardDetailModification.xhtml");
  }

  private void updateSelectedDashboard() {
    currentDashboardIndex = findIndexOfDashboardById(selectedDashboardId);
    selectedDashboard = dashboards.get(currentDashboardIndex);

    String selectedDashboardName = selectedDashboard.getTitles().stream()
        .filter(displayName -> displayName.getLocale().equals(Ivy.session().getContentLocale())).findFirst()
        .orElseGet(() -> selectedDashboard.getTitles().get(0)).getValue();
    setSelectedDashboardName(selectedDashboardName);
    initShareDashboardLink(selectedDashboard);
  }

  private void storeAndHighlightDashboardIfRequired() {
    if (StringUtils.isBlank(selectedDashboardId) || (!selectedDashboardId.equalsIgnoreCase(selectedDashboard.getId())
        && DashboardUtils.getDashboardsWithoutMenuItem().size() > 1)) {
      DashboardUtils.storeDashboardInSession(selectedDashboard.getId());
    }
    if (isReadOnlyMode) {
      DashboardUtils.highlightDashboardMenuItem(selectedDashboard.getId());
    }
  }

  protected List<Dashboard> collectDashboards() {
    return DashboardUtils.collectDashboards();
  }

  public void loadDashboardTemplate() {
    this.dashboardTemplates = DashboardUtils.getDashboardTemplates();
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

  public List<Dashboard> getDashboards() {
    return dashboards;
  }

  public void navigateToSelectedTaskDetails(SelectEvent<Object> event) {
    String uuid = ((ITask) event.getObject()).uuid();
    PortalNavigator.navigateToPortalTaskDetails(uuid);
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
    if (DashboardDisplayType.TOP_MENU.equals(selectedDashboard.getDashboardDisplayType())) {
      TaskUtils.handleStartTask(task, PortalPage.HOME_PAGE, PortalConstants.RESET_TASK_CONFIRMATION_DIALOG,
          selectedDashboardId);
    } else {
      TaskUtils.handleStartTask(task, PortalPage.HOME_PAGE, PortalConstants.RESET_TASK_CONFIRMATION_DIALOG);
    }

  }

  public void navigateToSelectedTaskDetails(ITask task) {
    PortalNavigator.navigateToPortalTaskDetails(task.uuid());
  }

  public void navigateToSelectedCaseDetails(SelectEvent<Object> event) {
    String uuid = ((ICase) event.getObject()).uuid();
    PortalNavigator.navigateToPortalCaseDetails(uuid);
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

  public String createParseTextFromHtml(String text) {
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

    if (widget.getType() == DashboardWidgetType.CASE) {
      CaseDashboardWidget caseWidget = ((CaseDashboardWidget) widget);

      if (CollectionUtils.isEmpty(caseWidget.getUserFilters())) {
        caseWidget.setUserFilters(new ArrayList<>());
      }

      List<DashboardFilter> savedFilters = caseWidget.getUserFilterCollection()
          .getSelectedWidgetFilters().stream()
          .map(WidgetFilterModel::getUserFilters)
          .filter(list -> CollectionUtils.isNotEmpty(list))
          .collect(ArrayList::new, List::addAll, List::addAll);
      caseWidget.setUserFilters(savedFilters);
      return;
    }

    if (widget.getType() == DashboardWidgetType.TASK) {
      TaskDashboardWidget taskWidget = ((TaskDashboardWidget) widget);

      if (CollectionUtils.isEmpty(taskWidget.getUserFilters())) {
        taskWidget.setUserFilters(new ArrayList<>());
      }

      List<DashboardFilter> savedFilters = taskWidget.getUserFilterCollection()
          .getSelectedWidgetFilters().stream()
          .map(WidgetFilterModel::getUserFilters)
          .filter(list -> CollectionUtils.isNotEmpty(list))
          .collect(ArrayList::new, List::addAll, List::addAll);
      taskWidget.setUserFilters(savedFilters);
      return;
    }


    var filterableColumns = new ArrayList<ColumnModel>();

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
    return CaseEmptyMessage.EMPTY_MESSAGE;
  }

  public TaskEmptyMessage getNoTasksMessage() {
    return TaskEmptyMessage.EMPTY_MESSAGE;
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

  private int findIndexOfDashboardById(String selectedDashboardId) {


    if (StringUtils.isNotBlank(selectedDashboardId)) {
      return dashboards.stream().filter(dashboard -> dashboard.getId().contentEquals(selectedDashboardId)).findFirst()
          .map(dashboards::indexOf).orElse(dashboards.stream().filter(dashboard -> DashboardDisplayType.SUB_MENU.equals(dashboard.getDashboardDisplayType()))
              .findFirst().map(dashboards::indexOf).orElse(0));
    }

    return dashboards.stream().filter(dashboard -> DashboardDisplayType.SUB_MENU.equals(dashboard.getDashboardDisplayType())).findFirst().map(dashboards::indexOf)
        .orElse(0);
  }


  public int getMaxRowNumberInExcel() {
    return Exporter.MAX_ROW_NUMBER_IN_EXCEL;
  }

  protected List<String> getSupportedLanguages() {
    return LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
  }

  public boolean isShowTranslation(DisplayName title) {
    return DeepLTranslationService.getInstance().isShowTranslation(title.getLocale());
  }

  public boolean isFocus(DisplayName title) {
    return !isShowTranslation(title) && title.getLocale().getLanguage().equals(UserUtils.getUserLanguage());
  }

  public String getTranslatedText() {
    return translatedText;
  }

  public void applyTranslatedText(DisplayName displayName) {
    if (StringUtils.isNotBlank(translatedText)) {
      displayName.setValue(translatedText);
      translatedText = "";
    }
  }

  public boolean isRequiredField(DisplayName displayName) {
    String currentLanguage = UserUtils.getUserLanguage();
    String displayLanguage = displayName.getLocale().getLanguage();
    return currentLanguage.equals(displayLanguage);
  }

  public String getWarningText() {
    return warningText;
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

  public String getStatisticApiUri() {
    return this.statisticApiUri;
  }

  public boolean canEnableQuickSearch(DashboardWidget widget) {
    return widget.getType().canEnableQuickSearch();
  }

  public boolean canShowWidgetInfoIcon(DashboardWidget widget) {
    return widget.getType().canShowWidgetInfoOption();
  }

  public boolean canShowExpandMode(DashboardWidget widget) {
    return widget.getType().canShowFullscreenModeOption();
  }

  public void setSelectedDashboardName(String dashboardName) {
    this.selectedDashboardName = dashboardName;
  }

  public String getSelectedDashboardName() {
    if (StringUtils.isBlank(selectedDashboardName)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dashboard");
    }
    return selectedDashboardName;
  }

  public boolean isHideCaseCreator() {
    return GlobalSettingService.getInstance().isHideCaseCreator();
  }

  public String getScreenReaderNotificationContent() {
    String templateId = Optional.ofNullable(this.selectedDashboard)
        .map(Dashboard::getTemplateId).orElse(StringUtils.EMPTY);

    if (StringUtils.isNotBlank(templateId)
        && ACCESSIBILITY_DASHBOARD_TEMPLATE_ID.equals(templateId)) {
      return Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/AccessibilityShortcuts/title");
    }
    return StringUtils.EMPTY;
  }

  public boolean isMainDashboardSelected() {
    return DashboardUtils.isMainDashboard(selectedDashboardId, true);
  }


  private void updateSelectedDashboardIdFromSessionAttribute() {
    if (Ivy.request().getRequestPath().endsWith("/PortalMainDashboard.xhtml")) {
      selectedDashboardId = (String) Ivy.session().getAttribute(SELECTED_DASHBOARD_ID.name());
    } else {
      selectedDashboardId = (String) Ivy.session().getAttribute(SELECTED_SUB_DASHBOARD_ID.name());
    }
  }
  
  public void setSearchScope(DashboardWidget widget) {
    if (widget instanceof TaskDashboardWidget taskWidget) {
      this.searchScope = getSearchScopeFromWidget(taskWidget.getFilterableColumns());
    }
    
    if (widget instanceof CaseDashboardWidget caseWidget) {
      this.searchScope = getSearchScopeFromWidget(caseWidget.getFilterableColumns());
    }
    
    if (widget instanceof ProcessDashboardWidget processWidget) {
      this.searchScope = getSearchScopeFromWidget(processWidget.getFilterableColumns());
    }
  }

  private String getSearchScopeFromWidget(List<ColumnModel> filterableColumns) {
    List<String> fieldList = filterableColumns.stream().filter(col -> Boolean.TRUE.equals(col.getQuickSearch()))
        .map(ColumnModel::getHeaderText).collect(Collectors.toList());
    if (fieldList.isEmpty()) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/NoColumnsEnabledForQuickSearch");
    }
    StringBuilder fieldNameList = appendFieldNameList(fieldList);
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/QuickSearchScope",
        Arrays.asList(fieldNameList.toString()));
  }

  private StringBuilder appendFieldNameList(List<String> fieldList) {
    return new StringBuilder(String.join(", ", fieldList));
}

  public String getSearchScope() {
    return this.searchScope;
  }
  
  public String getDashboardUrlByDashboard(String id) {
    return UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(id);
  }
  
  public List<String> getDashboardDisplayTypeList() {
    return DashboardDisplayType.getTypeList();
  }
  
  public String getDashboardDisplayTypeLabel(DashboardDisplayType type) {
    return DashboardDisplayType.getDisplayLabel(type);
  }
  
  public List<Dashboard> getPublicDashboards() {
    return DashboardUtils.getPublicDashboards();
  }
}
