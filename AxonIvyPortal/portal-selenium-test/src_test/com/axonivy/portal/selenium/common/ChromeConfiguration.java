package com.axonivy.portal.selenium.common;

public class ChromeConfiguration extends AbstractBrowserConfiguration {

  @Override
  protected String getDriverPathInClasspath() {
    return "./resources/ChromeDriver.exe";
  }
}
