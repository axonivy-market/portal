package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivyteam.ivy.environment.Ivy;

@ViewScoped
@ManagedBean
public class NavigationDashboardWidgetBean implements Serializable {
  private static final long serialVersionUID = -4224901891867040688L;
  
  public void setPreviousDashboardInNavigationDashboardWidget(Dashboard dashboard, NavigationDashboardWidget widget) {
    Ivy.log().info(widget.getName() + dashboard.getTitle());
  }
  
  
}
