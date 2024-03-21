package com.axonivy.portal.selenium.page.component;

import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.page.TemplatePage;
import com.codeborne.selenide.Condition;

public class ProcessViewerComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return LAYOUT_WRAPPER;
  }

  public String getProcessRequestPath() {
    return findElementByCssSelector("span[id$=':request-path']").getText();
  }

  public void waitForSprottyToolDisplayed() {
    switchToIframeWithId("process-viewer");
    $("[id='sprotty']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $(".sprotty-graph").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
}
