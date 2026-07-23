package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;


import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivyteam.ivy.environment.Ivy;

@Named
@ViewScoped
public class LoginBean implements Serializable {

  private static final long serialVersionUID = 2771794439843278846L;
  private final String PORTAL_LOGIN_PAGE_DISPLAY = "PortalLoginPageDisplay";
  private static final String LOGIN = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/login/login");
  
  private MasterDataBean masterDataBean;

  public LoginBean() {
    masterDataBean = (MasterDataBean) ManagedBeans.get("masterDataBean");
  }

  public boolean isLoginPageRendered() {
    return Boolean.parseBoolean(Ivy.var().get(PORTAL_LOGIN_PAGE_DISPLAY));
  }
  
  public void navigateToPortalLoginPage() {
    PortalNavigator.navigateToPortalLoginPage();
  }

  public String loginPageTitle() {
    String applicationName = masterDataBean.getApplicationName();
    return StringUtils.isBlank(applicationName) ? LOGIN : LOGIN + " - " + applicationName;
  }
}