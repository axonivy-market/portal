package ch.ivyteam.ivy.project.portal.guitest.mobile.tester;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementPresentTester<T> implements ExpectedCondition<Boolean> {
  private static final Log log = LogFactory.getLog(ElementPresentTester.class);

  private SearchContext root;
  private boolean refreshOnFail;
  private final T locator;
  private final boolean exists;

  public boolean getRefreshOnFail() {
    return refreshOnFail;
  }

  public void setRefreshOnFail(boolean refreshOnFail) {
    this.refreshOnFail = refreshOnFail;
  }

  public boolean isElementPresent(SearchContext root, T locator) {
    boolean result = false;
    By by;
    if (locator instanceof String) {
      by = By.xpath(String.class.cast(locator));
    } else {
      by = By.class.cast(locator);
    }
    int count = root.findElements(by).size();
    if (count == 0) {
      log.debug(locator + " is not present");
    } else if (count > 1) {
      log.debug(locator + " found " + count + " elements present.");
      result = true;
    } else {
      result = true;
    }
    return result;
  }

  @Override
  public Boolean apply(WebDriver driver) {
    log.debug("Testing: " + locator);
    if (root == null) {
      root = driver;
    }
    boolean result = exists ? isElementPresent(root, locator) : !isElementPresent(root, locator);
    if (result == false && refreshOnFail == true) {
      driver.navigate().refresh();
    }
    return result;
  }

  public ElementPresentTester(SearchContext root_, T locator_, boolean exists_) {
    this.root = root_;
    this.locator = locator_;
    this.exists = exists_;
  }

  public ElementPresentTester(T locator_, boolean exists_) {
    this.root = null;
    this.locator = locator_;
    this.exists = exists_;
  }

}
