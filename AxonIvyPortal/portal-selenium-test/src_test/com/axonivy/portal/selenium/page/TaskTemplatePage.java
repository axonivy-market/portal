package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskTemplatePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#content-container";
  }

  public SelenideElement getDisplayedTaskTitle() {
    return getTaskTitle().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getTaskTitle() {
    return $("span[id='title']");
  }

  public SelenideElement getStartedTaskTemplateTitle() {
    return $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }


  public SelenideElement getElementInPortalIFramTask(String cssSelector) {
    WaitHelper.waitForIFrameAvailable(WebDriverRunner.getWebDriver(), "iFrame");
    return $(cssSelector);
  }
}
