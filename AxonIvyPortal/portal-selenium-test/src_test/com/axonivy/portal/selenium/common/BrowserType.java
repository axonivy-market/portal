package com.axonivy.portal.selenium.common;

public enum BrowserType {
  FIREFOX(new FirefoxConfiguration()), CHROME(new ChromeConfiguration()), IE(new IEConfiguration());

  private AbstractBrowserConfiguration configuration;

  private BrowserType(AbstractBrowserConfiguration configuration) {
    this.configuration = configuration;
  }

  public AbstractBrowserConfiguration getConfiguration() {
    return configuration;
  }

}
