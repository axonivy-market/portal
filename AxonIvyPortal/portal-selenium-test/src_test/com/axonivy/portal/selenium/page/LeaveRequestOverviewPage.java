package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class LeaveRequestOverviewPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "[id='leave-request-header']";
  }

  public SelenideElement getHearText() {
    return findElementByCssSelector("[id$='leave-request-header']");
  }

  public SelenideElement getStepName(int index) {
    return findElementByCssSelector(String.format("[id$=':%d:process-step']", index));
  }

  public LeaveRequestPage start() {
    clickByJavaScript($("button[id$='start']"));
    waitForElementDisplayed(By.cssSelector("[id$='leave-request']"), true);
    switchBackToParent();
    return new LeaveRequestPage();
  }
}
