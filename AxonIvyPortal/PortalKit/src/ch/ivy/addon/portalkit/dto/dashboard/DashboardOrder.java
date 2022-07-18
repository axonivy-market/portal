package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardOrder extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 1L;
  private String dashboardId;
  private boolean isPublicDashboard;
  private boolean visible = true;
  @JsonIgnore
  private String dashboardTitle;

  public DashboardOrder() {}

  public DashboardOrder(Dashboard dashboard) {
    this.dashboardId = dashboard.getId();
    this.isPublicDashboard = dashboard.getIsPublic();
    this.dashboardTitle = dashboard.getTitle();
    this.visible = true;
  }

  public String getDashboardId() {
    return dashboardId;
  }

  public void setDashboardId(String dashboardId) {
    this.dashboardId = dashboardId;
  }

  public boolean getIsPublicDashboard() {
    return isPublicDashboard;
  }

  public void setIsPublicDashboard(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public String getDashboardTitle() {
    return dashboardTitle;
  }

  public void setDashboardTitle(String dashboardTitle) {
    this.dashboardTitle = dashboardTitle;
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
    DashboardOrder other = (DashboardOrder) obj;
    if (getId() == null) {
      if (other.getId() != null)
        return false;
    } else if (!getId().equals(other.getId()))
      return false;
    return true;
  }
}
