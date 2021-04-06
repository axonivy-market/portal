package ch.ivy.addon.portalkit.dto.taskdetails;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDetailsCustomWidgetData implements Serializable {

  private static final long serialVersionUID = 8763058243562205725L;

  private String url;

  @JsonProperty("ivy")
  private String ivyProcess;
  private Map<String, String> params;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public void setParams(Map<String, String> params) {
    this.params = params;
  }

  public String getIvyProcess() {
    return ivyProcess;
  }

  public void setIvyProcess(String ivyProcess) {
    this.ivyProcess = ivyProcess;
  }
}
