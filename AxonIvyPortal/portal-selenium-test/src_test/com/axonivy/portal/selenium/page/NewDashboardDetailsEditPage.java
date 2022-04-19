package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashboardDetailsEditPage extends TemplatePage {
  public void waitPageDisplay() {
    $("a.dashboard__title").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void addWidget() {
    $("button[id='add-button']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public TaskEditWidgetNewDashBoardPage addNewTaskWidget() {
    addWidgetByName("Task List");
    return new TaskEditWidgetNewDashBoardPage();
  }

  public CaseEditWidgetNewDashBoardPage addNewCaseWidget() {
    addWidgetByName("Case List");
    return new CaseEditWidgetNewDashBoardPage();
  }

  public ProcessEditWidgetNewDashBoardPage addNewProcessWidget() {
    addWidgetByName("Process List");
    return new ProcessEditWidgetNewDashBoardPage();
  }

  private void addWidgetByName(String name) {
    $("div[id='new-widget-dialog-content_content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("div.new-widget-dialog__item").filter(text(name)).first().$("div.ui-widget-content")
        .$("button[id^='new-widget-dialog-content']").shouldBe(getClickableCondition()).click();
  }

  public NewDashboardConfigurationPage backToConfigurationPage() {
    $("a[id='back-to-configuration']").click();
    NewDashboardConfigurationPage configurationPage = new NewDashboardConfigurationPage();
    configurationPage.waitPageDisplay();
    return configurationPage;
  }

  public void deleteCompactModeProcess() {
    $("a[id$=':delete-widget-2']").shouldBe(Condition.appear).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteImageModeProcess() {
    $("button[id$=':process-action-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("span.si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("span.si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteCombinedModeProcess() {
    try {
      openDeleteCombinedModeProcessDialog();
    } catch (Exception e) {
      openDeleteCombinedModeProcessDialog();
    }
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  private void openDeleteCombinedModeProcessDialog() {
    $(".process-grid-item__action--combined .si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public ProcessEditWidgetNewDashBoardPage editFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("span.si-graphic-tablet-drawing-pen").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    return new ProcessEditWidgetNewDashBoardPage();
  }
  
  private SelenideElement getRemoveWidgetDialog() {
    return $("div[id='remove-widget-dialog']");
  }

  private SelenideElement getRemoveWidgetButton() {
    return $("button[id='remove-widget-button']");
  }

  public SelenideElement getTitleByIndex(int index) {
    return $("a[id='dashboard-title-" + index + "']");
  }

  public ElementsCollection getWidgets() {
    return $("div[id='dashboard-body']").$$("div.grid-stack-item");
  }
}
