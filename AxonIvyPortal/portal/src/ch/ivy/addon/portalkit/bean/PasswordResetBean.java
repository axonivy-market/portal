package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

@Named
@ViewScoped
public class PasswordResetBean implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  public String getHomePageURL() {
    return PortalNavigator.getPortalStartUrl();
  }
}
