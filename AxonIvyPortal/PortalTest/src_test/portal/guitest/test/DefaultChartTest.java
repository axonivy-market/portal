package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;

public class DefaultChartTest extends BaseTest {

  private static final String CREATE_TESTING_TASK_FOR_CUSTOMIZATION_URL 
    = "portalExamples/162511D2577DBA88/createTasksForTaskListCustomization.ivp";
  private static final String DEFAULT_NAME_1 = "My default chart 1";
  private static final String DEFAULT_NAME_2 = "My default chart 2";
  private static final String RESTORE_DEFAULT = "Restore default";
  
  
  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(CREATE_TESTING_TASK_FOR_CUSTOMIZATION_URL);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
  }

  @Test
  public void testCreateDefaultChart() {
    grantPermissionToCreateChart();
    MainMenuPage mainMenuPage = new MainMenuPage();
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    Sleeper.sleepTight(20000);
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    
    WebElement taskByExpiryChartName1
      = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:0:chart-name");
    
    WebElement taskByExpiryChartName2
      = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:1:chart-name");
    
    WebElement restoreDefault = statisticWidgetPage.findElementById("statistics-widget:restore-default-chart-link-label");
    
    assertEquals(DEFAULT_NAME_1, taskByExpiryChartName1.getText());
    assertEquals(DEFAULT_NAME_2, taskByExpiryChartName2.getText());
    assertEquals(RESTORE_DEFAULT, restoreDefault.getText());
  }
  
  @Test
  public void testRestoreDefaultChart() {
    grantPermissionToCreateChart();
    MainMenuPage mainMenuPage = new MainMenuPage();
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    Sleeper.sleepTight(10000);
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    statisticWidgetPage.switchCreateMode();
    Sleeper.sleepTight(5000);
    createCaseByFinishedTask(statisticWidgetPage);
    statisticWidgetPage.switchCreateMode();
    Sleeper.sleepTight(5000);
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:0:chart-name"), true);
    WebElement restoreDefault = statisticWidgetPage.findElementById("statistics-widget:restore-default-chart-link-label");
    
    restoreDefault.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:restore-confirmation-dialog"), true, 30);
    
    WebElement okButton = statisticWidgetPage.findElementById("statistics-widget:confirm-restore");
    okButton.click();
    
    statisticWidgetPage.waitAjaxIndicatorDisappear();
    
    WebElement taskByExpiryChartName1
    = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:0:chart-name");
    
    WebElement taskByExpiryChartName2
    = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:1:chart-name");
    
    WebElement taskByExpiryChartName3 = null ;
    try {
      taskByExpiryChartName3 = statisticWidgetPage.findElementById("statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:2:chart-name");
    } catch (Exception ex) {
    }
    
    assertEquals(DEFAULT_NAME_1, taskByExpiryChartName1.getText());
    assertEquals(DEFAULT_NAME_2, taskByExpiryChartName2.getText());
    assertEquals(null, taskByExpiryChartName3);
  }
  
  private void createCaseByFinishedTask(StatisticWidgetPage statisticWidgetPage) {
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

    chartNameInput.sendKeys("User chart");
    createChartButton.click();
    statisticWidgetPage.waitAjaxIndicatorDisappear();
  }
  
  private void grantPermissionToCreateChart() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }
}
