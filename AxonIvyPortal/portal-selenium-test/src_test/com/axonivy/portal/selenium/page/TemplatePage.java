package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public abstract class TemplatePage extends AbstractPage {
  protected static final String LAYOUT_WRAPPER = ".layout-wrapper";
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

  public void waitForGrowlMessageDisappear() {
    $("div[id='portal-global-growl_container']").shouldBe(appear, DEFAULT_TIMEOUT)
          .$("div.ui-growl-message").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
  
  public void waitForGrowlMessageDisplayClearly() {
    $("div[id='portal-global-growl_container']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-growl-message").hover();
  }
  
  public SelenideElement waitForElementClickable(String cssSelector) {
    return $(cssSelector).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
  }

  public void waitForElementClickableThenClick(String cssSelector) {
    waitForElementClickable(cssSelector).click();
  }

  public void switchToIFrameOfTask() {
    switchToIframeWithId("iFrame");
  }
  
  public void switchBackToParent() {
    WebDriverRunner.getWebDriver().switchTo().parentFrame();
  }

  public LoginPage clickOnLogout() {
    $("[id='user-settings-menu']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='logout-setting:logout-menu-item']").shouldBe(appear, DEFAULT_TIMEOUT);
    WaitHelper.waitForNavigationToLoginPage(() -> $("[id='logout-setting:logout-menu-item']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new LoginPage();
  }

  public AbsencePage openAbsencePage() {
    clickUserMenuItem("absence-menu-item");
    return new AbsencePage();
  }

  private void clickUserMenuItem(String menuItemSelector) {
    $(By.id("user-settings-menu")).shouldBe(appear, DEFAULT_TIMEOUT);
    clickByJavaScript($(By.id("user-settings-menu")));
    $(By.id(menuItemSelector)).shouldBe(appear, DEFAULT_TIMEOUT);
    clickByJavaScript($(By.id(menuItemSelector)));
    $(By.id("user-setting-container")).shouldBe(disappear, DEFAULT_TIMEOUT);
//    WaitHelper.assertTrueWithWait(() -> !findElementById("user-setting-container").isDisplayed());

  }
}
