package ch.ivy.addon.portalkit.dto.widget;

import java.io.Serializable;
import java.util.Map;

public class CustomWidgetData implements Serializable {

  private static final long serialVersionUID = 8763058243562205725L;

  private String url;
  private String type;

  private String processPath;
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

  public String getProcessPath() {
    return processPath;
  }

  public void setProcessPath(String processPath) {
    this.processPath = processPath;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
