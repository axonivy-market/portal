package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.SelenideElement;

public class TaskEditWidgetNewDashBoardPage extends TemplatePage {

  private String taskEditWidgetId;

  public TaskEditWidgetNewDashBoardPage() {
    this("div[id='new-widget-configuration-dialog']");
  }

  public TaskEditWidgetNewDashBoardPage(String taskWidgetId) {
    this.taskEditWidgetId = taskWidgetId;
  }

  private SelenideElement widgetTitle() {
    return $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("span[id$='widget-title-group']")
        .$("input[id$='widget-title']");
  }

  public void changeWidgetTitle(String name) {
    widgetTitle().clear();
    widgetTitle().sendKeys(name);
  }
  
  public void save() {
    $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("button[id$='widget-configuration-save-button']")
        .shouldBe(getClickableCondition()).click();
  }

}
