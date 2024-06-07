package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public abstract class TemplatePage extends AbstractPage {
  
  public static final String CLASS_PROPERTY = "class";

  // If page load more than 45s, mark it failed by timeout
  protected long getTimeOutForLocator() {
    return 45L;
  }

  protected Condition getClickableCondition() {
    return and("should be clickable", visible, exist);
  }

  public void switchLastBrowserTab() {
    String oldTab = WebDriverRunner.getWebDriver().getWindowHandle();
    ArrayList<String> tabs = new ArrayList<String>(WebDriverRunner.getWebDriver().getWindowHandles());
    tabs.remove(oldTab);
    WebDriverRunner.getWebDriver().switchTo().window(tabs.get(0));
  }
  
  public void switchToIframeWithId(String id) {
    WebDriverRunner.getWebDriver().switchTo().frame($("iframe[id='" + id + "']"));
  }

  public void waitUntilElementToBeClickable(SelenideElement element) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until(ExpectedConditions.elementToBeClickable(element));
  }

  public void waitForGrowlMessageDisappear() {
    $("div[id='portal-global-growl_container']").shouldBe(appear, DEFAULT_TIMEOUT)
          .$("div.ui-growl-message").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public UserProfilePage openMyProfilePage() {
    clickUserMenuItem("user-profile");
    return new UserProfilePage();
  }
  
  private void clickUserMenuItem(String menuItemSelector) {
    $("a[id='user-settings-menu']").shouldBe(appear, DEFAULT_TIMEOUT);
    try {
      clickByJavaScript(findElementById("user-settings-menu"));
      $("ul[id$='user-setting-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    } catch (Error e) {
      clickByJavaScript(findElementById("user-settings-menu"));
    }
    waitForElementDisplayed(By.id(menuItemSelector), true);
    clickByJavaScript(findElementById(menuItemSelector));
    waitForPageLoad();
  }
  
  public SelenideElement findElementById(String selector) {
    return $(String.format("[id$='%s']", selector)).shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void waitForElementDisplayed(By element, boolean expected) {
    if (expected) {
      $(element).shouldBe(appear, DEFAULT_TIMEOUT);
    } else {
      $(element).shouldBe(disappear, DEFAULT_TIMEOUT);
    }
  }
  
  public void waitForPageLoad() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(
        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
  }
  
  public void waitForElementClickableThenClick(SelenideElement element) {
    waitForElementClickable(element).click();
  }
  
  public SelenideElement waitForElementClickable(SelenideElement element) {
    return element.shouldBe(clickable(), DEFAULT_TIMEOUT);
  }
  
  protected Condition clickable() {
    return and("should be clickable", visible, enabled);
  }
  
  public boolean isMainMenuOpen() {
    WebElement mainMenu = $(".layout-wrapper");
    return mainMenu.getAttribute(CLASS_PROPERTY).indexOf("static") > 0;
  }

  public String getPageTitle() {
    return WebDriverRunner.getWebDriver().getTitle();
  }
}
