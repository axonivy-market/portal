package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class NotificationCompactPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='notifications-panel']";
  }

  public void openNotificationFullPage() {
    $("[id$=':notification-full-page']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("a[id='notification-full-form:notifications-scroller:0:notification-mark-as-read']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
  }
  
  public void openNotificationMoreActionsMenu() {
    $("button[id$=':notification-more-option_button']").shouldBe(getClickableCondition()).click();
  }
}
