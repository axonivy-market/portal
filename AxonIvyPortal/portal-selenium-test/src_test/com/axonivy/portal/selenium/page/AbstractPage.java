package com.axonivy.portal.selenium.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractPage {
  
  protected final Log log;
  protected static final long DEFAULT_TIMEOUT = 45000;

  /**
   * This abstract method is used to determine identity of a page.
   * 
   * @return A unique xpath for the particular page.
   */
  protected abstract String getLoadedLocator();

  protected AbstractPage() {
    log = LogFactory.getLog(getClass());
    log.debug("Created page abstraction for " + getClass().getName());
  }

}
