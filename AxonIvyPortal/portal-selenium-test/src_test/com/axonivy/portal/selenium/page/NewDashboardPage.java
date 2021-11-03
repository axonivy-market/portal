package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class NewDashboardPage extends TemplatePage {

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
    return $("button[id$=':start-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getProcessImage() {
    return $("img.image-process-item-image").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public ProcessEditWidgetNewDashBoardPage editImageProcess() {
    $("button[id$=':process-action-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("span.si-graphic-tablet-drawing-pen").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    return new ProcessEditWidgetNewDashBoardPage();
  }
  
  public SelenideElement getProcessItemName() {
    return $("span[id$=':process-item-name']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void deleteImageProcess() {
    $("button[id$=':process-action-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("span.si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='remove-widget-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("button[id='remove-widget-button']").click();
    $("div[id='remove-widget-dialog']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getImageContainer() {
    return $("div.image-process-container");
  }
  
  public void switchToViewMode() {
    $("a[id='switch-to-view-mode']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("button[id='switch-to-edit-mode']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void startProcess() {
    getStartProcessButton().click();
  }
  
  public void startMoreInfoLink() {
    $("a[id$='more-information']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
  }
}
