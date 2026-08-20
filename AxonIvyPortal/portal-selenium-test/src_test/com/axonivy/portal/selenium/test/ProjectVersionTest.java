package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProjectVersionPage;

@IvyWebTest
public class ProjectVersionTest extends BaseTest {
  private NewDashboardPage newDashboardPage;

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void shouldShowProjectVersion() {
    ProjectVersionPage projectVersionPage = newDashboardPage.openProjectVersionPage();
    assertTrue(projectVersionPage.isEngineVersionDisplayed(), "Engine version not displayed");
    assertTrue(projectVersionPage.isPortalVersionDisplayed(), "Portal version not displayed");
    assertTrue(projectVersionPage.isFirstVersionDisplayed(), "First application version not displayed");
    assertTrue(projectVersionPage.isFirstProjectDisplayed(), "Projects of the selected version not displayed");
  }

  @Test
  public void shouldSwitchProjectsWhenSelectingAnotherVersion() {
    ProjectVersionPage projectVersionPage = newDashboardPage.openProjectVersionPage();
    assumeTrue(projectVersionPage.getVersionRowCount() > 1, "Only one application version is deployed, cannot test switching versions");
    String projectsOfFirstVersion = projectVersionPage.getProjectsOfVersionHeading();
    projectVersionPage.clickVersionRow(1);
    assertTrue(projectVersionPage.isFirstProjectDisplayed(), "Projects of the clicked version not displayed");
    assertTrue(!projectsOfFirstVersion.equals(projectVersionPage.getProjectsOfVersionHeading()),
        "Projects heading did not update after selecting another version");
  }

}
