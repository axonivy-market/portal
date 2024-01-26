package com.axonivy.portal.selenium.page.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.appear;

import com.axonivy.portal.selenium.page.TemplatePage;

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
    $("[id='sprotty']").shouldBe(appear, DEFAULT_TIMEOUT);
    $(".sprotty-graph").shouldBe(appear, DEFAULT_TIMEOUT);
  }

}
