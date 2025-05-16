package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {
  private MasterDataBean masterDataBean;
  private static final String LOGIN = 
      Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/login/login");

  public LoginBean() {
    masterDataBean = (MasterDataBean) ManagedBeans.get("masterDataBean");
  }

  private static final long serialVersionUID = 2771794439843278846L;
  private final String PORTAL_LOGIN_PAGE_DISPLAY = "PortalLoginPageDisplay";
  
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