package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Condition.appear;;

public class ExampleOverviewPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='content-container']";
  }

  public void waitForIFrameContentVisible() {
    $("div[id='content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public LeaveRequestOverviewPage openLeaveRequestOverview() {
    clickByJavaScript($("[id$='0:more-info']"));
    return new LeaveRequestOverviewPage();
  }

  public LendingOverviewPage openLendingOverview() {
    clickByJavaScript($("[id$='1:more-info']"));
    return new LendingOverviewPage();
  }

}
