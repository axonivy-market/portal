package com.axonivy.portal.selenium.common;

public abstract class AbstractBrowserConfiguration {

  public String getDriverPath() {
    String vmArgPath = System.getProperty("driverPath");
    if (vmArgPath != null) {
      return vmArgPath;
    }
    return getDriverPathInClasspath();
  }

  abstract protected String getDriverPathInClasspath();
}
