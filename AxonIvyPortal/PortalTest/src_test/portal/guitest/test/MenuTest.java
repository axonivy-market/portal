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
import portal.guitest.page.HomePage;
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
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    assertTrue(taskWidgetPage.isMainMenuOpen());
  }

  @Test
  public void testKeepClosedStateWhenNavigateToAnotherPage() {
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    StatisticWidgetPage dashboardPage = mainMenuPage.selectStatisticDashboard();
    dashboardPage.waitForPageLoaded();

    dashboardPage.closeMainMenu();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    assertFalse(homePage.isMainMenuOpen());
  }

  @Test
  public void testNavigateToThirdPartyApp() {
    createThirdPartyApp();
    login(TestAccount.DEMO_USER);
    // to refresh cache
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    mainMenuPage.clickThirdPartyAppByIndex(1);

    WebDriver driver = Browser.getBrowser().getDriver();
    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    WaitHelper.assertTrueWithWait(() -> "Google".equals(driver.getTitle()));
    assertEquals("https://www.google.com/", driver.getCurrentUrl());
  }
}
