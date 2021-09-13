package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class NewDashBoardPage extends TemplatePage {

  public CaseWidgetNewDashBoardPage selectCaseWidget(String caseWidgetName) {
    return new CaseWidgetNewDashBoardPage(caseWidgetName);
  }

  public TaskWidgetNewDashBoardPage selectTaskWidget(String taskWidgetName) {
    return new TaskWidgetNewDashBoardPage(taskWidgetName);
  }

  public void switchToEditMode() {
    $("button[id='switch-to-edit-mode']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("a[id='switch-to-view-mode']").waitUntil(appear, DEFAULT_TIMEOUT).should(appear);
  }

  public void addWidget() {
    $("button[id='add-button']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  private void addWidgetByName(String name) {
    $("div[id='new-widget-dialog-content_content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("div.new-widget-dialog__item").filter(text(name)).first().$("div.ui-widget-content")
        .$("button[id^='new-widget-dialog-content']").shouldBe(getClickableCondition()).click();
  }

  public CaseEditWidgetNewDashBoardPage addNewCaseWidget() {
    addWidgetByName("Case List");
    return new CaseEditWidgetNewDashBoardPage();
  }

}
