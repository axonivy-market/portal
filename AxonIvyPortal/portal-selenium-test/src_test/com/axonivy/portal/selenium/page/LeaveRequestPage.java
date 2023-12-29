package com.axonivy.portal.selenium.page;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class LeaveRequestPage extends TaskTemplateIFramePage {
  
  @Override
  protected String getLoadedLocator() {
    return "div[id='content-container']";
  }

  public void waitForIFrameContentVisible() {
    $("div[id='content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
}
