package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import com.axonivy.portal.components.jsf.ManagedBeans;

@ViewScoped
@Named
public class TopBarBean implements Serializable {
  private static final long serialVersionUID = -4224901891867040688L;
  private boolean showNavigationBackButton;
  
  @PostConstruct
  public void init() {
    setShowNavigationBackButton(getShowNavigationBackButtonFromNavigationDashboardWidgetBean());
  }
  
  public boolean getShowNavigationBackButtonFromNavigationDashboardWidgetBean() {
    NavigationDashboardWidgetBean navigationDashboardWidgetBean = ManagedBeans.get("navigationDashboardWidgetBean");
    if (navigationDashboardWidgetBean == null) {
      return false;
    }
    return Boolean.TRUE.equals(navigationDashboardWidgetBean.getIsNavigateToTargetDashboard());
  }

  public boolean getShowNavigationBackButton() {
    return showNavigationBackButton;
  }

  public void setShowNavigationBackButton(boolean showNavigationBackButton) {
    this.showNavigationBackButton = showNavigationBackButton;
  }
}
