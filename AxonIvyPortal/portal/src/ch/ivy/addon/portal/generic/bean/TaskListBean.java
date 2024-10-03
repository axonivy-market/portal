package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.CASE;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.NEWS;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.PROCESS;
import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.TASK;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.service.DeepLTranslationService;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.CaseEmptyMessage;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.TaskEmptyMessage;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class TaskListBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;

  private static final String DEFAULT_COMPLEX_USER_FILTER_ID =
      "widget-configuration-form:new-widget-configuration-component:predefined-filter";
  private static final String DEFAULT_USER_FILTER_ID =
      "widget-configuration-form:new-widget-configuration-component:user-filter";
  private static final String DEFAULT_WIDGET_TITLE_ID =
      "widget-configuration-form:new-widget-configuration-component:widget-title-group";
  private static final String PROCESS_ICON_CUSTOM_FIELD = "cssIcon";
  private static final String DEFAULT_PROCESS_ICON = "si si-hierarchy-6 si-rotate-270";

  private String selectedDashboardId;
  protected DashboardWidget widget;
  protected boolean isReadOnlyMode = true;
  private int currentDashboardIndex;
  private List<WidgetFilterModel> widgetFilters;
  private List<WidgetFilterModel> deleteFilters;
  private ITask selectedTask;
  private boolean isRunningTaskWhenClickingOnTaskInList;
  protected String translatedText;
  protected String warningText;
  protected String dashboardUrl;
  private String clientStatisticApiUri;

  @PostConstruct
  public void init() {
    this.widget = DashboardWidgetUtils.buildDefaultTaskWidget("ID", "Your Task");
  }


  protected List<Dashboard> collectDashboards() {
    return DashboardUtils.collectDashboards();
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
    TaskUtils.handleStartTask(task, PortalPage.HOME_PAGE, PortalConstants.RESET_TASK_CONFIRMATION_DIALOG);
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
  }

  // Update latest widget name
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

      List<DashboardFilter> savedFilters = caseWidget.getUserFilterCollection().getSelectedWidgetFilters().stream()
          .map(WidgetFilterModel::getUserFilters).filter(list -> CollectionUtils.isNotEmpty(list))
          .collect(ArrayList::new, List::addAll, List::addAll);
      caseWidget.setUserFilters(savedFilters);
      return;
    }

    if (widget.getType() == DashboardWidgetType.TASK) {
      TaskDashboardWidget taskWidget = ((TaskDashboardWidget) widget);

      if (CollectionUtils.isEmpty(taskWidget.getUserFilters())) {
        taskWidget.setUserFilters(new ArrayList<>());
      }

      List<DashboardFilter> savedFilters = taskWidget.getUserFilterCollection().getSelectedWidgetFilters().stream()
          .map(WidgetFilterModel::getUserFilters).filter(list -> CollectionUtils.isNotEmpty(list))
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

  public String getClientStatisticApiUri() {
    return this.clientStatisticApiUri;
  }

  public boolean canEnableQuickSearch(DashboardWidget widget) {
    return widget.getType().canEnableQuickSearch();
  }

  public boolean canShowWidgetInfoIcon(DashboardWidget widget) {
    return widget.getType().canShowWidgetInfoOption();
  }

  public boolean canShowExpandMode(DashboardWidget widget) {
    return false;
  }

  public boolean hasAdminPermission() {
    return PermissionUtils.isSessionUserHasAdminRole();
  }


  public String getComponentToProcessOnSave() {
    var componentId = "@this";
    DashboardWidget processWidget = getWidget();
    if (processWidget != null) {
      componentId = DEFAULT_WIDGET_TITLE_ID;
      if (NEWS != processWidget.getType() && CASE != processWidget.getType() && TASK != processWidget.getType()) {
        String userFilterId = String.format(DEFAULT_USER_FILTER_ID, processWidget.getId());
        componentId = componentId.concat(" ").concat(userFilterId);
      }
      if (PROCESS == processWidget.getType()) {
        componentId = componentId.concat(" widget-configuration-form");
      }
      if (CASE == processWidget.getType() || TASK == processWidget.getType()) {
        String userFilterId = String.format(DEFAULT_COMPLEX_USER_FILTER_ID, processWidget.getId());
        componentId = componentId.concat(" ").concat(userFilterId);
      }
    }
    return componentId;
  }

}
