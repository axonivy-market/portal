package portal.guitest.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.ProjectVersionPage;

public class ProjectVersionTest extends BaseTest {

  @Test
  public void shouldShowProjectVersion() {
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    ProjectVersionPage projectVersionPage = newDashboardPage2.openProjectVersionPage();
    assertTrue("Engine version not displayed", projectVersionPage.isEngineVersionDisplayed());
    assertTrue("Portal version not displayed", projectVersionPage.isPortalVersionDisplayed());
    assertTrue("First project version not displayed", projectVersionPage.isFirstProjectDisplayed());
  }

}
