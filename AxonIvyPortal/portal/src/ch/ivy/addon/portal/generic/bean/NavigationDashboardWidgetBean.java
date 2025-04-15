package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;

import java.io.Serializable;
import java.util.Stack;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivyteam.ivy.environment.Ivy;

@SessionScoped
@ManagedBean
public class NavigationDashboardWidgetBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;
  private String previousDashboardId;
  private Boolean isNavigateToTargetDashboard;
  private Stack<String> pageHistory = new Stack<>();
  
  public void pushPage(String pageId) {
      if (!pageHistory.isEmpty() && pageHistory.peek().equals(pageId)) {
          return;
      }
      
      pageHistory.push(pageId);
  }
  
  public String getPreviousPage() {
      return pageHistory.peek();
  }
  
  private void removeLast() {
    pageHistory.pop();
  }
  
  public boolean hasHistory() {
      return pageHistory.size() > 0;
  }
  
  public void clearHistory() {
      pageHistory.clear();
  }
  public void savePreviousDashboardId(String dashboardId) {
    setPreviousDashboardId(dashboardId);
  }

  public void redirectToDashboard(NavigationDashboardWidget widget, Dashboard currentDashboard) throws IOException {
    pushPage(currentDashboard.getId());
    setIsNavigateToTargetDashboard(Boolean.TRUE);
    navigateToDashboard(widget.getTargetDashboardId());
    Ivy.session().removeAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.name());
  }
  
  public void backToPreviousDashboard() throws IOException {
    if (hasHistory()) {
      navigateToDashboard(getPreviousPage());
      removeLast();
      if (pageHistory.size() == 0) {
        setIsNavigateToTargetDashboard(Boolean.FALSE);
      }
      Ivy.session().removeAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.name());
    }
    else {
      FacesContext.getCurrentInstance().getExternalContext().redirect(PortalNavigator.getPortalStartUrl());
    }
  }
  
  public void removeNavigationDashboardBackButton() {
    if (hasHistory()) {
      clearHistory();
      setIsNavigateToTargetDashboard(Boolean.FALSE);
    }
  }
  
  public void removeSessionAttribute() {
    SecurityServiceUtils.removeSessionAttribute(SessionAttribute.SELECTED_SUB_DASHBOARD_ID.name());
  }

  public void navigateToDashboard(String id) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(getDashboardUrlByDashboard(id));
  }

  private String getDashboardUrlByDashboard(String id) {
    return UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(id);
  }

  public String getPreviousDashboardId() {
    return this.previousDashboardId;
  }

  public void setPreviousDashboardId(String dashboardId) {
    this.previousDashboardId = dashboardId;
  }
  
  public Boolean getIsNavigateToTargetDashboard() {
    return this.isNavigateToTargetDashboard;
  }

  public void setIsNavigateToTargetDashboard(Boolean isNavigateToTargetDashboard) {
    this.isNavigateToTargetDashboard = isNavigateToTargetDashboard;
  }
  
  public Boolean isNotClickable(NavigationDashboardWidget widget, Boolean isReadOnlyMode) {
    if (!isReadOnlyMode) {
      return true;
    }
    return !DashboardUtils.collectDashboardIds().contains(widget.getTargetDashboardId());
  }
}
