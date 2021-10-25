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

  @JsonIgnore
  private List<StartParameter> params;

  @JsonIgnore
  private DashboardCustomWidgetType type;
  @JsonIgnore
  private IWebStartable startableProcessStart;
  
  private List<CustomDashboardWidgetParam> customParams;
  
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

  public List<CustomDashboardWidgetParam> getCustomParams() {
    return customParams;
  }

  public void setCustomParams(List<CustomDashboardWidgetParam> customParams) {
    this.customParams = customParams;
  }

  public List<StartParameter> getParams() {
    return params;
  }

  public void setParams(List<StartParameter> params) {
    this.params = params;
  }
}
