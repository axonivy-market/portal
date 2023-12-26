package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;


public abstract class TemplatePage extends AbstractPage {
  protected static final String LAYOUT_WRAPPER = ".layout-wrapper";
  public static final String CLASS_PROPERTY = "class";
  public static final String ID_PROPERTY = "id";

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

  public void waitUntilElementToBeClickable(SelenideElement element) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until(ExpectedConditions.elementToBeClickable(element));
  }
  
  public void switchToIframeWithId(String id) {
    WebDriverRunner.getWebDriver().switchTo().frame($("iframe[id='" + id + "']"));
  }
  
  public void switchToIframeWithNameOrId(String value) {
    WebDriverRunner.getWebDriver().switchTo().frame(value);
  }

  public void waitForGrowlMessageDisappear() {
    $("div[id='portal-global-growl_container']").shouldBe(exist, DEFAULT_TIMEOUT)
          .$("div.ui-growl-message").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
  
  public void waitForGrowlMessageDisplayClearly() {
    $("div[id='portal-global-growl_container']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-growl-message").hover();
  }
  
  public void switchToIFrameOfTask() {
    switchToIframeWithId("iFrame");
  }
  
  public void switchBackToParent() {
    WebDriverRunner.getWebDriver().switchTo().parentFrame();
  }

  public void openUserSettingMenu() {
    $("[id='user-settings-menu']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='user-setting-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public LoginPage clickOnLogout() {
    openUserSettingMenu();
    $("[id='logout-setting:logout-menu-item']").shouldBe(appear, DEFAULT_TIMEOUT);
    WaitHelper.waitForNavigationToLoginPage(() -> $("[id='logout-setting:logout-menu-item']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new LoginPage();
  }

  public void waitForElementValueChanged(String cssSelector, String expectedValue) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(ExpectedConditions.textToBe(By.cssSelector(cssSelector), expectedValue));
  }

  public void waitForGlobalGrowlDisappear() {
    $("div[id='portal-global-growl_container']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
  public AdminSettingsPage openAdminSettings() {
    openUserSettingMenu();
    WaitHelper.waitForNavigation(() -> $("[id='adminui-menu-item']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new AdminSettingsPage();
  }

  public void waitForAjaxIndicatorDisappeared() {
    WaitHelper.waitAttributeToBe(WebDriverRunner.getWebDriver(), By.id("ajax-indicator:ajax-indicator-ajax-indicator_start"), "display", "none");
  }

  private void clickUserMenuItem(String menuItemSelector) {
    openUserSettingMenu();
    $("[id='" + menuItemSelector + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public ChangePasswordPage openChangePasswordPage() {
    clickUserMenuItem("change-password-menu-item");
    return new ChangePasswordPage();
  }

  public ProjectVersionPage openProjectVersionPage() {
    clickUserMenuItem("project-info-menu-item");
    return new ProjectVersionPage();
  }

  public UserProfilePage openMyProfilePage() {
    clickUserMenuItem("user-profile");
    return new UserProfilePage();
  }

  public AbsencePage openAbsencePage() {
    clickUserMenuItem("absence-menu-item");
    return new AbsencePage();
  }
  
  public WebDriver getDriver() {
    return WebDriverRunner.getWebDriver();
  }
  
  public int countBrowserTab() {
    return getDriver().getWindowHandles().size();
  }
  
  public void waitForNewTabOpen() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30)).until(ExpectedConditions.numberOfWindowsToBe(2));
  }

  public QRCodePage openQRCode() {
    openUserSettingMenu();
    $("[id='mobile-app-item']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return new QRCodePage();
  }
}
