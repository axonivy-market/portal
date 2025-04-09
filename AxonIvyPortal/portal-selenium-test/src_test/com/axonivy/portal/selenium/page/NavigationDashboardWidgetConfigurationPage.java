package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class NavigationDashboardWidgetConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "div#new-widget-configuration-dialog";
  }
  
  public NavigationDashboardWidgetConfigurationPage() {}
  
  public NavigationDashboardWidgetConfigurationPage(String name, String description, String targetDashboard) {
    setWidgetTitle(name);
    setWidgetDescription(description);
    selectTargetDashboard(targetDashboard);
  }
  
  public void setWidgetTitle(String widgetTitle) {
    $("form#widget-configuration-form").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("span[id$=':widget-title-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("input[id$=':widget-title']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .setValue(widgetTitle);
  }
  
  public void setWidgetDescription(String description) {
    $("form#widget-configuration-form").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("span[id$=':widget-title-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("input[id$=':widget-description']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .setValue(description);
  }
  
  public void selectTargetDashboard(String targetDashboard) {
    $("form#widget-configuration-form").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("div[id$='dashboard-link-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("div[id$=':dashboard-link-selection-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    
    $("div[id$=':dashboard-link-selection-menu_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("ul").$$("li").filter(Condition.text(targetDashboard)).first().click();
  }
  
  public void save() {
    $("button#widget-configuration-save-button").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }

}
