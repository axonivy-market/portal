package com.axonivy.portal.selenium.common;

public class IEConfiguration extends AbstractBrowserConfiguration {
  
  @Override
  protected String getDriverPathInClasspath() {
    return "./resources/IEDriverServer.exe";
  }
}
