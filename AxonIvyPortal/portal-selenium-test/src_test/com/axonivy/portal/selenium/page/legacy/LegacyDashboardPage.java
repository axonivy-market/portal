package com.axonivy.portal.selenium.page.legacy;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.page.TemplatePage;

public class LegacyDashboardPage extends TemplatePage {

  public final static String PORTAL_EXAMPLES_HOME_PAGE_URL = "portal-developer-examples/164211E97C598DAA/DefaultApplicationHomePage.ivp";

  @Override
  protected String getLoadedLocator() {
    return "[id='task-widget:task-list-link:task-list-link']";
  }

  /** 
   * Wait for statistic charts are displayed in 5s
   */
  public void waitForStatisticFinishAnimation() {
    $(".js-loading-text").shouldBe(disappear, DEFAULT_TIMEOUT);
    openThenCloseStatisticInfoDialog();
  }

  private void openThenCloseStatisticInfoDialog() {
    $("a[id^='statistics-widget:statistic-dashboard-widget:statistic-carousel:0']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id='statistics-widget:statistic-dashboard-widget:chart-details-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-dialog-content").$("button").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id='statistics-widget:statistic-dashboard-widget:chart-details-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void waitForGrowlMessageDisappear() {
    $("div[id='portal-global-growl_container']").shouldBe(disappear, DEFAULT_TIMEOUT).$("div.ui-growl-message")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public WebElement getProcessWidgetElement() {
    return $("div[id='process-widget']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getStatisticWidgetElement() {
    return $("div[id='statistics-widget']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getTaskWidgetElement() {
    $("div[id='task-widget']").shouldBe(appear, DEFAULT_TIMEOUT)
      .$$("div[class*='task-start-list-item']").shouldHave(sizeGreaterThan(0), DEFAULT_TIMEOUT);
    return $("div[id='task-widget']");
  }

  public void clickGlobalSearch() {
    $(".topbar-item.search-item").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("input[id$='global-search-component:global-search-data']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getTopBar() {
    return $("ul[id='top-menu']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}
