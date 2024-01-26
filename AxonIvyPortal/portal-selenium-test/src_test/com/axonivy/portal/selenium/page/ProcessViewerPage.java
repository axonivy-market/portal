package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.appear;


public class ProcessViewerPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "[id='portal-process-viewer-form']";
  }

  public String getProcessRequestPath() {
    $("[id='process-viewer-information'").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("[id$='portal-process-viewer-form'] [id$='request-path']").getText();
  }

  public String getErrorMessage() {
    return findElementByCssSelector(".empty-message-text").getText();
  }

  public void waitForSprottyToolDisplayed() {
    switchToIframeWithId("process-viewer");
    $("[id='sprotty']").shouldBe(appear, DEFAULT_TIMEOUT);
    $(".sprotty-graph").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}
