package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.JsonVariable;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;

  public static final String DEFAULT_DASHBOARD_JSON_URI = "/ch.ivy.addon.portalkit/variables/DefaultDashboardJson";
  protected List<Dashboard> dashboards;
  protected Dashboard selectedDashboard;
  private String selectedDashboardId;
  protected ObjectMapper mapper;
  protected DashboardWidget widget;
  protected boolean isReadOnlyMode;
  private int currentDashboardIndex;
  private boolean canEdit;

  @PostConstruct
  public void init() {
    canEdit = PermissionUtils.hasDashboardWritePermission();
    currentDashboardIndex = 0;
    isReadOnlyMode = true;
    dashboards = new ArrayList<>();
    if (mapper == null) {
      mapper = new ObjectMapper();
      mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }
    String dashboardInUserProperty = readDashboardBySessionUser();
    try {
      dashboards = defaultDashboards();
      if (StringUtils.isNoneEmpty(dashboardInUserProperty)) {
        List<Dashboard> dashboardSavedList = mappingDashboards(dashboardInUserProperty);
        for (Dashboard d : dashboardSavedList) {
          if (CollectionUtils.isNotEmpty(d.getWidgets())) {
            loadWidgets(d.getWidgets());
          }
          dashboards.set(dashboards.indexOf(d), d);
        }
      }
      if (CollectionUtils.isNotEmpty(dashboards)) {
        selectedDashboard = dashboards.get(0);
      }
      buildWidgetModels();
    } catch (IOException e) {
      Ivy.log().error(e);
    }
  }

  private ArrayList<Dashboard> mappingDashboards(String dashboardJSON)
      throws JsonProcessingException, JsonMappingException {
    return new ArrayList<>(Arrays.asList(mapper.readValue(dashboardJSON, Dashboard[].class)));
  }

  protected String readDashboardBySessionUser() {
    return currentUser().getProperty(JsonVariable.DASHBOARD.key);
  }

  protected void removeDashboardInUserProperty() {
    currentUser().removeProperty(JsonVariable.DASHBOARD.key);
  }
  
  private void loadWidgets(List<DashboardWidget> widgets) {
    for (DashboardWidget widget : widgets) {
      if (widget.getType().equals(DashboardWidgetType.PROCESS)) {
        loadProcessesOfWidget(widget);
      }
    }
  }

  private void buildWidgetModels() {
    for (Dashboard dashboard : dashboards) {
      buildSubWidgetModels(dashboard.getWidgets());
    }
  }

  protected void buildSubWidgetModels(List<DashboardWidget> widgets) {
    if (CollectionUtils.isEmpty(widgets)) {
      return;
    }
    for (DashboardWidget widget : widgets) {
      switch (widget.getType()) {
        case TASK:
          TaskDashboardWidget.buildColumns((TaskDashboardWidget) widget);

          for (ColumnModel columnModel : ((TaskDashboardWidget) widget).getColumns()) {
            updateTypeForCustomColumn(columnModel);
          }

          if (StringUtils.isBlank(widget.getName())) {
            widget.setName(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourTasks"));
          }
          break;
        case CASE:
          CaseDashboardWidget.buildColumns((CaseDashboardWidget) widget);

          for (ColumnModel columnModel : ((CaseDashboardWidget) widget).getColumns()) {
            updateTypeForCustomColumn(columnModel);
          }

          if (StringUtils.isBlank(widget.getName())) {
            widget.setName(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCases"));
          }
          break;
        case PROCESS:
          loadProcessesOfWidget(widget);
          if (StringUtils.isBlank(widget.getName())) {
            widget.setName(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourProcesses"));
          }
          break;
        default:
          break;
      }

      try {
        widget.buildPredefinedFilterData();
      } catch (ParseException e) {
        Ivy.log().error(e);
      }
    }
  }

  private void loadProcessesOfWidget(DashboardWidget widget) {
    ProcessDashboardWidget processWidget = (ProcessDashboardWidget) widget;
    if (processWidget.getDisplayMode().equals(ProcessWidgetMode.COMPACT_MODE)) {
      loadProcesses(processWidget);
    } else {
      loadProcessByPath(processWidget);
    }
  }

  private void loadProcessByPath(ProcessDashboardWidget processWidget) {
    List<DashboardProcess> processes = getAllPortalProcesses();

    for (DashboardProcess process : processes) {
      if (process.getId().contains(processWidget.getProcessPath())) {
        updateProcessStartIdForCombined(processWidget, process);
        processWidget.setProcess(process);
        break;
      }
    }
  }

  public void updateProcessStartIdForCombined(ProcessDashboardWidget processWidget, DashboardProcess process) {
    if (processWidget.getDisplayMode().equals(ProcessWidgetMode.COMBINED_MODE) && process.getProcessStartId() == null) {
      long processStartId = Ivy.session().getStartableProcessStarts().stream()
          .filter(processStart -> processStart.getLink().getRelative().equals(process.getStartLink())).findFirst()
          .get().getId();
      process.setProcessStartId(processStartId);
    }
  }

  private void loadProcesses(ProcessDashboardWidget processWidget) {
    List<DashboardProcess> processes;
    if (processWidget.isSelectedAllProcess()) {
      processes = getAllPortalProcesses();
    } else if (CollectionUtils.isNotEmpty(processWidget.getProcesses())) {
      processes = processWidget.getProcesses();
      processWidget.setProcesses(processes);
    } else {
      if (CollectionUtils.isNotEmpty(processWidget.getCategories())) {
        List<DashboardProcess> allPortalProcesses = getAllPortalProcesses();
        processes = allPortalProcesses.stream()
            .filter(process -> isProcessMatchedCategory(process, processWidget.getCategories()))
            .collect(Collectors.toList());
      } else {
        processes = getAllPortalProcesses();
      }
    }

    processWidget.setDisplayProcesses(processes);
    processWidget.setOriginalDisplayProcesses(processes);
  }

  private List<DashboardProcess> getAllPortalProcesses() {
    DashboardProcessBean dashboardProcessBean = ManagedBeans.get("dashboardProcessBean");
    return dashboardProcessBean == null ? new ArrayList<>() : dashboardProcessBean.getAllPortalProcesses();
  }

  private boolean isProcessMatchedCategory(DashboardProcess process, List<String> categories) {
    
    boolean hasNoCategory = categories.indexOf(CategoryUtils.NO_CATEGORY) > -1;
    return categories.indexOf(process.getCategory()) > -1
        || (StringUtils.isBlank(process.getCategory()) && hasNoCategory);
  }

  protected List<Dashboard> defaultDashboards() throws IOException {
    String dashboardJson = Ivy.var().get(JsonVariable.DASHBOARD.key);
    List<Dashboard> dashboards = mappingDashboards(dashboardJson);
    for (int i = 0; i < dashboards.size(); i++) {
      boolean canRead = false;
      List<String> permissions = dashboards.get(i).getPermissions();
      if (permissions == null) {
        canRead = true;
      } else {
        for (String permission : permissions) {
          canRead = isSessionUserHasPermisson(permission);
          if (canRead) {
            break;
          }
        }
      }
      if (!canRead) {
        // Remove dashboard which user doesn't have permission to see
        dashboards.remove(i);
      }
    }
    
    // If user does not have permission to read any dashboard, load default dashboard stored in CMS
    if (CollectionUtils.isEmpty(dashboards)) {
      Dashboard defaultDashboard = mapper.readValue(Ivy.cms().co(DEFAULT_DASHBOARD_JSON_URI), Dashboard.class);
      dashboards.add(Optional.ofNullable(defaultDashboard).orElse(new Dashboard()));
    }
    return dashboards;
  }

  private boolean isSessionUserHasPermisson(String permission) {
    return StringUtils.startsWith(permission, "#") ? StringUtils.equals(currentUser().getMemberName(), permission)
        : PermissionUtils.doesSessionUserHaveRole(permission);
  }

  public List<Dashboard> getDashboards() {
    return dashboards;
  }

  public void save() throws JsonParseException, JsonMappingException, IOException {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String nodes = Optional.ofNullable(requestParamMap.get("nodes")).orElse(StringUtils.EMPTY);
    List<WidgetLayout> layouts = Arrays.asList(mapper.readValue(nodes, WidgetLayout[].class));
    for (WidgetLayout layout : layouts) {
      DashboardWidget updatedWidget = getSelectedDashboard().getWidgets().stream()
          .filter(w -> w.getId().contentEquals(layout.getId()))
          .findFirst().get();

      WidgetLayout updatedLayout = new WidgetLayout();
      updatedLayout.setAxisX(layout.getAxisX());
      updatedLayout.setAxisY(layout.getAxisY());
      updatedLayout.setWidth(layout.getWidth());
      updatedLayout.setHeight(layout.getHeight());

      updatedWidget.setLayout(updatedLayout);
    }
    saveOrUpdateDashboardToUserProperty(getSelectedDashboard());
  }

  public void saveSelectedWidget() throws JsonProcessingException {
    this.dashboards.set(this.dashboards.indexOf(this.getSelectedDashboard()), this.getSelectedDashboard());
    String dashboardInUserProperty = readDashboardBySessionUser();
    if (StringUtils.isNotEmpty(dashboardInUserProperty)) {
      saveOrUpdateDashboardToUserProperty(this.getSelectedDashboard());
    } else {
      for (Dashboard dashboard : this.dashboards) {
        saveOrUpdateDashboardToUserProperty(dashboard);
      }
    }
  }

  protected void saveOrUpdateDashboardToUserProperty(Dashboard dashboardWidget) throws JsonProcessingException {
    List<Dashboard> dashboardSavedList = new ArrayList<>();
    String dashboardSaved = readDashboardBySessionUser();
    if (StringUtils.isNotEmpty(dashboardSaved)) {
      dashboardSavedList = mappingDashboards(dashboardSaved);
      int indexOfWidget = dashboardSavedList.indexOf(dashboardWidget);
      if (indexOfWidget >= 0) {
        dashboardSavedList.set(indexOfWidget, dashboardWidget);
      }
    } else {
      dashboardSavedList.add(dashboardWidget);
    }

    currentUser().setProperty(JsonVariable.DASHBOARD.key, this.mapper.writeValueAsString(dashboardSavedList));
  }

  private Map<String, String> getRequestParameterMap() {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
  }

  public void navigateToSelectedTaskDetails(SelectEvent event) {
    Long taskId = ((ITask) event.getObject()).getId();
    PortalNavigator.navigateToPortalTaskDetails(taskId);
  }

  public void navigateToSelectedCaseDetails(SelectEvent event) {
    Long caseId = ((ICase) event.getObject()).getId();
    PortalNavigator.navigateToPortalCaseDetails(caseId);
  }

  private IUser currentUser() {
    return Ivy.session().getSessionUser();
  }

  public void onDashboardChange(int index) {
    currentDashboardIndex = index;
    selectedDashboard = dashboards.get(index);
  }

  public void  onDashboardChangeByDropdown() {
    if (selectedDashboardId != null) {
      currentDashboardIndex = dashboards.indexOf(dashboards.stream()
          .filter(dashboard -> dashboard.getId().contentEquals(selectedDashboardId)).findFirst().orElse(null));
      selectedDashboard = dashboards.get(currentDashboardIndex);
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

  public boolean getCanEdit() {
    return canEdit;
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

  public void setReadOnlyMode(boolean isReadOnlyMode) {
    this.isReadOnlyMode = isReadOnlyMode;
  }

  public String getSelectedDashboardId() {
    return selectedDashboardId;
  }

  public void setSelectedDashboardId(String selectedDashboardId) {
    this.selectedDashboardId = selectedDashboardId;
  }

  public void navigatetoConfigurationPage() {
    PortalNavigator.navigateToNewDashboardConfiguration();
  }

  private void updateTypeForCustomColumn(ColumnModel columnModel) {
    if (columnModel.getFormat() != null) {
      columnModel.setType(DashboardColumnType.CUSTOM);
    } else {
      columnModel.setType(DashboardColumnType.STANDARD);
    }
  }
}
