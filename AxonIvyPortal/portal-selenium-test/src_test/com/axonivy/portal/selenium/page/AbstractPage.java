package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.WebDriverRunner;

public abstract class AbstractPage {
  
  protected final Log log;
//  protected static final long DEFAULT_TIMEOUT = 45000;
  protected static final Duration DEFAULT_TIMEOUT= Duration.ofSeconds(45);

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
    $(getLoadedLocator()).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  /**
   * @param element
   * this function used for action click in some invisible element
   */
  public void clickByJavaScript(WebElement element) {
    ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("arguments[0].click();", element);
  }
}
