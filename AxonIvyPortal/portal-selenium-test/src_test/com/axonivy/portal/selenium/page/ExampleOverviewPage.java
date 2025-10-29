package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class ExampleOverviewPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='content-container']";
  }

  @Override
  public void waitForIFrameContentVisible() {
    $("div[id='content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
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
