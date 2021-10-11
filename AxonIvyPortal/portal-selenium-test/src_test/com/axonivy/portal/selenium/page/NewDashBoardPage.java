package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class NewDashBoardPage extends TemplatePage {

  public CaseWidgetNewDashBoardPage selectCaseWidget(String caseWidgetName) {
    return new CaseWidgetNewDashBoardPage(caseWidgetName);
  }

  public TaskWidgetNewDashBoardPage selectTaskWidget(String taskWidgetName) {
    return new TaskWidgetNewDashBoardPage(taskWidgetName);
  }
  
  public void waitForAbsencesGrowlMessageDisplay() {
    $("div[id='portal-global-growl_container']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("div.ui-growl-message").waitUntil(disappears, DEFAULT_TIMEOUT);
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
  
  public TaskEditWidgetNewDashBoardPage addNewTaskWidget() {
    addWidgetByName("Task List");
    return new TaskEditWidgetNewDashBoardPage();
  }

  public ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration() {
    switchToEditMode();
    $("a[id$=':edit-widget-2']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new ProcessEditWidgetNewDashBoardPage();
  }
  
  public SelenideElement getStartProcessButton() {
    return $("button[id$=':start-button']");
  }
  
  public SelenideElement getProcessButton() {
    return $("button[id$=':start-button']");
  }
  
  public SelenideElement getProcessImage() {
    return $("img.image-process-item-image");
  }
}
