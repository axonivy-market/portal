package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LeaveRequestOverviewPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "[id='leave-request-header']";
  }

  public String getHearText() {
    WebElement element = findElementByCssSelector("[id$='leave-request-header']");
    return element.getText();
  }

  public String getStepName(int index) {
    WebElement element = findElementByCssSelector(String.format("[id$=':%d:process-step']", index));
    return element.getText();
  }

  public LeaveRequestPage start() {
    clickByJavaScript($("button[id$='start']"));
    waitForElementDisplayed(By.cssSelector("[id$='leave-request']"), true);
    return new LeaveRequestPage();
  }
}
