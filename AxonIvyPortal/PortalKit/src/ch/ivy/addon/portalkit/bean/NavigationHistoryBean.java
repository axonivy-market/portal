package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class NavigationHistoryBean implements Serializable {
  private String navigationHistory;
  private static final long serialVersionUID = 1L;

  public String getNavigationHistory() {
    return navigationHistory;
  }

  public void setNavigationHistory(String navigationHistory) {
    this.navigationHistory = navigationHistory;
  }

}
