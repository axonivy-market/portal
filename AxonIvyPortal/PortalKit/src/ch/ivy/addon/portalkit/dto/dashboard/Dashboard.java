package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivyteam.ivy.environment.Ivy;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Dashboard extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 4580715578128184706L;
  private String title;
  private List<DashboardWidget> widgets;
  private List<String> permissions;

  public Dashboard() {}

  public Dashboard(String id, String title, List<DashboardWidget> widgets) {
    this.setId(id);
    this.title = title;
    this.widgets = widgets;
  }
  
  public String getTitle() {
    if (StringUtils.startsWithIgnoreCase(title, DashboardConfigurationPrefix.CMS)) {
      return Ivy.cms().co(StringUtils.removeStart(title, DashboardConfigurationPrefix.CMS));
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
  
  public List<String> getPermissions() {
    return permissions;
  }
  
  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
    if (getId() == null) {
      if (other.getId() != null)
        return false;
    } else if (!getId().equals(other.getId()))
      return false;
    return true;
  }
}
