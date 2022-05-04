package com.axonivy.portal.selenium.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.WebDriverRunner;

public abstract class AbstractPage {
  
  protected final Log log;
  protected static final long DEFAULT_TIMEOUT = 45000;
  private static final String JS_JQUERY_DEFINED = "return typeof jQuery != 'undefined';";
  private static final String JS_PRIMEFACES_DEFINED = "return typeof PrimeFaces != 'undefined';";
  private static final String JS_JQUERY_ACTIVE = "return jQuery.active != 0;";
  private static final String JS_PRIMEFACES_QUEUE_NOT_EMPTY = "return !PrimeFaces.ajax.Queue.isEmpty();";

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
  
  public void waitForJQueryAndPrimeFaces(long timeoutInSeconds) {

    new WebDriverWait(WebDriverRunner.getWebDriver(), timeoutInSeconds).until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver input) {
        boolean ajax = false;
        boolean jQueryDefined = executeBooleanJavascript(input, JS_JQUERY_DEFINED);
        boolean primeFacesDefined = executeBooleanJavascript(input, JS_PRIMEFACES_DEFINED);

        if (jQueryDefined) {
          ajax |= executeBooleanJavascript(input, JS_JQUERY_ACTIVE);
        }
        if (primeFacesDefined) {
          ajax |= executeBooleanJavascript(input, JS_PRIMEFACES_QUEUE_NOT_EMPTY);
        }

        return !ajax;
      }
    });

  }

  private static boolean executeBooleanJavascript(WebDriver input, String javascript) {
    return (Boolean) ((JavascriptExecutor) input).executeScript(javascript);
  }

}
