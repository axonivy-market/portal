package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.FullProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;

@ManagedBean
@ViewScoped
public class FullDashboardProcessBean implements DashboardProcessBeanHandler<FullProcessDashboardWidget>, Serializable {

  private static final long serialVersionUID = 1L;
  private DashboardProcessBean dashboardProcessBean;

  @PostConstruct
  public void init() {
    dashboardProcessBean = ManagedBeans.get("dashboardProcessBean");
  }

  public List<DashboardProcess> completeProcesses(String query) {
    return dashboardProcessBean.getPortalDashboardProcesses().stream()
        .filter(process -> StringUtils.containsIgnoreCase(process.getName(), query)).collect(Collectors.toList());
  }

  @Override
  public void preRender(FullProcessDashboardWidget widget) {
    dashboardProcessBean.preRender(widget);
  }

  @Override
  public void onChangeDisplayMode() {
    FullProcessDashboardWidget newWidget = new FullProcessDashboardWidget(dashboardProcessBean.getWidget());
    dashboardProcessBean.updateWidget(newWidget);
  }

  @Override
  public void preview() {
    dashboardProcessBean.preview();
  }
}
