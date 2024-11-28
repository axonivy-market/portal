package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.List;

import com.axonivy.portal.components.service.impl.ProcessService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessViewerDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = -6515048307703164743L;
  private String processPath;
  @JsonIgnore
  private DashboardProcess process;
  private boolean showFullscreenMode;

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.PROCESS_VIEWER;
  }

  @JsonIgnore
  public void buildProcessDataFirstTime() {
    List<IWebStartable> allPortalProcesses = ProcessService.getInstance().findProcesses();
    setProcess(allPortalProcesses.stream().filter(proccess -> proccess.getId().contains(getProcessPath()))
        .map(DashboardProcess::new).findFirst().orElse(null));
  }

  @JsonIgnore
  public static ProcessViewerDashboardWidget buildDefaultWidget(String id, String name) {
    ProcessViewerDashboardWidget result = new ProcessViewerDashboardWidget();
    result.setId(id);
    result.setName(name);
    result.setLayout(new WidgetLayout());
    result.getLayout().setWidth(12);
    result.getLayout().setHeight(6);
    result.getLayout().setAxisX(0);
    result.getLayout().setAxisY(0);
    result.setShowFullscreenMode(true);
    return result;
  }

  @Override
  public void resetWidgetFilters() {}

  public String getProcessPath() {
    return processPath;
  }

  public void setProcessPath(String processPath) {
    this.processPath = processPath;
  }

  public DashboardProcess getProcess() {
    return process;
  }

  public void setProcess(DashboardProcess process) {
    this.process = process;
    setProcessPath(process != null ? process.getId() : "");
  }

  @JsonIgnore
  public String getProcessLink() {
    return process != null ? process.getStartLink() : "";
  }

  @Override
  public void cancelUserFilter() {}

  public void setShowFullscreenMode(boolean showFullscreenMode) {
    this.showFullscreenMode = showFullscreenMode;
  }

  public boolean isShowFullscreenMode() {
    return showFullscreenMode;
  }

}
