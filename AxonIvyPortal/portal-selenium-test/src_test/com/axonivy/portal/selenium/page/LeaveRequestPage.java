package com.axonivy.portal.selenium.page;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

public class LeaveRequestPage extends TaskTemplateIFramePage {
  
  @Override
  protected String getLoadedLocator() {
    return "div[id='content-container']";
  }

  public void waitForIFrameContentVisible() {
    $("div[id='content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void fulfillAndSendMaternityLeaveRequest() {
    findElementById("leave-request:from_input").sendKeys("Jun 23, 2023 10:37");
    findElementById("leave-request:to_input").sendKeys("Jun 24, 2023 10:37");
    findElementById("leave-request:fullname").sendKeys("John Doe");
    findElementById("leave-request:substitute").sendKeys("John Wick");
    clickByJavaScript($(By.id("leave-request:button-submit")));
  }
}
