package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractPage {
  
  protected final Log log;
  protected static final long DEFAULT_TIMEOUT = 45000;

  /**
   * This abstract method is used to determine identity of a page.
   * 
   * @return A unique CSS selector for the particular page.
   */
  protected abstract String getLoadedLocator();

  protected AbstractPage() {
    log = LogFactory.getLog(getClass());
    log.debug("Created page abstraction for " + getClass().getName());
    waitPageLoaded();
  }

  public void waitPageLoaded() {
    $(getLoadedLocator()).waitUntil(appear, DEFAULT_TIMEOUT);
  }

}
