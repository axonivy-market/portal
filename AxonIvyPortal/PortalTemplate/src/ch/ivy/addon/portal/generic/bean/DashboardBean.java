package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CompactProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardOrder;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.CaseEmptyMessage;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.enums.TaskEmptyMessage;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IStartElement;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;

  protected List<Dashboard> dashboards;
  protected Dashboard selectedDashboard;
  private String selectedDashboardId;
  protected DashboardWidget widget;
  protected boolean isReadOnlyMode;
  private int currentDashboardIndex;
  private List<WidgetFilterModel> widgetFilters;
  private List<WidgetFilterModel> deleteFilters;
  private ITask selectedTask;
  private boolean isRunningTaskWhenClickingOnTaskInList;
  private CaseEmptyMessage noCasesMessage;
  private TaskEmptyMessage noTasksMessage;
  private List<DashboardTemplate> dashboardTemplates;

  @PostConstruct
  public void init() {
    currentDashboardIndex = 0;
    isReadOnlyMode = true;
    dashboards = collectDashboards();
    if (CollectionUtils.isNotEmpty(dashboards)) {
      selectedDashboardId = readDashboardFromSession();
      currentDashboardIndex = findIndexOfDashboardById(selectedDashboardId);
      selectedDashboard = dashboards.get(currentDashboardIndex);
      // can not find dashboard by dashboard id session in view mode
      if (StringUtils.isBlank(selectedDashboardId)
          || (!selectedDashboardId.equalsIgnoreCase(selectedDashboard.getId()) && dashboards.size() > 1)) {
        storeDashboardInSession(selectedDashboard.getId());
      }
    }
    buildWidgetModels(selectedDashboard);
    isRunningTaskWhenClickingOnTaskInList = new GlobalSettingService()
        .findGlobalSettingValue(GlobalVariable.DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST)
        .equals(BehaviourWhenClickingOnLineInTaskList.RUN_TASK.name());
  }

  protected List<Dashboard> collectDashboards() {
    List<Dashboard> visibleDashboards = DashboardUtils.getAllVisibleDashboardsOfSessionUser();
    List<DashboardOrder> dashboardOrders = DashboardUtils.getDashboardOrdersOfSessionUser();
    Map<String, Dashboard> idToDashboard = DashboardUtils.createMapIdToDashboard(visibleDashboards);
    List<Dashboard> collectedDashboards = new ArrayList<>();
    for (DashboardOrder dashboardOrder : dashboardOrders) {
      if (dashboardOrder.getDashboardId() == null) {
        continue;
      }
      Dashboard currentDashboard = idToDashboard.remove(dashboardOrder.getDashboardId());
      if (dashboardOrder.isVisible() && currentDashboard != null) {
        collectedDashboards.add(currentDashboard);
      }
    }
    collectedDashboards.addAll(idToDashboard.values());

    return collectedDashboards;
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
      String cmsUri = "";
      DashboardWidgetUtils.buildWidgetColumns(widget);
      switch (widget.getType()) {
        case TASK:
          cmsUri = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourTasks";
          break;
        case CASE:
          cmsUri = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCases";
          break;
        case PROCESS:
          cmsUri = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourProcesses";
          break;
        case CUSTOM:
          loadCustomWidget(widget);
          break;
        default:
          break;
      }
      if (StringUtils.isBlank(widget.getName())) {
        widget.setName(translate(cmsUri));
      }
      if (!(widget instanceof SingleProcessDashboardWidget)) {
        WidgetFilterService.getInstance().applyUserFilterFromSession(widget);
      }
      DashboardWidgetUtils.removeStyleNewWidget(widget);
    }
  }

  private void loadCustomWidget(DashboardWidget widget) {
    CustomDashboardWidget customWidget = (CustomDashboardWidget) widget;
    if (StringUtils.isNotBlank(customWidget.getData().getProcessStart())) {
      String url = ProcessStartAPI.findStartableLinkByUserFriendlyRequestPath(customWidget.getData().getProcessStart());

      if(StringUtils.isBlank(url)) return;

      IStartElement element = ProcessStartAPI.findStartElementByProcessStartFriendlyRequestPath(customWidget.getData().getProcessStart());

      if(Objects.isNull(element)) return;

      customWidget.getData().setStartProcessParams(element.startParameters());

      List<IWebStartable> allPortalProcesses = ProcessService.newInstance().findProcesses().getProcesses();
      customWidget.getData().setStartableProcessStart(allPortalProcesses.stream()
        .filter(proccess -> proccess.getLink().toString().contentEquals(url)).findFirst().get());
      customWidget.loadParameters();
      customWidget.getData().setUrl(url);
      customWidget.getData().setType(DashboardCustomWidgetType.PROCESS);
    } else {
      customWidget.getData().setType(DashboardCustomWidgetType.EXTERNAL_URL);
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
    TaskUtils.handleStartTask(task, null, PortalConstants.RESET_TASK_CONFIRMATION_DIALOG);
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
    storeDashboardInSession(selectedDashboard.getId());
    buildWidgetModels(selectedDashboard);
  }

  public void onDashboardChangeByDropdown() {
    if (selectedDashboardId != null) {
      currentDashboardIndex = findIndexOfDashboardById(selectedDashboardId);
      storeDashboardInSession(selectedDashboardId);
      selectedDashboard = dashboards.get(currentDashboardIndex);
      buildWidgetModels(selectedDashboard);
    }
  }

  public void startTask(ITask task) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(task.getStartLinkEmbedded().getRelative());
  }

  public String createExtractedTextFromHtml(String text) {
    return HtmlParser.extractTextFromHtml(text);
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
}
