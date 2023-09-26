package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;
import portal.guitest.page.TaskWidgetPage;
import vn.wawa.guitest.base.client.Browser;

public class MenuTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void testKeepOpenStateWhenNavigateToAnotherPage() {
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    MainMenuPage mainMenuPage = newDashboardPage2.openMainMenu();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    assertTrue(taskWidgetPage.isMainMenuOpen());
  }

  @Test
  public void testKeepClosedStateWhenNavigateToAnotherPage() {
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    MainMenuPage mainMenuPage = newDashboardPage2.openMainMenu();
    StatisticWidgetPage dashboardPage = mainMenuPage.selectStatisticDashboard();
    dashboardPage.waitForPageLoaded();

    dashboardPage.closeMainMenu();
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    newDashboardPage2 = new NewDashboardPage2();
    assertFalse(newDashboardPage2.isMainMenuOpen());
  }

  @Test
  public void testNavigateToThirdPartyApp() {
    createThirdPartyApp();
    login(TestAccount.DEMO_USER);
    // to refresh cache
    login(TestAccount.ADMIN_USER);
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    MainMenuPage mainMenuPage = newDashboardPage2.openMainMenu();
    mainMenuPage.clickThirdPartyApp();

    WebDriver driver = Browser.getBrowser().getDriver();
    WaitHelper.assertTrueWithWait(() -> driver.getWindowHandles().size() > 1);
    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    WaitHelper.assertTrueWithWait(() -> "Google".equals(driver.getTitle()));
    assertEquals("https://www.google.com/", driver.getCurrentUrl());
  }
}
