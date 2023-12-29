package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class ExampleOverviewPage extends TemplatePage{

  @Override
  protected String getLoadedLocator() {
    return "[id='content-container']";
  }
  
  public void waitForIFrameContentVisible() {
    $("div[id='content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

}
