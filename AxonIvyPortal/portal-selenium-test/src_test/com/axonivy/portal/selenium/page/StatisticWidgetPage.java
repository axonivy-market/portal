package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.Sleeper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class StatisticWidgetPage extends TemplatePage {

  public static final String TASK_BY_PRIORITY_CHART_NAME = "Task by priority chart";
  public static final String CASE_BY_STATE_CHART_NAME = "Case by state chart";
  public static final String TASK_BY_EXPIRY_CHART_NAME = "Task by expiry chart";
  public static final String ELAPSED_TIME_CHART_NAME = "Elapsed time chart";
  public static final String CASE_BY_FINISHED_TASK_CHART_NAME = "Case by finished task chart";
  public static final String CASE_BY_FINISHED_TIME_CHART_NAME = "Case by finished time chart";
  public static final String CASES_BY_CATEGORY_CHART_NAME = "Cases by category chart";

  @Override
  protected String getLoadedLocator() {
    return "[id='statistics-widget']";
  }

  public TaskAnalysisWidgetPage navigateToTaskAnalysisPage() {
    var taskAnalysLink = $("a[id='statistics-widget:task-analysis-page-navigation-link']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition());
    WaitHelper.waitForNavigation(() -> taskAnalysLink.click());
    $("[id='task-widget']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return new TaskAnalysisWidgetPage();
  }

  public void switchCreateMode() {
    $("a[id$='create-chart-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("a[id$='statistics-widget:back-from-chart-creation']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitForAllChartLoaded() {
    Sleeper.sleep(5000);//wait for last chart animation finish
  }

  public void createTaskByPriorityChart() {
    $("[id='statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='add-chart-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);

    inputNameForSupportedLanguages(TASK_BY_PRIORITY_CHART_NAME);
    $("button[id$='chart-save-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("span[class='ui-growl-title']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private void inputNameForSupportedLanguages(String value) {
    for (int i = 0; i < 4; i++) {
      $("input[id$='" + i + ":chart-name-input']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).sendKeys(value);
    }
  }

  public void createTaskByExpiryChart() {
    $("[id='statistics-widget:chart-creation-widget:chart-management-form:create-task-by-expiry-link']")
      .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    $("div[id$='add-chart-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    inputNameForSupportedLanguages(TASK_BY_EXPIRY_CHART_NAME);
    $("button[id$='chart-save-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    $("span[class='ui-growl-title']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getChartCreationContainer() {
    return $("[id='statistics-widget:chart-creation-widget:chart-management-form:chart-list']");
  }

  public WebElement waitAndGetChartCreationContainer() {
    return getChartCreationContainer().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getCaseByFinishedTaskCreationDialog() {
    $("[id='statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-task-link']")
      .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$='add-chart-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    
    inputNameForSupportedLanguages(CASE_BY_FINISHED_TASK_CHART_NAME);
    return $("div[id$='add-chart-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitForChartCreationPageRendered() {
    $("[id='statistics-widget:back-from-chart-creation']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void backToDashboard() {
    $("a[id$='back-from-chart-creation']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("a[id$='create-chart-link']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getChartPanelByIndex(int index) {
    return $(String.format("span[id$='%d:chart-panel']", index));
  }

  public WebElement waitAndGetChartPanelByIndex(int index) {
    return getChartPanelByIndex(index).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getChartInfoDialogOfChart(int chartIndex) {
    $("[id='" + String.format("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:%d:chart-name-container", chartIndex) + "']")
      .shouldBe(appear, DEFAULT_TIMEOUT).$("a.chart-info").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("[id='statistics-widget:statistic-dashboard-widget:chart-details-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}