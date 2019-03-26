package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;

public class StatisticWidgetTest extends BaseTest {

  private static final String TASK_BY_PRIORITY_CHART_NAME = "Task by priority chart";
  private static final String CASE_BY_STATE_CHART_NAME = "Case by state chart";
  private static final String TASK_BY_EXPIRY_CHART_NAME = "Task by expiry chart";
  private static final String ELAPSED_TIME_CHART_NAME = "Elapsed time chart";
  private static final String CASE_BY_FINISHED_TASK_CHART_NAME = "Case by finished task chart";
  private static final String CASE_BY_FINISHED_TIME_CHART_NAME = "Case by finished time chart";

  private HomePage homePage;
  private StatisticWidgetPage statisticWidgetPage;
  private MainMenuPage mainMenuPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();

    homePage = new HomePage();
  }

  @Test
  public void testNavigateToChartFromMenu() {
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    Sleeper.sleepTight(2000);
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    assertTrue(statisticWidgetPage.isFullMode());
  }

  @Test
  public void testCreateCharts() {
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    WebDriverWait wait = new WebDriverWait(statisticWidgetPage.getDriver(), 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("statistics-widget:widget-container")));
    statisticWidgetPage.switchCreateMode();
    
    Sleeper.sleepTight(20000);
    createTaskByPriorityChart();
    createTaskByExpiryChart();
    createCaseByStateChart();
    createElapsedTimeChart();
    createCaseByFinishedTask();
    createCaseByFinishTime();

    statisticWidgetPage.switchCreateMode();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:0:chart-name"), true);

    WebElement taskByPriorityChartName
      = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:0:chart-name");
    WebElement taskByExpiryChartName
      = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:1:chart-name");
    WebElement caseByStateChartName
      = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:2:chart-name");
    WebElement elapsedTimeChartName
      = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:3:chart-name");
    WebElement caseByFinishedTaskChartName
      = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:4:chart-name");
    WebElement caseByFinishedTimeChartName
      = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:5:chart-name");

    assertEquals(TASK_BY_PRIORITY_CHART_NAME, taskByPriorityChartName.getText());
    assertEquals(TASK_BY_EXPIRY_CHART_NAME, taskByExpiryChartName.getText());
    assertEquals(CASE_BY_STATE_CHART_NAME, caseByStateChartName.getText());
    assertEquals(ELAPSED_TIME_CHART_NAME, elapsedTimeChartName.getText());
    assertEquals(CASE_BY_FINISHED_TASK_CHART_NAME, caseByFinishedTaskChartName.getText());
    assertEquals(CASE_BY_FINISHED_TIME_CHART_NAME, caseByFinishedTimeChartName.getText());
  }

  private void createTaskByPriorityChart() {
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link"), true, 30);
    WebElement createTaskByPriorityLink
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-priority-link");
    createTaskByPriorityLink.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();

    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:add-chart-dialog"), true, 30);
    WebElement chartNameInput
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-name-input");
    WebElement createChartButton
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-save-command");

    chartNameInput.sendKeys(TASK_BY_PRIORITY_CHART_NAME);
    createChartButton.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();
  }

  private void createCaseByStateChart() {
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-state-link"), true, 30);
    WebElement createCaseByStateLink
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-state-link");
    createCaseByStateLink.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();

    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:add-chart-dialog"), true, 100);
    WebElement chartNameInput
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-name-input");
    WebElement createChartButton
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-save-command");

    chartNameInput.sendKeys(CASE_BY_STATE_CHART_NAME);
    createChartButton.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();
  }

  private void createTaskByExpiryChart() {
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-expiry-link"), true, 30);
    WebElement createTaskByExpiryLink
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-task-by-expiry-link");
    createTaskByExpiryLink.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();

    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:add-chart-dialog"), true, 30);
    WebElement chartNameInput
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-name-input");
    WebElement createChartButton
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-save-command");
  
    chartNameInput.sendKeys(TASK_BY_EXPIRY_CHART_NAME);
    createChartButton.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();
  }

  private void createElapsedTimeChart() {
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-elapsed-time-link"), true, 30);
    WebElement createElapsedTimeLink
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-elapsed-time-link");
    createElapsedTimeLink.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();

    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:add-chart-dialog"), true, 30);
    WebElement chartNameInput
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-name-input");
    WebElement createChartButton
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-save-command");

    chartNameInput.sendKeys(ELAPSED_TIME_CHART_NAME);
    createChartButton.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();
  }
  
  private void createCaseByFinishedTask() {
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-task-link"), true, 30);
    WebElement createCaseByFinishedTaskLink
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-task-link");
    createCaseByFinishedTaskLink.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();

    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:add-chart-dialog"), true, 30);
    WebElement chartNameInput
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-name-input");
    WebElement createChartButton
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-save-command");

    chartNameInput.sendKeys(CASE_BY_FINISHED_TASK_CHART_NAME);
    createChartButton.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();
  }
  
  private void createCaseByFinishTime() {
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-time-link"), true, 30);
    WebElement createCaseByFinishedTaskLink
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:create-case-by-finished-time-link");
    createCaseByFinishedTaskLink.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();

    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:chart-creation-widget:chart-management-form:add-chart-dialog"), true, 30);
    WebElement chartNameInput
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-name-input");
    WebElement createChartButton
      = statisticWidgetPage.findElementById("statistics-widget:chart-creation-widget:chart-management-form:add-statistic-form:chart-save-command");

    chartNameInput.sendKeys(CASE_BY_FINISHED_TIME_CHART_NAME);
    createChartButton.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();
  }
}
