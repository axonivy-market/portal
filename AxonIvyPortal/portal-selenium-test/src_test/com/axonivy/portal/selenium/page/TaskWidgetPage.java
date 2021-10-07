package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TaskWidgetPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'task-widget:task-view')]";
  }

  public void openTask(String taskName) {
    $("div[id='task-widget:task-view-container']").waitUntil(appear, DEFAULT_TIMEOUT);
    $$("div[id='task-widget:task-view-container'] ul li div[id$=':task-item:task-start'] div.task-start-info span")
        .filter(text(taskName)).first().click();
  }
}
