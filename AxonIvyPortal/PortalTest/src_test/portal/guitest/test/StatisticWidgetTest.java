package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static portal.guitest.page.StatisticWidgetPage.CASES_BY_CATEGORY_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.CASE_BY_FINISHED_TASK_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.CASE_BY_FINISHED_TIME_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.CASE_BY_STATE_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.ELAPSED_TIME_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.TASK_BY_EXPIRY_CHART_NAME;
import static portal.guitest.page.StatisticWidgetPage.TASK_BY_PRIORITY_CHART_NAME;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;
import portal.guitest.page.UserProfilePage;

public class StatisticWidgetTest extends BaseTest {
  private static final String TASK_BY_PRIORITY_DEFAULT_CHART_NAME = "Tasks by Priority";


  private NewDashboardPage2 newDashboardPage2;
  private StatisticWidgetPage statisticWidgetPage;
  private MainMenuPage mainMenuPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    Sleeper.sleep(2000); // To make Firefox test more stable, make business data updated correctly
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    newDashboardPage2 = new NewDashboardPage2();
  }

  @After
  public void clear() {
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testNavigateToChartFromMenu() {
    grantPermissionToCreateChart();
    mainMenuPage = newDashboardPage2.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    assertTrue(statisticWidgetPage.isFullMode());
  }
  
  @Test
  public void testNavigateToChartWithoutPermissionFromMenu() {
    String denyPortalPermissionsURL = "portalKitTestHelper/14DE09882B540AD5/denyPortalPermission.ivp";
    redirectToRelativeLink(denyPortalPermissionsURL);
    mainMenuPage = newDashboardPage2.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    assertEquals(false, statisticWidgetPage.hasCreateChartsLink());
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  @Test
  public void testCreateCharts() {
    grantPermissionToCreateChart();
    mainMenuPage = newDashboardPage2.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    statisticWidgetPage.switchCreateMode();
    
    statisticWidgetPage.createTaskByPriorityChart();
    statisticWidgetPage.createTaskByExpiryChart();
    statisticWidgetPage.createCaseByStateChart();
    statisticWidgetPage.createElapsedTimeChart();
    statisticWidgetPage.createCaseByFinishedTask();
    statisticWidgetPage.createCaseByFinishTime();
    statisticWidgetPage.createCasesByCategory();

    statisticWidgetPage.backToDashboard();
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='0:chart-name-container'] .chart-name").getText().equals(TASK_BY_PRIORITY_DEFAULT_CHART_NAME));
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='1:chart-name-container'] .chart-name").getText().equals(TASK_BY_PRIORITY_CHART_NAME));
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='2:chart-name-container'] .chart-name").getText().equals(TASK_BY_EXPIRY_CHART_NAME));
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='3:chart-name-container'] .chart-name").getText().equals(CASE_BY_STATE_CHART_NAME));
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='4:chart-name-container'] .chart-name").getText().equals(ELAPSED_TIME_CHART_NAME));
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='5:chart-name-container'] .chart-name").getText().equals(CASE_BY_FINISHED_TASK_CHART_NAME));
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='6:chart-name-container'] .chart-name").getText().equals(CASE_BY_FINISHED_TIME_CHART_NAME));
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='7:chart-name-container'] .chart-name").getText().equals(CASES_BY_CATEGORY_CHART_NAME));
  }

  @Test
  public void testBreadCrumb() {
    grantPermissionToCreateChart();
    mainMenuPage = newDashboardPage2.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    assertEquals("Statistics", statisticWidgetPage.getTextOfCurrentBreadcrumb());

    statisticWidgetPage.switchCreateMode();
    assertEquals("Statistics", statisticWidgetPage.getTextOfCurrentBreadcrumb());

    statisticWidgetPage.goToHomeFromBreadcrumb();
    newDashboardPage2 = new NewDashboardPage2();
    assertEquals(true, newDashboardPage2.isDisplayed());
  }

  private void grantPermissionToCreateChart() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  @Test
  public void testChartNameMultiLanguage() {
    resetLanguageOfCurrentUser();
    grantPermissionToCreateChart();
    mainMenuPage = newDashboardPage2.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);

    statisticWidgetPage.switchCreateMode();
    statisticWidgetPage.createTaskByPriorityChartMultiLanguage();

    statisticWidgetPage.backToDashboard();
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='1:chart-name-container'] .chart-name").getText().equals("Task by priority chart English"));

    UserProfilePage userProfilePage = statisticWidgetPage.openMyProfilePage();
    userProfilePage.selectLanguage(3);
    newDashboardPage2 = userProfilePage.save();

    mainMenuPage = newDashboardPage2.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    WaitHelper.assertTrueWithWait(() -> statisticWidgetPage.findElementByCssSelector("div[id$='1:chart-name-container'] .chart-name").getText().equals("Task by priority chart German"));
  }

}
