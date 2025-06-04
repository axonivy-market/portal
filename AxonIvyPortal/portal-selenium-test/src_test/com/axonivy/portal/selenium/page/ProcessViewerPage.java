package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProcessViewerPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='portal-process-viewer-form']";
  }

  public String getProcessRequestPath() {
    $("[id='process-viewer-information'").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $("[id$='portal-process-viewer-form'] [id$='request-path']").getText();
  }

  public String getErrorMessage() {
    return findElementByCssSelector(".empty-message-text").getText();
  }

  public void waitForSprottyToolDisplayed() {
    switchToIframeWithId("process-viewer");
    $("[id='sprotty']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $(".sprotty-graph").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void clickOnCloseButton() {
    SelenideElement closeButton = $("[id='close-process']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(closeButton);
  }

}
