package ch.ivy.addon.portalkit.dto.dashboard;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.impl.ProcessService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessViewerDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = -6515048307703164743L;
  private String processPath;
  @JsonIgnore
  private DashboardProcess process;

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.PROCESS_VIEWER;
  }

  @JsonIgnore
  public void buildProcessDataFirstTime() {
    if (StringUtils.isNotBlank(getProcessPath())) {
      IWebStartable startable = ProcessService.getInstance().findWebStartableInSecurityContextById(getProcessPath());
      setProcess(startable != null ? new DashboardProcess(startable) : null);
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

  public String getProcessPath() {
    return processPath;
  }

  public void setProcessPath(String processPath) {
    this.processPath = processPath;
  }

  /**
   * @param processStart 
   * @deprecated use {@link #setProcessPath(String)} instead
   * The processStart is replaced by processPath
   */
  @Deprecated(forRemoval = true, since = "10.0.3")
  @JsonProperty("processStart")
  public void setProcessStart(String processStart) {
    if (StringUtils.isBlank(processPath)) {
      this.processPath = processStart;
    }
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

}
