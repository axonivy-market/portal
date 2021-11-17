package ch.ivy.addon.portalkit.dto.widget;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidgetParam;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import ch.ivyteam.ivy.workflow.start.StartParameter;

public class CustomWidgetData implements Serializable {

  private static final long serialVersionUID = 8763058243562205725L;

  private String url;

  private String processStart;

  private List<CustomDashboardWidgetParam> params;

  @JsonIgnore
  private DashboardCustomWidgetType type;
  @JsonIgnore
  private IWebStartable startableProcessStart;

  @JsonIgnore
  private List<StartParameter> startProcessParams;
  
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getProcessStart() {
    return processStart;
  }

  public void setProcessStart(String processStart) {
    this.processStart = processStart;
  }

  public DashboardCustomWidgetType getType() {
    return type;
  }

  public void setType(DashboardCustomWidgetType type) {
    this.type = type;
  }

  public IWebStartable getStartableProcessStart() {
    return startableProcessStart;
  }

  public void setStartableProcessStart(IWebStartable startableProcessStart) {
    this.startableProcessStart = startableProcessStart;
  }

  public List<CustomDashboardWidgetParam> getParams() {
    return params;
  }

  public void setParams(List<CustomDashboardWidgetParam> params) {
    this.params = params;
  }

  public List<StartParameter> getStartProcessParams() {
    return startProcessParams;
  }

  public void setStartProcessParams(List<StartParameter> startProcessParams) {
    this.startProcessParams = startProcessParams;
  }
}
