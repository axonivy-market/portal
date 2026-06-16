package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.axonivy.ivy.webtest.engine.EngineUrl;

public class NavigationExtension implements ParameterResolver {

  private static final String PORTAL_HOME_PAGE_URL = "/portal/1549F58C18A6C562/DefaultApplicationHomePage.ivp";

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    return parameterContext.getParameter().getType().equals(NavigationExtension.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
    return this;
  }

  public NavigationExtension goToDashboard() {
    open(EngineUrl.createProcessUrl(PORTAL_HOME_PAGE_URL));
    return this;
  }

  public NavigationExtension logoutInDesigner() {
    try {
      open(UrlHelpers.getLogoutLink());
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
    return this;
  }
}
