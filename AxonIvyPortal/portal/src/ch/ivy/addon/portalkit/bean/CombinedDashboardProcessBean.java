package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CombinedProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;

@ManagedBean
@ViewScoped
public class CombinedDashboardProcessBean
    implements DashboardProcessBeanHandler<CombinedProcessDashboardWidget>, Serializable {

  private static final long serialVersionUID = 1L;
  private List<DashboardProcess> portalCombinedProcesses;
  private DashboardProcessBean dashboardProcessBean;
  private List<IProcessStart> startableProcessStarts;

  @PostConstruct
  public void init() {
    dashboardProcessBean = ManagedBeans.get("dashboardProcessBean");
    portalCombinedProcesses = new ArrayList<>();
  }

  public CombinedProcessDashboardWidget getWidget() {
    return (CombinedProcessDashboardWidget) dashboardProcessBean.getWidget();
  }

  public List<DashboardProcess> completeCombinedProcesses(String query) {
    return getPortalCombinedProcesses().stream()
        .filter(process -> StringUtils.containsIgnoreCase(process.getName(), query))
        .collect(Collectors.toList());
  }

  private List<DashboardProcess> getPortalCombinedProcesses() {
      portalCombinedProcesses = new ArrayList<>(
          dashboardProcessBean.getPortalDashboardProcesses().stream()
              .filter(process -> process.getType() != ProcessType.EXTERNAL_LINK)
              .filter(process -> !dashboardProcessBean.isCaseMap(process))
              .collect(Collectors.toList()));
    return portalCombinedProcesses;
  }

  @Override
  public void preRender(CombinedProcessDashboardWidget widget) {
    dashboardProcessBean.preRender(widget);
    widget.setShowCases(false);
  }

  @Override
  public void onChangeDisplayMode() {
    CombinedProcessDashboardWidget newWidget = new CombinedProcessDashboardWidget(dashboardProcessBean.getWidget());
    if (dashboardProcessBean.isCaseMap(newWidget.getProcess())) {
      newWidget.setProcess(null);
      newWidget.setProcessPath(null);
    }
    dashboardProcessBean.updateWidget(newWidget);
  }

  public void updateProcessStartId() {
    if (CollectionUtils.isEmpty(startableProcessStarts)) {
      initStartableProcessStarts();
    }

    if (getWidget() != null && getWidget().getProcess() != null
        && getWidget().getProcess().getType() != ProcessType.EXPRESS_PROCESS) {
      String startLink = getWidget().getProcess().getStartLink();
      IProcessStart processStart = startableProcessStarts.stream()
          .filter(process -> process.getLink().getRelative().equals(startLink)).findFirst().get();
      getWidget().getProcess().setProcessStartId(processStart.getId());
    }
  }

  private void initStartableProcessStarts() {
    startableProcessStarts = Ivy.session().getStartableProcessStarts();
  }

  @Override
  public void preview() {
    dashboardProcessBean.preview();
  }

  @Override
  public void onChangeApplications(List<String> applications) {
    dashboardProcessBean.setApplications(applications);
  }
}
