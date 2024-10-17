package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public abstract class AbstractPage {

  protected final Log log;
  // protected static final long DEFAULT_TIMEOUT = 45000;
  protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(45);

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

  public void waitPageDisappear() {
    $(getLoadedLocator()).shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public boolean isDisplayed() {
    return $(getLoadedLocator()).isDisplayed();
  }

  /**
   * @param element this function used for action click in some invisible element
   */
  public void clickByJavaScript(SelenideElement element) {
    ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("arguments[0].click();", element);

  }

  public SelenideElement findElementByCssSelector(String cssSelector) {
    return $(cssSelector).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitDocumentReady() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30))
        .until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState")
            .equals("complete"));
  }
  
  public void focusByJavascript(SelenideElement element) {
    ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("arguments[0].focus();", element);
  }
  
}
