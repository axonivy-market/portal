package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
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
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.dto.Dashboard;
import ch.ivy.addon.portalkit.dto.DashboardWidget;
import ch.ivy.addon.portalkit.dto.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.WidgetSample;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.enums.TaskDashboardWidgetType;
import ch.ivy.addon.portalkit.loader.ResourceLoader;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;
  private List<Dashboard> dashboards;
  private Dashboard selectedDashboard;
  private List<WidgetSample> samples;
  private String user;
  private ObjectMapper mapper;
  private DashboardWidget widget;
  private boolean isReadOnlyMode;
  private int tabActiveIndex;
  private boolean canEdit;

  @PostConstruct
  public void init() {
    canEdit = PermissionUtils.hasDashboardWritePermission();
    tabActiveIndex = 0;
    isReadOnlyMode = true;
    dashboards = new ArrayList<>();
    mapper = new ObjectMapper();
    samples = List.of(taskSample(), caseSample(), statisticSample(), processSample());
    user = Ivy.session().getSessionUserName();
    List<ICustomProperty> properties = Ivy.wf().getApplication().customProperties().findAllStartingWith("dashboard.widgets." + user);
    try {
      dashboards = defaultDashboards();
      if (CollectionUtils.isNotEmpty(properties)) {
        for (ICustomProperty property : properties) {
          Dashboard d = mapper.readValue(property.getValue(), Dashboard.class);
          dashboards.set(dashboards.indexOf(d), d);
        }
      }
      selectedDashboard = dashboards.get(0);
    } catch (IOException e) {
    }
  }
  
  private List<Dashboard> defaultDashboards() throws IOException {
    ILibrary portalStyleLib = Ivy.wf().getApplication().findReleasedLibrary(PortalLibrary.PORTAL_STYLE.getValue());
    ResourceLoader loader = new ResourceLoader(portalStyleLib.getProcessModelVersion());
    Optional<Path> path = loader.getWidgetConfiguration();
    String read = String.join("\n", Files.readAllLines(path.get()));
    return new ArrayList<>(Arrays.asList(mapper.readValue(read, Dashboard[].class)));
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
    result.setName("Your Tasks");
    result.setWidth(8);
    result.setHeight(6);
    result.setAutoPosition(true);
    result.setTaskDashboardWidgetType(TaskDashboardWidgetType.CUSTOM);
    result.setPriorities(new ArrayList<>(List.of(WorkflowPriority.LOW, WorkflowPriority.NORMAL, WorkflowPriority.HIGH, WorkflowPriority.EXCEPTION)));
    result.setStates(new ArrayList<>(List.of(TaskState.CREATED, TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE, TaskState.UNASSIGNED)));
    return result;
  }
  
  public void save() throws JsonParseException, JsonMappingException, IOException {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String nodes = Optional.ofNullable(requestParamMap.get("nodes")).orElse(StringUtils.EMPTY);
    List<DashboardWidget> widgets = Arrays.asList(mapper.readValue(nodes, DashboardWidget[].class));
    for (DashboardWidget widget : widgets) {
      DashboardWidget updatedWidget = selectedDashboard.getWidgets().get(selectedDashboard.getWidgets().indexOf(widget));
      updatedWidget.setX(widget.getX());
      updatedWidget.setY(widget.getY());
      updatedWidget.setWidth(widget.getWidth());
      updatedWidget.setHeight(widget.getHeight());
    }
    Ivy.wf().getApplication().customProperties().property(dashboardProperty(selectedDashboard)).setValue(mapper.writeValueAsString(selectedDashboard));
  }
  
  public void saveWidget() throws JsonProcessingException {
    List<DashboardWidget> widgets = selectedDashboard.getWidgets();
    if (widgets.contains(widget)) {
      widgets.set(widgets.indexOf(widget), widget);
    } else {
      widgets.add(widget);
    }
    dashboards.set(dashboards.indexOf(selectedDashboard), selectedDashboard);
    List<ICustomProperty> properties = Ivy.wf().getApplication().customProperties().findAllStartingWith("dashboard.widgets." + user);
    if (CollectionUtils.isNotEmpty(properties)) {
      Ivy.wf().getApplication().customProperties().property(dashboardProperty(selectedDashboard)).setValue(mapper.writeValueAsString(selectedDashboard));
    } else {
      for (Dashboard dashboard : dashboards) {
        Ivy.wf().getApplication().customProperties().property(dashboardProperty(dashboard)).setValue(mapper.writeValueAsString(dashboard));
      }
    }
  }
  
  private String dashboardProperty(Dashboard dashboard) {
    return "dashboard.widgets." + user + "." + dashboard.getId();
  }
  
  public void create() throws IOException {
    widget = getDefaultTaskDashboardWidget();
  }
  
  public void restore() throws IOException {
    List<ICustomProperty> properties = Ivy.wf().getApplication().customProperties().findAllStartingWith(dashboardProperty(selectedDashboard));
    for (ICustomProperty property : properties) {
      Ivy.wf().getApplication().customProperties().delete(property.getName());
    }
    List<Dashboard> defaultDashboards = defaultDashboards();
    selectedDashboard = defaultDashboards.get(defaultDashboards.indexOf(selectedDashboard));
    dashboards.set(dashboards.indexOf(selectedDashboard), selectedDashboard);
  }
  
  public void onTabChange(TabChangeEvent event) {
    selectedDashboard = (Dashboard) event.getData();
    tabActiveIndex = ((TabView) event.getSource()).getIndex();
  }
  
  public String getNewTaskWidgetId() {
    String result = "";
    List<String> ids = selectedDashboard.getWidgets().stream()
        .filter(widget -> widget.getId().startsWith("task_"))
        .map(DashboardWidget::getId).collect(Collectors.toList());

    Integer maxId = Collections.max(ids.stream().map(id -> Integer.parseInt(id.replace("task_", ""))).collect(Collectors.toList()));
    if (maxId == null || maxId < 0) {
      result = "task_0";
    } else {
      result = "task_".concat(Integer.toString(maxId + 1));
    }
    return result;
  }
  
  public void toggleMode() {
    isReadOnlyMode = !isReadOnlyMode;
  }
  
  private Map<String, String> getRequestParameterMap() {
    Map<String, String> requestParamMap =
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    return requestParamMap;
  }
  
  private WidgetSample taskSample() {
    return new WidgetSample("Task List", DashboardWidgetType.TASK, "task-widget-prototype.png");
  }
  
  private WidgetSample caseSample() {
    return new WidgetSample("Case List", DashboardWidgetType.CASE, "case-widget-prototype.png");
  }
  
  private WidgetSample statisticSample() {
    return new WidgetSample("Statistic Widget", DashboardWidgetType.STATISTIC, "statistic-widget-prototype.png");
  }
  
  private WidgetSample processSample() {
    return new WidgetSample("Process List", DashboardWidgetType.PROCESS, "process-widget-prototype.png");
  }
  
  public DashboardWidget getTaskWidget() {
    return widget;
  }
  
  public void setTaskWidget(DashboardWidget widget) {
    this.widget = widget;
  }
  
  public boolean getIsReadOnlyMode() {
    return isReadOnlyMode;
  }
  
  public void setIsReadOnlyMode(boolean isReadOnlyMode) {
    this.isReadOnlyMode = isReadOnlyMode;
  }
  
  public int getTabActiveIndex() {
    return tabActiveIndex;
  }
  
  public void setTabActiveIndex(int tabActiveIndex) {
    this.tabActiveIndex = tabActiveIndex;
  }
  
  public boolean getCanEdit() {
    return canEdit;
  }
}
