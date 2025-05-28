package com.axonivy.portal.selenium.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NavigationDashboardWidgetPage extends TemplatePage {
  private String widgetId;
  
  @Override
  protected String getLoadedLocator() {
    return ".js-navigation-dashboard-widget";
  }
  
  public NavigationDashboardWidgetPage() {
    this.widgetId = "#navigation-dashboard-widget-footer";
  }
  
  public void clickOnNavigateButton() {
    $("[id$=':navigation-dashboard-widget-footer']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).
    $("button[id$='navigate-dashboard-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    waitForPageLoad();
  }
  
  public SelenideElement getWidgetDialog() {
    return $("div[class*='navigation-dashboard-widget-panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
}
