package portalmigration.version112.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardCustomWidgetData implements Serializable {

  private static final long serialVersionUID = -5961158791241330155L;

  private String url;
  private String processPath;
  private List<CustomDashboardWidgetParam> params;


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

  public List<CustomDashboardWidgetParam> getParams() {
    return params;
  }

  public void setParams(List<CustomDashboardWidgetParam> params) {
    this.params = params;
  }
}
