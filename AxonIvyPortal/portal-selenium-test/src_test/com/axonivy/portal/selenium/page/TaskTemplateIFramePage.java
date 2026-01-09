package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

public class TaskTemplateIFramePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='task-template-title']";
  }

  public NewDashboardPage clickSubmitButton() {
    clickByJavaScript($("button[id$='button-submit']"));
    waitPageDisappear();
    switchToDefaultContent();
    return new NewDashboardPage();
  }

  public NewDashboardPage clickCancelButton() {
    clickByJavaScript($("a[id$='button-cancel']"));
    waitPageDisappear();
    switchBackToParent();
    return new NewDashboardPage();
  }
  
  
  public NotificationCompactPage openNotificationPanel() {
    $("[id='open-notifications-panel']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='notification-compact-form:notifications-scroller:0:notification-mark-as-read']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    return new NotificationCompactPage();
  }
}
