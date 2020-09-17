package ch.ivy.addon.portalkit.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class LoginUtils {
  private static final String PORTAL_LOGIN_ERROR_MESSAGE_DISPLAY = "PortalLoginPageDisplay";
  
  public boolean isLoginPageRendered() {
    return Boolean.parseBoolean(Ivy.var().get(PORTAL_LOGIN_ERROR_MESSAGE_DISPLAY));
  }
}
