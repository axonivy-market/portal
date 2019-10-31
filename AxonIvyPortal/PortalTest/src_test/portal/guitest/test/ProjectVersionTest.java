package portal.guitest.test;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.ProjectVersionPage;

public class ProjectVersionTest extends BaseTest {

  @Test
  public void shouldShowProjectVersion() {
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    HomePage homePage = new HomePage();
    ProjectVersionPage projectVersionPage = homePage.openProjectVersionPage();
    assertTrue("Engine version not displayed", projectVersionPage.isEngineVersionDisplayed());
    assertTrue("Portal version not displayed", projectVersionPage.isPortalVersionDisplayed());
    assertTrue("First project version not displayed", projectVersionPage.isFirstProjectDisplayed());
  }

}
