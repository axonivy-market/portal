package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class TaskTemplatePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('content-container')";
  }

  public SelenideElement getDisplayedTaskTitle() {
    return getTaskTitle().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getTaskTitle() {
    return $("span[id='title']");
  }
}
