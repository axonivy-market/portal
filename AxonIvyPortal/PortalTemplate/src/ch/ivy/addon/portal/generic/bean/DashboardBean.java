package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.CreatedDateColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.DescriptionColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.ExpiryDateColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.IdColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.NameColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.PriorityColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.ResponsibleColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.StartColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.StateColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetSample;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.loader.ResourceLoader;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.property.ICustomProperties;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;
  private List<Dashboard> dashboards;
  private Dashboard selectedDashboard;
  private List<WidgetSample> samples;
  private ObjectMapper mapper;
  private DashboardWidget widget;
  private boolean isReadOnlyMode;
  private int currentDashboardIndex;
  private boolean canEdit;
  private String dashboardPropertyPrefix;
  private String switchEditModeText;
  private String switchViewModeText;

  @PostConstruct
  public void init() {
    dashboardPropertyPrefix = "dashboard.widgets." + Ivy.session().getSessionUserName();
    canEdit = PermissionUtils.hasDashboardWritePermission();
    currentDashboardIndex = 0;
    isReadOnlyMode = true;
    dashboards = new ArrayList<>();
    mapper = new ObjectMapper();
    mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    samples = List.of(taskSample(), caseSample(), statisticSample(), processSample());
    List<ICustomProperty> properties = customProperties().findAllStartingWith(dashboardPropertyPrefix);
    try {
      dashboards = defaultDashboards();
      if (CollectionUtils.isNotEmpty(properties)) {
        for (ICustomProperty property : properties) {
          Dashboard d = mapper.readValue(property.getValue(), Dashboard.class);
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
    switchEditModeText = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/switchToEditMode");
    switchViewModeText = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/switchToViewMode");
  }
  
  private void buildWidgetModels() {
    for (Dashboard dashboard : dashboards) {
      for (DashboardWidget widget : dashboard.getWidgets()) {
        if (widget instanceof TaskDashboardWidget) {
          TaskDashboardWidget taskWidget = (TaskDashboardWidget) widget;
          buildColumns(taskWidget);
        }
      }
    }
  }
  
  private void buildColumns(TaskDashboardWidget taskWidget) {
    List<ColumnModel> columns = taskWidget.getColumns();
    for (int i = 0; i < columns.size(); i++) {
      ColumnModel column = columns.get(i);
      String field = column.getField();
      if (DashboardStandardTaskColumn.START.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, StartColumnModel.class);
      } else if (DashboardStandardTaskColumn.PRIORITY.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, PriorityColumnModel.class);
      } else if (DashboardStandardTaskColumn.ID.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, IdColumnModel.class);
      } else if (DashboardStandardTaskColumn.NAME.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, NameColumnModel.class);
      } else if (DashboardStandardTaskColumn.DESCRIPTION.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, DescriptionColumnModel.class);
      } else if (DashboardStandardTaskColumn.RESPONSIBLE.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, ResponsibleColumnModel.class);
      } else if (DashboardStandardTaskColumn.STATE.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, StateColumnModel.class);
      } else if (DashboardStandardTaskColumn.CREATED.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, CreatedDateColumnModel.class);
      } else if (DashboardStandardTaskColumn.EXPIRY.getField().equalsIgnoreCase(field)) {
        column = mapper.convertValue(column, ExpiryDateColumnModel.class);
      }
      column.initDefaultValue();
      columns.set(i, column);
    }
  }
  
  private List<Dashboard> defaultDashboards() throws IOException {
    ILibrary portalStyleLib = Ivy.wf().getApplication().findReleasedLibrary(PortalLibrary.PORTAL_STYLE.getValue());
    ResourceLoader loader = new ResourceLoader(portalStyleLib.getProcessModelVersion());
    Optional<Path> path = loader.getWidgetConfiguration();
    String read = String.join("\n", Files.readAllLines(path.get()));
    List<Dashboard> dashboards = new ArrayList<>(Arrays.asList(mapper.readValue(read, Dashboard[].class)));
    for (int i = 0; i < dashboards.size(); i++) {
      boolean canRead = false;
      List<String> permissions = dashboards.get(i).getPermissions();
      if (permissions == null) {
        canRead = true;
      } else {
        for (String permission : permissions) {
          canRead = StringUtils.startsWith(permission, "#") ? StringUtils.equals(Ivy.session().getSessionUser().getMemberName(), permission) : PermissionUtils.doesSessionUserHaveRole(permission);
          if (canRead) {
            break;
          }
        }
      }
      if (!canRead) {
        dashboards.remove(i);
      }
    }
    return dashboards;
  }
  
  public List<Dashboard> getDashboards() {
    return dashboards;
  }

  public List<WidgetSample> getSamples() {
    return samples;
  }

  public TaskDashboardWidget getDefaultTaskDashboardWidget() throws IOException {
    TaskDashboardWidget result = new TaskDashboardWidget();
    result.setId(getNewTaskWidgetId());
    result.setName(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourTasks"));
    result.setWidth(10);
    result.setHeight(6);
    result.setAutoPosition(true);
    result.setSortField(TaskSortField.ID.toString());
    result.setSortDescending(true);
    initStandardColumns(result);
    buildColumns(result);
    return result;
  }
  
  private void initStandardColumns(TaskDashboardWidget widget) {
    List<ColumnModel> columnModels = new ArrayList<>();
    for (DashboardStandardTaskColumn col : DashboardStandardTaskColumn.values()) {
      ColumnModel columnModel = new ColumnModel();
      columnModel.setField(col.getField());
      columnModels.add(columnModel);
    }
    widget.setColumns(columnModels);
  }
  
  public void save() throws JsonParseException, JsonMappingException, IOException {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String nodes = Optional.ofNullable(requestParamMap.get("nodes")).orElse(StringUtils.EMPTY);
    List<DashboardWidget> widgets = Arrays.asList(mapper.readValue(nodes, DashboardWidget[].class));
    for (DashboardWidget widget : widgets) {
      DashboardWidget updatedWidget = selectedDashboard.getWidgets().get(selectedDashboard.getWidgets().indexOf(widget));
      updatedWidget.setAxisX(widget.getAxisX());
      updatedWidget.setAxisY(widget.getAxisY());
      updatedWidget.setWidth(widget.getWidth());
      updatedWidget.setHeight(widget.getHeight());
    }
    customProperties().property(dashboardProperty(selectedDashboard)).setValue(mapper.writeValueAsString(selectedDashboard));
  }

  public void saveWidget() throws JsonProcessingException {
    List<DashboardWidget> widgets = selectedDashboard.getWidgets();
    resetUserFilters();
    if (widgets.contains(widget)) {
      widgets.set(widgets.indexOf(widget), widget);
    } else {
      widgets.add(widget);
    }
    dashboards.set(dashboards.indexOf(selectedDashboard), selectedDashboard);
    List<ICustomProperty> properties = customProperties().findAllStartingWith(dashboardPropertyPrefix);
    if (CollectionUtils.isNotEmpty(properties)) {
      customProperties().property(dashboardProperty(selectedDashboard)).setValue(mapper.writeValueAsString(selectedDashboard));
    } else {
      for (Dashboard dashboard : dashboards) {
        customProperties().property(dashboardProperty(dashboard)).setValue(mapper.writeValueAsString(dashboard));
      }
    }
  }
  
  private void resetUserFilters() {
    if (widget instanceof TaskDashboardWidget) {
      TaskDashboardWidget taskWidget = ((TaskDashboardWidget) widget);
      taskWidget.setInConfiguration(false);
      for (ColumnModel column : taskWidget.getColumns()) {
        column.setUserFilter(StringUtils.EMPTY);
        column.setUserFilterList(new ArrayList<>());
        column.setUserFilterFrom(StringUtils.EMPTY);
        column.setUserFilterTo(StringUtils.EMPTY);
      }
    }
  }
  
  private String dashboardProperty(Dashboard dashboard) {
    return dashboardPropertyPrefix + "." + dashboard.getId();
  }
  
  public void create() throws IOException {
    widget = getDefaultTaskDashboardWidget();
  }
  
  public void restore() throws IOException {
    List<ICustomProperty> properties = customProperties().findAllStartingWith(dashboardProperty(selectedDashboard));
    for (ICustomProperty property : properties) {
      customProperties().delete(property.getName());
    }
    List<Dashboard> defaultDashboards = defaultDashboards();
    selectedDashboard = defaultDashboards.get(defaultDashboards.indexOf(selectedDashboard));
    dashboards.set(dashboards.indexOf(selectedDashboard), selectedDashboard);
    for (DashboardWidget widget : selectedDashboard.getWidgets()) {
      if (widget instanceof TaskDashboardWidget) {
        TaskDashboardWidget taskWidget = (TaskDashboardWidget) widget;
        buildColumns(taskWidget);
      }
    }
  }
  
  public void onDashboardChange(int index) {
    currentDashboardIndex = index;
    selectedDashboard = dashboards.get(index);
  }
  
  public String getNewTaskWidgetId() {
    final String taskIdPrefix = "task_";
    String result = "";
    List<String> ids = selectedDashboard.getWidgets().stream()
        .filter(widget -> widget.getId().startsWith(taskIdPrefix))
        .map(DashboardWidget::getId).collect(Collectors.toList());

    if (CollectionUtils.isEmpty(ids)) {
      result = taskIdPrefix + "0";
    } else {
      Integer maxId = Collections.max(ids.stream().map(id -> Integer.parseInt(id.replace(taskIdPrefix, ""))).collect(Collectors.toList()));
      if (maxId == null || maxId < 0) {
        result = taskIdPrefix + "0";
      } else {
        result = taskIdPrefix.concat(Integer.toString(maxId + 1));
      }
    }
    return result;
  }

  public boolean hasPredefinedFilter(TaskDashboardWidget taskWidget) throws ParseException {
    if (CollectionUtils.isEmpty(taskWidget.getFilterableColumns())) {
      return false;
    }
    for (ColumnModel col : taskWidget.getFilterableColumns()) {
      if (col instanceof PriorityColumnModel && !CollectionUtils.isEmpty(((PriorityColumnModel) col).getPriorities())) {
        return true;
      }
      if (col instanceof StateColumnModel && !CollectionUtils.isEmpty(((StateColumnModel) col).getStates())) {
        return true;
      }
      if (col instanceof ResponsibleColumnModel && !CollectionUtils.isEmpty(((ResponsibleColumnModel) col).getResponsibles())) {
        return true;
      }
      if ((col.getFormat() == DashboardColumnFormat.TEXT || col.getFormat() == DashboardColumnFormat.STRING) && !(CollectionUtils.isEmpty(col.getFilterList()) && StringUtils.isBlank(col.getFilter()))) {
        return true;
      }
      if (col.getFormat() == DashboardColumnFormat.NUMBER && !(StringUtils.isBlank(col.getFilterFrom()) && StringUtils.isBlank(col.getFilterTo()))) {
        return true;
      }
      if (col.getFormat() == DashboardColumnFormat.TIMESTAMP && !(col.getDateFilterFrom() == null && col.getDateFilterTo() == null)) {
        return true;
      }
    }
    return false;
  }

  public void toggleMode() {
    isReadOnlyMode = !isReadOnlyMode;
  }

  public String getTaskStateDisplayName(String state) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/".concat(state));
  }

  public void startTask(ITask task) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(task.getStartLinkEmbedded().getRelative());
  }

  public int getCurrentTabIndex() {
    return dashboards.indexOf(selectedDashboard);
  }

  private Map<String, String> getRequestParameterMap() {
    Map<String, String> requestParamMap =
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    return requestParamMap;
  }
  
  private WidgetSample taskSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/taskList"), DashboardWidgetType.TASK, "task-widget-prototype.png");
  }
  
  private WidgetSample caseSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/caseList"), DashboardWidgetType.CASE, "case-widget-prototype.png");
  }
  
  private WidgetSample statisticSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/statisticWidget"), DashboardWidgetType.STATISTIC, "statistic-widget-prototype.png");
  }
  
  private WidgetSample processSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/processList"), DashboardWidgetType.PROCESS, "process-widget-prototype.png");
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
  
  public String getSwitchButtonText() {
    return isReadOnlyMode ? switchEditModeText : switchViewModeText;
  }
  
  private String translate(String cms) {
    return Ivy.cms().co(cms);
  }
  
  private ICustomProperties customProperties() {
    return Ivy.wf().getApplication().customProperties();
  }

  public Dashboard getSelectedDashboard() {
    return selectedDashboard;
  }
}
