package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;
  public static final String DASHBOARD_PREFIX = "dashboard.widgets";
  public static final String DASHBOARD_VARIABLE = "Portal.Dashboard";
  protected List<Dashboard> dashboards;
  protected Dashboard selectedDashboard;
  protected ObjectMapper mapper;
  protected DashboardWidget widget;
  protected boolean isReadOnlyMode;
  private int currentDashboardIndex;
  private boolean canEdit;
  protected String dashboardPropertyPrefix;

  @PostConstruct
  public void init() {
    dashboardPropertyPrefix = DASHBOARD_PREFIX;
    canEdit = PermissionUtils.hasDashboardWritePermission();
    currentDashboardIndex = 0;
    isReadOnlyMode = true;
    dashboards = new ArrayList<>();
    mapper = new ObjectMapper();
    mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

    String dashboardInUserProperty = readDashboardBySessionUser(DASHBOARD_PREFIX);
    try {
      dashboards = defaultDashboards();
      if (StringUtils.isNoneEmpty(dashboardInUserProperty)) {
        List<Dashboard> dashboardSavedList = mappingDashboards(dashboardInUserProperty);
        for (Dashboard d : dashboardSavedList) {
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

  private ArrayList<Dashboard> mappingDashboards(String dashboardJSON) throws JsonProcessingException, JsonMappingException {
    return new ArrayList<>(Arrays.asList(mapper.readValue(dashboardJSON, Dashboard[].class)));
  }

  protected String readDashboardBySessionUser(String dashboardPrefix) {
    return currentUser().getProperty(dashboardPrefix);
  }

  protected void removeDashboardInUserProperty(String dashboardProperty) {
    currentUser().removeProperty(dashboardProperty);
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
          if (StringUtils.isBlank(widget.getName())) {
            widget.setName(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourTasks"));
          }
          break;
        case CASE:
          CaseDashboardWidget.buildColumns((CaseDashboardWidget) widget);
          if (StringUtils.isBlank(widget.getName())) {
            widget.setName(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCases"));
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

  protected List<Dashboard> defaultDashboards() throws IOException {
    String dashboardJson = Ivy.var().get(DASHBOARD_VARIABLE);
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
        dashboards.remove(i);
      }
    }
    return dashboards;
  }

  private boolean isSessionUserHasPermisson(String permission) {
    return StringUtils.startsWith(permission, "#") ? StringUtils.equals(currentUser().getMemberName(), permission) : PermissionUtils.doesSessionUserHaveRole(permission);
  }

  public List<Dashboard> getDashboards() {
    return dashboards;
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
    saveOrUpdateDashboardToUserProperty(selectedDashboard);
  }

  public void saveSelectedWidget() throws JsonProcessingException {
    this.dashboards.set(this.dashboards.indexOf(this.selectedDashboard), this.selectedDashboard);
    String dashboardInUserProperty = readDashboardBySessionUser(this.dashboardPropertyPrefix);
    if (StringUtils.isNotEmpty(dashboardInUserProperty)) {
      saveOrUpdateDashboardToUserProperty(this.selectedDashboard);
    } else {
      for (Dashboard dashboard : this.dashboards) {
        saveOrUpdateDashboardToUserProperty(dashboard);
      }
    }
  }

  protected void saveOrUpdateDashboardToUserProperty(Dashboard dashboardWidget) throws JsonProcessingException {
    List<Dashboard> dashboardSavedList = new ArrayList<>();
    String dashboardSaved = readDashboardBySessionUser(DASHBOARD_PREFIX);
    if (StringUtils.isNotEmpty(dashboardSaved)) {
      dashboardSavedList = mappingDashboards(dashboardSaved);
      int indexOfWidget = dashboardSavedList.indexOf(dashboardWidget);
      if (indexOfWidget >= 0) {
        dashboardSavedList.set(indexOfWidget, dashboardWidget);
      }
    } else {
      dashboardSavedList.add(dashboardWidget);
    }

    currentUser().setProperty(DASHBOARD_PREFIX, this.mapper.writeValueAsString(dashboardSavedList));
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

  protected String dashboardProperty(Dashboard dashboard) {
    return dashboardPropertyPrefix + "." + dashboard.getId();
  }

  public void onDashboardChange(int index) {
    currentDashboardIndex = index;
    selectedDashboard = dashboards.get(index);
  }

  public void startTask(ITask task) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(task.getStartLinkEmbedded().getRelative());
  }

  public String createExtractedTextFromHtml(String text) {
    return HtmlParser.extractTextFromHtml(text);
  }

  public int getCurrentTabIndex() {
    return dashboards.indexOf(selectedDashboard);
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
}
