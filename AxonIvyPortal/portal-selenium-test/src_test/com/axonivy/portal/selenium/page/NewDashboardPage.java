package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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

  public SelenideElement getStartButton() {
    return $("button[id$=':start-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getProcessImage() {
    return $("img.image-process-item-image").waitUntil(Condition.exist, DEFAULT_TIMEOUT);
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

  public void deleteImageModeProcess() {
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
    getStartButton().click();
  }

  public void startMoreInfoLink() {
    getMoreInformationLink().click();
  }

  // ==================================
  public SelenideElement getDisabledMoreInformationLink() {
    return $("span[id$=':more-information']");
  }

  public SelenideElement getMoreInformationLink() {
    return $("a[id$=':more-information']");
  }

  public ProcessEditWidgetNewDashBoardPage editFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("span.si-graphic-tablet-drawing-pen").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public SelenideElement getFullModeProcessName() {
    return $("span[id$=':process-item:process-name']").shouldBe(Condition.appear);
  }

  public void deleteFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("span.si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='remove-widget-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("button[id='remove-widget-button']").click();
    $("div[id='remove-widget-dialog']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getFullModeProcessContainer() {
    return $(".process-widget.dashboard-widget-panel-container .process-widget--full");
  }

  // ==================================
  public ProcessEditWidgetNewDashBoardPage editCombinedModeProcess() {
    $(".process-grid-item__action--combined .si-pencil").shouldBe(Condition.appear).click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public SelenideElement getCombinedModeProcessName() {
    return $(".process-grid-view__name--combined").shouldBe(Condition.appear);
  }

  public void deleteCombinedModeProcess() {
    $(".process-grid-item__action--combined .si-bin-1").shouldBe(Condition.appear).click();
    $("div[id='remove-widget-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("button[id='remove-widget-button']").click();
    $("div[id='remove-widget-dialog']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCombinedModeProcessContainer() {
    return $(".process-widget.dashboard-widget-panel-container .dashboard-processes-container--combined");
  }

  public void expandCombindedModeProcess() {
    getCombinedModeProcessCollapseLink().shouldBe(Condition.disappear);
    getCombinedModeProcessExpandLink().shouldBe(Condition.appear).click();
    getCombinedModeProcessCollapseLink().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    getCombinedModeProcessExpandLink().shouldBe(Condition.disappear);
  }

  public SelenideElement getCombinedModeProcessExpandLink() {
    return $(".dashboard-processes-container--combined a[id$=':expand-link']");
  }

  public SelenideElement getCombinedModeProcessCollapseLink() {
    return $(".dashboard-processes-container--combined a[id$=':collapse-link']");
  }

  public SelenideElement getCombinedModeProcessFirstTaskStartAction() {
    return $("div[id$=':dashboard-process-tasks-container'] a.start-task-action");
  }

  public SelenideElement getCombinedModeProcessFirstTaskName() {
    return $("div[id$=':dashboard-process-tasks-container'] td.revelent-tasks__name");
  }

  public SelenideElement getCasesTab() {
    return $(".combined-process-widget__button-tabs.last-tab-button");
  }

  public SelenideElement getCombinedModeProcessFirstCaseName() {
    return $("div[id$=':dashboard-process-cases-container'] td.revelent-cases__name");
  }

  // ==================================
  public ElementsCollection getAllWidgetHeaders() {
    return $$("span[id$=':widget__header']");
  }

  public SelenideElement getCompactModeProcessDisabledFirstProcessItemName() {
    return $(".compact-processes-container span.ui-commandlink.process-item span[id$=':process-name-process-item']");
  }
}
