package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class DashboardNotificationWidgetPage extends TemplatePage {

  private String widgetName;
  private String widgetId;

  public DashboardNotificationWidgetPage() {
    super();
    var widgetHeader = $(".widget__header span[id*='notification-notification_']").shouldBe(Condition.appear,
        DEFAULT_TIMEOUT);
    widgetName = widgetHeader.getText();
    String widgetHeaderId = widgetHeader.getAttribute("id");
    widgetId = widgetHeaderId.substring("notification-notification_".length(), widgetHeaderId.indexOf(':'));
  }

  @Override
  protected String getLoadedLocator() {
    return ".notification-widget__content-panel";
  }

  public SelenideElement getWidgetElement() {
    return $(
        "div[id='notification-notification_" + widgetId + ":notification-widget-notification_" + widgetId + "-panel']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public String getWidgetName() {
    return widgetName;
  }

  public void setWidgetName(String widgetName) {
    this.widgetName = widgetName;
  }

  public ElementsCollection getListNotifications() {
    return $$("a[id*='notification-notification_" + widgetId + ":notifications-scroller-notification_" + widgetId + "']");
  }
}
