package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class ExpressTaskPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".task-template-container";
  }

  public SelenideElement findExpressTask() {
    return $(".js-task-header-container").waitUntil(appear, DEFAULT_TIMEOUT).$("div[id='task-template-title']");
  }

}
