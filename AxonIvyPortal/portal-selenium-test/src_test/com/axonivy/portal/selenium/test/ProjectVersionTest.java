package com.axonivy.portal.selenium.test;

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
    assertTrue(projectVersionPage.isFirstProjectDisplayed(), "First project version not displayed");
  }

}
