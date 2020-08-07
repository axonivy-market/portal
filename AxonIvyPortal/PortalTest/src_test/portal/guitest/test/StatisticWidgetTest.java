package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static portal.guitest.page.StatisticWidgetPage.CASE_BY_FINISHED_TASK_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.CASE_BY_FINISHED_TIME_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.CASE_BY_STATE_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.ELAPSED_TIME_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.TASK_BY_EXPIRY_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.TASK_BY_PRIORITY_CHART_NAME;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;

public class StatisticWidgetTest extends BaseTest {
  private static final String TASK_BY_PRIORITY_DEFAULT_CHART_NAME = "Tasks by Priority";


  private HomePage homePage;
  private StatisticWidgetPage statisticWidgetPage;
  private MainMenuPage mainMenuPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    Sleeper.sleep(2000); // To make Firefox test more stable, make business data updated correctly
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();
  }

  @Test
  public void testNavigateToChartFromMenu() {
    grantPermissionToCreateChart();
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    assertTrue(statisticWidgetPage.isFullMode());
  }
  
  @Test
  public void testNavigateToChartWithoutPermissionFromMenu() {
    String denyPortalPermissionsURL = "portalKitTestHelper/14DE09882B540AD5/denyPortalPermission.ivp";
    redirectToRelativeLink(denyPortalPermissionsURL);
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    assertEquals(false, statisticWidgetPage.hasCreateChartsLink());
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  @Test
  public void testCreateCharts() {
    grantPermissionToCreateChart();
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    statisticWidgetPage.switchCreateMode();
    
    statisticWidgetPage.createTaskByPriorityChart();
    statisticWidgetPage.createTaskByExpiryChart();
    statisticWidgetPage.createCaseByStateChart();
    statisticWidgetPage.createElapsedTimeChart();
    statisticWidgetPage.createCaseByFinishedTask();
    statisticWidgetPage.createCaseByFinishTime();

    statisticWidgetPage.backToDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.cssSelector("div[id$='6:chart-name-container'] .chart-name"), true);
    WebElement taskByPriorityDefaultChartName
    = statisticWidgetPage.findElementByCssSelector("div[id$='0:chart-name-container'] .chart-name");
    WebElement taskByPriorityChartName
      = statisticWidgetPage.findElementByCssSelector("div[id$='1:chart-name-container'] .chart-name");
    WebElement taskByExpiryChartName
      = statisticWidgetPage.findElementByCssSelector("div[id$='2:chart-name-container'] .chart-name");
    WebElement caseByStateChartName
      = statisticWidgetPage.findElementByCssSelector("div[id$='3:chart-name-container'] .chart-name");
    WebElement elapsedTimeChartName
      = statisticWidgetPage.findElementByCssSelector("div[id$='4:chart-name-container'] .chart-name");
    WebElement caseByFinishedTaskChartName
      = statisticWidgetPage.findElementByCssSelector("div[id$='5:chart-name-container'] .chart-name");
    WebElement caseByFinishedTimeChartName
      = statisticWidgetPage.findElementByCssSelector("div[id$='6:chart-name-container'] .chart-name");

    assertEquals(TASK_BY_PRIORITY_DEFAULT_CHART_NAME, taskByPriorityDefaultChartName.getText());
    assertEquals(TASK_BY_PRIORITY_CHART_NAME, taskByPriorityChartName.getText());
    assertEquals(TASK_BY_EXPIRY_CHART_NAME, taskByExpiryChartName.getText());
    assertEquals(CASE_BY_STATE_CHART_NAME, caseByStateChartName.getText());
    assertEquals(ELAPSED_TIME_CHART_NAME, elapsedTimeChartName.getText());
    assertEquals(CASE_BY_FINISHED_TASK_CHART_NAME, caseByFinishedTaskChartName.getText());
    assertEquals(CASE_BY_FINISHED_TIME_CHART_NAME, caseByFinishedTimeChartName.getText());
  }

  @Test
  public void testBreadCrumb() {
    grantPermissionToCreateChart();
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    assertEquals("Statistics", statisticWidgetPage.getTextOfCurrentBreadcrumb());

    statisticWidgetPage.switchCreateMode();
    assertEquals("Statistics", statisticWidgetPage.getTextOfCurrentBreadcrumb());

    statisticWidgetPage.goToHomeFromBreadcrumb();
    homePage = new HomePage();
    assertEquals(true, homePage.isDisplayed());
  }

  private void grantPermissionToCreateChart() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }


}
