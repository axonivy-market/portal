package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.Sleeper;

public class StatisticWidgetPage extends TemplatePage {
  public static final String TASK_BY_PRIORITY_CHART_NAME = "Task by priority chart";
  public static final String CASE_BY_STATE_CHART_NAME = "Case by state chart";
  public static final String TASK_BY_EXPIRY_CHART_NAME = "Task by expiry chart";
  public static final String ELAPSED_TIME_CHART_NAME = "Elapsed time chart";
  public static final String CASE_BY_FINISHED_TASK_CHART_NAME = "Case by finished task chart";
  public static final String CASE_BY_FINISHED_TIME_CHART_NAME = "Case by finished time chart";

  public StatisticWidgetPage() {
    Sleeper.sleep(1000);
    waitForPageLoaded();
    waitForElementDisplayed(By.id("statistics-widget"), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "id('statistics-widget')";
  }

  
  @Override
  protected long getTimeOutForLocator() {
    return 300L;
  }

  public void switchCreateMode() {
    clickByCssSelector("a[id$='create-chart-link']");
  }

  public void backToDashboard() {
    clickByCssSelector("a[id$='back-from-chart-creation']");
  }
  
  public TaskAnalysisWidgetPage navigateToTaskAnalysisPage() {
    String taskAnalysisLinkString = "statistics-widget:task-analysis-page-navigation-link";
    waitForElementDisplayed(By.id(taskAnalysisLinkString), true, DEFAULT_TIMEOUT);
    WebElement taskAnalysisLink = findElementById(taskAnalysisLinkString);

    taskAnalysisLink.click();
    waitForElementDisplayed(By.id("task-widget"), true, DEFAULT_TIMEOUT);

    return new TaskAnalysisWidgetPage();
  }

  private boolean isCompactMode() {
    waitForPageLoaded();
    waitForElementDisplayed(By.id("statistics-widget:widget-container"), true, DEFAULT_TIMEOUT);
    WebElement statisticContainer = findElementById("statistics-widget:widget-container");
    return statisticContainer.getAttribute(CLASS_PROPERTY).contains("compact-mode");
  }

  public boolean isFullMode() {
    return !isCompactMode();
  }
  public boolean hasCreateChartsLink(){
    return isElementPresent(By.id("statistics-widget:create-chart-link-label"));
  }

  public String getChartName(int chartIndex) {
    return findElementByCssSelector(String.format("div[id$='%d:chart-name-container'] #chart-name", chartIndex)).getText();
  }
  
  public String getRestoreDefaultButtonName() {
    return findElementByCssSelector("span[id$='restore-default-chart-link-label']").getText();
  }

  public void restoreDefaultCharts() {
    clickByCssSelector("span[id$='restore-default-chart-link-label']");
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.id("statistics-widget:restore-confirmation-dialog"), true, 30);
    WebElement okButton = findElementById("statistics-widget:confirm-restore");
    okButton.click();
    waitAjaxIndicatorDisappear();
  }

  public void createTaskByPriorityChart() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link"), true, 30);
    WebElement createTaskByPriorityLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link");
    createTaskByPriorityLink.click();
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    findElementByCssSelector("input[id$='chart-name-input']").sendKeys(TASK_BY_PRIORITY_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");
    waitAjaxIndicatorDisappear();
  }

  public void createCaseByStateChart() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-state-link"), true, 30);
    WebElement createCaseByStateLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-state-link");
    createCaseByStateLink.click();
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    findElementByCssSelector("input[id$='chart-name-input']").sendKeys(CASE_BY_STATE_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");

    waitAjaxIndicatorDisappear();
  }

  public void createTaskByExpiryChart() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-expiry-link"), true, 30);
    WebElement createTaskByExpiryLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-expiry-link");
    createTaskByExpiryLink.click();
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    findElementByCssSelector("input[id$='chart-name-input']").sendKeys(TASK_BY_EXPIRY_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");

    waitAjaxIndicatorDisappear();
  }

  public void createElapsedTimeChart() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-elapsed-time-link"), true, 30);
    WebElement createElapsedTimeLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-elapsed-time-link");
    createElapsedTimeLink.click();
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    findElementByCssSelector("input[id$='chart-name-input']").sendKeys(ELAPSED_TIME_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");

    waitAjaxIndicatorDisappear();
  }
  
  public void createCaseByFinishedTask() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-task-link"), true, 30);
    WebElement createCaseByFinishedTaskLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-task-link");
    createCaseByFinishedTaskLink.click();
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    findElementByCssSelector("input[id$='chart-name-input']").sendKeys(CASE_BY_FINISHED_TASK_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");

    waitAjaxIndicatorDisappear();
  }
  
  public void createCaseByFinishTime() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-time-link"), true, 30);
    WebElement createCaseByFinishedTaskLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-time-link");
    createCaseByFinishedTaskLink.click();
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    findElementByCssSelector("input[id$='chart-name-input']").sendKeys(CASE_BY_FINISHED_TIME_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");

    waitAjaxIndicatorDisappear();
  }

}
