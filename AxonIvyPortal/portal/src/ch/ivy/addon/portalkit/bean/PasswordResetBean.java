package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

@ManagedBean
@ViewScoped
public class PasswordResetBean implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  public String getHomePageURL() {
    return PortalNavigator.getPortalStartUrl();
  }
}
