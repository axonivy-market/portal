package ch.ivy.addon.portalkit.security;

import java.security.SecureRandom;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang.RandomStringUtils;

import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.IUser;

@Singleton
public class PortalConnectorUser {

  private ISecurityManager securityManager;
  private IApplicationConfigurationManager applicationManager;
  private String password;

  @Inject
  public PortalConnectorUser(ISecurityManager securityManager, IApplicationConfigurationManager applicationManager) {
    this.securityManager = securityManager;
    this.applicationManager = applicationManager;
    this.password = readPassword();
  }

  public static PortalConnectorUser getPortalConnectorUser() {
    return DiCore.getGlobalInjector().getInstance(PortalConnectorUser.class);
  }

  public String getUserName() {
    return "PortalConnector";
  }

  public String getPassword() {
    return password;
  }

  private String readPassword() {
    return securityManager.executeAsSystem2(() -> readPasswordAsSystem());
  }

  private String readPasswordAsSystem() {
    IUser user = getUser();
    if (user == null) {
      user = createUser();
    }
    return evaluateNewPassword(user);
  }

  private String evaluateNewPassword(IUser user) {
    String password = RandomStringUtils.random(40, 0, 0, true, true, null, new SecureRandom());
    user.setPassword(password);
    return password;
  }

  private IUser createUser() {
    return getSystemSecurityContext().createUser(getUserName(), getUserName(), "", Locale.ENGLISH, null, null);
  }

  private IUser getUser() {
    ISecurityContext securitySystem = getSystemSecurityContext();
    return securitySystem.findUser(getUserName());
  }

  private ISecurityContext getSystemSecurityContext() {
    return getSystemApplication().getSecurityContext();
  }

  private IApplication getSystemApplication() {
    IApplication systemApplication = applicationManager.getSystemApplication();
    if (systemApplication != null) {
      return systemApplication;
    }
    return applicationManager.findApplication(IApplication.DESIGNER_APPLICATION_NAME);
  }
}
