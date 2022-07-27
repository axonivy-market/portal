package ch.ivy.addon.portalkit.dto.dashboard;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessViewerDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = -6515048307703164743L;
  private String processStart;
  @JsonIgnore
  private DashboardProcess process;

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.PROCESS_VIEWER;
  }

  @JsonIgnore
  public void buildProcessDataFirstTime() {
    if (StringUtils.isNotBlank(getProcessStart())) {
      List<IWebStartable> allPortalProcesses = ProcessService.newInstance().findProcesses().getProcesses();
      setProcess(allPortalProcesses.stream().filter(proccess -> proccess.getId().contains(getProcessStart()))
          .map(DashboardProcess::new).findFirst().get());
    }
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
    return result;
  }

  @Override
  public void resetWidgetFilters() {}

  public String getProcessStart() {
    return processStart;
  }

  public void setProcessStart(String processStart) {
    this.processStart = processStart;
  }

  public DashboardProcess getProcess() {
    return process;
  }

  public void setProcess(DashboardProcess process) {
    this.process = process;
    setProcessStart(process != null ? process.getId() : "");
  }

  @JsonIgnore
  public String getProcessLink() {
    return process != null ? process.getStartLink() : "";
  }

}
