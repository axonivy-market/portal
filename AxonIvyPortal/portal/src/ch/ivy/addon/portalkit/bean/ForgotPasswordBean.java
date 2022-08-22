package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.util.SecuritySystemUtils;

@ManagedBean
@ViewScoped
public class ForgotPasswordBean implements Serializable {

  private static final long serialVersionUID = 1L;
  
  public String getForgotPasswordUrl() {
    return PortalNavigator.getForgotPasswordUrl();
  }

  public boolean isForgotPasswordRendered() {
    return SecuritySystemUtils.isIvySecuritySystem();
  }
}
