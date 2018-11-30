package ch.ivyteam.ivy.project.portal.guitest.mobile.common;

import java.time.Duration;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import ch.ivyteam.ivy.project.portal.guitest.mobile.core.Device;
import ch.ivyteam.ivy.project.portal.guitest.mobile.tester.ElementDisplayedTester;
import ch.ivyteam.ivy.project.portal.guitest.mobile.tester.ElementPresentTester;
import io.appium.java_client.AppiumDriver;

public abstract class MobileAbstractPage {
  protected final AppiumDriver<WebElement> driver;
  protected final Log log;
  protected static final long DEFAULT_TIMEOUT = 30;

  /**
   * This abstract method is used to determine identity of a page.
   * 
   * @return A unique xpath for the particular page.
   */
  protected abstract String getLoadedLocator();

  public WebDriver getDriver() {
    return driver;
  }

  protected MobileAbstractPage() {
    driver = Device.getDevice().getDriver();
    log = LogFactory.getLog(getClass());
    log.debug("Created page abstraction for " + getClass().getName());
  }

  public void click(By locator) {
    click(locator, null);
  }

  public void click(By locator, MobileAbstractPage nextPage) {
    click(locator, nextPage, DEFAULT_TIMEOUT);
  }

  public void click(By locator, MobileAbstractPage nextPage, long timeout) {
    click(driver.findElement(locator), nextPage, timeout);
  }

  public void click(WebElement element) {
    click(element, null, DEFAULT_TIMEOUT);
  }

  public void click(WebElement element, MobileAbstractPage nextPage) {
    click(element, nextPage, DEFAULT_TIMEOUT);
  }

  public void click(WebElement element, MobileAbstractPage nextPage, long timeout) {
    element.click();
    if (nextPage != null) {
      nextPage.waitForPageLoaded(timeout);
    }
  }

  public void waitForPageLoaded() {
    waitForPageLoaded(DEFAULT_TIMEOUT);
  }

  public void waitForPageLoaded(long timeout) {
    waitForElementPresent(getLoadedLocator(), true, timeout);
  }

  public <T> boolean isElementPresent(T locator) {
    return new ElementPresentTester<T>(null, locator, true).apply(driver);
  }

  public <T> boolean isElementPresent(SearchContext root, T locator) {
    return new ElementPresentTester<T>(root, locator, true).apply(driver);
  }

  public <T> boolean isElementDisplayed(T locator) {
    return new ElementDisplayedTester<T>(null, locator, true).apply(driver);
  }

  public <T> boolean isElementDisplayed(SearchContext root, T locator) {
    return new ElementDisplayedTester<T>(root, locator, true).apply(driver);
  }

  public <T> void waitForElementPresent(final T locator, final boolean expected, final long timeout) {
    new WebDriverWait(driver, timeout).until(new ElementPresentTester<T>(locator, expected));
  }

  public <T> void waitForElementPresent(final SearchContext root, final T locator, final boolean expected,
      final long timeout) {
    new WebDriverWait(driver, timeout).until(new ElementPresentTester<T>(root, locator, expected));
  }

  public <T> void waitForElementDisplayed(final T locator, final boolean expected, final long timeout) {
    new WebDriverWait(driver, timeout).until(new ElementDisplayedTester<T>(locator, expected));
  }

  public <T> void waitForElementDisplayed(final SearchContext root, final T locator, final boolean expected,
      final long timeout) {
    new WebDriverWait(driver, timeout).until(new ElementDisplayedTester<T>(root, locator, expected));
  }

  public WebElement findElementByLocator(By locator) {
    return driver.findElement(locator);
  }
  
  public List<WebElement> findElementsByLocator(By locator) {
    return driver.findElements(locator);
  }
  
  public WebElement findElementById(String id) {
    return driver.findElement(By.id(id));
  }

  public List<WebElement> findListElementsById(String id) {
    return driver.findElements(By.id(id));
  }

  public WebElement findElementByClassName(String className) {
    return driver.findElement(By.className(className));
  }

  public List<WebElement> findListElementsByClassName(String className) {
    return driver.findElements(By.className(className));
  }

  public WebElement findElementByTagName(String tagName) {
    return driver.findElement(By.tagName(tagName));
  }

  public List<WebElement> findListElementsByTagName(String tagName) {
    return driver.findElements(By.tagName(tagName));
  }

  public WebElement findElementByCssSelector(String cssSelector) {
    return driver.findElement(By.cssSelector(cssSelector));
  }

  public List<WebElement> findListElementsByCssSelector(String cssSelector) {
    return driver.findElements(By.cssSelector(cssSelector));
  }

  public WebElement findElementByXpath(String xpath) {
    return driver.findElement(By.xpath(xpath));
  }

  public List<WebElement> findListElementsByXpath(String xpath) {
    return driver.findElements(By.xpath(xpath));
  }

  public String getPageTitle() {
    return driver.getTitle();
  }
  
  public void sleep(Duration duration) {
    try {
        Sleeper.SYSTEM_SLEEPER.sleep(duration);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
}
