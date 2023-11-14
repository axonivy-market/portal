package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashboardDetailsEditPage extends TemplatePage {

  public static final String TASK_WIDGET = "Task List";
  public static final String CASE_WIDGET = "Case List";
  public static final String PROCESS_WIDGET = "Process List";
  public static final String PROCESS_VIEWER_WIDGET = "Process Viewer";
  public static final String CUSTOM_WIDGET = "Custom Widget";
  public static final String STATISTIC_WIDGET = "Statistic chart";
  public static final String WELCOME_WIDGET = "Welcome widget";
  public static final String NEWS_WIDGET = "News feed widget";
  public static final String TASK_BY_PRIORITY = "Tasks by Priority";
  public static final String TASK_GROUPED_BY_PRIORITY = "Tasks grouped by expiry";
  public static final String NUMBER_OF_OPEN_TASKS = "Number of OPEN Tasks";
  public static final String COMPLETED_TASKS_PER_DAY = "Completed Tasks per Day";
  public static final String STARTED_CASES_PER_DAY = "Started Cases per Day";

  @Override
  protected String getLoadedLocator() {
    return "#add-button";
  }

  public WebElement addWidget() {
    $("button[id='add-button']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return $("div[id$='new-widget-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void collapseStandardWidgets() {
    $(".ui-fieldset-legend.ui-corner-all.ui-state-default").click();
    $(".ui-fieldset-content").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
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
  
  public ProcessViewerWidgetNewDashBoardPage addNewProcessViewerWidget() {
    addWidgetByName("Process Viewer");
    return new ProcessViewerWidgetNewDashBoardPage();
  }

  public CustomWidgetNewDashBoardPage addNewCustomrWidget(String processName) {
    addCustomWidgetByName(processName);
    return new CustomWidgetNewDashBoardPage();
  }

  public CustomWidgetNewDashBoardPage addExternalPageWidget() {
    addWidgetByName("External Page");
    return new CustomWidgetNewDashBoardPage();
  }

  public void addWidgetByName(String name) {
    $("[id='search-input']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(name);
    $("div[id$='new-widget-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("div.new-widget-dialog__item").filter(text(name)).first()
        .$("button[id^='new-widget-dialog-content']").shouldBe(getClickableCondition()).click();
  }

  public void addStatisticWidgetByName(String name) {
    $("[id='search-input']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(name);
    $("div[id$='new-widget-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT).$$("div.new-widget-dialog__item").filter(text(name)).first()
        .$("button[id^='new-statistic-widget-dialog-content']").shouldBe(getClickableCondition()).click();
    $("[id='new-widget-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  private void addCustomWidgetByName(String name) {
    $("div[id$='new-widget-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT).$$("div.new-widget-dialog__item")
        .filter(text(name)).first().$("button[id^='new-custom-widget-dialog-content']")
        .shouldBe(getClickableCondition()).click();
  }

  public DashboardConfigurationPage backToConfigurationPage() {
    clickByJavaScript($("[id='back-to-configuration']"));
    return new DashboardConfigurationPage();
  }

  public void deleteCompactModeProcess() {
    $("[id$=':delete-widget-2']").shouldBe(Condition.appear).click();
    getRemoveWidgetDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteImageModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("[id$=':process-action-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
      .$("span.si-bin-1").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("[id$=':process-action-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
      .$("span.si-bin-1").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void deleteCombinedModeProcess() {
    try {
      openDeleteCombinedModeProcessDialog();
    } catch (Exception e) {
      openDeleteCombinedModeProcessDialog();
    }
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void clickOnRemoveWidgetButton() {
    getRemoveWidgetButton().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  private void openDeleteCombinedModeProcessDialog() {
    $(".process-grid-item__action--combined .si-bin-1").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public ProcessEditWidgetNewDashBoardPage editFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("[id$=':process-item:grid-process-action-component:edit-process']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("span.si-graphic-tablet-drawing-pen").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
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
    $("[id$='restore-button-group']").shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='restore-button']")
        .shouldBe(Condition.enabled, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='restore-confirm-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getRestoreDashboardMessage() {
    return $("div[id$='restore-confirm-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("span.dashboard-template-name-message").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void restoreDashboardToStandard() {
    $("div[id$='restore-confirm-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='reset-dashboard-button']")
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getRestoreDashboardButton() {
    return $("[id$='restore-button-group']").shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='restore-button']")
        .shouldBe(Condition.exist, DEFAULT_TIMEOUT);
  }

  public DashboardNewsWidgetConfigurationPage addNewsFeedWidget() {
    addWidgetByName("News feed widget");
    return new DashboardNewsWidgetConfigurationPage();
  }

  public void waitForCaseWidgetLoaded() {
    $("div[id$='dashboard-cases-container']").shouldBe(appear, DEFAULT_TIMEOUT)
      .$("div[id$='dashboard-cases']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void clickToAddClientStatisticWidget() {
    $("button[id$='new-statistic-widget-dialog-content:0:add-widget']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    
  }

  public ElementsCollection countStatisticCharts() {
    return $("div[id='dashboard-body']").$$(".statistic-chart-widget__chart");
  }

  public SelenideElement getStatisticWidgetConfigurationDialog() {
    $("[id$=':case-component:dashboard-cases']").shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement statisticElement = $("div[id^='client-statistic-client_statistic']").shouldBe(appear, DEFAULT_TIMEOUT).scrollTo();
    $("canvas").shouldBe(appear, DEFAULT_TIMEOUT);
    return statisticElement;
  }
}
