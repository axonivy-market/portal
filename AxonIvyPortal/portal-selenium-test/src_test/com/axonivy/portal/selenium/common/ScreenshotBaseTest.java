package com.axonivy.portal.selenium.common;

import org.junit.jupiter.api.AfterEach;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.codeborne.selenide.WebDriverRunner;

@IvyWebTest
public class ScreenshotBaseTest extends BaseTest{

  @AfterEach
  public void tearDown() {
    WebDriverRunner.getWebDriver().quit();
  }
  
}
