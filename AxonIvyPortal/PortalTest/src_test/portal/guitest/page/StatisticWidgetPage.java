package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StatisticWidgetPage extends TemplatePage {
  private WebElement statisticWidget;
  private final static String TASK_MENU_ID = "main-menu-container:main-menu-form:main-menu-container_node_1";

  public static final String TASK_BY_PRIORITY_CHART_NAME = "Task by priority chart";
  public static final String CASE_BY_STATE_CHART_NAME = "Case by state chart";
  public static final String TASK_BY_EXPIRY_CHART_NAME = "Task by expiry chart";
  public static final String ELAPSED_TIME_CHART_NAME = "Elapsed time chart";
  public static final String CASE_BY_FINISHED_TASK_CHART_NAME = "Case by finished task chart";
  public static final String CASE_BY_FINISHED_TIME_CHART_NAME = "Case by finished time chart";

  public StatisticWidgetPage() {
    WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    Sleeper.sleepTight(1000);
    waitForPageLoaded();
    waitForElementDisplayed(By.id("statistics-widget"), true);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("statistics-widget")));
    statisticWidget = findElementById("statistics-widget");
  }

  @Override
  protected String getLoadedLocator() {
    return "id('statistics-widget')";
  }

  
  @Override
  protected long getTimeOutForLocator() {
    return 300L;
  }

  public WebElement getStatisticWidget() {
    return statisticWidget;
  }

  public boolean isTaskMenuOpen() {
    return isElementDisplayed(By.id(TASK_MENU_ID + "_0"));
  }

  public void switchMode() {
    WebElement switchLink = findElementById("statistics-widget:statistic-link:statistic-link");
    switchLink.click();
  }

  public void switchCreateMode() {
    clickByCssSelector("a[id$='create-chart-link']");
  }

  public void backToDashboard() {
    clickByCssSelector("button[id$='back-from-chart-creation']");
  }
  
  public TaskAnalysisWidgetPage navigateToTaskAnalysisPage() {
    String taskAnalysisLinkString = "statistics-widget:task-analysis-page-navigation-link";
    waitForElementDisplayed(By.id(taskAnalysisLinkString), true, DEFAULT_TIMEOUT);
    WebElement taskAnalysisLink = findElementById(taskAnalysisLinkString);

    taskAnalysisLink.click();
    waitForElementDisplayed(By.id("task-widget"), true, DEFAULT_TIMEOUT);

    return new TaskAnalysisWidgetPage();
  }

  public boolean isCompactMode() {
    waitForPageLoaded();
    waitForElementDisplayed(By.id("statistics-widget:widget-container"), true, DEFAULT_TIMEOUT);
    WebElement statisticContainer = findElementById("statistics-widget:widget-container");
    return statisticContainer.getAttribute(CLASS_PROPERTY).contains("compact-mode");
  }

  public boolean isFullMode() {
    return !isCompactMode();
  }

  public boolean isCreateMode() {
    waitForElementDisplayed(By.id("statistics-widget:chart-list-container"), true, DEFAULT_TIMEOUT);
    return isElementPresent(By.id("statistics-widget:chart-list-container"));
  }
  
  public boolean hasCreateChartsLink(){
    return isElementPresent(By.id("statistics-widget:create-chart-link-label"));
  }

  public boolean hasTaskAnalysisLink() {
    return isElementPresent(By.id("statistics-widget:task-analysis-page-navigation-link"));
  }

  public String getChartName(int chartIndex) {
    return findElementByCssSelector(String.format("div[id$='%d:chart-name-container'] #chart-name", chartIndex)).getText();
  }
  
  public String getRestoreDefaultButtonName() {
    return findElementByCssSelector("span[id$='restore-default-chart-link-label']").getText();
  }

  public void restoreDefaultCharts() {
    findElementByCssSelector("span[id$='restore-default-chart-link-label']").click();
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
