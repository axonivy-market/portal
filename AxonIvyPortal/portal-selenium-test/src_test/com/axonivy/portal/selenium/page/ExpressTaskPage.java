package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;


import com.codeborne.selenide.SelenideElement;

public class ExpressTaskPage extends TemplatePage {

  public ExpressTaskPage() {
    $(".task-template-container").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement findExpressTask() {
    return $(".js-task-header-container").waitUntil(appear, DEFAULT_TIMEOUT).$("div[id='task-template-title']");
  }
}
