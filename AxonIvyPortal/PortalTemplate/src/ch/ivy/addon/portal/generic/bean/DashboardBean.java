package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
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
      for (DashboardWidget widget : dashboard.getWidgets()) {
        if (widget instanceof TaskDashboardWidget) {
          TaskDashboardWidget.buildColumns((TaskDashboardWidget) widget);
        } else if (widget instanceof CaseDashboardWidget) {
          CaseDashboardWidget.buildColumns((CaseDashboardWidget) widget);
        }
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

  protected void saveOrUpdateDashboardToUserProperty(Dashboard dashboardWidget) throws JsonProcessingException {
    List<Dashboard> dashboardSavedList = new ArrayList<>();
    String dashboardSaved = readDashboardBySessionUser(DASHBOARD_PREFIX);
    if (StringUtils.isNotEmpty(dashboardSaved)) {
      dashboardSavedList = mappingDashboards(dashboardSaved);
      int indexOfWidget = dashboardSavedList.indexOf(dashboardWidget);
      dashboardSavedList.set(indexOfWidget, dashboardWidget);
    } else {
      dashboardSavedList.add(dashboardWidget);
    }

    currentUser().setProperty(DASHBOARD_PREFIX, this.mapper.writeValueAsString(dashboardSavedList));
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

  public boolean hasPredefinedFilter(DashboardWidget widget) throws ParseException {
    if (widget instanceof TaskDashboardWidget) {
      return TaskDashboardWidget.hasPredefinedFilter((TaskDashboardWidget) widget);
    } else if (widget instanceof CaseDashboardWidget) {
      return CaseDashboardWidget.hasPredefinedFilter((CaseDashboardWidget) widget);
    }

    return false;
  }

  public String getTaskStateDisplayName(String state) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/".concat(state));
  }

  public String getCaseStateDisplayName(String state) {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/".concat(state));
  }

  public void startTask(ITask task) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(task.getStartLinkEmbedded().getRelative());
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
