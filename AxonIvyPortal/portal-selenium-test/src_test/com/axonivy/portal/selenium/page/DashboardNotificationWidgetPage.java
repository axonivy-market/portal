package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class DashboardNotificationWidgetPage extends TemplatePage {

  private String widgetName;

  public DashboardNotificationWidgetPage() {
    super();
    var selectedWidget = $(".widget__header span[id*='notification-notification_']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    widgetName = selectedWidget.getText();
  }

  @Override
  protected String getLoadedLocator() {
    return ".notification-widget__content-panel";
  }

  public String getWidgetName() {
    return widgetName;
  }

  public void setWidgetName(String widgetName) {
    this.widgetName = widgetName;
  }
}
