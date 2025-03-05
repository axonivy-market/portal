package com.axonivy.portal.selenium.common;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

public class ProxyExtension implements BeforeAllCallback, AfterAllCallback {

  private boolean originalProxyEnabled;

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    Configuration.proxyEnabled = originalProxyEnabled;
    Selenide.closeWebDriver();
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    Selenide.closeWebDriver();
    this.originalProxyEnabled = Configuration.proxyEnabled;
    Configuration.proxyEnabled = true;
  }

}
