package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashboardDetailsEditPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#add-button";
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
    $("div[id$='new-widget-dialog_content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("div.new-widget-dialog__item").filter(text(name)).first().$("tr.ui-widget-content")
        .$("button[id^='new-widget-dialog-content']").shouldBe(getClickableCondition()).click();
  }

  public DashboardConfigurationPage backToConfigurationPage() {
    $("[id='back-to-configuration']").click(0, 0);
    return new DashboardConfigurationPage();
  }

  public void deleteCompactModeProcess() {
    $("[id$=':delete-widget-2']").shouldBe(Condition.appear).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteImageModeProcess() {
    $("button[id$=':process-action-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("[id$=':process-action-menu']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
      .$("span.si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("[id$=':process-action-menu']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
      .$("span.si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
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
  
  public void clickOnRemoveWidgetButton() {
    getRemoveWidgetButton().waitUntil(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
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

  public StatisticEditWidgetNewDashboardPage addNewStatisticWidget() {
    addWidgetByName("Statistic chart");
    return new StatisticEditWidgetNewDashboardPage();
  }

  public StatisticWidgetDashboardPage selectStatisticWidget() {
    return new StatisticWidgetDashboardPage();
  }

  public SelenideElement getTitleByIndex(int index) {
    return $("a[id='dashboard-title-" + index + "']");
  }

  public SelenideElement getIconByIndex(int index, String icon) {
    return $("a[id='dashboard-title-" + index + "'] span." + icon);
  }

  public ElementsCollection getWidgets() {
    return $("div[id='dashboard-body']").$$("div.grid-stack-item");
  }

  public void clickOnRestoreDashboard() {
    $("[id$='restore-button-group']").waitUntil(appear, DEFAULT_TIMEOUT).$("button[id$='restore-button']")
        .waitUntil(Condition.exist, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='restore-confirm-dialog']").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getRestoreDashboardMessage() {
    return $("div[id$='restore-confirm-dialog']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("span.dashboard-template-name-message").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public void restoreDashboardToStandard() {
    $("div[id$='restore-confirm-dialog']").waitUntil(appear, DEFAULT_TIMEOUT).$("button[id$='reset-dashboard-button']")
        .waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getRestoreDashboardButton() {
    return $("[id$='restore-button-group']").waitUntil(appear, DEFAULT_TIMEOUT).$("button[id$='restore-button']")
        .waitUntil(Condition.exist, DEFAULT_TIMEOUT);
  }
}
