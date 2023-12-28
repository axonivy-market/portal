package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.StatisticWidgetPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

@IvyWebTest
public class StatisticWidgetTest extends BaseTest {
  private static final String TASK_BY_PRIORITY_DEFAULT_CHART_NAME = "Tasks by Priority";
  public static final String TASK_BY_PRIORITY_CHART_NAME = "Task by priority chart";
  public static final String CASE_BY_STATE_CHART_NAME = "Case by state chart";
  public static final String TASK_BY_EXPIRY_CHART_NAME = "Task by expiry chart";
  public static final String ELAPSED_TIME_CHART_NAME = "Elapsed time chart";
  public static final String CASE_BY_FINISHED_TASK_CHART_NAME = "Case by finished task chart";
  public static final String CASE_BY_FINISHED_TIME_CHART_NAME = "Case by finished time chart";
  public static final String CASES_BY_CATEGORY_CHART_NAME = "Cases by category chart";

  private NewDashboardPage newDashboardPage;
  private StatisticWidgetPage statisticWidgetPage;
  private MainMenuPage mainMenuPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
  }

  @AfterEach
  public void clear() {
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testNavigateToChartFromMenu() {
    grantPermissionToCreateChart();
    mainMenuPage = newDashboardPage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    assertTrue(statisticWidgetPage.isFullMode());
  }

  @Test
  public void testNavigateToChartWithoutPermissionFromMenu() {
    String denyPortalPermissionsURL = "portalKitTestHelper/14DE09882B540AD5/denyPortalPermission.ivp";
    redirectToRelativeLink(denyPortalPermissionsURL);
    mainMenuPage = newDashboardPage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    assertEquals(false, statisticWidgetPage.hasCreateChartsLink());
    redirectToRelativeLink(grantPortalPermission);
  }

  @Test
  public void testCreateCharts() {
    grantPermissionToCreateChart();
    mainMenuPage = newDashboardPage.openMainMenu();
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
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='0:chart-name-container'] .chart-name").getText()
            .equals(TASK_BY_PRIORITY_DEFAULT_CHART_NAME));
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='1:chart-name-container'] .chart-name").getText()
            .equals(TASK_BY_PRIORITY_CHART_NAME));
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='2:chart-name-container'] .chart-name").getText()
            .equals(TASK_BY_EXPIRY_CHART_NAME));
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='3:chart-name-container'] .chart-name").getText()
            .equals(CASE_BY_STATE_CHART_NAME));
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='4:chart-name-container'] .chart-name").getText()
            .equals(ELAPSED_TIME_CHART_NAME));
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='5:chart-name-container'] .chart-name").getText()
            .equals(CASE_BY_FINISHED_TASK_CHART_NAME));
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='6:chart-name-container'] .chart-name").getText()
            .equals(CASE_BY_FINISHED_TIME_CHART_NAME));
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='7:chart-name-container'] .chart-name").getText()
            .equals(CASES_BY_CATEGORY_CHART_NAME));
  }

  @Test
  public void testBreadCrumb() {
    grantPermissionToCreateChart();
    mainMenuPage = newDashboardPage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    assertEquals("Statistics", statisticWidgetPage.getTextOfCurrentBreadcrumb());

    statisticWidgetPage.switchCreateMode();
    assertEquals("Statistics", statisticWidgetPage.getTextOfCurrentBreadcrumb());

    statisticWidgetPage.goToHomeFromBreadcrumb();
    newDashboardPage = new NewDashboardPage();
    assertEquals(true, newDashboardPage.isDisplayed());
  }

  private void grantPermissionToCreateChart() {
    redirectToRelativeLink(grantPortalPermission);
  }

  @Test
  public void testChartNameMultiLanguage() {
    resetLanguageOfCurrentUser();
    grantPermissionToCreateChart();
    mainMenuPage = newDashboardPage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);

    statisticWidgetPage.switchCreateMode();
    statisticWidgetPage.createTaskByPriorityChartMultiLanguage();

    statisticWidgetPage.backToDashboard();
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='1:chart-name-container'] .chart-name").getText()
            .equals("Task by priority chart English"));

    UserProfilePage userProfilePage = statisticWidgetPage.openMyProfilePage();
    userProfilePage.selectLanguage(3);
    newDashboardPage = userProfilePage.save();

    mainMenuPage = newDashboardPage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    WaitHelper.assertTrueWithWait(
        () -> statisticWidgetPage.findElementByCssSelector("div[id$='1:chart-name-container'] .chart-name").getText()
            .equals("Task by priority chart German"));
  }

}
