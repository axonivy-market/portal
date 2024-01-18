package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;
import java.util.ArrayList;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.Not;


public abstract class TemplatePage extends AbstractPage {
  protected static final String LAYOUT_WRAPPER = ".layout-wrapper";
  public static final String ID_PROPERTY = "id";
  public static final String CLASS_PROPERTY = "class";
  public static final String CURRENT_BREADCRUMB_SELECTOR =
      ".portal-breadcrumb li:last-child .ui-menuitem-link.ui-state-disabled";
  public static final String PORTAL_GLOBAL_GROWL_ID = "portal-global-growl_container";
  protected static final String COMPONENT_PAGE_LOCATOR = "//*[contains(@id,'theme-selection')]";

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

  public void waitForGrowlMessageDisplayClearly() {
    $("div[id='portal-global-growl_container']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-growl-message").hover();
  }
  
  public boolean isElementDisplayed(By element) {
    try {
      waitPageLoaded();
      return $(element).shouldBe(appear, DEFAULT_TIMEOUT).isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }
  
  public void waitForElementDisplayed(By element, boolean expected) {
    if (expected) {
      $(element).shouldBe(appear, DEFAULT_TIMEOUT);
    } else {
      $(element).shouldBe(disappear, DEFAULT_TIMEOUT);
    }
  }

  public void waitForElementDisplayed(SelenideElement element, boolean expected) {
    if (expected) {
      element.shouldBe(appear, DEFAULT_TIMEOUT);
    } else {
      element.shouldBe(disappear, DEFAULT_TIMEOUT);
    }
  }

  public void waitForElementClickableThenClick(SelenideElement element) {
    waitForElementClickable(element).click();
  }
  
  public void waitForElementClickableThenClick(String cssSelector) {
    waitForElementClickable(cssSelector).click();
  }

  public SelenideElement waitForElementClickable(SelenideElement element) {
    return element.shouldBe(clickable(), DEFAULT_TIMEOUT);
  }
  
  public SelenideElement waitForElementClickable(String cssSelector) {
    return $(cssSelector).shouldBe(clickable(), DEFAULT_TIMEOUT);
  }
  
  protected Condition clickable() {
    return and("should be clickable", visible, enabled);
  }

  public SelenideElement findElementById(String selector) {
    return $(String.format("[id$='%s']", selector)).shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void waitForElementExisted(By element, boolean expected) {
    if (expected) {
      $(element).shouldBe(exist, DEFAULT_TIMEOUT);
    } else {
      $(element).shouldBe(Condition.not(exist), DEFAULT_TIMEOUT);
    }
  }

  public boolean isElementPresent(By element) {
    return $(element).is(visible);
  }
  
  public void waitForElementDisplayed(By element, boolean expected, long timeout) {
    if (expected) {
      $(element).shouldBe(appear, Duration.ofSeconds(timeout));
    } else {
      $(element).shouldBe(disappear, Duration.ofSeconds(timeout));
    }
    $("div[id='portal-global-growl_container']").shouldBe(exist, DEFAULT_TIMEOUT).$("div.ui-growl-message")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void switchToIFrameOfTask() {
    switchToIframeWithId("iFrame");
  }

  public void switchBackToParent() {
    WebDriverRunner.getWebDriver().switchTo().parentFrame();
  }
  
  public void waitForElementClickableThenClick(By by) {
    waitForElementClickable($(by)).click();
  }
  
  public WebDriver driver = WebDriverRunner.getWebDriver();

  public void waitForElementEnabled(By element, boolean expected) {
    if (expected) {
      $(element).shouldBe(enabled, DEFAULT_TIMEOUT);
    } else {
      $(element).shouldBe(disabled, DEFAULT_TIMEOUT);
    }
  }

  public ChatPage getChat() {
    waitForElementDisplayed(By.id("toggle-chat-panel-command"), true, 5);
    waitForElementClickableThenClick("[id$='toggle-chat-panel-command']");
    return new ChatPage();
  }
  
  public void switchToDefaultContent() {
    driver.switchTo().defaultContent();
  }

  public LoginPage clickOnLogout() {
    openUserSettingMenu();
    $("[id='logout-setting:logout-menu-item']").shouldBe(appear, DEFAULT_TIMEOUT);
    WaitHelper.waitForNavigation(
        () -> $("[id='logout-setting:logout-menu-item']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new LoginPage();
  }
  
  public void openUserSettingMenu() {
//     waitForElementDisplayed(By.id("user-settings-menu"), true);
    clickByJavaScript(findElementById("user-settings-menu"));
    $("[id='user-setting-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebDriver getDriver() {
    return WebDriverRunner.getWebDriver();
  }

  public void waitForElementPresent(By element, boolean expected) {
    if (expected) {
      $(element).shouldBe(exist, DEFAULT_TIMEOUT);
    } else {
      $(element).shouldBe(Not.exist, DEFAULT_TIMEOUT);
    }
  }

  public AdminSettingsPage openAdminSettings() {
    openUserSettingMenu();
    clickByJavaScript($("[id='adminui-menu-item']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT));
    return new AdminSettingsPage();
  }

  public String getTextOfCurrentBreadcrumb() {
    WebElement breadcrumb = findElementByCssSelector(CURRENT_BREADCRUMB_SELECTOR);
    String result = "";
    if (CollectionUtils.isNotEmpty(breadcrumb.findElements(By.cssSelector(".js-count")))) {
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML")
          + breadcrumb.findElement(By.cssSelector(".js-count")).getAttribute("innerHTML");
    } else {
      result = breadcrumb.findElement(By.cssSelector(".ui-menuitem-text")).getAttribute("innerHTML");
    }
    return result;
  }

  public void switchToIframeWithNameOrId(String value) {
    WebDriverRunner.getWebDriver().switchTo().frame(value);
  }

  public void waitForNewTabOpen() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(ExpectedConditions.numberOfWindowsToBe(2));
  }

  public ProjectVersionPage openProjectVersionPage() {
    clickUserMenuItem("project-info-menu-item");
    return new ProjectVersionPage();
  }
  
  private void clickUserMenuItem(String menuItemSelector) {
    waitForElementDisplayed(By.id("user-settings-menu"), true);
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
  
  public void waitForPageLoad() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(
        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
  }

  public UserProfilePage openMyProfilePage() {
    clickUserMenuItem("user-profile");
    return new UserProfilePage();
  }

  public AbsencePage openAbsencePage() {
    clickUserMenuItem("absence-menu-item");
    return new AbsencePage();
  }
}
