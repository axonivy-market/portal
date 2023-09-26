package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.StatisticWidgetPage;

public class DefaultChartTest extends BaseTest {

  private static final String DEFAULT_CHART = "Tasks by Priority";
  private static final String RESTORE_DEFAULT = "Restore default";
  
  @Override
  @Before
  public void setup() {
    super.setup();
    Sleeper.sleep(2000); // To make Firefox test more stable, make business data updated correctly 
    login(TestAccount.ADMIN_USER);
    grantPermissionToCreateChart();
    redirectToRelativeLink(NewDashboardPage2.PORTAL_EXAMPLES_HOME_PAGE_URL);
  }

  @After
  public void clear() {
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testCreateDefaultChart() {
    NewDashboardPage2 home = new NewDashboardPage2();

    MainMenuPage mainMenuPage = new MainMenuPage();
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    Set<String> chartNames = statisticWidgetPage.getAllChartNames();
    statisticWidgetPage.findListElementsByCssSelector("div[id$=':chart-name-container'] .chart-name").stream().map(e -> e.getText()).collect(Collectors.toSet());
    System.out.println("All default chart names " + chartNames);
    assertTrue(chartNames.contains(DEFAULT_CHART));
    assertEquals(RESTORE_DEFAULT, statisticWidgetPage.getRestoreDefaultButtonName());
  }
  
  @Test
  public void testRestoreDefaultChart() {
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
    
    assertEquals(DEFAULT_CHART, statisticWidgetPage.getChartName(0));
    assertEquals(null, taskByExpiryChartName3);
  }
  
  private void grantPermissionToCreateChart() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }
}
