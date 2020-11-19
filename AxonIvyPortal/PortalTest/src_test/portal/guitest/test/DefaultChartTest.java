package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
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

public class DefaultChartTest extends BaseTest {

  private static final String CREATE_TESTING_TASK_FOR_CUSTOMIZATION_URL 
    = "portal-developer-examples/162511D2577DBA88/createTasksForTaskListCustomization.ivp";
  private static final String DEFAULT_NAME = "Tasks by Priority";
  private static final String DEFAULT_NAME_1 = "My default chart 1";
  private static final String DEFAULT_NAME_2 = "My default chart 2";
  private static final String RESTORE_DEFAULT = "Restore default";
  
  
  @Override
  @Before
  public void setup() {
    super.setup();
    Sleeper.sleep(2000); // To make Firefox test more stable, make business data updated correctly 
    redirectToRelativeLink(CREATE_TESTING_TASK_FOR_CUSTOMIZATION_URL);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
  }

  @After
  public void clear() {
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testCreateDefaultChart() {
    grantPermissionToCreateChart();
    MainMenuPage mainMenuPage = new MainMenuPage();
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    
    assertEquals(DEFAULT_NAME, statisticWidgetPage.getChartName(0));
    assertEquals(DEFAULT_NAME_1, statisticWidgetPage.getChartName(1));
    assertEquals(DEFAULT_NAME_2, statisticWidgetPage.getChartName(2));
    assertEquals(RESTORE_DEFAULT, statisticWidgetPage.getRestoreDefaultButtonName());
  }
  
  @Test
  public void testRestoreDefaultChart() {
    grantPermissionToCreateChart();
    MainMenuPage mainMenuPage = new MainMenuPage();
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    statisticWidgetPage.switchCreateMode();
    statisticWidgetPage.createCaseByFinishedTask();
    statisticWidgetPage.backToDashboard();
    statisticWidgetPage.restoreDefaultCharts();

    WebElement taskByExpiryChartName3 = null ;
    try {
      taskByExpiryChartName3 = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:2:chart-name");
    } catch (Exception ex) {
    }
    
    assertEquals(DEFAULT_NAME_1, statisticWidgetPage.getChartName(0));
    assertEquals(DEFAULT_NAME_2, statisticWidgetPage.getChartName(1));
    assertEquals(null, taskByExpiryChartName3);
  }
  
  private void grantPermissionToCreateChart() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }
}
