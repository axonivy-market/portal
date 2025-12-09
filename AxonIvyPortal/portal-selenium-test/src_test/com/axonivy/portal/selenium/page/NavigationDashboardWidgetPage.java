package com.axonivy.portal.selenium.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NavigationDashboardWidgetPage extends TemplatePage {
  private String widgetId;
  
  @Override
  protected String getLoadedLocator() {
    return ".navigation-dashboard-widget-info";
  }
  
  public NavigationDashboardWidgetPage() {
    this.setWidgetId("#navigation-dashboard-widget-footer");
  }
  
  public void clickOnNavigateButton() {
    $("[id$=':navigation-dashboard-widget-footer']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).
    $("button[id$='navigate-dashboard-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    waitForPageLoad();
  }
  
  public SelenideElement getWidgetDialog() {
    return $("div[class*='navigation-dashboard-widget-panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public String getWidgetId() {
    return widgetId;
  }

  public void setWidgetId(String widgetId) {
    this.widgetId = widgetId;
  }
}
