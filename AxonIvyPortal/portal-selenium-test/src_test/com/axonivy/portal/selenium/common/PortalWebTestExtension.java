package com.axonivy.portal.selenium.common;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

public class PortalWebTestExtension implements BeforeAllCallback, AfterEachCallback {

  private static void initConfig() {
    FirefoxOptions options = new FirefoxOptions();
    options.addPreference("dom.disable_beforeunload", true);
    
    if (Configuration.browserCapabilities != null) {
      Configuration.browserCapabilities = Configuration.browserCapabilities.merge(options);
    } else {
      Configuration.browserCapabilities = options;
    }
  }
  
  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    initConfig();
  }
  
  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    WebDriverRunner.getWebDriver().quit();
  }
}
