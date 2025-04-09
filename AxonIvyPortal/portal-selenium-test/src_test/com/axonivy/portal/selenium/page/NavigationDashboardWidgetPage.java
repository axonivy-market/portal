package com.axonivy.portal.selenium.page;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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
    $("div#navigation-dashboard-widget-footer").shouldBe(Condition.appear, DEFAULT_TIMEOUT).
    $("button[id$='navigate-dashboard-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }
}
