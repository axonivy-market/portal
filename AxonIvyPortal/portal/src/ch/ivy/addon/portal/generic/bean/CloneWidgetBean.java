package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.util.DashboardCloneUtils;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.util.DashboardUtils;

@ManagedBean
@ViewScoped
public class CloneWidgetBean implements Serializable {

  private static final long serialVersionUID = -973028688683038591L;

  private DashboardWidget widget;
  private List<Dashboard> availableDashboards;
  private Dashboard targetDashboard;

  @PostConstruct
  public void initConfigration() {
    targetDashboard = null;
  }

  public void cloneWidget() {
    if (targetDashboard == null) {
      return;
    }

    if (targetDashboard.getWidgets() == null) {
      targetDashboard.setWidgets(new ArrayList<>());
    }

    targetDashboard.getWidgets().add(DashboardCloneUtils.cloneWidget(widget));
    DashboardService.getInstance().save(targetDashboard);
    targetDashboard = null;
  }

  public List<Dashboard> getAvailableDashboards() {
    if (CollectionUtils.isEmpty(availableDashboards)) {
      availableDashboards = DashboardUtils
          .getAllVisibleDashboardsOfSessionUser();
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

  public DashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(DashboardWidget widget) {
    this.widget = widget;
  }
}
