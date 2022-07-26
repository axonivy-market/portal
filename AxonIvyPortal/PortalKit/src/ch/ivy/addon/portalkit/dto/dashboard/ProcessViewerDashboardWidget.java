package ch.ivy.addon.portalkit.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessViewerDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = -6515048307703164743L;
  private String processStart;
  @JsonIgnore
  private IWebStartable startableProcessStart;

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.PROCESS_VIEWER;
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

  public IWebStartable getStartableProcessStart() {
    return startableProcessStart;
  }

  public void setStartableProcessStart(IWebStartable startableProcessStart) {
    this.startableProcessStart = startableProcessStart;
  }

}
