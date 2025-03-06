package ch.ivy.addon.portal.generic.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.service.StatisticService;
import com.axonivy.portal.util.DashboardCloneUtils;

import ch.ivy.addon.portalkit.dto.dashboard.StatisticDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.widget.DashboardCustomWidgetData;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class CloneWidgetBean extends DashboardDetailModificationBean {

  private static final long serialVersionUID = -973028688683038591L;

  private static final String CLONE_TO_DASHBOARD_DIALOG_HEADER_CMS = "/Dialogs/com/axonivy/portal/dashboard/component/CloneWidgetDialog/Header";

  private DashboardWidget cloneWidget;
  private List<Dashboard> availableDashboards;
  private Dashboard targetDashboard;
  private List<Statistic> statisticWidgets;

  @PostConstruct
  public void initConfigration() {
    targetDashboard = null;
    initStatisticWidgets();
  }

  public void cloneWidget() {
    if (targetDashboard == null) {
      return;
    }

    if (targetDashboard.getWidgets() == null) {
      targetDashboard.setWidgets(new ArrayList<>());
    }

    targetDashboard.getWidgets()
        .add(DashboardCloneUtils.cloneWidget(cloneWidget));
    DashboardService.getInstance().save(targetDashboard);
    targetDashboard = null;
  }

  public List<Dashboard> getAvailableDashboards() {
    if (CollectionUtils.isEmpty(availableDashboards)) {
      availableDashboards = initCloneableDashboards();
    }
    return availableDashboards;
  }

  public void setAvailableDashboards(List<Dashboard> targetDashboards) {
    this.availableDashboards = targetDashboards;
  }

  public Dashboard getTargetDashboard() {
    return targetDashboard;
  }

  public void setTargetDashboard(Dashboard targetDashboard) {
    this.targetDashboard = targetDashboard;
  }

  public DashboardWidget getCloneWidget() {
    return cloneWidget;
  }

  public void setCloneWidget(DashboardWidget cloneWidget) {
    this.cloneWidget = cloneWidget;
  }

  public String getCloneToDashboardDialogHeader() {
    if (cloneWidget == null) {
      return "";
    }

    String result = cloneWidget.getName();
    // Statistic widget need to load name from list of pre-built client
    // statistic
    if (cloneWidget.getType() == DashboardWidgetType.STATISTIC) {
      StatisticDashboardWidget statisticWidget = (StatisticDashboardWidget) cloneWidget;
      result = statisticWidgets.stream()
          .filter(chart -> chart.getId()
              .contentEquals(statisticWidget.getChartId()))
          .findFirst().map(Statistic::getName).orElseGet(() -> "");
    }

    // For custom widget, need to build before get name
    if (cloneWidget.getType() == DashboardWidgetType.CUSTOM
        && StringUtils.isBlank(result)) {
      cloneWidget = DashboardWidgetUtils.buildWidgetColumns(cloneWidget);
      CustomDashboardWidget customWidget = (CustomDashboardWidget) cloneWidget;
      result = Optional.ofNullable(customWidget)
          .map(CustomDashboardWidget::getData)
          .map(DashboardCustomWidgetData::getStartableProcessStart)
          .map(IWebStartable::getDisplayName).orElse("");
    }
    return Ivy.cms().co(CLONE_TO_DASHBOARD_DIALOG_HEADER_CMS,
        Arrays.asList(result));
  }

  protected void initStatisticWidgets() {
    statisticWidgets = new ArrayList<>();
    statisticWidgets.addAll(statisticWidgets);
    statisticWidgets
        .addAll(StatisticService.getInstance().findAllCharts());
  }
}
