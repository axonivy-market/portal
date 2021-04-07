package ch.ivy.addon.portal.generic.bean;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetSample;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

@ViewScoped
@ManagedBean
public class DashboardConfigurationBean extends DashboardBean implements Serializable {

  private static final long serialVersionUID = -7803140491152435429L;
  private static final String WIDGET_ID_PATTERN = "%s_%s";
  protected List<WidgetSample> samples;
  private String newWidgetHeader;
  private DashboardWidget deleteWidget;

  @PostConstruct
  public void initConfigration() {
    super.init();
    this.isReadOnlyMode = false;
    samples = List.of(taskSample(), caseSample(), statisticSample(), processSample());
  }

  private WidgetSample taskSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/taskList"), DashboardWidgetType.TASK,
        "task-widget-prototype.png");
  }

  private WidgetSample caseSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/caseList"), DashboardWidgetType.CASE,
        "case-widget-prototype.png");
  }

  private WidgetSample statisticSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/statisticWidget"), DashboardWidgetType.STATISTIC,
        "statistic-widget-prototype.png");
  }

  private WidgetSample processSample() {
    return new WidgetSample(translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/processList"), DashboardWidgetType.PROCESS,
        "process-widget-prototype.png");
  }

  public void restore() throws IOException {
    removeDashboardInUserProperty(DASHBOARD_PREFIX);

    List<Dashboard> defaultDashboards = this.defaultDashboards();
    this.selectedDashboard = defaultDashboards.get(defaultDashboards.indexOf(this.selectedDashboard));
    this.dashboards.set(this.dashboards.indexOf(this.selectedDashboard), this.selectedDashboard);
    buildSubWidgetModels(this.selectedDashboard.getWidgets());
  }

  public void create(WidgetSample sample) {
    this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader", Arrays.asList(EMPTY));
    switch (sample.getType()) {
      case CASE:
        this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/common/case")));
        this.widget = getDefaultCaseDashboardWidget();
        break;
      case TASK:
        this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/newWidgetHeader",
            Arrays.asList(translate("/ch.ivy.addon.portalkit.ui.jsf/common/tasks")));
        this.widget = getDefaultTaskDashboardWidget();
        break;
      default:
        break;
    }
  }

  public void removeWidget() throws JsonProcessingException {
    if (this.deleteWidget != null) {
      this.selectedDashboard.getWidgets().remove(deleteWidget);
      saveOrUpdateDashboardToUserProperty(selectedDashboard);
    }
  }
  
  private CaseDashboardWidget getDefaultCaseDashboardWidget() {
    String widgetId = generateNewWidgetId(DashboardWidgetType.CASE);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourCases");
    return CaseDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  public TaskDashboardWidget getDefaultTaskDashboardWidget() {
    String widgetId = generateNewWidgetId(DashboardWidgetType.TASK);
    String widgetName = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/yourTasks");
    return TaskDashboardWidget.buildDefaultWidget(widgetId, widgetName);
  }

  public boolean isTaskWidget(DashboardWidget widget) {
    return widget instanceof TaskDashboardWidget;
  }

  public boolean isCaseWidget(DashboardWidget widget) {
    return widget instanceof CaseDashboardWidget;
  }

  public String generateNewWidgetId(DashboardWidgetType type) {
    final String widgetIdPrefix = String.format(WIDGET_ID_PATTERN, type.name(), EMPTY).toLowerCase();

    List<String> ids = selectedDashboard.getWidgets().stream()
            .filter(widget -> widget.getId().startsWith(widgetIdPrefix))
            .map(DashboardWidget::getId).collect(Collectors.toList());
    if (CollectionUtils.isNotEmpty(ids)) {
      Integer maxId = Collections.max(ids.stream()
                        .map(id -> Integer.parseInt(id.replace(widgetIdPrefix, EMPTY)))
                        .collect(Collectors.toList()));
      if (maxId != null && maxId >= 0) {
        String widgetId = Integer.toString(maxId + 1);
        return String.format(WIDGET_ID_PATTERN, type.name(), widgetId).toLowerCase();
      }
    }

    return String.format(WIDGET_ID_PATTERN, type.name(), 0).toLowerCase();
  }

  public void saveWidget() throws JsonProcessingException, ParseException {
    List<DashboardWidget> widgets = this.selectedDashboard.getWidgets();
    this.widget.resetUserFilters();
    this.widget.buildPredefinedFilterData();
    if (widgets.contains(this.widget)) {
      widgets.set(widgets.indexOf(this.widget), this.widget);
    } else {
      widgets.add(widget);
    }
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

  public void setEditWidget(DashboardWidget widget) {
    this.setWidget(widget);
    this.newWidgetHeader = translate("/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/editWidgetHeader",
        Arrays.asList(widget.getName()));
  }

  public List<WidgetSample> getSamples() {
    return samples;
  }

  public String getNewWidgetHeader() {
    return newWidgetHeader;
  }

  public DashboardWidget getDeleteWidget() {
    return deleteWidget;
  }

  public void setDeleteWidget(DashboardWidget deleteWidget) {
    this.deleteWidget = deleteWidget;
  }

}
