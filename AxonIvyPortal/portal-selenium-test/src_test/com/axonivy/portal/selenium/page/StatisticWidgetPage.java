package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

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
    waitForPageLoad();
    var taskAnalysLink = $("a[id='statistics-widget:task-analysis-page-navigation-link']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    WaitHelper.waitForNavigation(() -> waitForElementClickableThenClick(taskAnalysLink));
    $("[id='task-widget']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return new TaskAnalysisWidgetPage();
  }

  public Set<String> getAllChartNames() {
    $(By.id("chart-name-0")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $$("div[id$=':chart-name-container'] .chart-name").shouldHave(size(1), DEFAULT_TIMEOUT).asFixedIterable()
        .stream().map(e -> e.getText()).collect(Collectors.toSet());
  }

  public String getRestoreDefaultButtonName() {
    return findElementByCssSelector("span[id$='restore-default-chart-link-label']").getText();
  }

  public boolean hasCreateChartsLink() {
    return isElementPresent(By.id("statistics-widget:create-chart-link-label"));
  }

  private boolean isCompactMode() {
    waitPageLoaded();
    waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    WebElement statisticContainer = findElementById("statistics-widget:widget-container");
    return statisticContainer.getAttribute(CLASS_PROPERTY).contains("compact-mode");
  }

  public boolean isFullMode() {
    return !isCompactMode();
  }

  public void createCaseByStateChart() {
    waitForElementDisplayed(
        By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-state-link"), true, 30);
    SelenideElement createCaseByStateLink =
        findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-state-link");
    waitForElementClickableThenClick(createCaseByStateLink);

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);

    inputNameForSupportedLanguages(CASE_BY_STATE_CHART_NAME);
    waitForElementClickableThenClick("button[id$='chart-save-command']");

    waitForElementExisted(By.cssSelector("span[class='ui-growl-title']"), true);
  }

  public void createCaseByFinishTime() {
    waitForElementDisplayed(
        By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-time-link"), true,
        30);
    SelenideElement createCaseByFinishedTaskLink = findElementById(
        "statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-time-link");
    waitForElementClickableThenClick(createCaseByFinishedTaskLink);

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    inputNameForSupportedLanguages(CASE_BY_FINISHED_TIME_CHART_NAME);
    waitForElementClickableThenClick("button[id$='chart-save-command']");

    waitForElementExisted(By.cssSelector("span[class='ui-growl-title']"), true);
  }

  public void createTaskByPriorityChartMultiLanguage() {
    waitForElementDisplayed(
        By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link"), true, 30);
    waitForElementClickableThenClick(
        $(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link")));

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);

    findElementByCssSelector("input[id$='0:chart-name-input']")
        .sendKeys(TASK_BY_PRIORITY_CHART_NAME.concat(" English"));
    findElementByCssSelector("input[id$='1:chart-name-input']").sendKeys(TASK_BY_PRIORITY_CHART_NAME.concat(" French"));
    findElementByCssSelector("input[id$='2:chart-name-input']").sendKeys(TASK_BY_PRIORITY_CHART_NAME.concat(" German"));
    findElementByCssSelector("input[id$='3:chart-name-input']")
        .sendKeys(TASK_BY_PRIORITY_CHART_NAME.concat(" Spanish"));

    waitForElementClickableThenClick("button[id$='chart-save-command']");
    waitForElementExisted(By.cssSelector("span[class='ui-growl-title']"), true);
  }

  public void createCasesByCategory() {
    waitForElementDisplayed(
        By.id("statistics-widget:chart-creation-widget:chart-management-form:create-cases-by-category-link"), true, 30);
    SelenideElement createCaseByFinishedTaskLink =
        findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-cases-by-category-link");
    waitForElementClickableThenClick(createCaseByFinishedTaskLink);

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    inputNameForSupportedLanguages(CASES_BY_CATEGORY_CHART_NAME);
    waitForElementClickableThenClick("button[id$='chart-save-command']");

    waitForElementExisted(By.cssSelector("span[class='ui-growl-title']"), true);
  }

  public void createElapsedTimeChart() {
    waitForElementDisplayed(
        By.id("statistics-widget:chart-creation-widget:chart-management-form:create-elapsed-time-link"), true, 30);
    SelenideElement createElapsedTimeLink =
        findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-elapsed-time-link");
    waitForElementClickableThenClick(createElapsedTimeLink);

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    inputNameForSupportedLanguages(ELAPSED_TIME_CHART_NAME);
    waitForElementClickableThenClick("button[id$='chart-save-command']");

    waitForElementExisted(By.cssSelector("span[class='ui-growl-title']"), true);
  }

  public void createCaseByFinishedTask() {
    var caseByFinishedTaskSelector = "button[id$=':chart-management-form:create-case-by-finished-task-link']";
    waitForElementClickableThenClick($(caseByFinishedTaskSelector));
    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);

    inputNameForSupportedLanguages(CASE_BY_FINISHED_TASK_CHART_NAME);
    waitForElementClickableThenClick($(By.cssSelector("button[id$='chart-save-command']")));
    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), false);
    waitForElementExisted(By.cssSelector("span[class='ui-growl-title']"), true);
  }

  public void restoreDefaultCharts() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until((webDriver) -> {
      WebElement restoreDefault = findElementByCssSelector("span[id$='restore-default-chart-link-label']");
      try {
        return restoreDefault.getText().contains("Restore default");
      } catch (WebDriverException e) {
        System.out.println("Exception when waiting for element existed, try again.");
      }
      return false;
    });
    waitForElementClickableThenClick($("span[id$='restore-default-chart-link-label']"));
    waitForElementDisplayed(By.id("statistics-widget:restore-confirmation-dialog"), true, 30);
    waitForElementClickableThenClick($(By.id("statistics-widget:confirm-restore")));
  }

  public String getChartName(int chartIndex) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until((webDriver) -> {
      try {
        return findElementByCssSelector(String.format("div[id$='%d:chart-name-container'] .chart-name", chartIndex))
            .getText().length() > 1;
      } catch (WebDriverException e) {
        System.out.println("Exception when waiting for element existed, try again.");
      }
      return false;
    });
    WebElement chartName =
        findElementByCssSelector(String.format("div[id$='%d:chart-name-container'] .chart-name", chartIndex));
    return chartName.getText();
  }

  public void switchCreateMode() {
    $("a[id$='create-chart-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("a[id$='statistics-widget:back-from-chart-creation']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitForAllChartLoaded() {
    $("[id$='statistics-widget:chart-creation-widget:chart-management-form:statistic-filter:statistic-filter-accordion-panel:period-time-filter-tab_header']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id$='statistics-widget:chart-creation-widget:chart-management-form:statistic-filter:statistic-filter-accordion-panel:period-time-filter-tab_header']").shouldBe(getClickableCondition()).click();
    $("[id$='statistics-widget:chart-creation-widget:chart-management-form:statistic-filter:statistic-filter-accordion-panel:role-filter-tab_header']").shouldBe(getClickableCondition()).click();
    $("[id$='statistics-widget:chart-creation-widget:chart-management-form:statistic-filter:statistic-filter-accordion-panel:case-category-filter-tab_header']").shouldBe(getClickableCondition()).click();
    $("[id$='statistics-widget:chart-creation-widget:chart-management-form:statistic-filter:statistic-filter-accordion-panel:case-state-filter-tab_header']").shouldBe(getClickableCondition()).click();
    $("[id$='statistics-widget:chart-creation-widget:chart-management-form:statistic-filter:statistic-filter-accordion-panel:task-priority-filter-tab_header']").shouldBe(getClickableCondition()).click();
  }

  public void createTaskByPriorityChart() {
    $("[id='statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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
    $("[id='"
        + String.format("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:%d:chart-name-container",
            chartIndex)
        + "']").shouldBe(appear, DEFAULT_TIMEOUT).$("a.chart-info").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
            .click();
    return $("[id='statistics-widget:statistic-dashboard-widget:chart-details-dialog']").shouldBe(appear,
        DEFAULT_TIMEOUT);
  }
  
  public void clickChartInfoAndCloseToWaitAnimation() {
    getChartInfoDialogOfChart(0);
    pressESC();
    
    getChartInfoDialogOfChart(0);
    pressESC();
  }
  
}
