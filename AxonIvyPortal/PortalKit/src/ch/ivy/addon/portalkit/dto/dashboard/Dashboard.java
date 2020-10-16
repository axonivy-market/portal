package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.environment.Ivy;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Dashboard implements Serializable {

  private static final long serialVersionUID = 4580715578128184706L;

  private String id;
  private String title;
  private List<DashboardWidget> widgets;

  public Dashboard() {}

  public Dashboard(String id, String title, List<DashboardWidget> widgets) {
    this.id = id;
    this.title = title;
    this.widgets = widgets;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getTitle() {
    if (StringUtils.startsWithIgnoreCase(title, "cms:")) {
      return Ivy.cms().co(StringUtils.removeStart(title, "cms:"));
    }
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<DashboardWidget> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<DashboardWidget> widgets) {
    this.widgets = widgets;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Dashboard other = (Dashboard) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
