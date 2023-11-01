package portal.guitest.page;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;
import portal.guitest.common.WaitHelper;

public class StatisticWidgetPage extends TemplatePage {
  public static final String TASK_BY_PRIORITY_CHART_NAME = "Task by priority chart";
  public static final String CASE_BY_STATE_CHART_NAME = "Case by state chart";
  public static final String TASK_BY_EXPIRY_CHART_NAME = "Task by expiry chart";
  public static final String ELAPSED_TIME_CHART_NAME = "Elapsed time chart";
  public static final String CASE_BY_FINISHED_TASK_CHART_NAME = "Case by finished task chart";
  public static final String CASE_BY_FINISHED_TIME_CHART_NAME = "Case by finished time chart";
  public static final String CASES_BY_CATEGORY_CHART_NAME = "Cases by category chart";

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
    return 30L;
  }

  public void switchCreateMode() {
    clickByCssSelector("a[id$='create-chart-link']");
    waitForElementDisplayed(By.cssSelector("a[id$='statistics-widget:back-from-chart-creation']"), true);
  }

  public void backToDashboard() {
    clickByCssSelector("a[id$='back-from-chart-creation']");
    waitForElementDisplayed(By.cssSelector("a[id$='create-chart-link']"), true);
  }
  
  public TaskAnalysisWidgetPage navigateToTaskAnalysisPage() {
    String taskAnalysisLinkString = "statistics-widget:task-analysis-page-navigation-link";
    waitForElementDisplayed(By.id(taskAnalysisLinkString), true, DEFAULT_TIMEOUT);
    WebElement taskAnalysisLink = findElementById(taskAnalysisLinkString);

    taskAnalysisLink.click();
    waitForElementDisplayed(By.id("task-widget"), true, DEFAULT_TIMEOUT);
    WaitHelper.assertTrueWithWait(() -> {
      return isElementPresent(By.cssSelector(".js-layout-wrapper.has-breadcrumb"));
    });

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
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> {
      try {
        return findElementByCssSelector(String.format("div[id$='%d:chart-name-container'] .chart-name", chartIndex)).getText().length()>1;
      } catch (WebDriverException e) {
        System.out.println("Exception when waiting for element existed, try again.");
      }
      return false;
    });
    WebElement chartName = findElementByCssSelector(String.format("div[id$='%d:chart-name-container'] .chart-name", chartIndex));
    return chartName.getText();
  }

  public Set<String> getAllChartNames() {
    return findListElementsByCssSelector("div[id$=':chart-name-container'] .chart-name").stream().map(e -> e.getText())
        .collect(Collectors.toSet());
  }

  public String getRestoreDefaultButtonName() {
    return findElementByCssSelector("span[id$='restore-default-chart-link-label']").getText();
  }

  @SuppressWarnings("deprecation")
  public void restoreDefaultCharts() {
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> {
      WebElement restoreDefault = findElementByCssSelector("span[id$='restore-default-chart-link-label']");
      try {
        return restoreDefault.getText().contains("Restore default");
      } catch (WebDriverException e) {
        System.out.println("Exception when waiting for element existed, try again.");
      }
      return false;
    });
    clickByCssSelector("span[id$='restore-default-chart-link-label']");
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.id("statistics-widget:restore-confirmation-dialog"), true, 30);
    WebElement okButton = findElementById("statistics-widget:confirm-restore");
    click(okButton);
    waitAjaxIndicatorDisappear();
  }

  private void inputNameForSupportedLanguages(String value) {
    for (int i = 0; i < 4; i++) {
      findElementByCssSelector("input[id$='" + i + ":chart-name-input']").sendKeys(value);
    }
  }

  @SuppressWarnings("deprecation")
  public void createTaskByPriorityChart() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link"), true, 30);
    click(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link"));
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    inputNameForSupportedLanguages(TASK_BY_PRIORITY_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");
    waitAjaxIndicatorDisappear();
    waitForElementExisted("span[class='ui-growl-title']", true, DEFAULT_TIMEOUT);
  }

  @SuppressWarnings("deprecation")
  public void createTaskByPriorityChartMultiLanguage() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link"), true, 30);
    click(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link"));
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);

    findElementByCssSelector("input[id$='0:chart-name-input']").sendKeys(TASK_BY_PRIORITY_CHART_NAME.concat(" English"));
    findElementByCssSelector("input[id$='1:chart-name-input']").sendKeys(TASK_BY_PRIORITY_CHART_NAME.concat(" French"));
    findElementByCssSelector("input[id$='2:chart-name-input']").sendKeys(TASK_BY_PRIORITY_CHART_NAME.concat(" German"));
    findElementByCssSelector("input[id$='3:chart-name-input']").sendKeys(TASK_BY_PRIORITY_CHART_NAME.concat(" Spanish"));

    clickByCssSelector("button[id$='chart-save-command']");
    waitAjaxIndicatorDisappear();
    waitForElementExisted("span[class='ui-growl-title']", true, DEFAULT_TIMEOUT);
  }
  

  @SuppressWarnings("deprecation")
  public void createCaseByStateChart() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-state-link"), true, 30);
    WebElement createCaseByStateLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-state-link");
    click(createCaseByStateLink);
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);

    inputNameForSupportedLanguages(CASE_BY_STATE_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");

    waitAjaxIndicatorDisappear();
    waitForElementExisted("span[class='ui-growl-title']", true, DEFAULT_TIMEOUT);
  }

  @SuppressWarnings("deprecation")
  public void createTaskByExpiryChart() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-expiry-link"), true, 30);
    WebElement createTaskByExpiryLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-expiry-link");
    click(createTaskByExpiryLink);
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    inputNameForSupportedLanguages(TASK_BY_EXPIRY_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");

    waitAjaxIndicatorDisappear();
    waitForElementExisted("span[class='ui-growl-title']", true, DEFAULT_TIMEOUT);
  }

  @SuppressWarnings("deprecation")
  public void createElapsedTimeChart() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-elapsed-time-link"), true, 30);
    WebElement createElapsedTimeLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-elapsed-time-link");
    click(createElapsedTimeLink);
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    inputNameForSupportedLanguages(ELAPSED_TIME_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");

    waitAjaxIndicatorDisappear();
    waitForElementExisted("span[class='ui-growl-title']", true, DEFAULT_TIMEOUT);
  }

  public void createCaseByFinishedTask() {
    var caseByFinishedTaskSelector = "button[id$=':chart-management-form:create-case-by-finished-task-link']";
    waitForElementDisplayed(By.cssSelector(caseByFinishedTaskSelector), true);
    clickByCssSelector(caseByFinishedTaskSelector);
    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT,
        "statistics-widget\\:chart-creation-widget\\:add-statistic-form\\:chart-name-list\\:0\\:chart-name-input",
        CLASS_PROPERTY);

    inputNameForSupportedLanguages(CASE_BY_FINISHED_TASK_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");
    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), false);
    waitForElementExisted("span[class='ui-growl-title']", true, DEFAULT_TIMEOUT);
  }

  @SuppressWarnings("deprecation")
  public void createCaseByFinishTime() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-time-link"), true, 30);
    WebElement createCaseByFinishedTaskLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-time-link");
    click(createCaseByFinishedTaskLink);
    waitAjaxIndicatorDisappear();

    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    inputNameForSupportedLanguages(CASE_BY_FINISHED_TIME_CHART_NAME);
    clickByCssSelector("button[id$='chart-save-command']");

    waitAjaxIndicatorDisappear();
    waitForElementExisted("span[class='ui-growl-title']", true, DEFAULT_TIMEOUT);
  }
  
  public void createCasesByCategory() {
	    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-cases-by-category-link"), true, 30);
	    WebElement createCaseByFinishedTaskLink
	      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-cases-by-category-link");
	    click(createCaseByFinishedTaskLink);

	    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
	    inputNameForSupportedLanguages(CASES_BY_CATEGORY_CHART_NAME);
	    clickByCssSelector("button[id$='chart-save-command']");

	    waitForElementExisted("span[class='ui-growl-title']", true, DEFAULT_TIMEOUT);
	  }
  
  public WebElement getChartCreationContainer() {
    return findElementById("statistics-widget:chart-creation-widget:chart-management-form:chart-list");
  }

  @SuppressWarnings("deprecation")
  public WebElement getCaseByFinishedTaskCreationDialog() {
    waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-task-link"), true, 30);
    WebElement createCaseByFinishedTaskLink
      = findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-task-link");
    click(createCaseByFinishedTaskLink);
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.cssSelector("div[id$='add-chart-dialog']"), true);
    inputNameForSupportedLanguages(CASE_BY_FINISHED_TASK_CHART_NAME);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "statistics-widget\\\\:chart-creation-widget\\\\:add-statistic-form\\\\:chart-name-list\\\\:3\\\\:chart-name-input", "id");
    return findElementByCssSelector("div[id$='add-chart-dialog']");
  }
  
  public void waitForChartCreationPageRendered() {
    waitForElementDisplayed(By.id("statistics-widget:back-from-chart-creation"), true);
  }

  @SuppressWarnings("deprecation")
  public void waitForAllChartLoaded() {
    ensureNoBackgroundRequest();
    Sleeper.sleep(5000);//wait for last chart animation finish
  }
  
  public WebElement getChartPanelByIndex(int index) {
    return findElementByCssSelector(String.format("span[id$='%d:chart-panel']", index));
  }
  
  public WebElement getChartInfoDialogOfChart(int chartIndex) {
    List<WebElement> chartInfoIcons = findListElementsByClassName("chart-info"); 
    click(chartInfoIcons.get(chartIndex));
    waitForElementDisplayed(By.id("statistics-widget:statistic-dashboard-widget:chart-details-dialog"), true);
    return findElementById("statistics-widget:statistic-dashboard-widget:chart-details-dialog");
  }
}
