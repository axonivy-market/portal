package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

public class NotificationCompactPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='notifications-panel']";
  }

  public void openNotificationFullPage() {
    $("a[id='notification-full-page']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("a[id='notification-full-form:notifications-scroller:0:notification-mark-as-read']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
  }
}
