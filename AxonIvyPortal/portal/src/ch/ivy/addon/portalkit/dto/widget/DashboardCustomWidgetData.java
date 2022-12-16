package ch.ivy.addon.portalkit.dto.widget;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidgetParam;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessStartDTO;
import ch.ivyteam.ivy.workflow.start.StartParameter;

public class DashboardCustomWidgetData implements Serializable {

  private static final long serialVersionUID = -5961158791241330155L;

  private String url;

  private String processPath;

  private List<CustomDashboardWidgetParam> params;

  @JsonIgnore
  private DashboardCustomWidgetType type;
  @JsonIgnore
  private IvyProcessStartDTO ivyProcessStartDTO;

  @JsonIgnore
  private List<StartParameter> startProcessParams;

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

  /**
   * @deprecated use {@link #setProcessPath()} instead
   * The processStart is replaced by processPath
   */
  @Deprecated
  @JsonProperty("processStart")
  public void setProcessStart(String processStart) {
    if (StringUtils.isBlank(processPath)) {
      this.processPath = processStart;
    }
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

  public List<StartParameter> getStartProcessParams() {
    return startProcessParams;
  }

  public void setStartProcessParams(List<StartParameter> startProcessParams) {
    this.startProcessParams = startProcessParams;
  }

  public boolean isHasParamChanged() {
    return hasParamChanged;
  }

  public void setHasParamChanged(boolean hasParamChanged) {
    this.hasParamChanged = hasParamChanged;
  }

  public IvyProcessStartDTO getIvyProcessStartDTO() {
    return ivyProcessStartDTO;
  }

  public void setIvyProcessStartDTO(IvyProcessStartDTO ivyProcessStartDTO) {
    this.ivyProcessStartDTO = ivyProcessStartDTO;
  }
}
