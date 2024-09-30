package ch.ivy.addon.portalkit.dto.widget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidgetParam;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import ch.ivyteam.ivy.workflow.start.StartParameter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardCustomWidgetData implements Serializable {

  private static final long serialVersionUID = -5961158791241330155L;

  private String url;
  private String processPath;
  @JsonIgnore
  private String startRequestPath;
  private List<CustomDashboardWidgetParam> params;
  @JsonIgnore
  private DashboardCustomWidgetType type;
  @JsonIgnore
  private IWebStartable startableProcessStart;
  @JsonIgnore
  private boolean hasParamChanged;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getProcessPath() {
    return processPath;
  }

  public void setProcessPath(String processPath) {
    this.processPath = processPath;
  }

  public String getStartRequestPath() {
    return startRequestPath;
  }

  public void setStartRequestPath(String startRequestPath) {
    this.startRequestPath = startRequestPath;
  }

  public DashboardCustomWidgetType getType() {
    return type;
  }

  public void setType(DashboardCustomWidgetType type) {
    this.type = type;
  }

  public List<CustomDashboardWidgetParam> getParams() {
    return params;
  }

  public void setParams(List<CustomDashboardWidgetParam> params) {
    this.params = params;
  }

  @JsonIgnore
  public List<StartParameter> getStartProcessParams() {
    return Optional.ofNullable(startableProcessStart)
        .map(IWebStartable::parameters).orElse(new ArrayList<>());
  }

  public boolean isHasParamChanged() {
    return hasParamChanged;
  }

  public void setHasParamChanged(boolean hasParamChanged) {
    this.hasParamChanged = hasParamChanged;
  }

  public IWebStartable getStartableProcessStart() {
    return startableProcessStart;
  }

  public void setStartableProcessStart(IWebStartable startableProcessStart) {
    this.startableProcessStart = startableProcessStart;
  }
}
