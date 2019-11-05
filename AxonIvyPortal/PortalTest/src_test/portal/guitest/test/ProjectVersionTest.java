package portal.guitest.test;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProjectVersionPage;

public class ProjectVersionTest extends BaseTest {

  @Test
  public void shouldShowProjectVersion() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    ProjectVersionPage projectVersionPage = homePage.openProjectVersionPage();
    assertTrue("Engine version not displayed", projectVersionPage.isEngineVersionDisplayed());
    assertTrue("Portal version not displayed", projectVersionPage.isPortalVersionDisplayed());
    assertTrue("First project version not displayed", projectVersionPage.isFirstProjectDisplayed());
  }

}
