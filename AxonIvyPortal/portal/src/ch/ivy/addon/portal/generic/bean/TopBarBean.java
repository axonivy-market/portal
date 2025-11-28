package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.jsf.ManagedBeans;

@ViewScoped
@ManagedBean
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
